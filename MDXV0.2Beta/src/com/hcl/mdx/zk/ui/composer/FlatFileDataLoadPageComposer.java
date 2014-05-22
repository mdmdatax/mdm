package com.hcl.mdx.zk.ui.composer;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ddlutils.model.Database;
import org.apache.ddlutils.model.Table;
import org.apache.log4j.Logger;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Div;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Progressmeter;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;

import com.hcl.mdx.data.model.DataLoadDetailsObject;
import com.hcl.mdx.data.model.FlatFileDetails;
import com.hcl.mdx.data.model.SessionDetailsObject;
import com.hcl.mdx.database.objects.CanonicalColumnObject;
import com.hcl.mdx.database.util.DBUtils;
import com.hcl.mdx.file.FileHandler;
import com.hcl.mdx.file.data.ColumnToColumnMapObject;
import com.hcl.mdx.file.data.TableAndClmMapDetailsObject;
import com.hcl.mdx.file.parser.CSVParser;
import com.hcl.mdx.task.executor.InitialDataLoadTaskExecutor;
import com.hcl.mdx.task.objects.InitialDataLoadTaskInput;
import com.hcl.mdx.task.objects.ProgressMessageObject;
import com.hcl.mdx.task.objects.TaskInput;
import com.hcl.mdx.util.Constants;
import com.hcl.mdx.zk.data.manager.FlatFileDetailsDataManager;
import com.hcl.mdx.zk.ui.event.listener.OnChangeEventListenerForMDXDataView;
import com.hcl.mdx.zk.ui.event.listener.OnClickEventListenerForDBLoad;
import com.hcl.mdx.zk.ui.status.renderers.ProgressRenderer;

public class FlatFileDataLoadPageComposer extends AbstractPageComposer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("FlatFileDataLoadPageComposer");

	private Radiogroup fileDelimiter;
	private Textbox otherDelimiterCharBox;
	private Combobox textQualifier;
	private Combobox dataTablesList;
	private Combobox dataPreviewTablesList;
	private Panelchildren loadDetailsViewPanelChildren;
	private Panelchildren fileDetailsViewPanelChildren;
	private Panelchildren dataPreviewPanelChildren;
	private Button dbLoadButton;
	private Button setPreviewTableButton;
	private Progressmeter dataTransferMeter;
	private Label processStatus;
	private Label processActivity;
	private Label timeElapsed;
	private Panel middlePanel;
	private Panel topPanel;
	private Label report;
	private Panel dataPreviewPanel;
	private static final String PAGE_URL = "initialDataLoad.zul";

	private static int PAGE_ID = 3;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}

	public void onCreate$window(Event event) throws Exception{
		boolean paramsSet = checkSessionParameters(event, PAGE_URL);

		if(paramsSet){

			HttpSession httpSession = (javax.servlet.http.HttpSession)Sessions.getCurrent().getNativeSession();
			SessionDetailsObject sessionDetailsObject = (SessionDetailsObject)httpSession.getAttribute(Constants.SESSION_DETAILS_OBJECT_NAME);

			Listbox flatFileDetailsBox = sessionDetailsObject.getFlatFileDetailsListboxBuilder().buildListBox();
			if(flatFileDetailsBox != null){
				fileDetailsViewPanelChildren.appendChild(flatFileDetailsBox);
			}

			/*
			 * Append the list of target tables if exists.
			 */
			buildTargetTablesList(sessionDetailsObject.getMdxSchema(), sessionDetailsObject);

			/*
			 * Append the load details table
			 */
			Grid dataLoadDetailsGrid = sessionDetailsObject.getDataLoadDetailsGridBuilder().buildGrid();
			loadDetailsViewPanelChildren.appendChild(dataLoadDetailsGrid);

			/*
			 * Set the data load button reference and add an event listener to it.
			 */
			dbLoadButton = (Button) ((Component)(((Component)(((Component) loadDetailsViewPanelChildren.getChildren().get(1)).getChildren().get(1))).getChildren().get(1))).getChildren().get(1);

			dbLoadButton.addEventListener(Events.ON_CLICK, new OnClickEventListenerForDBLoad(this));
			dataPreviewTablesList.addEventListener(Events.ON_CHANGE, new OnChangeEventListenerForMDXDataView(
					sessionDetailsObject.getMdxTableMetadata(), 
					sessionDetailsObject.getMdxSchema(), 
					dataPreviewPanelChildren, Constants.IN_TABLE_PREFIX));

			self.setAttribute(Constants.PAGE_COMPOSER_PROPERTY_NAME, this);
		}

	} 

	public void onClick$fileUploadButton(Event event) throws InterruptedException{
		HttpSession httpSession = (javax.servlet.http.HttpSession)Sessions.getCurrent().getNativeSession();

		String errorMessage = "Please set an appropriate file delimiter value before proceeding.";
		java.io.InputStream fileDataStream = null;
		SessionDetailsObject sessionDetailsObject = 
			(SessionDetailsObject)httpSession.getAttribute(Constants.SESSION_DETAILS_OBJECT_NAME);

		try{	  

			Media uploadedFile = Fileupload.get(true);
			fileDataStream = uploadedFile.getStreamData();

			String sessionId = httpSession.getId();

			FileHandler fileHandler = new FileHandler();
			Constants.TMP_FOLDER_PATH =  httpSession.getServletContext().getRealPath("/");
			String uploadStatus = fileHandler.handleFile(uploadedFile.getName(), sessionId, fileDataStream);

			String fileDelimiterStr = getRadioGroupValue(fileDelimiter);

			boolean errorsExist = false;

			if((fileDelimiterStr != null) && (fileDelimiterStr.compareTo("OTHER_CHAR") == 0) && (otherDelimiterCharBox.getValue() == null)){
				errorsExist = true;		
			}
			else if((fileDelimiterStr != null) && (fileDelimiterStr.compareTo("OTHER_CHAR") == 0)){ 
				fileDelimiterStr = otherDelimiterCharBox.getValue();  
			}

			String textQualifierStr = getComboBoxValue(textQualifier);

			if((uploadStatus.compareTo("1") == 0) && (!errorsExist)){
				String infoMessage = "File Type: "+uploadedFile.getContentType()+
				"\n" + 
				"File Format: " + uploadedFile.getFormat() +
				"\n" +
				"File Name: " + uploadedFile.getName();

				FlatFileDetails flatFileDetails = new FlatFileDetails();
				flatFileDetails.setName(uploadedFile.getName());
				flatFileDetails.setFlatFileDelimiter(fileDelimiterStr);
				flatFileDetails.setFlatFileTextQualifier(textQualifierStr);
				flatFileDetails.setPath(fileHandler.getFilePath());
				
				InputStream inputStream = new FileInputStream(fileHandler.getFilePath());
				CSVParser parser = new CSVParser(fileDelimiterStr, textQualifierStr);

				ArrayList<Hashtable<String, String>> displayList = parser.parseInputStream(inputStream, true);
				/*
				 * Grab the list of column headers in the file.
				 */
				Hashtable<String, String> firstRow = displayList.get(0);
				ArrayList<String> clmHeaders = new ArrayList<String>();
				if(firstRow != null){
					Enumeration<String> keys = firstRow.keys();

					while(keys.hasMoreElements()){
						clmHeaders.add(keys.nextElement());
					}
				}

				flatFileDetails.setColumns(clmHeaders);

				Messagebox.show(
						infoMessage, "Success!", 
						Messagebox.OK, 
						Messagebox.INFORMATION, 
						new org.zkoss.zk.ui.event.EventListener() {
							public void onEvent(Event event) throws Exception {
								if (Events.ON_OK.equals(event.getName())) {	
									event.getTarget().detach();
								}
							}
						});
				FlatFileDetailsDataManager dataManager = (FlatFileDetailsDataManager) sessionDetailsObject.getFlatFileDetailsListboxBuilder().getDataManager();
				dataManager.addDataItem(flatFileDetails);

				/*
				 * Refresh the listbox
				 */
				Button button = (Button) ((Component)(((Component)(fileDetailsViewPanelChildren.getFirstChild().getChildren().get(0))).getChildren().get(1))).getChildren().get(0);
				Events.echoEvent(Events.ON_CLICK, button, null);
			}
			else{

				Messagebox.show(
						uploadStatus, "Failure!", 
						Messagebox.OK, 
						Messagebox.ERROR,
						null);
			}


		}
		catch(WrongValueException wve){
			Messagebox.show(
					errorMessage + ": " + wve.getMessage(), "Error!", 
					Messagebox.OK, 
					Messagebox.ERROR, 
					null);
		}
		catch(Exception e){
			e.printStackTrace();
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
				Comboitem comboitem = new Comboitem();
				comboitem.setSclass(Constants.CSS_BLACK_LABEL_9px_CLASS);
				comboitem.setLabel(name);
				comboitem.setValue(name);
				
				Comboitem comboitem1 = new Comboitem();
				comboitem1.setSclass(Constants.CSS_BLACK_LABEL_9px_CLASS);
				comboitem1.setLabel(name);
				comboitem1.setValue(name);
				
				dataTablesList.appendChild(comboitem);
				dataPreviewTablesList.appendChild(comboitem1);
			}


		}
	}

	public void onClick$createLoadButton(Event event) throws InterruptedException{
		String targetTable = getComboBoxValue(dataTablesList);
		Listitem selectedFileListItem = 
			((Listbox)(fileDetailsViewPanelChildren.getFirstChild())).getSelectedItem();
		if((targetTable == null) || (selectedFileListItem == null)){
			Messagebox.show(
					"Please select a source File and the target table before adding a load", "Failure!", 
					Messagebox.OK, 
					Messagebox.ERROR,
					null);
		}
		else{
			HttpSession httpSession = (javax.servlet.http.HttpSession)Sessions.getCurrent().getNativeSession();
			SessionDetailsObject sessionDetailsObject = (SessionDetailsObject)httpSession.getAttribute(Constants.SESSION_DETAILS_OBJECT_NAME);
			FlatFileDetails selectedFile = (FlatFileDetails)selectedFileListItem.getAttribute("data");
			DataLoadDetailsObject dataLoadDetailsObject = new DataLoadDetailsObject();
			dataLoadDetailsObject.setFlatFileDetails(selectedFile);
			dataLoadDetailsObject.setTargetTable(targetTable);

			sessionDetailsObject.getDataLoadDetailsGridBuilder().getDataManager().addDataItem(dataLoadDetailsObject);

			/*
			 * Refresh the Grid
			 */

			Grid loadDetailsGrid = (Grid) loadDetailsViewPanelChildren.getChildren().get(1);
			Component refreshButton = (Component) ((Component)(((Component)(loadDetailsGrid.getChildren().get(1))).getChildren().get(1))).getChildren().get(0);

			Events.echoEvent(Events.ON_CLICK, refreshButton, null);
		}
	}

	@SuppressWarnings("unchecked")
	public void onClick$databaseLoadButton(Event event) throws InterruptedException{
		Grid grid = (Grid) loadDetailsViewPanelChildren.getChildren().get(1);
		if((grid == null) || (grid.getRows().getChildren().size() == 0)){
			Messagebox.show(
					"No loads defined. Define at least one load item before attempting to do this.", "Error!", 
					Messagebox.OK, 
					Messagebox.ERROR,
					null);
		}
		else{

			try{
				HttpSession httpSession = (javax.servlet.http.HttpSession)Sessions.getCurrent().getNativeSession();
				SessionDetailsObject sessionDetailsObject = (SessionDetailsObject)httpSession.getAttribute(Constants.SESSION_DETAILS_OBJECT_NAME);
				
				Rows rowsObj = (Rows)grid.getRows();
				List rows = rowsObj.getChildren();
				ArrayList<TaskInput> loadTasksList = new ArrayList<TaskInput>();
				
				for(int counter = 0; counter < rows.size(); counter++){
					Row nextRow = (Row)rows.get(counter);
					DataLoadDetailsObject dataLoadDetailsObject = (DataLoadDetailsObject) nextRow.getAttribute("data");

					Grid mapDetailsGrid = (Grid) nextRow.getAttribute("mapDetailsGrid");

					DataLoadDetailsObject loadDetailsObject = (DataLoadDetailsObject) nextRow.getAttribute("data");

					TableAndClmMapDetailsObject dataDetailsObject = parseMapDetailsGrid(
							mapDetailsGrid, 
							loadDetailsObject.getFlatFileDetails().getColumns(), 
							loadDetailsObject.getTargetTable(),
							loadDetailsObject.getId().toString());

					if(dataDetailsObject == null){
						throw new Exception("MDXLoadConfigException: No column maps set for Load Id: "+loadDetailsObject.getId()+". Set maps and try again.");
					}
					loadTasksList.add(new InitialDataLoadTaskInput(
							dataLoadDetailsObject.getId().toString(), 
							dataLoadDetailsObject.getFlatFileDetails(), 
							dataDetailsObject));
				}
				
				/*
				 * Start the load process
				 */
				desktop.enableServerPush(true);
				topPanel.setOpen(false);
				middlePanel.setOpen(false);
				dataPreviewPanel.setOpen(false);
				progressPanel.setVisible(true); 
				ProgressMessageObject messageObject = new ProgressMessageObject();
				ProgressRenderer progressRenderer = new ProgressRenderer(
						desktop,
						dataTransferMeter, 
						messageObject, 
						processStatus, 
						processActivity, 
						timeElapsed,
						report);

				progressRenderer.start();
				
				InitialDataLoadTaskExecutor dataLoadTaskExecutor = new InitialDataLoadTaskExecutor(sessionDetailsObject, httpSession);
				dataLoadTaskExecutor.setProgressMessageObject(messageObject);
				dataLoadTaskExecutor.setTaskInputs(loadTasksList);	
				new Thread(dataLoadTaskExecutor).start();
				
			}
			catch (Exception e) {
				log.error(e);
				e.printStackTrace();
				Messagebox.show(
						e.getMessage(), "Error!", 
						Messagebox.OK, 
						Messagebox.ERROR,
						null);
			}
		}

	}

	private TableAndClmMapDetailsObject parseMapDetailsGrid(
			Grid mapDetailsGrid, 
			ArrayList<String> columnNamesList,
			String targetTable,
			String loadId) throws Exception{
		try{
			Rows rowsObj = mapDetailsGrid.getRows();
			List rows = rowsObj.getChildren();

			ArrayList<ColumnToColumnMapObject> columns = new ArrayList<ColumnToColumnMapObject>();
			boolean NO_MAPS_PRESENT_FOR_LOAD = true;
			for(int counter = 0; counter < rows.size(); counter++){
				Row nextRow = (Row)rows.get(counter);
				List rowElements = nextRow.getChildren();

				Div fileColumnDiv = (Div)rowElements.get(0);
				Div canonicalColumnDiv = (Div)rowElements.get(1);

				Label fileColumnLabel = (Label)fileColumnDiv.getChildren().get(0);
				String nextColumnName = fileColumnLabel.getValue();
				Combobox canonicalColumnComboBox = (Combobox)canonicalColumnDiv.getChildren().get(0);
				String nextComboboxValue = getComboBoxValue(canonicalColumnComboBox); 

				if(nextComboboxValue != null){								 						  											 									
					NO_MAPS_PRESENT_FOR_LOAD = false;
					ColumnToColumnMapObject dataColumn = 
						new ColumnToColumnMapObject(
								nextColumnName, 
								new CanonicalColumnObject(nextComboboxValue, null, null));

					columns.add(dataColumn);
				}

			}
			TableAndClmMapDetailsObject detailsObject = new TableAndClmMapDetailsObject(targetTable, columns);

			if(NO_MAPS_PRESENT_FOR_LOAD){
				return null;
			}
			else{
				return detailsObject;
			}
		}
		catch(Exception e){
			throw new Exception("MDXLoadConfigException: No column maps set for Load Id: "+loadId+". Set maps and try again.");
		}
	}
}
