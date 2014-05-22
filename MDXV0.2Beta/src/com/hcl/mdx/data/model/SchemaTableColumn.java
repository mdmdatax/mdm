package com.hcl.mdx.data.model;

import org.apache.ddlutils.model.Column;

public class SchemaTableColumn implements AbstractModelObject, Comparable<SchemaTableColumn>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Column column;

	/**
	 * @return the column
	 */
	public Column getColumn() {
		return column;
	}

	/**
	 * @param column the column to set
	 */
	public void setColumn(Column column) {
		this.column = column;
	}
	
	@Override
	public int compareTo(SchemaTableColumn obj) {

		if (!(obj instanceof SchemaTableColumn))
			throw new ClassCastException("Cannot cast object to schemaTableColumn");

		return this.column.getName().compareTo(obj.getColumn().getName());


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
