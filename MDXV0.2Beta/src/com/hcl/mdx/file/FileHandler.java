package com.hcl.mdx.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.hcl.mdx.util.Constants;

/**
 * Servlet implementation class FileHandler
 */
public class FileHandler {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("FileHandler");
	static int counter = 0;
	private String filePath;
	
	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Default constructor. 
	 */
	public FileHandler() {
		
	}

	public String handleFile(String fileName, String sessionId, InputStream inputStream) throws IOException  {
		counter++;
		String success = null;
		
		FileOutputStream fileOutputStream = null;
		// Create a new file upload handler
		log.info(counter+" sess: "+sessionId);
		try {			        
			fileOutputStream = new FileOutputStream(new File(Constants.TMP_FOLDER_PATH + fileName + sessionId + ".tmp"));
			
			fileOutputStream.write(IOUtils.toByteArray(inputStream));
			
			success = "1";
			
			setFilePath(Constants.TMP_FOLDER_PATH + fileName +  sessionId + ".tmp");
		}
		catch(Exception e) {
			log.error("Error parsing uploaded file: ", e);
			//response.getWriter().println(e.getMessage());
			success = e.getMessage();
		}
		finally{
			try{
				inputStream.close();
				fileOutputStream.close();
			}
			catch(Exception e){
				
			}
		}
		return success;
	}

}
