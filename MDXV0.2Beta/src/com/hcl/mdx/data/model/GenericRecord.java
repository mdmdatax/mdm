package com.hcl.mdx.data.model;

import java.util.HashMap;

public class GenericRecord implements AbstractModelObject{

	private String recordId;
	private HashMap<String, Object> fieldMap;
	
	/**
	 * @return the fieldMap
	 */
	public HashMap<String, Object> getFieldMap() {
		return fieldMap;
	}

	/**
	 * @param fieldMap the fieldMap to set
	 */
	public void setFieldMap(HashMap<String, Object> fieldMap) {
		this.fieldMap = fieldMap;
	}

	public GenericRecord(){
		fieldMap = new HashMap<String, Object>();
	}
	
	@Override
	public Object getId() {
		return recordId;
	}

	@Override
	public void setId(String id) {
		this.recordId = id;
	}

	public void addField(String fieldName, Object fieldValue){
		fieldMap.put(fieldName, fieldValue);
	}
	
	public Object getFieldValue(String fieldName){
		return fieldMap.get(fieldName);
	}
	
	
}
