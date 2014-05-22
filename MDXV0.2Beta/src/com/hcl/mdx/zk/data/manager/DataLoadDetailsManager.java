package com.hcl.mdx.zk.data.manager;

import java.util.ArrayList;

import com.hcl.mdx.data.model.AbstractModelObject;

/**
 * The Data Manager implementation for the flat file to target table map grid.
 * 
 * @author vaidyanathan.s
 *
 */

public class DataLoadDetailsManager extends AbstractDataManager{

	public DataLoadDetailsManager() {
		listOfDisplayHeaders = new ArrayList<String>();
		listOfDisplayHeaders.add("Load Id");
		listOfDisplayHeaders.add("Source File");
		listOfDisplayHeaders.add("Target Table");
		listOfDisplayHeaders.add(" ");
	}
	/**
	 * Adds a load item to the list. Generates an id using the system time in milliseconds
	 */
	@Override
	public void addDataItem(AbstractModelObject abstractModelObject){
		String id = Long.toString(System.currentTimeMillis());
		abstractModelObject.setId(id);
		super.addDataItem(abstractModelObject);
	}
}
