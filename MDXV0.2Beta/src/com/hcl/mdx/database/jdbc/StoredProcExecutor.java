package com.hcl.mdx.database.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.OracleCallableStatement;

import com.hcl.mdx.task.objects.ProgressMessageObject;
import com.hcl.mdx.util.Constants;


public class StoredProcExecutor {

	private static final String[] NC_AND_ERR_TABLE_COLUMN_APPENDER_PROCS = { 
		"ALTR_ERR_TABLES_ADDCOLS",
		"ALTR_NC_TABLES_ADDCOLS"
	};

	private ProgressMessageObject progressMessageObject;

	public StoredProcExecutor(ProgressMessageObject progressMessageObject) {
		this.progressMessageObject = progressMessageObject;
	}

	public void appendAdditionSystemColsToERRAndNCTables() throws Exception {
		Connection connection = null;
		OracleCallableStatement callableStatement = null;

		try {
			progressMessageObject.setActivityName("Adding System columns to Error and Net Change tables.");
			progressMessageObject.setProgressPercent(0);

			connection = (Connection) JDBCConnector
			.getDBConnection(Constants.CONNECTION_DETAILS_OBJECT);

			for(int counter = 0; counter < NC_AND_ERR_TABLE_COLUMN_APPENDER_PROCS.length; counter++){
				callableStatement = (OracleCallableStatement) connection.prepareCall(
						"Begin "+NC_AND_ERR_TABLE_COLUMN_APPENDER_PROCS[counter]+"(?, ?, ?);end;");
				callableStatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
				callableStatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
				callableStatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);

				callableStatement.setString(1, null);
				callableStatement.setString(2, null);
				callableStatement.execute();
				callableStatement.close();
			}
			progressMessageObject.setProgressPercent(50);

			progressMessageObject.setProcessStatus("Completed.");
			progressMessageObject.setProgressPercent(100);


			progressMessageObject.setProgressPercent(100);
		} catch (Exception e) {
			e.printStackTrace();

			throw new Exception("Error adding system columns to ERROR/NC tables: "+ e.getMessage());
		} finally {
			JDBCConnector.closeDBConnection(connection);
		}
	}

	public void enableOrDisableTableConstraints(
			String physicalTableName, 
			String tableNamePrefix,
			boolean enableConstraints) throws Exception {
		String enableConstraintsSQL = "Begin ENABLE_ALL_CONSTRAINTS (?, ?, ?);end;";
		String disableConstraintsSQL = "Begin DISABLE_ALL_CONSTRAINTS (?, ?, ?);end;";
		String SQL;
		if(enableConstraints){
			SQL = enableConstraintsSQL;
		}
		else{
			SQL = disableConstraintsSQL;
		}
		Connection connection = null;
		OracleCallableStatement callableStatement = null;

		try {
			progressMessageObject.setActivityName("Altering state of table constraints.");
			progressMessageObject.setProcessStatus("Working...");
			progressMessageObject.setProgressPercent(0);

			connection = (Connection) JDBCConnector
			.getDBConnection(Constants.CONNECTION_DETAILS_OBJECT);
			callableStatement = (OracleCallableStatement) connection.prepareCall(SQL);
			callableStatement.registerOutParameter(1,
					oracle.jdbc.OracleTypes.VARCHAR);
			callableStatement.registerOutParameter(2,
					oracle.jdbc.OracleTypes.VARCHAR);
			callableStatement.registerOutParameter(3,
					oracle.jdbc.OracleTypes.VARCHAR);

			callableStatement.setString(1, physicalTableName);
			callableStatement.setString(2, tableNamePrefix);
			callableStatement.execute();

			String status = callableStatement.getString(3);
			System.out.println("status: " + status);

			callableStatement.close();
			progressMessageObject.setProcessStatus("Completed.");
			progressMessageObject.setProgressPercent(100);

		} catch (Exception e) {
			e.printStackTrace();

			throw new Exception("Error manipulating table constraints for '"+ physicalTableName +"': "+ e.getMessage());
		} finally {
			JDBCConnector.closeDBConnection(connection);
		}
	}
}
