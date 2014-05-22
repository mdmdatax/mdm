package com.hcl.mdx.zk.ui.grid.builder;

import org.apache.ddlutils.model.Database;


public abstract class StandardizationConfigGridBuilder extends AbstractGridBuilder{

	protected Database database;
	
	public StandardizationConfigGridBuilder(Database database){
		this.database = database;
	}
}
