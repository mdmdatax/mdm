package com.hcl.mdx.zk.data.manager;

import java.util.ArrayList;

/**
 * The Data Manager implementation for the initial data load page.
 * There are only two columns to be displayed -- the source flat file 
 * column name header and the target table column name header. 
 * @author vaidyanathan.s
 *
 */

public class DataLoadColumnMapDataManager extends AbstractDataManager{
	
	public DataLoadColumnMapDataManager() {
		listOfDisplayHeaders = new ArrayList<String>();
		listOfDisplayHeaders.add("Source Column");
		listOfDisplayHeaders.add("Target Column");
		
	}
}
