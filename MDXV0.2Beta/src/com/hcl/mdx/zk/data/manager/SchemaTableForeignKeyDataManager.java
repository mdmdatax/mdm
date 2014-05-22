package com.hcl.mdx.zk.data.manager;

import java.util.ArrayList;

import org.apache.ddlutils.model.ForeignKey;

import com.hcl.mdx.data.model.AbstractModelObject;
import com.hcl.mdx.data.model.SchemaTable;
import com.hcl.mdx.data.model.SchemaTableForeignKey;

/**
 * The Data Manager implementation that holds the details of
 * all the foreign keys present in a given table present in the
 * schema xml uploaded by the user.
 * @author vaidyanathan.s
 *
 */


public class SchemaTableForeignKeyDataManager extends AbstractDataManager{

	public SchemaTableForeignKeyDataManager() {
		listOfDisplayHeaders = new ArrayList<String>();
		listOfDisplayHeaders.add("Foreign Key Table Name");
		listOfDisplayHeaders.add("Foreign Key Relationship");
	}
	/**
	 * Takes a schema table object as argument and extracts all foreign key
	 * details from it which are then added to a list and set as the data.
	 * @param schemaTable	the table whose foreign key details are to be 
	 * 						extracted
	 */
	public void setData(SchemaTable schemaTable){
		ForeignKey[] foreignKeys = schemaTable.getTable().getForeignKeys();
		ArrayList<AbstractModelObject> listOfFKeys = new ArrayList<AbstractModelObject>();
		if(foreignKeys.length > 0){
			for(int counter = 0; counter < foreignKeys.length; counter++){
				SchemaTableForeignKey key = new SchemaTableForeignKey();
				key.setForeignKey(foreignKeys[counter]);
				listOfFKeys.add(key);
			}

		}
		this.setData(listOfFKeys);
	}
}