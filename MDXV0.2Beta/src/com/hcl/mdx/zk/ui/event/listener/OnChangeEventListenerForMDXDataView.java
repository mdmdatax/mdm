package com.hcl.mdx.zk.ui.event.listener;

import org.apache.ddlutils.model.Database;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;

import com.hcl.mdx.database.objects.MDXTableMetadata;
import com.hcl.mdx.zk.ui.grid.builder.GenericMDXTableDataViewGridBuilder;

public class OnChangeEventListenerForMDXDataView extends AbstractEventListener{

	private MDXTableMetadata mdxTableMetadata;
	private Database database;
	private Component parentcomponent;
	private String tableNamePrefix;
	
	private static Logger log = Logger.getLogger("OnChangeEventListenerForMDXDataView");
	
	public OnChangeEventListenerForMDXDataView(MDXTableMetadata mdxTableMetadata, Database database, Component parentcomponent, String tableNamePrefix){
		this.mdxTableMetadata = mdxTableMetadata;
		this.database = database;
		this.parentcomponent = parentcomponent;
		this.tableNamePrefix = tableNamePrefix;
	}
	
	@Override
	public void onEvent(Event event) throws Exception {
		Combobox combobox = (Combobox) event.getTarget().getParent().getChildren().get(0);
		
		if((combobox.getSelectedItem() != null) && (mdxTableMetadata != null)){
			String tableName = combobox.getSelectedItem().getValue().toString();
			
			GenericMDXTableDataViewGridBuilder gridBuilder = new GenericMDXTableDataViewGridBuilder(mdxTableMetadata, tableNamePrefix);
			
			Grid grid = gridBuilder.buildGrid(
					tableName, 
					true, 
					null, 
					null, 
					database);
			
			if(grid != null){
				
				if((parentcomponent.getChildren().size() > 1) && (parentcomponent.getChildren().get(1) != null)){
					parentcomponent.removeChild((Component) parentcomponent.getChildren().get(1));
				}
				parentcomponent.appendChild(grid);
			}
		}
	}

}
