package com.hcl.mdx.zk.ui.grid.builder.factory;

import org.apache.ddlutils.model.Database;

import com.hcl.mdx.util.Constants.StandardizationAdapterType;
import com.hcl.mdx.zk.ui.grid.builder.QSStandardizationConfigGridBuilder;
import com.hcl.mdx.zk.ui.grid.builder.StandardizationConfigGridBuilder;

public class StandardizationConfigGridBuilderFactory {

	public static StandardizationConfigGridBuilder getGridBuilderInstance(Database database, StandardizationAdapterType adapterType) throws Exception{
		StandardizationConfigGridBuilder configGridBuilder;
		switch(adapterType){
		case QualityStage : configGridBuilder = new QSStandardizationConfigGridBuilder(database); break;
		default : throw new Exception("Inavlid grid builder option: "+adapterType); 
		}
		
		return configGridBuilder;
	}
	
}
