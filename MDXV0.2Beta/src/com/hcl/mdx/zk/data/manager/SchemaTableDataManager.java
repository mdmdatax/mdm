package com.hcl.mdx.zk.data.manager;

import java.util.ArrayList;

import org.apache.ddlutils.model.Database;

/**
 * The Data Manager implementation for storing the list of
 * tables present in the xml schema uploaded by the user.
 * 
 * @author vaidyanathan.s
 *
 */

public class SchemaTableDataManager extends AbstractDataManager{
	/**The complete database*/
	Database database;
	/**The data definition language sql for creating the database.*/
	String ddlString;
	
	/**
	 * @return the ddlString
	 */
	public String getDdlString() {
		return ddlString;
	}

	/**
	 * @param ddlString the ddlString to set
	 */
	public void setDdlString(String ddlString) {
		this.ddlString = ddlString;
	}

	/**
	 * @return the database
	 */
	public Database getDatabase() {
		return database;
	}

	/**
	 * @param database the database to set
	 */
	public void setDatabase(Database database) {
		this.database = database;
	}

	public SchemaTableDataManager(){
		listOfDisplayHeaders = new ArrayList<String>();
		listOfDisplayHeaders.add("Table Name");
		listOfDisplayHeaders.add("Table Description");
		listOfDisplayHeaders.add("Catalog");
	}

}
