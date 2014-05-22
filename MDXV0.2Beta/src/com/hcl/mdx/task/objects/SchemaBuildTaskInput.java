package com.hcl.mdx.task.objects;

import org.apache.ddlutils.model.Database;

import com.hcl.mdx.database.objects.connection.ConnectionDetailsObject;

public class SchemaBuildTaskInput extends AbstractTaskInput{

	Database database;
	ConnectionDetailsObject connectionDetailsObject;
	
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
	
	/**
	 * @return the connectionDetailsObject
	 */
	public ConnectionDetailsObject getConnectionDetailsObject() {
		return connectionDetailsObject;
	}
	/**
	 * @param connectionDetailsObject the connectionDetailsObject to set
	 */
	public void setConnectionDetailsObject(
			ConnectionDetailsObject connectionDetailsObject) {
		this.connectionDetailsObject = connectionDetailsObject;
	}
	
}
