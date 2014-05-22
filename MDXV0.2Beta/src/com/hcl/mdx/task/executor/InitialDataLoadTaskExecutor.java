package com.hcl.mdx.task.executor;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.ddlutils.Platform;
import org.apache.ddlutils.model.Database;
import org.apache.ddlutils.platform.oracle.Oracle10Platform;

import com.hcl.mdx.data.model.SessionDetailsObject;
import com.hcl.mdx.database.jdbc.JDBCConnector;
import com.hcl.mdx.database.jdbc.QueryExecutor;
import com.hcl.mdx.database.jdbc.StoredProcExecutor;
import com.hcl.mdx.file.data.ColumnToColumnMapObject;
import com.hcl.mdx.file.parser.CSVParser;
import com.hcl.mdx.task.objects.InitialDataLoadTaskInput;
import com.hcl.mdx.task.objects.TaskInput;
import com.hcl.mdx.util.Constants;
import com.hcl.mdx.util.UtilFunctions;

public class InitialDataLoadTaskExecutor extends AbstractTaskExecutor{

	ArrayList<TaskInput> taskInputs;

	public InitialDataLoadTaskExecutor(SessionDetailsObject sessionDetailsObject, HttpSession httpSession){
		this.sessionDetailsObject = sessionDetailsObject;
		this.session = httpSession;
	}
	
	/**
	 * @return the taskInputs
	 */
	public ArrayList<TaskInput> getTaskInputs() {
		return taskInputs;
	}

	/**
	 * @param taskInputs the taskInputs to set
	 */
	public void setTaskInputs(ArrayList<TaskInput> taskInputs) {
		this.taskInputs = taskInputs;
	}

	@Override
	public void run() {
		Connection connection = null;
		Database database = null;
		Platform platform = null;
		StoredProcExecutor storedProcExecutor = null;
		try{		
			connection = (Connection) JDBCConnector.getDBConnection(Constants.CONNECTION_DETAILS_OBJECT);			
			storedProcExecutor = new StoredProcExecutor(progressMessageObject);
			database = sessionDetailsObject.getMdxSchema();
			platform = new Oracle10Platform();
			String dbLoadProgressString = null;
			for(int taskCounter = 0; taskCounter < taskInputs.size(); taskCounter++){
				
				InitialDataLoadTaskInput taskInput = (InitialDataLoadTaskInput) taskInputs.get(taskCounter);
				
				String activityNamePart = "Load ID: "+taskInput.getLoadId();
				progressMessageObject.setActivityName(activityNamePart +" (Initializing)");

				DynaBean tableBean = database.createDynaBeanFor(taskInput.getTargetDetailsObject().getTableName(), false);
				
				/*
				 * Also create a dynamic bean for the corresponding reject table.
				 */
				DynaBean rejectTableBean = database.createDynaBeanFor(taskInput.getTargetDetailsObject().getTableName().replaceFirst(Constants.IN_TABLE_PREFIX+"_", "ERR_"), false);

				progressMessageObject.setActivityName(activityNamePart+" (Reading file contents)");
				progressMessageObject.setProcessStatus("Working...");
				
				/*
				 * Parse the file
				 */
				File file = new File(taskInput.getFlatFileDetails().getPath());

				InputStream inputStream = new FileInputStream(file);

				CSVParser parser = new CSVParser(taskInput.getFlatFileDetails().getFlatFileDelimiter(), taskInput.getFlatFileDetails().getFlatFileTextQualifier());

				ArrayList dataList = parser.parseInputStream(inputStream, false);

				progressMessageObject.setProcessStatus("Completed.");

				ArrayList<ColumnToColumnMapObject> tableColumns = taskInput.getTargetDetailsObject().getColumns();
				
				/*
				 * Disable all constraints on the table before load.
				 */
				progressMessageObject.setActivityName(activityNamePart+" (Disabling table Constraints)");
				progressMessageObject.setProcessStatus("Working...");
				String physicalTableName = sessionDetailsObject.getMdxTableMetadata().getTableDetailsForName(taskInput.getTargetDetailsObject().getTableName()).getPhysicalName();
				storedProcExecutor.enableOrDisableTableConstraints(physicalTableName, Constants.IN_TABLE_PREFIX, false);
				progressMessageObject.setProcessStatus("Completed");
				
				progressMessageObject.setActivityName(activityNamePart+" (Writing to database)");
				progressMessageObject.setProcessStatus("Working...");
				int insertCount = 0;
				int rejectCount = 0;
				
				for (int dataIter = 0; dataIter < dataList.size(); dataIter++) {
					Hashtable nextRow = (Hashtable) dataList.get(dataIter);

					for (int colCounter = 0; colCounter < tableColumns.size(); colCounter++) {
						ColumnToColumnMapObject nextDataColumn = (ColumnToColumnMapObject) tableColumns.get(colCounter);
						Object value = nextRow.get(UtilFunctions.removeWhiteSpaces(nextDataColumn.getName()));

						tableBean.set(nextDataColumn.getCanonicalColumnObject().getColumnName(), value);
						rejectTableBean.set(nextDataColumn.getCanonicalColumnObject().getColumnName(), value);
					}
					
					try{
						platform.insert(connection, database, tableBean);
						insertCount++;
					}
					catch(Exception e1){
						rejectCount++;
						rejectTableBean.set("SYS_ERR_CODE", e1.getMessage());
						platform.insert(connection, database, rejectTableBean);
					}
										
					dbLoadProgressString =  insertCount + " of " + dataList.size()
					+ " records inserted. " +rejectCount+" records rejected. " +
							"(" 
					+ ((dataIter+1) * 100 / dataList.size()) + "% completed.)";

					progressMessageObject.setProcessStatus(dbLoadProgressString);
					progressMessageObject.setProgressPercent((dataIter+1) * 100/ dataList.size());
					
					if((insertCount + rejectCount) > 50){
						JDBCConnector.closeDBConnection(connection);
						connection = (Connection) JDBCConnector.getDBConnection(Constants.CONNECTION_DETAILS_OBJECT);
					}
				}
				
				/*
				 * Enable all constraints on the table after load.
				 */
				progressMessageObject.setActivityName(activityNamePart+" (Enabling table Constraints)");
				progressMessageObject.setProcessStatus("Working...");
				storedProcExecutor.enableOrDisableTableConstraints(physicalTableName, Constants.IN_TABLE_PREFIX, true);
				progressMessageObject.setProcessStatus("Completed");
				
				if(progressMessageObject.getReport() == null){
					progressMessageObject.setReport("");
				}
				else{
					progressMessageObject.setReport(progressMessageObject.getReport()+"\n\n");
				}
				progressMessageObject.setReport(progressMessageObject.getReport()+(taskCounter + 1)+". " + "(LoadID: "+taskInput.getLoadId()+") "+dbLoadProgressString);
			}
			progressMessageObject.setProgressPercent(50);
			progressMessageObject.setActivityName("Refreshing schema details in Memory");
			progressMessageObject.setProcessStatus("Working...");
			sessionDetailsObject.setMdxTableMetadata(QueryExecutor.getMDXTableMetadata());
			progressMessageObject.setProcessStatus("Completed.");
			progressMessageObject.setProgressPercent(100);
			
			progressMessageObject.setCompleted(true);
		}
		catch(Exception e){
			progressMessageObject.setCompleted(true);
			progressMessageObject.setEnteredErrorState(true);
			String errorMsgString = "Error Loading data table ";
			progressMessageObject.setProcessStatus( errorMsgString + e.getMessage());

			e.printStackTrace();
		}
		finally{
			JDBCConnector.closeDBConnection(connection);
		}

	}



}
