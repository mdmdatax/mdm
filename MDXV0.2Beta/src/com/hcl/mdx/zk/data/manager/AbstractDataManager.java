package com.hcl.mdx.zk.data.manager;

import java.util.ArrayList;
import java.util.HashMap;

import com.hcl.mdx.data.model.AbstractModelObject;


/**
 * The abstract class that all Data Manager implementations must implement.
 * It holds the data as a list of AbstractModelObject implementations and
 * has getters and setters for this list.
 * It also has a sort property hashMap. This maps the display name of each column
 * to its physical name. This map is required if the data needs to be sorted at
 * display time.
 * @author vaidyanathan.s
 *
 */
public abstract class AbstractDataManager{
	
	private ArrayList<AbstractModelObject> data;
	protected ArrayList<String> listOfDisplayHeaders;
	private HashMap<String, String> columnSortPropertyMap;
	/**
	 * Returns the data list.
	 * @return the data
	 */
	public ArrayList<AbstractModelObject> getData() {
		return data;
	}
	/**
	 * Sets the data list.
	 * @param data the data to set
	 */
	public void setData(ArrayList<AbstractModelObject> data) {
		this.data = data;
	}
	/**
	 * Returns the list of display headers for the data.
	 * @return the listOfDisplayHeaders
	 */
	public ArrayList<String> getListOfDisplayHeaders() {
		return listOfDisplayHeaders;
	}
	/**
	 * sets the list of display headers for the data
	 * @param listOfDisplayHeaders the listOfDisplayHeaders to set
	 */
	public void setListOfDisplayHeaders(ArrayList<String> listOfDisplayHeaders) {
		this.listOfDisplayHeaders = listOfDisplayHeaders;
	}
	/**
	 * Returns the column sort property hash map
	 * @return the columnSortPropertyMap
	 */
	public HashMap<String, String> getColumnSortPropertyMap() {
		return columnSortPropertyMap;
	}
	/**
	 * Sets the column sort property hash map
	 * @param columnSortPropertyMap the columnSortPropertyMap to set
	 */
	public void setColumnSortPropertyMap(
			HashMap<String, String> columnSortPropertyMap) {
		System.out.println("Setting sort prop map "+ columnSortPropertyMap.size());
		this.columnSortPropertyMap = columnSortPropertyMap;
	}
	/**
	 * Adds a single data item to the list.
	 * @param abstractModelObject the item to add.
	 */
	public void addDataItem(AbstractModelObject abstractModelObject){
		if(this.data == null){
			this.data = new ArrayList<AbstractModelObject>();
		}
		this.data.add(abstractModelObject);
	}
	/**
	 * Removes a single data item from the list for the given id.
	 * @param id the identifier of the item to remove
	 */
	public void removeDataItem(String id){
		if(data != null){
			for(int counter = 0; counter < data.size(); counter++){
				if(data.get(counter).getId().toString().compareToIgnoreCase(id) == 0){
					data.remove(counter);
				}
			}
		}
	}
}
