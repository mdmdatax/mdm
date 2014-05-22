package com.hcl.mdx.zk.ui.event.listener;

import org.zkoss.zul.Column;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.event.Event;

public class GenericGridSortEventListener extends AbstractEventListener{

	private static Logger log = Logger.getLogger("GenericGridSortEventListener");
	
	private String sortPropertyName;
	
	public GenericGridSortEventListener(String sortPropertyName){
		this.sortPropertyName = sortPropertyName;
	}
	
	@Override
	public void onEvent(Event event) throws Exception {
		Column column = (Column) event.getTarget();
		//column.setSo
		Grid parentGrid = (Grid) column.getParent().getParent();
		
		log.info("Sort on "+sortPropertyName+": "+column.getSortDirection());
		
		ListModel data = parentGrid.getModel();
		
		for(int counter = 0; counter < data.getSize(); counter++){
			
		}
	}

}
