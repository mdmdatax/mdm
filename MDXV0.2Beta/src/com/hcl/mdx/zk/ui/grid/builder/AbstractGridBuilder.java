package com.hcl.mdx.zk.ui.grid.builder;

import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.RowRenderer;

import com.hcl.mdx.data.sorter.AbstractSorter;
import com.hcl.mdx.zk.data.manager.AbstractDataManager;
import com.hcl.mdx.zk.ui.DataComponentBuilder;
import com.hcl.mdx.zk.ui.renderers.GridRenderer;


/**
 * The abstract Grid Builder that all Grid builders must extend.
 * Provides methods to build a display grid filled with data.
 * @author vaidyanathan.s
 *
 */

public abstract class AbstractGridBuilder implements DataComponentBuilder{
	/**The Data Manager.*/
	AbstractDataManager dataManager;

	/**
	 * Returns the data manager implementation.
	 * @return the dataManager
	 */
	public AbstractDataManager getDataManager() {
		return dataManager;
	}

	/**
	 * sets the data manager implementation
	 * @param dataManager the dataManager to set
	 */
	public void setDataManager(AbstractDataManager dataManager) {
		this.dataManager = dataManager;
	}
	/**
	 * Refreshes the data model of the supplied grid.
	 * @param grid the gird whose data is to be refreshed.
	 */
	public void refreshGrid(Grid grid){
		if(dataManager.getData() != null){
			grid.setModel(new ListModelList(dataManager.getData()));
		}
	}
	/**
	 * Adds a blank column to the grid's column header. This is required when the
	 * grid has sub-grids and therefore 'detail' elements which are expanded to
	 * display these sub-grids.
	 * @param grid the grid to which the column is to be added.
	 */
	void addAdditionalDummyColumnForTreeGrid(Grid grid){
		Columns columns = grid.getColumns();
		Column firstColumn = (Column) columns.getFirstChild();

		Column dummyColumn = new Column();
		dummyColumn.setWidth("5%");
		columns.insertBefore(dummyColumn, firstColumn);
	}
	/**
	 * The method that all implementations of this class must override.
	 * @return				the completely built grid 
	 * @throws Exception	if there are problems during the build
	 */
	public abstract Grid buildGrid() throws Exception;
	
	/**
	 * Constructs a Grid with the supplied arguments.
	 * @param columnsLabelStyleClassString	the column header labels style css class
	 * @param columnHeaderCellStyleClass	the column header style css class
	 * @param rowRenderer					the row renderer implementation
	 * @param childGridsPresent				true if present, false if not
	 * @return								the completely built Grid
	 * @throws Exception
	 */
	public Grid buildGrid(
			String columnsLabelStyleClassString, 
			String columnHeaderCellStyleClass, 
			RowRenderer rowRenderer,
			boolean childGridsPresent) throws Exception{
		GridRenderer gridRenderer = new GridRenderer(
				dataManager.getListOfDisplayHeaders(), 
				columnsLabelStyleClassString, 
				rowRenderer, 
				dataManager.getData(), 
				columnHeaderCellStyleClass);
		Grid grid = (Grid) gridRenderer.renderComponent(null);
		
		if(childGridsPresent){
			addAdditionalDummyColumnForTreeGrid(grid);
		}
		
		return grid;
	}
	/**
	 * Constructs a Grid with the supplied arguments.
	 * @param columnsLabelStyleClassString	the column header labels style css class
	 * @param columnHeaderCellStyleClass	the column header style css class
	 * @param rowRenderer					the row renderer implementation
	 * @param childGridsPresent				true if present, false if not
	 * @param gridSorter					the grid data sorter implementation
	 * @return
	 * @throws Exception
	 */
	public Grid buildGrid(
			String columnsLabelStyleClassString, 
			String columnHeaderCellStyleClass, 
			RowRenderer rowRenderer,
			boolean childGridsPresent,
			AbstractSorter gridSorter) throws Exception{
		GridRenderer gridRenderer = new GridRenderer(
				dataManager.getListOfDisplayHeaders(), 
				columnsLabelStyleClassString, 
				rowRenderer, 
				dataManager.getData(), 
				columnHeaderCellStyleClass, 
				gridSorter);
		
		gridRenderer.setColumnSortPropertyMap(dataManager.getColumnSortPropertyMap());
		Grid grid = (Grid) gridRenderer.renderComponent(null);
		
		if(childGridsPresent){
			addAdditionalDummyColumnForTreeGrid(grid);
		}
		
		return grid;
	}
}
