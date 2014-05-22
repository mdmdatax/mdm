package com.hcl.mdx.util;

import java.util.Arrays;
import java.util.HashMap;

public class UtilFunctions  {

	public static String removeWhiteSpaces(String stringToProcess) {
		if(stringToProcess == null) {
			return null;
		}
		else {
			return stringToProcess.trim().replaceAll("\\s+", "_");
		}
	}

	public static String buildPipeSeparatedStringFromArray(String[] listOfValues) {
		String delimitedString = "";
		if((listOfValues == null) || (listOfValues.length <= 0)) {
			return null;
		}
		for(int counter = 0; counter < listOfValues.length; counter ++ ) {
			delimitedString  += listOfValues[counter];

			if(counter < listOfValues.length - 1) {
				delimitedString  += "||";
			}
		}
		return delimitedString; 
	}
	
	public static String sortDelimitedMatchRuleAttrsString(String delimitedAttrString){
		String[] tokens = delimitedAttrString.split("\\|");
		
		HashMap<String, String> attrMapMatch = new HashMap<String, String>();
		String sortedDelimitedString = "";
		
		for(int counter = 0; counter < tokens.length; counter++){
			String nextToken = tokens[counter];
			
			if(nextToken.charAt(0) == '!'){
				String strippedString = nextToken.substring(1, nextToken.length());
				tokens[counter] = strippedString;
				attrMapMatch.put(strippedString, "N");
			}
		}
		
		Arrays.sort(tokens);
		
		for(int counter = 0; counter < tokens.length; counter++){
			String nextAttr = tokens[counter];
			
			if(attrMapMatch.containsKey(nextAttr)){
				nextAttr = "!" + nextAttr;
			}
			
			sortedDelimitedString += nextAttr + "|";
		}
		
		return sortedDelimitedString.substring(0, sortedDelimitedString.length() -1);
	}
}
