package com.hcl.mdx.data.model;

import org.apache.commons.beanutils.DynaBean;



public class MDXDynaBeanWrapper implements AbstractModelObject{

	private DynaBean dynaBean;
	private String primaryKeyColumn;
	
	public MDXDynaBeanWrapper(DynaBean dynaBean, String primaryKeyColumn){
		this.dynaBean = dynaBean;
	}
	
	@Override
	public Object getId() {
		if(primaryKeyColumn != null){
			return this.dynaBean.get(primaryKeyColumn);
		}
		return null;
	}

	@Override
	public void setId(String id) {
		this.dynaBean.set(primaryKeyColumn, id);		
	}

	/**
	 * @return the dynaBean
	 */
	public DynaBean getDynaBean() {
		return dynaBean;
	}

	/**
	 * @param dynaBean the dynaBean to set
	 */
	public void setDynaBean(DynaBean dynaBean) {
		this.dynaBean = dynaBean;
	}

	/**
	 * @return the primaryKeyColumn
	 */
	public String getPrimaryKeyColumn() {
		return primaryKeyColumn;
	}

	/**
	 * @param primaryKeyColumn the primaryKeyColumn to set
	 */
	public void setPrimaryKeyColumn(String primaryKeyColumn) {
		this.primaryKeyColumn = primaryKeyColumn;
	}

	
}
