package com.hcl.mdx.zk.data.manager;

import java.util.ArrayList;

import com.hcl.mdx.data.model.AbstractModelObject;

/**
 * The Data Manager implementation for the uploaded flat file details
 * grid.
 * Has a single display column: File Name.
 * @author vaidyanathan.s
 *
 */


public class FlatFileDetailsDataManager extends AbstractDataManager{

	public FlatFileDetailsDataManager() {
		listOfDisplayHeaders = new ArrayList<String>();
		listOfDisplayHeaders.add("File Name");
		listOfDisplayHeaders.add(" ");
		
	}
	
	@Override
	public void addDataItem(AbstractModelObject abstractModelObject){
		String id = Long.toString(System.currentTimeMillis());
		abstractModelObject.setId(id);
		super.addDataItem(abstractModelObject);
	}
	
}
