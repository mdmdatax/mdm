/**
 * 
 */
package com.hcl.mdx.database.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.hcl.mdx.database.jdbc.JDBCConnector;
import com.hcl.mdx.database.objects.connection.ConnectionDetailsObject;
import com.hcl.mdx.util.Constants;

/**
 * @author kamaleshkannan.k
 * @description Duplicate check for the Table Creation
 */
public class ListOfTables {

	private static Logger _logger = Logger.getLogger(ListOfTables.class);

	public ArrayList<String> getListOfTables(
			ConnectionDetailsObject connectionDetailsObject) {

		ArrayList<String> listOfTables = new ArrayList<String>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = (Connection) JDBCConnector
					.getDBConnection(connectionDetailsObject);
			pstmt = connection.prepareStatement(Constants.QUERY_LIST_OF_TABLES);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				listOfTables.add(rs.getString(Constants.COLOUMN_NAME));
			}

		} catch (SQLException e) {
			_logger.error("Unable to get List Of Tables from DataBase");
			//String message = (String)e.getMessage();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}

		return listOfTables;
	}

	public boolean callDropFunction(String logicalTables,
		ConnectionDetailsObject connectionDetailsObject) {
		Connection connection = null;
		CallableStatement cstmt = null;
		boolean status = false;

		connection = (Connection) JDBCConnector
				.getDBConnection(connectionDetailsObject);
		try {
			cstmt = connection.prepareCall("{? = call DROP_TABLE(?)}");
			cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
			cstmt.setString(2, logicalTables);
			cstmt.execute();
			status = true;
		} catch (SQLException e) {
			_logger.error("Unable To Drop The Tables");
			e.printStackTrace();

		}finally{
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
		return status;
	}
}
