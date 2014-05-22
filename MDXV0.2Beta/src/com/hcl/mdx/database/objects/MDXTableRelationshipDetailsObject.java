package com.hcl.mdx.database.objects;

import com.hcl.mdx.data.model.AbstractModelObject;

public class MDXTableRelationshipDetailsObject implements GenericDatabaseObject, AbstractModelObject{

	String parentTableName;
	String foriegnTableName;
	String parentColumnName;
	String foriegnColumnName;
	/**
	 * @return the parentTableName
	 */
	public String getParentTableName() {
		return parentTableName;
	}
	/**
	 * @param parentTableName the parentTableName to set
	 */
	public void setParentTableName(String parentTableName) {
		this.parentTableName = parentTableName;
	}
	/**
	 * @return the foriegnTableName
	 */
	public String getForiegnTableName() {
		return foriegnTableName;
	}
	/**
	 * @param foriegnTableName the foriegnTableName to set
	 */
	public void setForiegnTableName(String foriegnTableName) {
		this.foriegnTableName = foriegnTableName;
	}
	/**
	 * @return the parentColumnName
	 */
	public String getParentColumnName() {
		return parentColumnName;
	}
	/**
	 * @param parentColumnName the parentColumnName to set
	 */
	public void setParentColumnName(String parentColumnName) {
		this.parentColumnName = parentColumnName;
	}
	/**
	 * @return the foriegnColumnName
	 */
	public String getForiegnColumnName() {
		return foriegnColumnName;
	}
	/**
	 * @param foriegnColumnName the foriegnColumnName to set
	 */
	public void setForiegnColumnName(String foriegnColumnName) {
		this.foriegnColumnName = foriegnColumnName;
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
