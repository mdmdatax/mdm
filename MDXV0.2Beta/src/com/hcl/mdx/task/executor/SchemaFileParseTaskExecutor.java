package com.hcl.mdx.task.executor;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.ddlutils.Platform;
import org.apache.ddlutils.io.DatabaseIO;
import org.apache.ddlutils.model.Column;
import org.apache.ddlutils.model.Database;
import org.apache.ddlutils.model.ForeignKey;
import org.apache.ddlutils.model.Table;
import org.apache.ddlutils.platform.oracle.Oracle10Platform;
import org.apache.log4j.Logger;

import com.hcl.mdx.data.model.AbstractModelObject;
import com.hcl.mdx.data.model.SchemaTable;
import com.hcl.mdx.data.model.SessionDetailsObject;
import com.hcl.mdx.task.objects.SchemaFileParseTaskInput;
import com.hcl.mdx.task.objects.SchemaFileParseTaskOutput;
import com.hcl.mdx.util.Constants;
import com.hcl.mdx.zk.data.manager.SchemaTableDataManager;
import com.hcl.mdx.zk.ui.grid.builder.SchemaTableGridBuilder;



public class SchemaFileParseTaskExecutor extends AbstractTaskExecutor{

	private Logger log = Logger.getLogger("SchemaFileParseTaskExecutor");
	public SchemaFileParseTaskExecutor(SessionDetailsObject sessionDetailsObject, HttpSession httpSession){
		taskOutput = new SchemaFileParseTaskOutput();
		this.sessionDetailsObject = sessionDetailsObject;
		this.session = httpSession;
	}


	@Override
	public void run() {
		DatabaseIO databaseIO = null;
		SchemaFileParseTaskInput input = (SchemaFileParseTaskInput) taskInput;
		Platform platform = null;
		try{
			databaseIO = new DatabaseIO();

			progressMessageObject.setActivityName("Parsing XML contents");
			progressMessageObject.setProgressPercent(0);
			progressMessageObject.setProcessStatus("Working...");
			log.info(input.getFilePath());
			Database database = databaseIO.read(new File(input.getFilePath()));

			SchemaTableDataManager schemaTableDataManager = new SchemaTableDataManager();
			Table[] tables = database.getTables();

			ArrayList<AbstractModelObject> listOfTables = new ArrayList<AbstractModelObject>();

			for(int counter = 0; counter < tables.length; counter++){
				Table nextTable =  tables[counter];
				
				SchemaTable schemaTable = new SchemaTable();
				schemaTable.setTable(nextTable);
				listOfTables.add(schemaTable);
			}
			
			progressMessageObject.setProgressPercent(25);
			platform = new Oracle10Platform();
			progressMessageObject.setActivityName("Building DDL scripts");
			progressMessageObject.setProcessStatus("Working...");
			
			/*
			 * Build new db with mst, in, out and err tables.
			 */
			Database newDatabase = new Database();
			newDatabase.setName(database.getName());
			
			for(int counter = 0; counter < tables.length; counter++){
				Table table = tables[counter];
				log.info("Table name: " + table.getName());
				/*
				 * Clone table and change the names prefixed with in,out,mst and err.
				 * Remove all constraints for the err tables.
				 */
				
				Table newMSTTable = (Table) table.clone();
				newMSTTable.setName("MST_"+table.getName());
				
				Table newOUTTable = (Table) table.clone();
				newOUTTable.setName("OUT_"+table.getName());
				
				Table newINTable = (Table) table.clone();
				newINTable.setName(Constants.IN_TABLE_PREFIX+"_"+table.getName());
				
				Table newERRTable = (Table) table.clone();
				newERRTable.setName("ERR_"+table.getName());
				
				Table newNCTable = (Table) table.clone();
				newNCTable.setName("NC_"+table.getName());
				
				Column[] columns = table.getColumns();
				ForeignKey[] foreignKeys = table.getForeignKeys();
				
				for(int colCounter = 0; colCounter < columns.length; colCounter++){
					Column column = columns[colCounter];
					
					Column errTableColumn = (Column) column.clone();
					errTableColumn.setPrimaryKey(false);
					errTableColumn.setRequired(false);
					errTableColumn.setAutoIncrement(false);
					//errTableColumn.setType("VARCHAR");
					//errTableColumn.setSize("500");
					
					newERRTable.removeColumn(colCounter);
					newERRTable.addColumn(colCounter, errTableColumn);
				}
				
								
				if(foreignKeys != null){
					for(int fKeyCounter = 0; fKeyCounter < foreignKeys.length; fKeyCounter++){
						ForeignKey foreignKey = foreignKeys[fKeyCounter];

												
						/*
						 * Clone foreign keys, alter the table names to match the modified table 
						 * names created earlier and add to each table. also remove the original 
						 * foreignkey from each new table as this relationship is no longer valid.
						 * Donot add any fkey constraints to the error table. just remove the original
						 * from it.
						 */
						ForeignKey mstTableFkey = (ForeignKey) foreignKey.clone();
						mstTableFkey.setForeignTableName("MST_"+mstTableFkey.getForeignTableName());
						newMSTTable.removeForeignKey(fKeyCounter);
						newMSTTable.addForeignKey(fKeyCounter, mstTableFkey);
						//log.info(mstTableFkey.toVerboseString());
						
						ForeignKey inTableFkey = (ForeignKey) foreignKey.clone();
						inTableFkey.setForeignTableName(Constants.IN_TABLE_PREFIX+"_"+inTableFkey.getForeignTableName());
						newINTable.removeForeignKey(fKeyCounter);
						newINTable.addForeignKey(fKeyCounter, inTableFkey);
						
						ForeignKey outTableFkey = (ForeignKey) foreignKey.clone();
						outTableFkey.setForeignTableName("OUT_"+outTableFkey.getForeignTableName());
						newOUTTable.removeForeignKey(fKeyCounter);
						newOUTTable.addForeignKey(fKeyCounter, outTableFkey);
						
						ForeignKey ncTableFkey = (ForeignKey) foreignKey.clone();
						ncTableFkey.setForeignTableName("NC_"+outTableFkey.getForeignTableName());
						newNCTable.removeForeignKey(fKeyCounter);
						newNCTable.addForeignKey(fKeyCounter, outTableFkey);
						
						newERRTable.removeForeignKey(fKeyCounter);
						
					}
				}

				
				
				
				/*
				 * Add the newly created tables to the new database.
				 */
				//log.info(newDatabase.toVerboseString());
				
				newDatabase.addTable(counter, newMSTTable);
				log.info("New mst table: " +newMSTTable.getName());
				newDatabase.addTable(counter, newOUTTable);
				log.info("New out table: " +newOUTTable.getName());
				newDatabase.addTable(counter, newINTable);
				log.info("New in table: " +newINTable.getName());
				newDatabase.addTable(counter, newERRTable);
				log.info("New err table: " +newERRTable.getName());
				newDatabase.addTable(counter, newNCTable);
				log.info("New err table: " +newNCTable.getName());
			}
						
			log.debug(newDatabase.getTableCount()+" "+newDatabase.toVerboseString());
			String sqlString  = platform.getCreateTablesSql(newDatabase, false, false);
			sessionDetailsObject.setSchemaToBuild(newDatabase);
			
			schemaTableDataManager.setDdlString(sqlString);
			
			progressMessageObject.setProgressPercent(75);
			progressMessageObject.setProcessStatus("Completed");
			
			
			schemaTableDataManager.setData(listOfTables);
			schemaTableDataManager.setDatabase(database);
			SchemaTableGridBuilder schemaTableGridBuilder = sessionDetailsObject.getSchemaTableGridBuilder();
			schemaTableGridBuilder.setDataManager(schemaTableDataManager);
			sessionDetailsObject.setSchemaTableGridBuilder(schemaTableGridBuilder);
			session.setAttribute(Constants.SESSION_DETAILS_OBJECT_NAME, sessionDetailsObject);
			
			progressMessageObject.setProgressPercent(100);
			progressMessageObject.setProcessStatus("Completed");
			progressMessageObject.setCompleted(true);
		}
		catch (Exception e) {
			progressMessageObject.setCompleted(true);
			progressMessageObject.setEnteredErrorState(true);
			progressMessageObject.setProcessStatus("Error parsing schema definition XML: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
