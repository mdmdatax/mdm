package com.hcl.mdx.zk.ui.composer;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.ddlutils.model.Database;
import org.apache.ddlutils.model.Table;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Progressmeter;

import com.hcl.mdx.data.model.AbstractModelObject;
import com.hcl.mdx.data.model.SessionDetailsObject;
import com.hcl.mdx.database.jdbc.QueryExecutor;
import com.hcl.mdx.database.util.DBUtils;
import com.hcl.mdx.file.data.TableAndClmMapDetailsObject;
import com.hcl.mdx.task.objects.ProgressMessageObject;
import com.hcl.mdx.util.Constants;
import com.hcl.mdx.util.Constants.StandardizationAdapterType;
import com.hcl.mdx.zk.data.standardize.MDXDataStandardizationAdapter;
import com.hcl.mdx.zk.ui.event.listener.OnChangeEventListenerForDataStdizationEngine;
import com.hcl.mdx.zk.ui.event.listener.OnChangeEventListenerForDataStdizationEntity;
import com.hcl.mdx.zk.ui.event.listener.OnChangeEventListenerForMDXDataView;
import com.hcl.mdx.zk.ui.status.renderers.ProgressRenderer;

public class StandardizeDataPageComposer extends AbstractPageComposer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("StandardizeDataPageComposer");
	private Combobox dataPreviewTablesList;
	private Combobox standardizationEnginesList;
	private Combobox standardizationEntitiesList;
	private Panelchildren dataPreviewPanelChildren;
	private Button doStandardizeButton;
	private Progressmeter dataTransferMeter;
	private Label processStatus;
	private Label processActivity;
	private Label timeElapsed;
	private Panel middlePanel;
	private Panelchildren stdizationConfigPanelChildren;
	private Panel topPanel;
	private Panel dataPreviewPanel;
	private static final String PAGE_URL = "standardizeData.zul";

	private static int PAGE_ID = 4;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}

	public void onCreate$window(Event event) throws Exception{
		boolean paramsSet = checkSessionParameters(event, PAGE_URL);

		if(paramsSet){

			HttpSession httpSession = (javax.servlet.http.HttpSession)Sessions.getCurrent().getNativeSession();
			SessionDetailsObject sessionDetailsObject = (SessionDetailsObject)httpSession.getAttribute(Constants.SESSION_DETAILS_OBJECT_NAME);

			/*
			 * Append the list of usable standardization engines.
			 */
			for(StandardizationAdapterType adapterType : StandardizationAdapterType.values()){
				Comboitem comboitem = new Comboitem();
				comboitem.setSclass(Constants.CSS_BLACK_LABEL_9px_CLASS);
				comboitem.setValue(adapterType);
				comboitem.setLabel(adapterType.toString());
				standardizationEnginesList.appendChild(comboitem);
			}
			standardizationEnginesList.addEventListener(Events.ON_CHANGE, new OnChangeEventListenerForDataStdizationEngine(standardizationEntitiesList));
			/*
			 * Make a list of panels to collapse during stdization.
			 */
			ArrayList<Panel> panelsToCollapseDuringOperation = new ArrayList<Panel>();
			panelsToCollapseDuringOperation.add(topPanel);
			panelsToCollapseDuringOperation.add(middlePanel);
			panelsToCollapseDuringOperation.add(dataPreviewPanel);

			standardizationEntitiesList.addEventListener(Events.ON_CHANGE, 
					new OnChangeEventListenerForDataStdizationEntity(
							stdizationConfigPanelChildren, 
							sessionDetailsObject.getMdxSchema(), 
							sessionDetailsObject.getMdxTableMetadata(), 
							standardizationEnginesList,
							this));


			/*
			 * Append the list of target tables if exists.
			 */
			buildTargetTablesList(sessionDetailsObject.getMdxSchema(), sessionDetailsObject);		

			/*
			 * Set the data load button reference and add an event listener to it.
			 */
			//dbLoadButton = (Button) ((Component)(((Component)(((Component) loadDetailsViewPanelChildren.getChildren().get(1)).getChildren().get(1))).getChildren().get(1))).getChildren().get(1);

			//dbLoadButton.addEventListener(Events.ON_CLICK, new OnClickEventListenerForDBLoad(this));
			dataPreviewTablesList.addEventListener(Events.ON_CHANGE, new OnChangeEventListenerForMDXDataView(
					sessionDetailsObject.getMdxTableMetadata(), 
					sessionDetailsObject.getMdxSchema(), 
					dataPreviewPanelChildren, Constants.IN_TABLE_PREFIX));

			self.setAttribute(Constants.PAGE_COMPOSER_PROPERTY_NAME, this);
		}

	} 

	@Override
	public int getPageId() {
		// TODO Auto-generated method stub
		return PAGE_ID;
	}

	private void buildTargetTablesList(Database database, SessionDetailsObject sessionDetailsObject){
		if(database == null){
			database = DBUtils.readModelFromSchema();
			sessionDetailsObject.setMdxSchema(database);
		}

		Table[] tables = database.getTables();
		for(int counter = 0; counter < tables.length; counter++){
			String name = tables[counter].getName();

			/*
			 * Add only IN_ tables to list.
			 */
			if((name.substring(0, 3) != null) && (name.substring(0, 3).compareToIgnoreCase(Constants.IN_TABLE_PREFIX+"_") == 0)){

				Comboitem comboitem1 = new Comboitem();
				comboitem1.setSclass(Constants.CSS_BLACK_LABEL_9px_CLASS);
				comboitem1.setLabel(name);
				comboitem1.setValue(name);
				dataPreviewTablesList.appendChild(comboitem1);
			}


		}
	}

	public void doStandardization(
			TableAndClmMapDetailsObject srcMapDetails,
			TableAndClmMapDetailsObject targetMapDetails,
			MDXDataStandardizationAdapter dataStandardizationAdapter){

		HttpSession httpSession = (javax.servlet.http.HttpSession)Sessions.getCurrent().getNativeSession();
		SessionDetailsObject sessionDetailsObject = (SessionDetailsObject)httpSession.getAttribute(Constants.SESSION_DETAILS_OBJECT_NAME);
		ProgressMessageObject messageObject = null;
		try {
			/*
			 * Start the standardization process
			 */
			desktop.enableServerPush(true);
			topPanel.setOpen(false);
			middlePanel.setOpen(false);
			dataPreviewPanel.setOpen(false);
			progressPanel.setVisible(true); 
			messageObject = new ProgressMessageObject();
			ProgressRenderer progressRenderer = new ProgressRenderer(
					desktop,
					dataTransferMeter, 
					messageObject, 
					processStatus, 
					processActivity, 
					timeElapsed,
					null);

			progressRenderer.start();
			messageObject.setActivityName("Fetching data to standardize");
			messageObject.setProcessStatus("Working...");
			messageObject.setProgressPercent(0);

			/*
			 * Fetch data to standardize from the source table. First build the list of columns
			 * to fetch.
			 */
			ArrayList<String> listOfClmsToFetch = new ArrayList<String>();
			for(int counter = 0; counter < srcMapDetails.getColumns().size(); counter++){
				listOfClmsToFetch.add(srcMapDetails.getColumns().get(counter).getCanonicalColumnObject().getColumnName());
			}
			listOfClmsToFetch.add(srcMapDetails.getKeyColumnName());

			QueryExecutor queryExecutor = new QueryExecutor();
			ArrayList<AbstractModelObject> data = QueryExecutor.getDataForTable(srcMapDetails.getTableName(), listOfClmsToFetch, null, null, sessionDetailsObject.getMdxSchema());
			messageObject.setProgressPercent(100);

			if((data == null) || (data.size() == 0)){
				messageObject.setProcessStatus("Operation Completed. No data found in the source. Nothing to standardize. ");
			}
			else{
				ArrayList<AbstractModelObject> stdizedRecords = dataStandardizationAdapter.doStandardization(data, messageObject);

				/*
				 * Load standardized data into database
				 */
				queryExecutor.upsertRecords(stdizedRecords, targetMapDetails.getTableName(), targetMapDetails.getKeyColumnName(), messageObject);
				messageObject.setProcessStatus("Operation Completed");
			}
			
			messageObject.setProgressPercent(100);
			messageObject.setCompleted(true);
		} catch (Exception e) {
			log.error(e);
			messageObject.setCompleted(true);
			messageObject.setEnteredErrorState(true);
			String errorMsgString = "Error Standardizing data ";
			messageObject.setProcessStatus( errorMsgString + e.getMessage());
		}

	}
}
