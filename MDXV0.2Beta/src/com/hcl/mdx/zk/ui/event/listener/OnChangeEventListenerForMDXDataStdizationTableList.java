package com.hcl.mdx.zk.ui.event.listener;

import java.util.ArrayList;
import java.util.List;

import org.apache.ddlutils.model.Column;
import org.apache.ddlutils.model.Database;
import org.apache.ddlutils.model.Table;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Auxheader;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Row;

public class OnChangeEventListenerForMDXDataStdizationTableList extends AbstractEventListener{

	Database database; 
	private static Logger log = Logger.getLogger("OnChangeEventListenerForMDXTableList");
	public OnChangeEventListenerForMDXDataStdizationTableList(
			Database database){
		this.database = database;
	}


	@Override
	public void onEvent(Event event) throws Exception {
		Table[] tables = database.getTables();
		Combobox mdxTableSelectionBox = (Combobox) event.getTarget();
		
		
		if(mdxTableSelectionBox.getSelectedItem() != null){
			
			/*
			 * also get a reference to the key column selection box.
			 */
			Combobox keyColumnSelectionBox = 
				(Combobox) ((Auxheader)(mdxTableSelectionBox.getParent().getParent().getChildren().get(1))).getChildren().get(1);
			if(keyColumnSelectionBox.getChildren()!= null){
				keyColumnSelectionBox.getChildren().clear();
			}
			String selectedTable = mdxTableSelectionBox.getSelectedItem().getValue().toString();
			Grid grid = (Grid) mdxTableSelectionBox.getParent().getParent().getParent();

			List rows = grid.getRows().getChildren();
			ArrayList<String> listOfColumns = new ArrayList<String>();
			
			for(int counter = 0; counter < tables.length; counter++){
				if(tables[counter].getName().compareToIgnoreCase(selectedTable) == 0){
					Column[] columns = tables[counter].getColumns();
					for(int colCounter = 0; colCounter < columns.length; colCounter++){
						listOfColumns.add(columns[colCounter].getName());
						
						/*
						 * add to key column selection box.
						 */
						Comboitem comboitem = new Comboitem();
						comboitem.setLabel(columns[colCounter].getName());
						comboitem.setValue(columns[colCounter].getName());
						keyColumnSelectionBox.appendChild(comboitem);
					}
					break;
				}
			}
			
			
			for(int rowCounter = 0; rowCounter < rows.size(); rowCounter++){
				Row row = (Row) rows.get(rowCounter);
				Div div = (Div) row.getChildren().get(1);
				Combobox colSelectionBox = (Combobox) div.getChildren().get(0);
				
				if(colSelectionBox.getChildren() != null) {
					colSelectionBox.getChildren().clear();
				}
				
				for(int counter = 0; counter < listOfColumns.size(); counter++){
					Comboitem comboitem = new Comboitem();
					comboitem.setLabel(listOfColumns.get(counter));
					comboitem.setValue(listOfColumns.get(counter));
					colSelectionBox.appendChild(comboitem);
				}
			}
		}
	}

}
