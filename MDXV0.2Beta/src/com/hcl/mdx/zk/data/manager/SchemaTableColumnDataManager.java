package com.hcl.mdx.zk.data.manager;

import java.util.ArrayList;

import org.apache.ddlutils.model.Column;

import com.hcl.mdx.data.model.AbstractModelObject;
import com.hcl.mdx.data.model.SchemaTable;
import com.hcl.mdx.data.model.SchemaTableColumn;

/**
 * The Data Manager implementation for the columns present
 * in each table defined in the xml schema uploaded by the
 * user.
 * 
 * @author vaidyanathan.s
 *
 */


public class SchemaTableColumnDataManager extends AbstractDataManager{

	public SchemaTableColumnDataManager() {
		listOfDisplayHeaders = new ArrayList<String>();
		listOfDisplayHeaders.add("Column Name");
		listOfDisplayHeaders.add("Column Description");
		listOfDisplayHeaders.add("Data Type");
		listOfDisplayHeaders.add("Size");
		listOfDisplayHeaders.add("Primary Key");
		listOfDisplayHeaders.add("Required");
		listOfDisplayHeaders.add("Auto Increment");
	}
	/**
	 * Sets the list of columns present in the supplied Schema
	 * Table object.
	 * 
	 * @param schemaTable the table whose columns are to be set
	 * 					  as data.
	 */
	public void setData(SchemaTable schemaTable){
		Column[] columns = schemaTable.getTable().getColumns();
		ArrayList<AbstractModelObject> listOfTableColumns = new ArrayList<AbstractModelObject>();
		for(int counter = 0; counter < columns.length; counter++){
			SchemaTableColumn schemaTableColumn = new SchemaTableColumn();
			schemaTableColumn.setColumn(columns[counter]);
			listOfTableColumns.add(schemaTableColumn);
		}
		
		this.setData(listOfTableColumns);
	}
}
