package com.hcl.mdx.task.objects;

import org.apache.ddlutils.model.Database;

public class SchemaFileParseTaskOutput extends AbstractTaskOutput{

	Database database;
	
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

}
