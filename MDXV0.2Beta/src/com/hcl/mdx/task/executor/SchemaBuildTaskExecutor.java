package com.hcl.mdx.task.executor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.ddlutils.model.Column;
import org.apache.ddlutils.model.Database;
import org.apache.ddlutils.model.ForeignKey;
import org.apache.ddlutils.model.Table;
import org.apache.ddlutils.platform.oracle.Oracle10Platform;
import org.apache.log4j.Logger;

import com.hcl.mdx.data.model.AbstractModelObject;
import com.hcl.mdx.data.model.SessionDetailsObject;
import com.hcl.mdx.database.jdbc.JDBCConnector;
import com.hcl.mdx.database.jdbc.QueryExecutor;
import com.hcl.mdx.database.jdbc.StoredProcExecutor;
import com.hcl.mdx.database.objects.MDXTableColumnDetailsObject;
import com.hcl.mdx.database.objects.MDXTableDetailsObject;
import com.hcl.mdx.database.objects.MDXTableRelationshipDetailsObject;
import com.hcl.mdx.database.util.DBUtils;
import com.hcl.mdx.task.objects.SchemaBuildTaskOutput;
import com.hcl.mdx.util.Constants;
import com.hcl.mdx.zk.data.manager.SchemaTableDataManager;

public class SchemaBuildTaskExecutor extends AbstractTaskExecutor{

	private static Logger log = Logger.getLogger("SchemaBuildTaskExecutor");

	SessionDetailsObject sessionDetailsObject;
	HttpSession httpSession;
	
	public SchemaBuildTaskExecutor(SessionDetailsObject sessionDetailsObject, HttpSession httpSession){
		taskOutput = new SchemaBuildTaskOutput();
		this.sessionDetailsObject = sessionDetailsObject;
		this.session = httpSession;
	}

	@Override
	public void run() {
		Connection connection = null;
		Database database = null;
		Table[] tables = null;

		progressMessageObject.setActivityName("Initializing...");
		database = ((SchemaTableDataManager)(sessionDetailsObject.getSchemaTableGridBuilder().getDataManager())).getDatabase();
		
		if(database != null){
			try{

				tables = database.getTables();

				ArrayList<AbstractModelObject> listOfTableDetails = new ArrayList<AbstractModelObject>();
				ArrayList<AbstractModelObject> listOfTableClmDetails = new ArrayList<AbstractModelObject>();
				ArrayList<AbstractModelObject> listOfTableRelationshipDetails = new ArrayList<AbstractModelObject>();

				progressMessageObject.setActivityName("Enumerating database objects");
				progressMessageObject.setProcessStatus("Working...");
								
				for(int counter = 0; counter < tables.length; counter++){
					Table table = tables[counter];
					
					MDXTableDetailsObject mdxTableDetailsObject = new MDXTableDetailsObject(table.getName(), table.getDescription());
					listOfTableDetails.add(mdxTableDetailsObject);


					Column[] columns = table.getColumns();
					ForeignKey[] foreignKeys = table.getForeignKeys();

					if(foreignKeys != null){
						for(int fKeyCounter = 0; fKeyCounter < foreignKeys.length; fKeyCounter++){
							ForeignKey foreignKey = foreignKeys[fKeyCounter];

							MDXTableRelationshipDetailsObject tableRelationshipDetailsObject = new MDXTableRelationshipDetailsObject();
							tableRelationshipDetailsObject.setForiegnTableName(table.getName());
							tableRelationshipDetailsObject.setParentTableName(foreignKey.getForeignTableName());
							tableRelationshipDetailsObject.setParentColumnName(foreignKey.getFirstReference().getForeignColumnName());
							tableRelationshipDetailsObject.setForiegnColumnName(foreignKey.getFirstReference().getLocalColumnName());

							listOfTableRelationshipDetails.add(tableRelationshipDetailsObject);
														
						}
					}

					for(int colCounter = 0; colCounter < columns.length; colCounter++){
						Column column = columns[colCounter];

						MDXTableColumnDetailsObject columnDetailsObject = new MDXTableColumnDetailsObject();
						columnDetailsObject.setTableName(table.getName());
						columnDetailsObject.setTableColumnName(column.getName());
						columnDetailsObject.setTableColumnDesc(column.getDescription());
						columnDetailsObject.setPrimaryKey(column.isPrimaryKey());

						listOfTableClmDetails.add(columnDetailsObject);						
					}
				}
				
				HashMap<String, Integer> listOfTableIds = QueryExecutor.loadMDXTableMapRecordsIntoDB(listOfTableDetails, progressMessageObject);
				
				for(int counter = 0; counter < listOfTableClmDetails.size(); counter++){
					MDXTableColumnDetailsObject columnDetailsObject = (MDXTableColumnDetailsObject) listOfTableClmDetails.get(counter);
					columnDetailsObject.setTableId(listOfTableIds.get(columnDetailsObject.getTableName()));
				}
				
				QueryExecutor.loadMDXTableClmRecordsIntoDB(listOfTableClmDetails, progressMessageObject);
				
				QueryExecutor.loadMDXTableRelationshipRecordsIntoDB(listOfTableRelationshipDetails, progressMessageObject);
				
				Oracle10Platform platform = new Oracle10Platform();
				connection = (Connection) JDBCConnector.getDBConnection(Constants.CONNECTION_DETAILS_OBJECT);
				platform.createTables(connection, sessionDetailsObject.getSchemaToBuild(), true, true);
				
				/*
				 * Add additional system columns to ERR and NC tables 
				 */
				StoredProcExecutor storedProcExecutor = new StoredProcExecutor(progressMessageObject);
				storedProcExecutor.appendAdditionSystemColsToERRAndNCTables();
				
				/*
				 * Refresh the database schema object in the session.
				 */
				progressMessageObject.setProgressPercent(25);
				progressMessageObject.setActivityName("Refreshing schema details in-memory");
				progressMessageObject.setProcessStatus("Working...");
				
				
				sessionDetailsObject.setMdxSchema(DBUtils.readModelFromSchema());
				sessionDetailsObject.setMdxTableMetadata(QueryExecutor.getMDXTableMetadata());
				progressMessageObject.setProgressPercent(100);
				progressMessageObject.setProcessStatus("Completed");
				progressMessageObject.setCompleted(true);
				
			}
			catch (Exception e) {
				progressMessageObject.setCompleted(true);
				progressMessageObject.setEnteredErrorState(true);
				String errorMsgString = "Error Building Schema: ";
				if((e.getMessage() != null) && (e.getMessage().contains("unique constraint"))){
					errorMsgString += "There is already a table with the same name as the one you're trying to create: ";
				}
				progressMessageObject.setProcessStatus( errorMsgString + e.getMessage());
				
				e.printStackTrace();
			}
			finally{
				JDBCConnector.closeDBConnection(connection);
			}
		}
		else{
			progressMessageObject.setCompleted(true);
			progressMessageObject.setProcessStatus("Error: Nothing to create");
		}
	}

}
