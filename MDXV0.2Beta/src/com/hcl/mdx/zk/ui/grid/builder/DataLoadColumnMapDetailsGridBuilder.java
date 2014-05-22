package com.hcl.mdx.zk.ui.grid.builder;

import org.zkoss.zul.Grid;

import com.hcl.mdx.util.Constants;
import com.hcl.mdx.zk.ui.grid.row.renderer.SrcTargetClmMapDetailsRowRenderer;

/**
 * The Grid Builder implementation that builds the mapping
 * grid to connect source data columns in the flat file
 * and target data columns in a database table.
 * @author vaidyanathan.s
 *
 */


public class DataLoadColumnMapDetailsGridBuilder extends AbstractGridBuilder{

	@Override
	public Grid buildGrid() throws Exception {
		Grid grid = buildGrid(
				Constants.CSS_BLACK_LABEL_9px_CLASS, 
				Constants.CSS_LV1_SUB_GRID_HEADER_COLOR_CLASS, 
				 new SrcTargetClmMapDetailsRowRenderer(),
				 false);		
		return grid;
	}

}
