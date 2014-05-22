package com.hcl.mdx.data.model;

public class DataLoadDetailsObject implements AbstractModelObject{

	String id;
	FlatFileDetails flatFileDetails;
	String targetTable;
		
	/**
	 * @return the flatFileDetails
	 */
	public FlatFileDetails getFlatFileDetails() {
		return flatFileDetails;
	}

	/**
	 * @param flatFileDetails the flatFileDetails to set
	 */
	public void setFlatFileDetails(FlatFileDetails flatFileDetails) {
		this.flatFileDetails = flatFileDetails;
	}
	
	/**
	 * @return the targetTable
	 */
	public String getTargetTable() {
		return targetTable;
	}

	/**
	 * @param targetTable the targetTable to set
	 */
	public void setTargetTable(String targetTable) {
		this.targetTable = targetTable;
	}

	@Override
	public Object getId() {
		
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;		
	}

	
}
