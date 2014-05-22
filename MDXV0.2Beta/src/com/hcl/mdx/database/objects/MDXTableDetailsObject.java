package com.hcl.mdx.database.objects;

import com.hcl.mdx.data.model.AbstractModelObject;
import com.hcl.mdx.util.Constants;

public class MDXTableDetailsObject implements GenericDatabaseObject, AbstractModelObject{

	int id;
	String physicalName;
	String logicalName;
	String physicalMstTableName;
	String physicalInTableName;
	String physicalNCTableName;
	String physicalOutTableName;
	String physicalErrorTableName;
	boolean active;
	boolean snaphotCreate;
	String status;
	
	public MDXTableDetailsObject(String physicalName, String logicalName){
		this.physicalName = physicalName;
		this.logicalName = logicalName;
		
		this.physicalMstTableName = "MST_" + physicalName.toUpperCase();
		this.physicalInTableName = Constants.IN_TABLE_PREFIX+"_" + physicalName.toUpperCase();
		this.physicalNCTableName = "NC_" + physicalName.toUpperCase();
		this.physicalOutTableName = "OUT_" + physicalName.toUpperCase();
		this.physicalErrorTableName = "ERR_" + physicalName.toUpperCase();

	}
	
	/**
	 * @return the id
	 */
	public Object getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the physicalName
	 */
	public String getPhysicalName() {
		return physicalName;
	}
	/**
	 * @param physicalName the physicalName to set
	 */
	public void setPhysicalName(String physicalName) {
		this.physicalName = physicalName;
	}
	/**
	 * @return the logicalName
	 */
	public String getLogicalName() {
		return logicalName;
	}
	/**
	 * @param logicalName the logicalName to set
	 */
	public void setLogicalName(String logicalName) {
		this.logicalName = logicalName;
	}
	/**
	 * @return the physicalMstTableName
	 */
	public String getPhysicalMstTableName() {
		return physicalMstTableName;
	}
	/**
	 * @param physicalMstTableName the physicalMstTableName to set
	 */
	public void setPhysicalMstTableName(String physicalMstTableName) {
		this.physicalMstTableName = physicalMstTableName;
	}
	/**
	 * @return the physicalInTableName
	 */
	public String getPhysicalInTableName() {
		return physicalInTableName;
	}
	/**
	 * @param physicalInTableName the physicalInTableName to set
	 */
	public void setPhysicalInTableName(String physicalInTableName) {
		this.physicalInTableName = physicalInTableName;
	}
	/**
	 * @return the physicalNCTableName
	 */
	public String getPhysicalNCTableName() {
		return physicalNCTableName;
	}
	/**
	 * @param physicalNCTableName the physicalNCTableName to set
	 */
	public void setPhysicalNCTableName(String physicalNCTableName) {
		this.physicalNCTableName = physicalNCTableName;
	}
	/**
	 * @return the physicalOutTableName
	 */
	public String getPhysicalOutTableName() {
		return physicalOutTableName;
	}
	/**
	 * @param physicalOutTableName the physicalOutTableName to set
	 */
	public void setPhysicalOutTableName(String physicalOutTableName) {
		this.physicalOutTableName = physicalOutTableName;
	}
	/**
	 * @return the physicalErrorTableName
	 */
	public String getPhysicalErrorTableName() {
		return physicalErrorTableName;
	}
	/**
	 * @param physicalErrorTableName the physicalErrorTableName to set
	 */
	public void setPhysicalErrorTableName(String physicalErrorTableName) {
		this.physicalErrorTableName = physicalErrorTableName;
	}
	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	/**
	 * @return the snaphotCreate
	 */
	public boolean isSnaphotCreate() {
		return snaphotCreate;
	}
	/**
	 * @param snaphotCreate the snaphotCreate to set
	 */
	public void setSnaphotCreate(boolean snaphotCreate) {
		this.snaphotCreate = snaphotCreate;
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

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}

	
}
