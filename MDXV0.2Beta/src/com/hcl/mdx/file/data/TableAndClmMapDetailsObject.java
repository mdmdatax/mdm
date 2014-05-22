package com.hcl.mdx.file.data;

import java.util.ArrayList;


public class TableAndClmMapDetailsObject  {

	String tableName;
	
	ArrayList<ColumnToColumnMapObject> columns;
	
	String keyColumnName;
	
	public TableAndClmMapDetailsObject(String tableName, ArrayList<ColumnToColumnMapObject> columns) {
		this.tableName = tableName;
		this.columns = columns;
	}
	
	
	/**
	 * @return the keyColumnName
	 */
	public String getKeyColumnName() {
		return keyColumnName;
	}


	/**
	 * @param keyColumnName the keyColumnName to set
	 */
	public void setKeyColumnName(String keyColumnName) {
		this.keyColumnName = keyColumnName;
	}


	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName)  {
		this.tableName = tableName;
	}

	public ArrayList<ColumnToColumnMapObject> getColumns() {
		return columns;
	}

	public void setColumns(ArrayList<ColumnToColumnMapObject> columns)  {
		this.columns = columns;
	}
	
	public String getCanonicalNameForClm(String clmName){
		String canonicalName = null;
		
		if(columns != null){
			for(int counter = 0; counter < columns.size(); counter++){
				ColumnToColumnMapObject columnMapObject = columns.get(counter);
				if(columnMapObject.getName().compareToIgnoreCase(clmName) == 0){
					canonicalName = columnMapObject.getCanonicalColumnObject().getColumnName();
					
					break;
				}
			}
		}
		System.out.println("ColumnName: "+clmName+" Canonical: "+canonicalName);
		return canonicalName;
	}
}
