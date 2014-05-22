package com.hcl.mdx.zk.ui.grid.builder;

import org.zkoss.zul.Grid;

import com.hcl.mdx.util.Constants;
import com.hcl.mdx.zk.ui.grid.row.renderer.SchemaTableColumnRowRenderer;

public class SchemaTableColumnGridBuilder extends AbstractGridBuilder{

	@Override
	public Grid buildGrid() throws Exception{
		
		Grid grid = buildGrid(
				Constants.CSS_BLACK_LABEL_9px_CLASS, 
				Constants.CSS_LV1_SUB_GRID_HEADER_COLOR_CLASS, 
				 new SchemaTableColumnRowRenderer(),
				 false);		
		return grid;
	}
			
}
