package com.hcl.mdx.zk.ui.event.listener;

import java.util.ArrayList;

import org.apache.ddlutils.model.Database;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;

import com.hcl.mdx.data.model.AbstractModelObject;
import com.hcl.mdx.data.model.DataLoadClmMapperObject;
import com.hcl.mdx.database.objects.MDXTableMetadata;
import com.hcl.mdx.util.Constants;
import com.hcl.mdx.util.Constants.StandardizationAdapterType;
import com.hcl.mdx.zk.data.manager.StandardizationConfigDataManager;
import com.hcl.mdx.zk.data.manager.factory.StandardizationConfigDataManagerFactory;
import com.hcl.mdx.zk.data.standardize.MDXDataStandardizationAdapter;
import com.hcl.mdx.zk.data.standardize.factory.MDXDataStandardizationAdapterFactory;
import com.hcl.mdx.zk.ui.composer.AbstractPageComposer;
import com.hcl.mdx.zk.ui.grid.builder.AbstractGridBuilder;
import com.hcl.mdx.zk.ui.grid.builder.factory.StandardizationConfigGridBuilderFactory;
import com.hcl.mdx.zk.ui.grid.row.renderer.SrcTargetClmMapDetailsRowRenderer;

public class OnChangeEventListenerForDataStdizationEntity extends AbstractEventListener{

	Component parentComponent;
	Combobox engineSelectionBox;
	Database database;
	MDXTableMetadata mdxTableMetadata;
	AbstractPageComposer pageComposer;
		
	private static Logger log = Logger.getLogger("OnChangeEventListenerForDataStdizationEntity"); 
	public OnChangeEventListenerForDataStdizationEntity(
			Component parentComponent,
			Database database,
			MDXTableMetadata mdxTableMetadata,
			Combobox engineSelectionBox,
			AbstractPageComposer pageComposer){
		this.parentComponent = parentComponent;
		this.database = database;
		this.mdxTableMetadata = mdxTableMetadata;
		this.engineSelectionBox = engineSelectionBox;
		this.pageComposer = pageComposer;
	}

	@Override
	public void onEvent(Event event) throws Exception {
		Combobox entitySelectionBox = (Combobox) event.getTarget();

		Comboitem selectedEngine = engineSelectionBox.getSelectedItem();
		Comboitem selectedEntity = entitySelectionBox.getSelectedItem();

		if((selectedEngine != null) && (selectedEntity != null)){
			Tabbox tabbox = null;
			Tabs tabs = null;
			Tab tab = null;
			Tabpanels tabpanels = null;
			Tabpanel tabpanel = null;

			try{
				tabbox = new Tabbox();
				tabs = new Tabs();
				tab = new Tab("Input Field Map");
				tabs.appendChild(tab);
				tab = new Tab("Output Field Map");
				tabs.appendChild(tab);
				tabbox.appendChild(tabs);
				tabpanels = new Tabpanels();
				tabpanel = new Tabpanel();
				AbstractGridBuilder gridBuilder = 
					StandardizationConfigGridBuilderFactory.getGridBuilderInstance(
							database, 
							StandardizationAdapterType.valueOf(selectedEngine.getValue().toString())); 

				StandardizationConfigDataManager inDataManager = 
					StandardizationConfigDataManagerFactory.getDataManagerInstance(
							StandardizationAdapterType.valueOf(selectedEngine.getValue().toString()));

				StandardizationConfigDataManager outDataManager = 
					StandardizationConfigDataManagerFactory.getDataManagerInstance(
							StandardizationAdapterType.valueOf(selectedEngine.getValue().toString()));

				MDXDataStandardizationAdapter dataStandardizationAdapter = 
					MDXDataStandardizationAdapterFactory.getAdapterInstance(
							StandardizationAdapterType.valueOf(selectedEngine.getValue().toString()));

				
				ArrayList<AbstractModelObject> inAttrData = new ArrayList<AbstractModelObject>();
				ArrayList<AbstractModelObject> outAttrData = new ArrayList<AbstractModelObject>();
				
				
				Enum[] inAttrs = dataStandardizationAdapter.getListOfINAttributesForSelectedEntity(
						selectedEntity.getValue().toString());
				Enum[] outAttrs = dataStandardizationAdapter.getListOfOUTAttributesForSelectedEntity(
						selectedEntity.getValue().toString());

				for(int counter = 0; counter < inAttrs.length; counter++){
					DataLoadClmMapperObject clmMapperObject = new DataLoadClmMapperObject();
					clmMapperObject.setSourceColumnName(inAttrs[counter].toString());
					inAttrData.add(clmMapperObject);
				}
				for(int counter = 0; counter < outAttrs.length; counter++){
					DataLoadClmMapperObject clmMapperObject = new DataLoadClmMapperObject();
					clmMapperObject.setSourceColumnName(outAttrs[counter].toString());
					outAttrData.add(clmMapperObject);
				}

				/*
				 * build both in and out map grids.
				 */
				inDataManager.setData(inAttrData);
				gridBuilder.setDataManager(inDataManager);
				Grid inMapGrid = gridBuilder.buildGrid(Constants.CSS_BLACK_LABEL_9px_CLASS, null, 
						new SrcTargetClmMapDetailsRowRenderer(), false);
				inMapGrid.setPageSize(50);
				tabpanel.appendChild(inMapGrid);
				tabpanels.appendChild(tabpanel);

				outDataManager.setData(outAttrData);
				gridBuilder.setDataManager(outDataManager);
				Grid outMapGrid = gridBuilder.buildGrid(Constants.CSS_BLACK_LABEL_9px_CLASS, null, 
						new SrcTargetClmMapDetailsRowRenderer(), false);
				outMapGrid.setPageSize(50);
				tabpanel = new Tabpanel();
				tabpanel.appendChild(outMapGrid);
				tabpanels.appendChild(tabpanel);

				tabbox.appendChild(tabpanels);
				
				/*
				 * Set selected entity as an attribute in the tabbox.
				 */
				tabbox.setAttribute("SELECTED_ENTITY", selectedEntity.getValue().toString());
				tabbox.setAttribute("PAGE_COMPOSER", pageComposer);
				
				/*
				 * clear previous grids if any
				 */
				if(parentComponent.getChildren() != null){
					parentComponent.getChildren().clear();
				}
				parentComponent.appendChild(tabbox);
				
				parentComponent.getParent().setVisible(true);
			}
			catch (Exception e) {
				parentComponent.getParent().setVisible(false);
				log.error("Problems building the stdization map grids: ", e);
				throw new Exception(e.getMessage());

			}
		}
	}

}
