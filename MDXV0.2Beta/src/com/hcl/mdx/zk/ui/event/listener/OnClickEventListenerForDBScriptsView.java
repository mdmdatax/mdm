package com.hcl.mdx.zk.ui.event.listener;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Vbox;

import com.hcl.mdx.util.Constants;
import com.hcl.mdx.zk.data.manager.SchemaTableDataManager;
import com.hcl.mdx.zk.ui.grid.builder.AbstractGridBuilder;
import com.hcl.mdx.zk.ui.renderers.LabelRenderer;

public class OnClickEventListenerForDBScriptsView extends ShowPopupEventListener{

	Event event;
	
	public OnClickEventListenerForDBScriptsView(
			String urlOfPopupPage,
			String pageTitle, 
			Component componentToAppend, 
			boolean closable, 
			AbstractGridBuilder gridBuilder) {
		
		super(urlOfPopupPage, pageTitle, componentToAppend, closable, gridBuilder);
	}

	
	@Override
	public void onEvent(Event event) throws Exception{
		SchemaTableDataManager dataManager = (SchemaTableDataManager) ((AbstractGridBuilder)dataComponentBuilder).getDataManager();
		Label label = new LabelRenderer(Constants.CSS_BLACK_LABEL_9px_CLASS, null).createLabelWithText(dataManager.getDdlString());
		label.setMultiline(true);
		
		Div div = new Div();
		div.setStyle("height:600px;max-height:400px;width:500px;max-width:600px;overflow:auto");
		Vbox vbox = new Vbox();
		vbox.appendChild(label);
		div.appendChild(vbox);
		super.componentToAppend = div;
		
		super.onEvent(event);
	}
	
}
