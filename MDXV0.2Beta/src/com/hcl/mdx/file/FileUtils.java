package com.hcl.mdx.file;

import java.io.File;

import org.apache.log4j.Logger;

public class FileUtils  {

    private static Logger log = Logger.getLogger(FileUtils.class);
    
	public static boolean deleteFile(String path) {
		boolean success = false;
		try {
			File file = new File(path);
			success = file.delete();
			log.info("Temp uploaded File deleted successfully.");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return success;
	}
}
