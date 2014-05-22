package com.hcl.mdx.zk.ui.event.listener;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Detail;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Vbox;

import com.hcl.mdx.data.model.SchemaTable;
import com.hcl.mdx.data.model.SessionDetailsObject;
import com.hcl.mdx.zk.data.manager.SchemaTableColumnDataManager;
import com.hcl.mdx.zk.data.manager.SchemaTableForeignKeyDataManager;
import com.hcl.mdx.zk.ui.grid.builder.SchemaTableColumnGridBuilder;
import com.hcl.mdx.zk.ui.grid.builder.SchemaTableForeignKeyGridBuilder;

public class OnOpenEventListenerForSchemaGrid extends AbstractEventListener{

	private SchemaTable schemaTable;

	public OnOpenEventListenerForSchemaGrid(SessionDetailsObject sessionDetailsObject, SchemaTable schemaTable){
		this.schemaTable = schemaTable;
		this.sessionDetailsObject = sessionDetailsObject;
	}

	@Override
	public void onEvent(Event event) throws Exception {

		boolean subGridsExist = false;

		Detail detail = (Detail)event.getTarget();
		try{
			if(detail.getFirstChild().getFirstChild() !=null){
				detail.getFirstChild().invalidate();
				detail.getFirstChild().detach();
				detail.getFirstChild().getFirstChild().detach();
				subGridsExist = true;
			}
		}
		catch(NullPointerException ne){

		}

		if(subGridsExist){
		}
		else{
			Div div = new Div();
			Vbox vBox = new Vbox();

			SchemaTableColumnGridBuilder tableColumnGridBuilder = new SchemaTableColumnGridBuilder();
			SchemaTableColumnDataManager tableColumnDataManager = new SchemaTableColumnDataManager();
			tableColumnDataManager.setData(schemaTable);
			tableColumnGridBuilder.setDataManager(tableColumnDataManager);
			Grid tableColumnsGrid = tableColumnGridBuilder.buildGrid();

			SchemaTableForeignKeyGridBuilder foreignKeyGridBuilder = new SchemaTableForeignKeyGridBuilder();
			SchemaTableForeignKeyDataManager foreignKeyDataManager = new SchemaTableForeignKeyDataManager();
			foreignKeyDataManager.setData(schemaTable);
			foreignKeyGridBuilder.setDataManager(foreignKeyDataManager);
			Grid fkeyGrid = foreignKeyGridBuilder.buildGrid();

			if(fkeyGrid.getModel().getSize() > 0){
				vBox.appendChild(fkeyGrid);
				vBox.insertBefore(tableColumnsGrid, fkeyGrid);
			}
			else{
				vBox.appendChild(tableColumnsGrid);
			}
			
			div.appendChild(vBox);
			detail.appendChild(div);
		}
	}

}



