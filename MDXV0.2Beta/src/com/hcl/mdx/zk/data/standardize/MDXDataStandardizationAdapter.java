package com.hcl.mdx.zk.data.standardize;

import java.util.ArrayList;

import com.hcl.mdx.data.model.AbstractModelObject;
import com.hcl.mdx.file.data.TableAndClmMapDetailsObject;
import com.hcl.mdx.task.objects.ProgressMessageObject;


public abstract class MDXDataStandardizationAdapter {

	public MDXDataStandardizationAdapter(){}
	
	public MDXDataStandardizationAdapter(TableAndClmMapDetailsObject sourceMap, TableAndClmMapDetailsObject targetMap){}
	
	public abstract ArrayList<AbstractModelObject> doStandardization(ArrayList<AbstractModelObject> input, ProgressMessageObject progressMessageObject) throws Exception;
	
	public abstract Enum[] getListOfStdizableEntities();
	
	public abstract void setEntityToStandardize(Enum entity);
	
	public abstract Enum getEnumValueOfEntity(String entity);
	
	public abstract Enum[] getListOfINAttributesForSelectedEntity(String entity) throws Exception;
	
	public abstract Enum[] getListOfOUTAttributesForSelectedEntity(String entity) throws Exception;
}
