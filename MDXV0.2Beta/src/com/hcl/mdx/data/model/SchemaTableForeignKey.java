package com.hcl.mdx.data.model;

import org.apache.ddlutils.model.ForeignKey;

public class SchemaTableForeignKey implements AbstractModelObject{

	ForeignKey foreignKey;

	/**
	 * @return the foreignKey
	 */
	public ForeignKey getForeignKey() {
		return foreignKey;
	}

	/**
	 * @param foreignKey the foreignKey to set
	 */
	public void setForeignKey(ForeignKey foreignKey) {
		this.foreignKey = foreignKey;
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
