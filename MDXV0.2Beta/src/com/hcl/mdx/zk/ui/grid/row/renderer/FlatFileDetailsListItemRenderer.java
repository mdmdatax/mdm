package com.hcl.mdx.zk.ui.grid.row.renderer;

import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import com.hcl.mdx.data.model.FlatFileDetails;
import com.hcl.mdx.util.Constants;
import com.hcl.mdx.zk.data.manager.AbstractDataManager;
import com.hcl.mdx.zk.ui.event.listener.DeleteItemEventListener;
import com.hcl.mdx.zk.ui.event.listener.OnClickEventListenerForFileClmsView;
import com.hcl.mdx.zk.ui.renderers.LabelRenderer;

public class FlatFileDetailsListItemRenderer implements ListitemRenderer{

	AbstractDataManager dataManager;
	Button refreshButton;
	public FlatFileDetailsListItemRenderer(AbstractDataManager abstractDataManager, Button refreshButton){
		this.dataManager = abstractDataManager;
		this.refreshButton = refreshButton;
	}
	
	@Override
	public void render(Listitem listitem, Object data) throws Exception {

		FlatFileDetails	flatFileDetails = (FlatFileDetails) data;
		LabelRenderer labelRenderer = new LabelRenderer(Constants.CSS_BLACK_LABEL_9px_CLASS, null);
		Listcell listcell = new Listcell();
		listcell.appendChild(labelRenderer.createLabelWithText(flatFileDetails.getName()));
		listcell.setParent(listitem);

		listcell = new Listcell();
		Button button = new Button();
		button.setSclass("formButton");
		button.setImage(Constants.VIEW_DETAILS_IMG_LOCATION);
		button.setTooltiptext("View File Columns");
		button.addEventListener(
				Events.ON_CLICK, 
				new OnClickEventListenerForFileClmsView(Constants.BLANK_PAGE_URI, 
						"Column Headers in File", 
						null, 
						true, 
						null,
						flatFileDetails));
		listcell.appendChild(button);
		button = new Button();
		button.setSclass("formButton");
		button.setImage(Constants.DELETE_IMG_LOCATION);
		button.setTooltiptext("Remove File");
		button.addEventListener(Events.ON_CLICK, 
				new DeleteItemEventListener(dataManager, flatFileDetails.getId().toString(), refreshButton));
		listcell.appendChild(button);
		
		listcell.setParent(listitem);
		
		listitem.setAttribute("data", data);
	}


}
