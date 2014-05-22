package com.hcl.mdx.zk.ui.event.listener;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ddlutils.model.Database;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Grid;

import com.hcl.mdx.data.model.AbstractModelObject;
import com.hcl.mdx.database.jdbc.QueryExecutor;
import com.hcl.mdx.database.objects.MDXTableMetadata;
import com.hcl.mdx.database.objects.MDXTableRelationshipDetailsObject;
import com.hcl.mdx.zk.ui.grid.builder.GenericMDXTableDataViewGridBuilder;

public class OnOpenEventListenerForMDXDataGridDetailView extends AbstractEventListener{

	private MDXTableMetadata mdxTableMetadata;
	private Database database;
	private HashMap<String, Object> listOfParentKeyValuesMap;
	String tableNamePrefix;
	private String parentTableName;
	private ArrayList<MDXTableRelationshipDetailsObject> relationships;
	
	private static Logger log = Logger.getLogger("OnOpenEventListenerForMDXDataGridDetailView");
	
	public OnOpenEventListenerForMDXDataGridDetailView(
			MDXTableMetadata mdxTableMetadata, 
			Database database, 
			HashMap<String, Object> listOfParentKeyValuesMap, 
			String tableNamePrefix,
			String parentTableName,
			ArrayList<MDXTableRelationshipDetailsObject> relationships){
		this.mdxTableMetadata = mdxTableMetadata;
		this.database = database;
		this.listOfParentKeyValuesMap = listOfParentKeyValuesMap;
		this.tableNamePrefix = tableNamePrefix;
		this.parentTableName = parentTableName;
		this.relationships = relationships;
	}

	@Override
	public void onEvent(Event event) throws Exception {
		
		if((event.getTarget().getChildren() == null) || (event.getTarget().getChildren().size() ==0)){

			for(int counter = 0; counter < relationships.size(); counter++){
				MDXTableRelationshipDetailsObject detailsObject = relationships.get(counter);
				
				String nextTable = detailsObject.getForiegnTableName();
				
				GenericMDXTableDataViewGridBuilder gridBuilder = new GenericMDXTableDataViewGridBuilder(
						mdxTableMetadata, tableNamePrefix);
				ArrayList<AbstractModelObject> data = QueryExecutor.getDataForTable(nextTable, null, detailsObject.getForiegnColumnName(), listOfParentKeyValuesMap.get(detailsObject.getForiegnColumnName()), database, tableNamePrefix);
				log.info("FilterCLM: "+detailsObject.getForiegnColumnName()+" Value: "+ listOfParentKeyValuesMap.get(detailsObject.getForiegnColumnName())+ " Count:"+data.size());
				gridBuilder.getDataManager().setData(data);
				
				Grid grid = gridBuilder.buildGrid(
						nextTable, 
						false, 
						detailsObject.getForiegnColumnName(), 
						listOfParentKeyValuesMap.get(detailsObject.getParentColumnName()), 
						database);
				
				event.getTarget().appendChild(grid);
				
			}
		}
	}
}
