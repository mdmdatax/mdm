package com.hcl.mdx.zk.ui.event.listener;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Grid;

import com.hcl.mdx.zk.ui.grid.builder.AbstractGridBuilder;

public class GridRefreshEventListener extends RefreshEventListener{

	AbstractGridBuilder gridBuilder;
	
	public GridRefreshEventListener(AbstractGridBuilder gridBuilder){
		this.gridBuilder = gridBuilder;
	}
	
	@Override
	public void onEvent(Event event) throws Exception {
		Grid grid = (Grid) event.getTarget().getParent().getParent().getParent();
		gridBuilder.refreshGrid(grid);
	}

	
}
