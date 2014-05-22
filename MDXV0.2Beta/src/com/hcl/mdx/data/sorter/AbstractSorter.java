package com.hcl.mdx.data.sorter;

import java.util.Comparator;

public abstract class AbstractSorter implements Comparator<Object>, Cloneable{

	String sortProperty;
	boolean ascending;
	
	/**
	 * @return the sortProperty
	 */
	public String getSortProperty() {
		return sortProperty;
	}
	/**
	 * @param sortProperty the sortProperty to set
	 */
	public void setSortProperty(String sortProperty) {
		this.sortProperty = sortProperty;
	}
	/**
	 * @return the ascending
	 */
	public boolean isAscending() {
		return ascending;
	}
	/**
	 * @param ascending the ascending to set
	 */
	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
        return super.clone();
	}

}
