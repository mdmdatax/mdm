package com.hcl.mdx.zk.ui.grid.row.renderer;

import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

import com.hcl.mdx.data.model.DataLoadDetailsObject;
import com.hcl.mdx.data.model.SessionDetailsObject;
import com.hcl.mdx.util.Constants;
import com.hcl.mdx.zk.ui.event.listener.DeleteItemEventListener;
import com.hcl.mdx.zk.ui.event.listener.OnClickEventListenerForLoadDetailsGrid;
import com.hcl.mdx.zk.ui.renderers.LabelRenderer;

public class DataLoadDetailsRowRenderer implements RowRenderer{

	SessionDetailsObject sessionDetailsObject;
	Button refreshButton;
	
	public DataLoadDetailsRowRenderer(SessionDetailsObject sessionDetailsObject, Button refreshButton){
		this.sessionDetailsObject = sessionDetailsObject;
		this.refreshButton = refreshButton;
	}
	
	@Override
	public void render(Row row, Object data) throws Exception {
		DataLoadDetailsObject loadDetailsObject = (DataLoadDetailsObject) data;
		
		/*Detail detail = new Detail();
		detail.setOpen(false);
		detail.addEventListener(
				Events.ON_OPEN, 
				new OnOpenEventListenerForLoadDetailsGrid(
						loadDetailsObject.getTargetTable(), 
						loadDetailsObject.getFlatFileDetails(), 
						sessionDetailsObject));
		row.appendChild(detail);*/
		
		LabelRenderer labelRenderer = new LabelRenderer(Constants.CSS_BLACK_LABEL_9px_CLASS, null);
		Div div = new Div();
		div.appendChild(labelRenderer.createLabelWithText((String) loadDetailsObject.getId()));
		row.appendChild(div);
		
		div = new Div();
		div.appendChild(labelRenderer.createLabelWithText((String) loadDetailsObject.getFlatFileDetails().getName()));
		row.appendChild(div);
		
		div = new Div();
		div.appendChild(labelRenderer.createLabelWithText((String) loadDetailsObject.getTargetTable()));
		row.appendChild(div);
		
		Button button = new Button();
		button.setSclass("formButton");
		button.setImage(Constants.DELETE_IMG_LOCATION);
		button.setTooltiptext("Remove Load Item");
		button.addEventListener(Events.ON_CLICK, 
				new DeleteItemEventListener(
						sessionDetailsObject.getDataLoadDetailsGridBuilder().getDataManager(), 
						loadDetailsObject.getId().toString(),
						refreshButton));
		
		div = new Div();
		div.appendChild(button);
		
		button = new Button();
		button.setSclass("formButton");
		button.setImage(Constants.EDIT_IMG_LOCATION);
		button.setTooltiptext("Configure Column Maps");
		button.addEventListener(Events.ON_CLICK, 
				new OnClickEventListenerForLoadDetailsGrid(
						loadDetailsObject.getTargetTable(), 
						loadDetailsObject.getFlatFileDetails(), 
						sessionDetailsObject));
		
		div.appendChild(button);
		row.appendChild(div);
		
		row.setAttribute("data", loadDetailsObject);
	}

}
