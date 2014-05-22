package com.hcl.mdx.zk.data.manager.factory;

import com.hcl.mdx.util.Constants.StandardizationAdapterType;
import com.hcl.mdx.zk.data.manager.QualityStageStandardizationConfigDataManager;
import com.hcl.mdx.zk.data.manager.StandardizationConfigDataManager;

public class StandardizationConfigDataManagerFactory {

	public static StandardizationConfigDataManager getDataManagerInstance(StandardizationAdapterType adapterType) throws Exception{
		StandardizationConfigDataManager configDataManager;
		switch(adapterType){
		case QualityStage : configDataManager = new QualityStageStandardizationConfigDataManager(); break;
		default: throw new Exception("Invalid Stdization data manger option: "+adapterType);
		}
		return configDataManager;
	}
}
