package com.hcl.mdx.file.parser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVReader;

import com.hcl.mdx.util.UtilFunctions;

public class CSVParser  {

	private static Logger log = Logger.getLogger("CSVParser");
	
	public static final int TAB_CHARACTER = 0;
	
	
	String delimiter;
	String textQualifier;
	
	public CSVParser(String delimiter, String textQualifier){
		this.delimiter = delimiter;
		this.textQualifier = textQualifier;
		log.info("Delimiter:"+delimiter+"TextQualifier:"+textQualifier);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Hashtable<String, String>> parseInputStream(
			InputStream inputStream,
			boolean isForSampleDisplay) throws Exception {

		InputStreamReader inputStreamReader = null;
		CSVReader csvReader = null;
		char textQualifierChar = CSVReader.DEFAULT_QUOTE_CHARACTER;
		char delimiterChar = CSVReader.DEFAULT_SEPARATOR;

		inputStreamReader = new InputStreamReader(inputStream);
	
		if(textQualifier != null){
			textQualifierChar = convertStringToChar(textQualifier);
		}
		if(delimiter != null){
			delimiterChar = convertStringToChar(delimiter);
		}

		csvReader = new CSVReader(inputStreamReader, delimiterChar, textQualifierChar);
		
		ArrayList fileContents = (ArrayList) csvReader.readAll();

		ArrayList displayList = new ArrayList();

		String[] columnHeaders = (String[]) fileContents.get(0);

		/*
		 * Remove white spaces in the header columns and add
		 * underscores in their place and capitalize letters.
		 */
		for(int headerCounter = 0; headerCounter < columnHeaders.length; headerCounter++) {
			columnHeaders[headerCounter] = 
				UtilFunctions.removeWhiteSpaces(columnHeaders[headerCounter]).toUpperCase();
			
		}

		for(int i = 1; i< fileContents.size(); i++ ) {

			String[] nextRow = (String[]) fileContents.get(i);
			Hashtable m1 = new Hashtable();
			for(int j = 0; j < nextRow.length; j++ ) {
				m1.put(columnHeaders[j], nextRow[j]);
			}
			displayList.add(m1);
			
			/*
			 *Restrict count of displayed rows to 10 to reduce burden on the jvm  
			 */
			if(i == 10){
				if(isForSampleDisplay){
					break;
				}
			}
		}
		inputStreamReader.close();
		inputStream.close();
		fileContents.clear();
		fileContents = null;
		

		return displayList;
	}

	private char convertStringToChar(String str){
		
		if(str.compareTo(""+TAB_CHARACTER) == 0){
			log.info("Returning tab");
			return '\t';
		}
		
		if(str.length() == 1){
			return str.charAt(0);
		}
		
		if(str.compareTo("\t") == 0){
			return TAB_CHARACTER;
		}
		else return CSVReader.DEFAULT_SEPARATOR;
	}
	
	/*public static void main(String args[]){
		CSVParser csvParser = new CSVParser("\t", null);
		try {
			ArrayList<Hashtable<String, String>> contents = csvParser.parseInputStream(new FileInputStream("D:/sample1.tmp"), true);
			
			for(int counter = 0; counter < contents.size(); counter++){
				Hashtable<String, String> nextRow = contents.get(counter);
				
				Enumeration<String> headers = nextRow.keys();
				
				while(headers.hasMoreElements()){
					String nextHeader = headers.nextElement();
					System.out.println(nextHeader + ": " + nextRow.get(nextHeader));
				}
				
				System.out.println("-----------------------------------------");
			}
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
}

