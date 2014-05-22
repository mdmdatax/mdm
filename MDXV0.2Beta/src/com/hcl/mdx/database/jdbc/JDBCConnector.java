package com.hcl.mdx.database.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hcl.mdx.database.objects.connection.ConnectionDetailsObject;

public class JDBCConnector {

	private static Logger log = Logger.getLogger(JDBCConnector.class);

	private static final String JDBC_DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";

	// private static final String JDBC_DRIVER_NAME = "com.mysql.jdbc.Driver";

	public static Object getDBConnection(
			ConnectionDetailsObject connectionDetailsObject) {
		Connection connection = null;
		Object returnValue = null;
		try {
			Class.forName(JDBC_DRIVER_NAME);
			String url = "jdbc:oracle:thin:@"
					+ connectionDetailsObject.getServerName() + ":"
					+ connectionDetailsObject.getPortNumber() + ":"
					+ connectionDetailsObject.getServiceName();
			/*
			 * String url = "jdbc:mysql://" +
			 * connectionDetailsObject.getServerName() + ":" +
			 * connectionDetailsObject.getPortNumber() + "/" +
			 * connectionDetailsObject.getServiceName();
			 */
			connection = DriverManager.getConnection(url,
					connectionDetailsObject.getUserName(),
					connectionDetailsObject.getPassword());
			returnValue = (Connection) connection;
		} catch (SQLException e) {
			log.error("Error making a connection to the database: ", e);
			returnValue = (String) e.getMessage();
		} catch (Exception e) {
			log.error("Error making a connection to the database: ", e);
			returnValue = (String) e.getMessage();
		}
		return returnValue;
	}

	public static void closeDBConnection(Connection connection) {
		try {
			if(connection != null)
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
