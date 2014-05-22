package com.hcl.mdx.data.model;

import java.util.ArrayList;

public class DataLoadClmMapperObject implements AbstractModelObject{

	String sourceColumnName;
	ArrayList<String> targetColumnList;
	
	
	/**
	 * @return the sourceColumnName
	 */
	public String getSourceColumnName() {
		return sourceColumnName;
	}

	/**
	 * @param sourceColumnName the sourceColumnName to set
	 */
	public void setSourceColumnName(String sourceColumnName) {
		this.sourceColumnName = sourceColumnName;
	}

	/**
	 * @return the targetColumnList
	 */
	public ArrayList<String> getTargetColumnList() {
		return targetColumnList;
	}

	/**
	 * @param targetColumnList the targetColumnList to set
	 */
	public void setTargetColumnList(ArrayList<String> targetColumnList) {
		this.targetColumnList = targetColumnList;
	}

	@Override
	public Object getId() {
		return null;
	}

	@Override
	public void setId(String id) {
			
	}

}
