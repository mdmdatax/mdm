package com.hcl.mdx.database.objects;

import java.util.ArrayList;

import org.apache.log4j.Logger;

public class MDXTableMetadata implements GenericDatabaseObject{

	private ArrayList<MDXTableDetailsObject> listOfTables;

	private ArrayList<MDXTableColumnDetailsObject> listOfTableColumns;

	private ArrayList<MDXTableRelationshipDetailsObject> listOfTableRelationhips;
	
	private static Logger log = Logger.getLogger("MDXTableMetadata");
	
	/**
	 * @return the listOfTables
	 */
	public ArrayList<MDXTableDetailsObject> getListOfTables() {
		return listOfTables;
	}

	/**
	 * @param listOfTables the listOfTables to set
	 */
	public void setListOfTables(ArrayList<MDXTableDetailsObject> listOfTables) {
		this.listOfTables = listOfTables;
	}

	/**
	 * @return the listOfTableColumns
	 */
	public ArrayList<MDXTableColumnDetailsObject> getListOfTableColumns() {
		return listOfTableColumns;
	}

	/**
	 * @param listOfTableColumns the listOfTableColumns to set
	 */
	public void setListOfTableColumns(
			ArrayList<MDXTableColumnDetailsObject> listOfTableColumns) {
		this.listOfTableColumns = listOfTableColumns;
	}

	/**
	 * @return the listOfTableRelationhips
	 */
	public ArrayList<MDXTableRelationshipDetailsObject> getListOfTableRelationhips() {
		return listOfTableRelationhips;
	}

	/**
	 * @param listOfTableRelationhips the listOfTableRelationhips to set
	 */
	public void setListOfTableRelationhips(
			ArrayList<MDXTableRelationshipDetailsObject> listOfTableRelationhips) {
		this.listOfTableRelationhips = listOfTableRelationhips;
	}

	public MDXTableDetailsObject getTableDetailsForId(String tableId){
		MDXTableDetailsObject returnValue = null;
		for(int counter = 0; counter < listOfTables.size(); counter++){
			if(listOfTables.get(counter).getId().toString().compareToIgnoreCase(tableId) == 0){
				returnValue = listOfTables.get(counter);
				break;
			}
		}

		return returnValue;
	}

	public MDXTableDetailsObject getTableDetailsForName(String tableName){
		MDXTableDetailsObject returnValue = null;
		if(listOfTables != null){
			for(int counter = 0; counter < listOfTables.size(); counter++){
				if((listOfTables.get(counter).getPhysicalName().toString().compareToIgnoreCase(tableName) == 0)
				|| (listOfTables.get(counter).getPhysicalInTableName().toString().compareToIgnoreCase(tableName) == 0)
				|| (listOfTables.get(counter).getPhysicalMstTableName().toString().compareToIgnoreCase(tableName) == 0)
				|| (listOfTables.get(counter).getPhysicalErrorTableName().toString().compareToIgnoreCase(tableName) == 0)){
					returnValue = listOfTables.get(counter);
					break;
				}
			}
		}
		return returnValue;
	}

	public ArrayList<MDXTableColumnDetailsObject> getTableColumnsForTableId(String tableId){
		ArrayList<MDXTableColumnDetailsObject> returnValue = new ArrayList<MDXTableColumnDetailsObject>();
		log.info("TableID: "+ tableId);
		for(int counter = 0; counter < listOfTableColumns.size(); counter++){
			if(Integer.toString(listOfTableColumns.get(counter).getTableId()).compareToIgnoreCase(tableId) == 0){
				returnValue.add(listOfTableColumns.get(counter));
			}
		}
		return returnValue;
	}

	public ArrayList<MDXTableRelationshipDetailsObject> getTableRelationShipsForTableName(String tableName){
		ArrayList<MDXTableRelationshipDetailsObject> returnValue = new ArrayList<MDXTableRelationshipDetailsObject>();
		log.info("Relationship details requested for: "+tableName);
		for(int counter = 0; counter < listOfTableRelationhips.size(); counter++){
			if(listOfTableRelationhips.get(counter).getParentTableName().compareToIgnoreCase(tableName) == 0){
				log.info("--Found "+counter+". "+listOfTableRelationhips.get(counter).getForiegnTableName());
				returnValue.add(listOfTableRelationhips.get(counter));
			}
		}
		return returnValue;
	}
}
