package com.hcl.mdx.data.model;

import java.util.ArrayList;

public class FlatFileDetails implements AbstractModelObject{

	String flatFileDelimiter;
	String flatFileTextQualifier;
	String name;
	String path;
	ArrayList<String> columns;
	String id;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	/**
	 * @return the columns
	 */
	public ArrayList<String> getColumns() {
		return columns;
	}
	/**
	 * @param columns the columns to set
	 */
	public void setColumns(ArrayList<String> columns) {
		this.columns = columns;
	}
	public String getFlatFileDelimiter() {
		return flatFileDelimiter;
	}
	public void setFlatFileDelimiter(String flatFileDelimiter) {
		this.flatFileDelimiter = flatFileDelimiter;
	}
	public String getFlatFileTextQualifier() {
		return flatFileTextQualifier;
	}
	public void setFlatFileTextQualifier(String flatFileTextQualifier) {
		this.flatFileTextQualifier = flatFileTextQualifier;
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
