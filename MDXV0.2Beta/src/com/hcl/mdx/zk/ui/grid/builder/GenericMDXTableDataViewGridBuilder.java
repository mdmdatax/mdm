package com.hcl.mdx.zk.ui.grid.builder;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ddlutils.model.Database;
import org.apache.log4j.Logger;
import org.zkoss.zul.Grid;

import com.hcl.mdx.data.sorter.DynaBeanWrapperSorter;
import com.hcl.mdx.database.jdbc.QueryExecutor;
import com.hcl.mdx.database.objects.MDXTableColumnDetailsObject;
import com.hcl.mdx.database.objects.MDXTableDetailsObject;
import com.hcl.mdx.database.objects.MDXTableMetadata;
import com.hcl.mdx.database.objects.MDXTableRelationshipDetailsObject;
import com.hcl.mdx.util.Constants;
import com.hcl.mdx.zk.data.manager.GenericMDXTableDataManager;
import com.hcl.mdx.zk.ui.grid.row.renderer.GenericMDXTableDataViewRowRenderer;

/**
 * The grid builder implementation that builds grids for views of all data present
 * in MDX IN, MST and ERR tables.
 * 
 * @author vaidyanathan.s
 *
 */

public class GenericMDXTableDataViewGridBuilder extends AbstractGridBuilder{
	/**The MDX meta details object.*/
	MDXTableMetadata mdxTableMetadata;
	/**The table name prefix (IN, OUT, MST or ERR)*/
	String tableNamePrefix;
	/**The Logger object.*/
	private static Logger log = Logger.getLogger("GenericMDXTableDataViewGridBuilder");
	/**
	 * Builds a new instance of the GenericMDXTableDataViewGridBuilder with the supplied
	 * arguments.
	 * @param mdxTableMetadata	the MDX meta details object.
	 * @param tableNamePrefix   the name prefix of the tables whose data is to be displayed.
	 */
	public GenericMDXTableDataViewGridBuilder(MDXTableMetadata mdxTableMetadata, String tableNamePrefix){
		this.dataManager = new GenericMDXTableDataManager();
		this.mdxTableMetadata = mdxTableMetadata;
		this.tableNamePrefix = tableNamePrefix;
	}
	/**
	 * Builds a Gird object with the supplied arguments.
	 * @param tableName				the name of the table whose data is to be displayed
	 * @param isFirstLevelTable		true if this table has no other parents.
	 * @param keyColumnName			the column whose value will be used to fetch data for
	 * 								child tables.
	 * @param keyColumnValue		the value of the column whose value will be used to fetch
	 * 								data for child tables.
	 * @param database				the database object.
	 * @return						a completely built grid
	 * @throws Exception
	 */
	public Grid buildGrid(
			String tableName, 
			boolean isFirstLevelTable, 
			String keyColumnName, 
			Object keyColumnValue,
			Database database) throws Exception{

		if(!(tableName.indexOf(tableNamePrefix) == 0)){
			tableName = tableNamePrefix + "_" +tableName;
		}
		MDXTableDetailsObject mdxTableDetailsObject = mdxTableMetadata.getTableDetailsForName(tableName);
		log.info(tableName);
		log.info(database.getTableCount()+" tables in memory");
		
		ArrayList<MDXTableColumnDetailsObject> columnDetailsObjects = 
			mdxTableMetadata.getTableColumnsForTableId(""+ mdxTableDetailsObject.getId());

		ArrayList<String> listOfColumnHeaders = new ArrayList<String>();
		HashMap<String, String> columnSortPropertyMap = new HashMap<String, String>();
		for(int colCounter = 0; colCounter < columnDetailsObjects.size(); colCounter++){
			String columnName = columnDetailsObjects.get(colCounter).getTableColumnName();
			String columnDesc = columnDetailsObjects.get(colCounter).getTableColumnDesc();
			listOfColumnHeaders.add((columnDesc != null)?columnDesc:columnName);
			columnSortPropertyMap.put((columnDesc != null)?columnDesc:columnName, columnName);
		}
		this.dataManager.setListOfDisplayHeaders(listOfColumnHeaders);
		this.dataManager.setColumnSortPropertyMap(columnSortPropertyMap);

		ArrayList<MDXTableRelationshipDetailsObject> relationshipDetailsObjects = mdxTableMetadata.getTableRelationShipsForTableName(mdxTableDetailsObject.getPhysicalName());

		boolean childObjectsPresent = false;

		String columnHeaderStyleClass = null;
		if((relationshipDetailsObjects != null) && (relationshipDetailsObjects.size() > 0)){
			childObjectsPresent = true;
		}
		if(!isFirstLevelTable){
			columnHeaderStyleClass = Constants.CSS_LV1_SUB_GRID_HEADER_COLOR_CLASS;  
		}
		Grid grid = null;

		this.dataManager.setData(
				QueryExecutor.getDataForTable(tableName, null, keyColumnName, keyColumnValue, database));

		grid = buildGrid(
				Constants.CSS_BLACK_LABEL_9px_CLASS, 
				columnHeaderStyleClass, 
				new GenericMDXTableDataViewRowRenderer(
						columnDetailsObjects, 
						relationshipDetailsObjects, 
						mdxTableMetadata, 
						database, 
						tableNamePrefix, 
						tableName), 
						childObjectsPresent, new DynaBeanWrapperSorter());

		
		return grid;
	}

	@Override
	public Grid buildGrid() {
		return null;
	}

}
