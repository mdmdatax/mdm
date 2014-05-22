package com.hcl.mdx.zk.data.manager;

import java.util.ArrayList;

public class QualityStageStandardizationConfigDataManager extends StandardizationConfigDataManager{

	public QualityStageStandardizationConfigDataManager(){
		listOfDisplayHeaders = new ArrayList<String>();
		listOfDisplayHeaders.add("External Engine I/O Port");
		listOfDisplayHeaders.add("MDX Master Table Clm");
	}
}
