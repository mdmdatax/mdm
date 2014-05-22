package com.hcl.mdx.zk.ui.grid.builder;

import javax.servlet.http.HttpSession;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Auxhead;
import org.zkoss.zul.Auxheader;
import org.zkoss.zul.Button;
import org.zkoss.zul.Grid;

import com.hcl.mdx.data.model.SessionDetailsObject;
import com.hcl.mdx.util.Constants;
import com.hcl.mdx.zk.data.manager.DataLoadDetailsManager;
import com.hcl.mdx.zk.ui.event.listener.GridRefreshEventListener;
import com.hcl.mdx.zk.ui.grid.row.renderer.DataLoadDetailsRowRenderer;

/**
 * The Grid Builder implementation that is responsible for building the Load details grid.
 * It has three columns: flat file name, target table name and a delete button for each
 * row.
 * 
 * @author vaidyanathan.s
 *
 */

public class DataLoadDetailsGridBuilder extends AbstractGridBuilder{
	/**The Refresh Grid button.*/
	private Button refreshButton;
	/**
	 * Builds a DataLoadDetailsGridBuilder instance.
	 */
	public DataLoadDetailsGridBuilder(){
		this.dataManager = new DataLoadDetailsManager();
	}
	
	@Override
	public Grid buildGrid() throws Exception{
		refreshButton = new Button();
		HttpSession httpSession = (javax.servlet.http.HttpSession)Sessions.getCurrent().getNativeSession();
		SessionDetailsObject sessionDetailsObject = (SessionDetailsObject)httpSession.getAttribute(Constants.SESSION_DETAILS_OBJECT_NAME);
		
		Grid grid = buildGrid(
				Constants.CSS_BLACK_LABEL_9px_CLASS, 
				null, 
				new DataLoadDetailsRowRenderer(sessionDetailsObject, refreshButton), 
				false);
		addAuxHeader(grid);
				
		return grid;
	}
	/**
	 * Adds an auxiliary header to the grid.  
	 * @param grid the grid to modify
	 */
	private void addAuxHeader(Grid grid){
		Auxhead auxhead = new Auxhead();
		Auxheader auxheader = new Auxheader();
		auxheader.setColspan(3);
				
		auxhead.appendChild(auxheader);

		auxheader = new Auxheader();
		auxheader.setColspan(1);
		
		refreshButton.setSclass("formButton");
		refreshButton.setImage(Constants.REFRESH_VIEW_IMG_LOCATION);
		refreshButton.setTooltiptext("Refresh Grid");
				
		refreshButton.addEventListener(Events.ON_CLICK, new GridRefreshEventListener(this));
		auxheader.appendChild(refreshButton);
		
				
		Button button = new Button();
		button.setSclass("formButton");
		button.setImage(Constants.RUN_OPERATION_IMG_LOCATION);
		button.setId("executeDataLoadButton");
		button.setTooltiptext("Execute Database Scripts");
		auxheader.appendChild(button);
				

		auxhead.appendChild(auxheader);

		grid.insertBefore(auxhead, grid.getColumns());
		
	}
}
