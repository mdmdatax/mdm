package com.hcl.mdx.zk.ui.renderers;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.RowRenderer;

import com.hcl.mdx.data.sorter.AbstractSorter;
import com.hcl.mdx.zk.ui.event.listener.ClearBusyEventListener;
import com.hcl.mdx.zk.ui.event.listener.ShowBusyEventListener;

public class GridRenderer implements ComponentRenderer{

	ArrayList<String> listOfColumns; 
	String columnLabelStyleClass;
	RowRenderer rowRenderer;
	ArrayList gridData; 
	String columnHeaderCellStyleClass;
	HashMap<String, String> columnSortPropertyMap;
	AbstractSorter gridSorter;
	private static Logger log = Logger.getLogger("GridRenderer");
	public GridRenderer(
			ArrayList<String> listOfColumns, 
			String columnsStyleClass,
			RowRenderer rowRenderer,
			ArrayList gridData, 
			String columnHeaderCellStyleClass){
		this.listOfColumns = listOfColumns;
		this.columnLabelStyleClass = columnsStyleClass;
		this.rowRenderer = rowRenderer;
		this.gridData = gridData;
		this.columnHeaderCellStyleClass = columnHeaderCellStyleClass;
	}

	public GridRenderer(
			ArrayList<String> listOfColumns, 
			String columnsStyleClass,
			RowRenderer rowRenderer,
			ArrayList gridData, 
			String columnHeaderCellStyleClass,
			AbstractSorter gridSorter){
		this.listOfColumns = listOfColumns;
		this.columnLabelStyleClass = columnsStyleClass;
		this.rowRenderer = rowRenderer;
		this.gridData = gridData;
		this.columnHeaderCellStyleClass = columnHeaderCellStyleClass;
		this.gridSorter = gridSorter;
	}

	//@Override
	public AbstractComponent renderComponent(String componentId) throws Exception {

		Grid grid = new Grid();
		grid.setFixedLayout(true);
		grid.setMold("paging");
		grid.setPageSize(10);

		LabelRenderer labelRenderer = new LabelRenderer(columnLabelStyleClass, null);
		Columns columns = new Columns();

		columns.setSizable(true);
		for(int colCounter = 0; colCounter < listOfColumns.size();colCounter++){
			Column column = new Column();

			column.appendChild(labelRenderer.createLabelWithText((String) listOfColumns.get(colCounter)));

			if(columnHeaderCellStyleClass != null){
				column.setSclass(columnHeaderCellStyleClass);
			}

			if(gridSorter != null){
				log.info("Sort map size: "+columnSortPropertyMap.size());
				if((columnSortPropertyMap.get(listOfColumns.get(colCounter)) != null)){
					AbstractSorter ascSorter = (AbstractSorter) gridSorter.clone();
					ascSorter.setAscending(true);
					ascSorter.setSortProperty(columnSortPropertyMap.get(listOfColumns.get(colCounter)));
					column.setSortAscending(ascSorter);
					column.addEventListener(Events.ON_SORT, new ShowBusyEventListener("Sorting. Please wait..."));
					
					column.addEventListener(Events.ON_NOTIFY, new ClearBusyEventListener());
					AbstractSorter dscSorter = (AbstractSorter) gridSorter.clone();
					dscSorter.setAscending(false);
					dscSorter.setSortProperty(columnSortPropertyMap.get(listOfColumns.get(colCounter)));
					column.setSortDescending(dscSorter);
				}
			}

			columns.appendChild(column);
		}
		grid.appendChild(columns);

		grid.setRowRenderer(rowRenderer);
		if(gridData != null){
			grid.setModel(new ListModelList(gridData));
		}
		else{
			grid.setModel(new ListModelList());
		}
		return grid;
	}

	public HashMap<String, String> getColumnSortPropertyMap() {
		return columnSortPropertyMap;
	}

	public void setColumnSortPropertyMap(
			HashMap<String, String> columnSortPropertyMap) {
		this.columnSortPropertyMap = columnSortPropertyMap;
	}


}
