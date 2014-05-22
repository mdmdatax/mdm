package com.hcl.mdx.zk.ui.grid.builder;

import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Auxhead;
import org.zkoss.zul.Auxheader;
import org.zkoss.zul.Button;
import org.zkoss.zul.Grid;

import com.hcl.mdx.util.Constants;
import com.hcl.mdx.zk.data.manager.SchemaTableDataManager;
import com.hcl.mdx.zk.ui.event.listener.GridRefreshEventListener;
import com.hcl.mdx.zk.ui.event.listener.OnClickEventListenerForDBScriptsView;
import com.hcl.mdx.zk.ui.grid.row.renderer.SchemaTableRowRenderer;
import com.hcl.mdx.zk.ui.renderers.LabelRenderer;

public class SchemaTableGridBuilder extends AbstractGridBuilder{

	public SchemaTableGridBuilder(){
		this.dataManager = new SchemaTableDataManager();
	}
	
	@Override
	public Grid buildGrid() throws Exception{
		
		Grid grid = buildGrid(Constants.CSS_BLACK_LABEL_9px_CLASS, null, new SchemaTableRowRenderer(), true);
		addAuxHeader(grid);
		
		
		return grid;
	}
	
	private void addAuxHeader(Grid grid){
		Auxhead auxhead = new Auxhead();
		Auxheader auxheader = new Auxheader();
		auxheader.setColspan(3);
				
		auxheader.appendChild(new LabelRenderer(Constants.CSS_BLACK_LABEL_9px_CLASS, null).createLabelWithText("Review Data"));
		auxhead.appendChild(auxheader);

		auxheader = new Auxheader();
		auxheader.setColspan(1);
		
				
		Button button = new Button();
		button.setSclass("formButton");
		button.setImage(Constants.REFRESH_VIEW_IMG_LOCATION);
		button.setTooltiptext("Refresh Grid");
				
		button.addEventListener(Events.ON_CLICK, new GridRefreshEventListener(this));
		auxheader.appendChild(button);
		
		button = new Button();
		button.setSclass("formButton");
		button.setImage(Constants.VIEW_DETAILS_IMG_LOCATION);
		button.setTooltiptext("View Database Scripts");
		button.addEventListener(
				Events.ON_CLICK, 
				new OnClickEventListenerForDBScriptsView(Constants.BLANK_PAGE_URI, 
						"SQL scripts for Schema Creation", 
						null, 
						true, 
						this));
		auxheader.appendChild(button);
		
		button = new Button();
		button.setSclass("formButton");
		button.setImage(Constants.RUN_OPERATION_IMG_LOCATION);
		button.setId("executeDatabaseScriptsButton");
		button.setTooltiptext("Execute Database Scripts");
		auxheader.appendChild(button);
				
		button.addEventListener(Events.ON_CLICK, new GridRefreshEventListener(this));

		auxhead.appendChild(auxheader);

		grid.insertBefore(auxhead, grid.getColumns());
		
	}

}
