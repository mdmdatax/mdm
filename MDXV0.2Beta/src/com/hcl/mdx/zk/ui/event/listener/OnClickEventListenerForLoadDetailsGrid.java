package com.hcl.mdx.zk.ui.event.listener;

import java.util.ArrayList;

import org.apache.ddlutils.model.Column;
import org.apache.ddlutils.model.Table;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Row;
import org.zkoss.zul.Vbox;

import com.hcl.mdx.data.model.AbstractModelObject;
import com.hcl.mdx.data.model.DataLoadClmMapperObject;
import com.hcl.mdx.data.model.FlatFileDetails;
import com.hcl.mdx.data.model.SessionDetailsObject;
import com.hcl.mdx.util.Constants;
import com.hcl.mdx.zk.data.manager.DataLoadColumnMapDataManager;
import com.hcl.mdx.zk.ui.grid.builder.DataLoadColumnMapDetailsGridBuilder;

public class OnClickEventListenerForLoadDetailsGrid extends AbstractEventListener{

	String targetTableName;
	FlatFileDetails flatFileDetails;

	public OnClickEventListenerForLoadDetailsGrid(
			String targetTableName,
			FlatFileDetails flatFileDetails,
			SessionDetailsObject sessionDetailsObject){
		this.targetTableName = targetTableName;
		this.flatFileDetails = flatFileDetails;
		this.sessionDetailsObject = sessionDetailsObject;
	}

	@Override
	public void onEvent(Event event) throws Exception {
		Row row = (Row) event.getTarget().getParent().getParent();
		Vbox vBox = new Vbox();
		Grid grid;
		
		if(row.getAttribute("mapDetailsGrid") != null){
			grid = (Grid) row.getAttribute("mapDetailsGrid");
		}
		else{
			Table[] tables = sessionDetailsObject.getMdxSchema().getTables();
			ArrayList<String> targetColumns = new ArrayList<String>();
			for(int counter = 0; counter < tables.length; counter++){
				if(tables[counter].getName().compareToIgnoreCase(targetTableName) == 0){
					Column[] columns = tables[counter].getColumns();
					for(int colCounter = 0; colCounter < columns.length; colCounter++){
						targetColumns.add(columns[colCounter].getName());
					}
					break;
				}
			}

			ArrayList<AbstractModelObject> mapData = new ArrayList<AbstractModelObject>();
			for(int counter = 0; counter < flatFileDetails.getColumns().size(); counter++){
				DataLoadClmMapperObject clmMapperObject = new DataLoadClmMapperObject();
				clmMapperObject.setSourceColumnName(flatFileDetails.getColumns().get(counter));
				clmMapperObject.setTargetColumnList(targetColumns);

				mapData.add(clmMapperObject);
			}


			DataLoadColumnMapDetailsGridBuilder gridBuilder = new DataLoadColumnMapDetailsGridBuilder();
			DataLoadColumnMapDataManager dataLoadColumnMapDataManager = new DataLoadColumnMapDataManager();
			dataLoadColumnMapDataManager.setData(mapData);
			gridBuilder.setDataManager(dataLoadColumnMapDataManager);
			grid = gridBuilder.buildGrid();
			grid.setMold("default");
			
			row.setAttribute("mapDetailsGrid", grid);
		}
		vBox.appendChild(grid);

		ShowPopupEventListener popupEventListener = new 
		ShowPopupEventListener(Constants.BLANK_PAGE_URI, "Configure Column Map", vBox, true, null);
		popupEventListener.onEvent(event);
	}
}
