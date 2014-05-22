package com.hcl.mdx.zk.ui.event.listener;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;

import com.hcl.mdx.util.ActionRequired;
import com.hcl.mdx.zk.data.manager.AbstractDataManager;

public class DeleteItemEventListener extends AbstractEventListener{

	AbstractDataManager abstractDataManager;
	String itemId;
	Button refreshButtonReference;

	public DeleteItemEventListener(
			AbstractDataManager abstractDataManager, 
			String itemId,
			Button refreshButtonReference){
		this.abstractDataManager = abstractDataManager;
		this.itemId = itemId;
		this.refreshButtonReference = refreshButtonReference;
	}

	@Override
	public void onEvent(Event event) throws Exception {

		final ActionRequired actionRequired = new ActionRequired();	
		actionRequired.setActionRequired(false);

		Messagebox.show(
				"Are you sure you want to delete this item?", "Important", 
				Messagebox.OK | Messagebox.CANCEL, 
				Messagebox.QUESTION, 
				new org.zkoss.zk.ui.event.EventListener() {
					public void onEvent(Event event) throws Exception {
						if ("onOK".equals(event.getName())) {
							actionRequired.setActionRequired(true);
						} 
					}
				}); 

		if(actionRequired.isActionRequired()){
			abstractDataManager.removeDataItem(itemId);

			/*
			 * Refresh the grid/listbox
			 */
			//Component component = event.getTarget().getParent().getParent().getParent();
			//System.out.println(component.getClass());
			//Button refreshButton = null;
			//if(component.getClass().isInstance(new Listbox())){
				//refreshButton = (Button) ((Component)(((Component)(component.getChildren().get(0))).getChildren().get(1))).getChildren().get(0);
				Events.echoEvent(Events.ON_CLICK, refreshButtonReference, null);
			//}

			//if(component.getClass().isInstance(new Grid())){
				/*
				 * Refresh the Grid
				 */

				//Grid grid = (Grid) event.getTarget().getParent().getParent().getParent();
				//refreshButton = (Button) ((Component)(((Component)(grid.getChildren().get(1))).getChildren().get(1))).getChildren().get(0);

				//Events.echoEvent(Events.ON_CLICK, refreshButton, null);
			//}
		}
	}
}
