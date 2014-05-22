package com.hcl.mdx.database.util;

import java.sql.Connection;

import org.apache.ddlutils.model.Database;
import org.apache.ddlutils.platform.oracle.Oracle10Platform;

import com.hcl.mdx.database.jdbc.JDBCConnector;
import com.hcl.mdx.util.Constants;

public class DBUtils {

	public static Database readModelFromSchema(){
		Oracle10Platform platform = null;
		Connection connection = null;
		
		try{
			platform = new Oracle10Platform();
			connection = (Connection) JDBCConnector.getDBConnection(Constants.CONNECTION_DETAILS_OBJECT);
			
			return platform.readModelFromDatabase(connection, "mdx2", null, Constants.CONNECTION_DETAILS_OBJECT.getUserName().toUpperCase(), null);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
