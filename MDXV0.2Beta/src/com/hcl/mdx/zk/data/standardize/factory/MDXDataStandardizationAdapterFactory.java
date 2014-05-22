package com.hcl.mdx.zk.data.standardize.factory;

import com.hcl.mdx.file.data.TableAndClmMapDetailsObject;
import com.hcl.mdx.util.Constants.StandardizationAdapterType;
import com.hcl.mdx.zk.data.standardize.MDXDataStandardizationAdapter;
import com.hcl.mdx.zk.data.standardize.qs.QualityStageStandardizationAdapter;

public class MDXDataStandardizationAdapterFactory {

	public static MDXDataStandardizationAdapter getAdapterInstance(StandardizationAdapterType adapterType) throws Exception{
		MDXDataStandardizationAdapter dataStandardizationAdapter;
		switch(adapterType){
		case QualityStage : dataStandardizationAdapter = new QualityStageStandardizationAdapter();break;
		default : throw new Exception("Invalid adapter type : "+ adapterType);
		}
		return dataStandardizationAdapter;
	}
	
	public static MDXDataStandardizationAdapter getAdapterInstance(
			StandardizationAdapterType adapterType,
			TableAndClmMapDetailsObject sourceMap,
			TableAndClmMapDetailsObject targetMap) throws Exception{
		MDXDataStandardizationAdapter dataStandardizationAdapter;
		switch(adapterType){
		case QualityStage : dataStandardizationAdapter = 
			new QualityStageStandardizationAdapter(sourceMap, targetMap);break;
		default : throw new Exception("Invalid adapter type : "+ adapterType);
		}
		return dataStandardizationAdapter;
	}
}
