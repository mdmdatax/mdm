package com.hcl.mdx.zk.data.manager;

import java.util.HashMap;

/**
 * Holds the list of zul pages in the application with
 * their identifiers.
 * Contains methods to return the forward page's id and
 * the backward page's id.
 * @author vaidyanathan.s
 *
 */

public class ApplicationProgressManager {

	private static HashMap<Integer, String> appProgressInfoMap;
	
	static{
		appProgressInfoMap = new HashMap<Integer, String>();
		
		appProgressInfoMap.put(new Integer(0), "index.zul");
		appProgressInfoMap.put(new Integer(1), "index.zul");
		appProgressInfoMap.put(new Integer(2), "createSchema.zul");
		appProgressInfoMap.put(new Integer(3), "initialDataLoad.zul");
		/*appProgressInfoMap.put(new Integer(4), "cleanseData.zul");*/
		appProgressInfoMap.put(new Integer(4), "standardizeData.zul");
		/*appProgressInfoMap.put(new Integer(6), "runDeterministicMatchRules.zul");
		appProgressInfoMap.put(new Integer(7), "runProbabilisticMatchRules.zul");
		appProgressInfoMap.put(new Integer(8), "viewGoldenRecords.zul");*/
		
	}
	/**
	 * Get the identifier of the next page in sequence.
	 * @param currentPageIndex	the identifier of the current page.
	 * @return the identifier of the next page in sequence.
	 */
	public static String getForwardPage(int currentPageIndex){
		int forwardPageIndex = currentPageIndex + 1;
		
		return appProgressInfoMap.get(new Integer(forwardPageIndex));
	}
	/**
	 * Get the identifier of the previous page in sequence.
	 * @param currentPageIndex	the identifier of the current page.
	 * @return the identifier of the previous page in sequence.
	 */
	public static String getBackwardPage(int currentPageIndex){
		int forwardPageIndex = currentPageIndex - 1;
		
		return appProgressInfoMap.get(new Integer(forwardPageIndex));
	}
}
