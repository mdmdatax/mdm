package com.hcl.mdx.zk.ui.listbox.builder;

import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Auxhead;
import org.zkoss.zul.Auxheader;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;

import com.hcl.mdx.util.Constants;
import com.hcl.mdx.zk.data.manager.FlatFileDetailsDataManager;
import com.hcl.mdx.zk.ui.event.listener.ListboxRefreshEventListener;
import com.hcl.mdx.zk.ui.grid.row.renderer.FlatFileDetailsListItemRenderer;
import com.hcl.mdx.zk.ui.renderers.LabelRenderer;

public class FlatFileDetailsListboxBuilder extends AbstractListboxBuilder{
	
	private Button refreshButton;
	
	public FlatFileDetailsListboxBuilder(){
		this.dataManager = new FlatFileDetailsDataManager();
	}
	
	private void addAuxHeader(Listbox listbox){
		Auxhead auxhead = new Auxhead();
		Auxheader auxheader = new Auxheader();
		auxheader.setColspan(1);
				
		auxheader.appendChild(new LabelRenderer(Constants.CSS_BLACK_LABEL_9px_CLASS, null).createLabelWithText("Review Data"));
		auxhead.appendChild(auxheader);

		auxheader = new Auxheader();
		auxheader.setColspan(1);
		
		if(refreshButton == null){
			refreshButton = new Button();
		}
		refreshButton.setSclass("formButton");
		refreshButton.setImage(Constants.REFRESH_VIEW_IMG_LOCATION);
		refreshButton.setTooltiptext("Refresh Grid");
				
		refreshButton.addEventListener(Events.ON_CLICK, new ListboxRefreshEventListener(this));
		auxheader.appendChild(refreshButton);
		
		auxhead.appendChild(auxheader);

		listbox.insertBefore(auxhead, listbox.getListhead());
		
	}

	@Override
	public Listbox buildListBox() {
		refreshButton = new Button();
		Listbox listbox = buildListbox(
				Constants.CSS_BLACK_LABEL_9px_CLASS, 
				null, 
				new FlatFileDetailsListItemRenderer(this.dataManager, refreshButton));		
		addAuxHeader(listbox);
		
		return listbox;
	}
}
