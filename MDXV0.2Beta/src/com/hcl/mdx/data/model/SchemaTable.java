package com.hcl.mdx.data.model;


import org.apache.ddlutils.model.Table;

public class SchemaTable implements AbstractModelObject, Comparable<SchemaTable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Table table;

	/**
	 * @return the table
	 */
	public Table getTable() {
		return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(Table table) {
		this.table = table;
	}

	@Override
	public int compareTo(SchemaTable obj) {

		if (!(obj instanceof SchemaTable))
			throw new ClassCastException("Cannot cast object to schemaTable");

		return this.table.getName().compareTo(obj.getTable().getName());


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
