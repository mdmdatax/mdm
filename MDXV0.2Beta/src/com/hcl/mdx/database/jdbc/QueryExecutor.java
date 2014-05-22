package com.hcl.mdx.database.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.beanutils.BasicDynaBean;
import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.ResultSetDynaClass;
import org.apache.ddlutils.model.Database;
import org.apache.log4j.Logger;

import com.hcl.mdx.data.model.AbstractModelObject;
import com.hcl.mdx.data.model.GenericRecord;
import com.hcl.mdx.data.model.MDXDynaBeanWrapper;
import com.hcl.mdx.database.objects.MDXTableColumnDetailsObject;
import com.hcl.mdx.database.objects.MDXTableDetailsObject;
import com.hcl.mdx.database.objects.MDXTableMetadata;
import com.hcl.mdx.database.objects.MDXTableRelationshipDetailsObject;
import com.hcl.mdx.file.data.TableAndClmMapDetailsObject;
import com.hcl.mdx.task.objects.ProgressMessageObject;
import com.hcl.mdx.util.Constants;


public class QueryExecutor {

	private static Logger log = Logger.getLogger("QueryExecutor");

	/**
	 * 
	 * @param listOfRecords
	 * @param progressMessageObject
	 * @throws Exception
	 */
	public static HashMap<String, Integer> loadMDXTableMapRecordsIntoDB(
			ArrayList<AbstractModelObject> listOfRecords,
			ProgressMessageObject progressMessageObject) throws Exception {
		String insertSQL = "INSERT INTO MDX2.SYS_MDX_TB_MAP (" +
		"PHYSICAL_NAME, LOGICAL_NAME, " + 
		"PHYSICAL_MST, PHYSICAL_IN, PHYSICAL_NC, " +
		"PHYSICAL_OUT, PHYSICAL_ERR, ACTIVE, " +
		"SNAPHOT_CREATE, STATUS) " +
		"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		progressMessageObject.setActivityName("Updating database with Table Details Records");
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		HashMap<String, Integer> listOfGeneratedKeys = null;
		try {
			connection = (Connection) JDBCConnector
			.getDBConnection(Constants.CONNECTION_DETAILS_OBJECT);

			listOfGeneratedKeys = new HashMap<String, Integer>();

			preparedStatement = connection.prepareStatement(insertSQL, new String[] {"TB_MAP_ID"});
			for (int counter = 0; counter < listOfRecords.size(); counter++) {
				MDXTableDetailsObject tableDetailsObject = (MDXTableDetailsObject) listOfRecords.get(counter);

				preparedStatement.setString(1, tableDetailsObject
						.getPhysicalName());
				preparedStatement.setString(2, tableDetailsObject
						.getLogicalName());
				preparedStatement.setString(3, tableDetailsObject
						.getPhysicalMstTableName());
				preparedStatement.setString(4, tableDetailsObject
						.getPhysicalInTableName());
				preparedStatement.setString(5, tableDetailsObject
						.getPhysicalNCTableName());
				preparedStatement.setString(6, tableDetailsObject
						.getPhysicalOutTableName());
				preparedStatement.setString(7, tableDetailsObject
						.getPhysicalErrorTableName());
				preparedStatement.setString(8, "Y");
				preparedStatement.setString(9, "Y");
				preparedStatement.setString(10, "Active: Newly Created");


				preparedStatement.executeUpdate();

				ResultSet keys = preparedStatement.getGeneratedKeys();
				int key = 0;
				while(keys.next()){
					key = keys.getInt(1);
				}
				listOfGeneratedKeys.put(tableDetailsObject.getPhysicalName(), key);

				String cleanseProgressString = (counter + 1) + " of "
				+ listOfRecords.size() + " records inserted. ("
				+ ((counter + 1) * 100 / (listOfRecords.size()))
				+ "% completed.)";

				progressMessageObject.setProcessStatus(cleanseProgressString);
				progressMessageObject.setProgressPercent((counter + 1) * 100
						/ (listOfRecords.size()));
			}

			progressMessageObject.setProcessStatus("Completed");
			progressMessageObject.setProgressPercent(100);

			preparedStatement.close();
			listOfRecords.clear();
			listOfRecords = null;
		} catch (Exception e) {
			String exceptionString = "Error loading Table Details Records: "
				+ e.getMessage();
			log.error(exceptionString, e);
			progressMessageObject.setProcessStatus(exceptionString);
			throw new Exception(e.getMessage());
		} finally {
			JDBCConnector.closeDBConnection(connection);
		}

		return listOfGeneratedKeys;
	}

	/**
	 * 
	 * @param listOfRecords
	 * @param progressMessageObject
	 * @throws Exception
	 */
	public static void loadMDXTableClmRecordsIntoDB(
			ArrayList<AbstractModelObject> listOfRecords,
			ProgressMessageObject progressMessageObject) throws Exception {
		String insertSQL = "INSERT INTO MDX2.SYS_MDX_TB_COLS( "+
		"TB_MAP_ID, TB_COLUMNS, STATUS, "+
		"PRIM_KEY, INDEX_KEY, COLUMN_DESC) "+ 
		"VALUES( ?, ?, ?, ?, ?, ?)";

		progressMessageObject.setActivityName("Updating database with Table Column Details Records");
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = (Connection) JDBCConnector
			.getDBConnection(Constants.CONNECTION_DETAILS_OBJECT);
			preparedStatement = connection.prepareStatement(insertSQL);
			for (int counter = 0; counter < listOfRecords.size(); counter++) {
				MDXTableColumnDetailsObject tableClmDetailsObject = (MDXTableColumnDetailsObject) listOfRecords.get(counter);

				preparedStatement.setInt(1, tableClmDetailsObject.getTableId());
				preparedStatement.setString(2, tableClmDetailsObject.getTableColumnName());
				preparedStatement.setString(3, "Active: Newly Created");
				preparedStatement.setString(4, (tableClmDetailsObject.isPrimaryKey()?"Y":"N"));
				preparedStatement.setString(5, tableClmDetailsObject.isIndexKey()?"Y":"N");
				preparedStatement.setString(6, tableClmDetailsObject.getTableColumnDesc());


				preparedStatement.execute();

				String cleanseProgressString = (counter + 1) + " of "
				+ listOfRecords.size() + " records inserted. ("
				+ ((counter + 1) * 100 / (listOfRecords.size()))
				+ "% completed.)";

				progressMessageObject.setProcessStatus(cleanseProgressString);
				progressMessageObject.setProgressPercent((counter + 1) * 100
						/ (listOfRecords.size()));
			}

			progressMessageObject.setProcessStatus("Completed");
			progressMessageObject.setProgressPercent(100);

			preparedStatement.close();
			listOfRecords.clear();
			listOfRecords = null;
		} catch (Exception e) {
			String exceptionString = "Error loading Table Column Details Records: "
				+ e.getMessage();
			log.error(exceptionString, e);
			progressMessageObject.setProcessStatus(exceptionString);
			throw new Exception(e.getMessage());
		} finally {
			JDBCConnector.closeDBConnection(connection);
		}
	}

	/**
	 * 
	 * @param listOfRecords
	 * @param progressMessageObject
	 * @throws Exception
	 */
	public static void loadMDXTableRelationshipRecordsIntoDB(
			ArrayList<AbstractModelObject> listOfRecords,
			ProgressMessageObject progressMessageObject) throws Exception {
		String insertSQL = "INSERT INTO MDX2.SYS_MDX_TB_REL( " +
		"REL_PARENT_TAB, REL_FORIEGN_TAB, REL_PARENT_COL, " + 
		"REL_FORIEGN_COL) " +
		"VALUES( ?, ?, ?, ?)";

		progressMessageObject.setActivityName("Updating database with Table Relationship Details Records");
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = (Connection) JDBCConnector
			.getDBConnection(Constants.CONNECTION_DETAILS_OBJECT);
			preparedStatement = connection.prepareStatement(insertSQL);
			for (int counter = 0; counter < listOfRecords.size(); counter++) {
				MDXTableRelationshipDetailsObject tableRelationshipDetailsObject = (MDXTableRelationshipDetailsObject) listOfRecords.get(counter);

				preparedStatement.setString(1, tableRelationshipDetailsObject.getParentTableName());
				preparedStatement.setString(2, tableRelationshipDetailsObject.getForiegnTableName());
				preparedStatement.setString(3, tableRelationshipDetailsObject.getParentColumnName());
				preparedStatement.setString(4, tableRelationshipDetailsObject.getForiegnColumnName());

				preparedStatement.execute();

				String cleanseProgressString = (counter + 1) + " of "
				+ listOfRecords.size() + " records inserted. ("
				+ ((counter + 1) * 100 / (listOfRecords.size()))
				+ "% completed.)";

				progressMessageObject.setProcessStatus(cleanseProgressString);
				progressMessageObject.setProgressPercent((counter + 1) * 100
						/ (listOfRecords.size()));
			}

			progressMessageObject.setProcessStatus("Completed");
			progressMessageObject.setProgressPercent(100);

			preparedStatement.close();
			listOfRecords.clear();
			listOfRecords = null;
		} catch (Exception e) {
			String exceptionString = "Error loading Table Relationship Details Records: "
				+ e.getMessage();
			log.error(exceptionString, e);
			progressMessageObject.setProcessStatus(exceptionString);
			throw new Exception(e.getMessage());
		} finally {
			JDBCConnector.closeDBConnection(connection);
		}
	}

	public static ArrayList<AbstractModelObject> getDataForTable(
			String tableName, 
			ArrayList<String> listOfColumnsToFetch,
			String keyColumnName, 
			Object keyColumnValue,
			Database database,
			String tablePrefix){
		
		return getDataForTable(tablePrefix +"_" +tableName, listOfColumnsToFetch, keyColumnName, keyColumnValue, database);
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<AbstractModelObject> getDataForTable(
			String tableName, 
			ArrayList<String> listOfColumnsToFetch,
			String keyColumnName, 
			Object keyColumnValue,
			Database database){
		
		Iterator<DynaBean> resultsIter;
		ArrayList<AbstractModelObject> results;
		Connection connection = null;
		ResultSet resultSet;
		PreparedStatement preparedStatement;
		try{
			log.info(tableName);
			connection = (Connection) JDBCConnector.getDBConnection(Constants.CONNECTION_DETAILS_OBJECT);
			
			ArrayList<Object> params   = new ArrayList<Object>();
			params.add(keyColumnValue);
			results = new ArrayList<AbstractModelObject>();
			String sqlQuery = "SELECT ";
			
			if(listOfColumnsToFetch != null){
				for(int counter = 0; counter < listOfColumnsToFetch.size(); counter++){
					sqlQuery += listOfColumnsToFetch.get(counter);
					if(counter < listOfColumnsToFetch.size()-1){
						sqlQuery += ", ";
					}
				}
			}
			else{
				sqlQuery += "*";
			}
			sqlQuery += " FROM " + tableName;
			
			if(keyColumnName != null){
				sqlQuery += " WHERE " + keyColumnName +" = ?";
				//resultsIter = platform.query(database, sqlQuery, params);
				preparedStatement = connection.prepareStatement(sqlQuery);
				preparedStatement.setObject(1, (Object)keyColumnValue);
			}
			else{
				//resultsIter = platform.query(database, sqlQuery);
				preparedStatement = connection.prepareStatement(sqlQuery);
			}
			log.info(sqlQuery);
			resultSet = preparedStatement.executeQuery();
			ResultSetDynaClass rsdc = new ResultSetDynaClass(resultSet, true);
			
			resultsIter = rsdc.iterator();
			
			   BasicDynaClass bdc =
			     new BasicDynaClass("foo", BasicDynaBean.class,
			                        rsdc.getDynaProperties());

			while (resultsIter.hasNext()){
				DynaBean dataBean = (DynaBean)resultsIter.next();
				DynaBean newRow = bdc.newInstance();
			    PropertyUtils.copyProperties(newRow, dataBean);  
			    MDXDynaBeanWrapper beanWrapper = new MDXDynaBeanWrapper(newRow, null);
				results.add(beanWrapper);
			}
			resultSet.close();
			preparedStatement.close();
			
			return results;
		}
		catch(Exception e){	
			e.printStackTrace();
			return null;
		}
		finally{
			JDBCConnector.closeDBConnection(connection);
		}
	}

	public static MDXTableMetadata getMDXTableMetadata(){
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		ArrayList<MDXTableDetailsObject> tablesList = null;
		ArrayList<MDXTableColumnDetailsObject> columnsList = null;
		ArrayList<MDXTableRelationshipDetailsObject> relationshipsList = null;
		
		try{
			connection = (Connection) JDBCConnector.getDBConnection(Constants.CONNECTION_DETAILS_OBJECT);
			tablesList = new ArrayList<MDXTableDetailsObject>();
			columnsList = new ArrayList<MDXTableColumnDetailsObject>();
			relationshipsList = new ArrayList<MDXTableRelationshipDetailsObject>();
			MDXTableMetadata mdxTableMetadata = new MDXTableMetadata();

			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * from SYS_MDX_TB_MAP");
			while(resultSet.next()){
				MDXTableDetailsObject tableDetailsObject = new MDXTableDetailsObject(
						resultSet.getString("PHYSICAL_NAME"), 
						resultSet.getString("LOGICAL_NAME"));
				tableDetailsObject.setId(resultSet.getInt("TB_MAP_ID"));
				tablesList.add(tableDetailsObject);
			}
			resultSet.close();
			statement.close();
			mdxTableMetadata.setListOfTables(tablesList);

			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT " +
					"TB_MAP_ID, TB_COLUMNS, STATUS, " + 
					"PRIM_KEY, INDEX_KEY, COLUMN_DESC " +
			"FROM MDX2.SYS_MDX_TB_COLS");
			while(resultSet.next()){
				MDXTableColumnDetailsObject columnDetailsObject = new MDXTableColumnDetailsObject();
				columnDetailsObject.setTableId(resultSet.getInt("TB_MAP_ID"));
				columnDetailsObject.setTableColumnDesc(resultSet.getString("COLUMN_DESC"));
				columnDetailsObject.setTableColumnName(resultSet.getString("TB_COLUMNS"));
				columnsList.add(columnDetailsObject);
			}
			resultSet.close();
			statement.close();
			mdxTableMetadata.setListOfTableColumns(columnsList);

			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT " +
					"REL_PARENT_TAB, REL_FORIEGN_TAB, REL_PARENT_COL, " + 
					"REL_FORIEGN_COL " +
			"FROM MDX2.SYS_MDX_TB_REL");
			
			while(resultSet.next()){
				MDXTableRelationshipDetailsObject relationshipDetailsObject = new MDXTableRelationshipDetailsObject();
				relationshipDetailsObject.setForiegnTableName((resultSet.getString("REL_FORIEGN_TAB")));
				relationshipDetailsObject.setParentTableName((resultSet.getString("REL_PARENT_TAB")));
				relationshipDetailsObject.setForiegnColumnName((resultSet.getString("REL_FORIEGN_COL")));
				relationshipDetailsObject.setParentColumnName((resultSet.getString("REL_PARENT_COL")));
				
				relationshipsList.add(relationshipDetailsObject);
			}
			resultSet.close();
			statement.close();
			mdxTableMetadata.setListOfTableRelationhips(relationshipsList);
			return mdxTableMetadata;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		finally{
			JDBCConnector.closeDBConnection(connection);
		}
	}
	
	public void upsertRecords(
			ArrayList<AbstractModelObject> listOfRecords, 
			String targetTableName, 
			String targetTableKeyClmName, 
			ProgressMessageObject progressMessageObject) throws Exception{ 
		Connection connection = null;
		PreparedStatement statement = null;
		String queryPart1 = "MERGE INTO " + targetTableName + " DEST using dual ON " +
		"(DEST." + targetTableKeyClmName + " = ?)WHEN MATCHED THEN ";
		
		String queryPart3 =" WHEN NOT MATCHED THEN ";
		progressMessageObject.setActivityName("Updating database with Standardized Records");
		progressMessageObject.setProcessStatus("Working...");
		progressMessageObject.setProgressPercent(0);
		
		try{
			connection = (Connection) JDBCConnector.getDBConnection(Constants.CONNECTION_DETAILS_OBJECT);
			
			for(int counter = 0; counter < listOfRecords.size(); counter++){
				GenericRecord record = (GenericRecord) listOfRecords.get(counter);
				Iterator<String> keyIter = record.getFieldMap().keySet().iterator();
				
				String queryPart2 = " UPDATE SET ";
				String queryPart4 = " INSERT (";
				String queryPart5 = " Values (";
				while(keyIter.hasNext()){
					String nextClmName = keyIter.next();
					queryPart2 += "DEST."+nextClmName+" = '"+record.getFieldValue(nextClmName)+"', ";
					queryPart4 += "DEST."+nextClmName+", ";
					queryPart5 += "'"+record.getFieldValue(nextClmName)+"', ";
				}
				
				/*
				 * replace trailing commas
				 */
				queryPart2 = queryPart2.substring(0, queryPart2.length()-2);
				queryPart4 = queryPart4.substring(0, queryPart4.length()-2) + ") ";
				queryPart5 = queryPart5.substring(0, queryPart5.length()-2) + ") ";
				
				String query = queryPart1 + queryPart2 + queryPart3 + queryPart4 + queryPart5;
				
				statement = connection.prepareStatement(query);
				statement.setObject(1, record.getId());
				
				statement.execute();
				String progressString = (counter + 1) + " of "
				+ listOfRecords.size() + " records inserted. ("
				+ ((counter + 1) * 100 / (listOfRecords.size()))
				+ "% completed.)";

				progressMessageObject.setProcessStatus(progressString);
				progressMessageObject.setProgressPercent((counter + 1) * 100
						/ (listOfRecords.size()));
			}

			progressMessageObject.setProcessStatus("Completed");
			progressMessageObject.setProgressPercent(100);

			statement.close();
			listOfRecords.clear();
			listOfRecords = null;
		} catch (Exception e) {
			String exceptionString = "Error updating database with standardized Records: "
				+ e.getMessage();
			log.error(exceptionString, e);
			progressMessageObject.setProcessStatus(exceptionString);
			throw new Exception(e.getMessage());
		} finally {
			JDBCConnector.closeDBConnection(connection);
		}
	}
	
}


