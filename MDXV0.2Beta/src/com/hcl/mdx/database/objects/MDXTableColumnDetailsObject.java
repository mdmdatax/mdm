package com.hcl.mdx.database.objects;

import com.hcl.mdx.data.model.AbstractModelObject;

public class MDXTableColumnDetailsObject implements GenericDatabaseObject, AbstractModelObject {

	int tableId;
	String tableName;
	String tableColumnName;
	String tableColumnDesc;
	String status;
	boolean primaryKey;
	boolean indexKey;
	
	/**
	 * @return the tableColumnDesc
	 */
	public String getTableColumnDesc() {
		return tableColumnDesc;
	}
	/**
	 * @param tableColumnDesc the tableColumnDesc to set
	 */
	public void setTableColumnDesc(String tableColumnDesc) {
		this.tableColumnDesc = tableColumnDesc;
	}
	/**
	 * @return the tableId
	 */
	public int getTableId() {
		return tableId;
	}
	/**
	 * @param tableId the tableId to set
	 */
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	/**
	 * @return the tableColumnName
	 */
	public String getTableColumnName() {
		return tableColumnName;
	}
	/**
	 * @param tableColumnName the tableColumnName to set
	 */
	public void setTableColumnName(String tableColumnName) {
		this.tableColumnName = tableColumnName;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the primaryKey
	 */
	public boolean isPrimaryKey() {
		return primaryKey;
	}
	/**
	 * @param primaryKey the primaryKey to set
	 */
	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}
	/**
	 * @return the indexKey
	 */
	public boolean isIndexKey() {
		return indexKey;
	}
	/**
	 * @param indexKey the indexKey to set
	 */
	public void setIndexKey(boolean indexKey) {
		this.indexKey = indexKey;
	}
	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}
	
}
