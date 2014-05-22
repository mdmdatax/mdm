package com.hcl.mdx.zk.ui.event.listener;

import java.util.ArrayList;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Vbox;

import com.hcl.mdx.data.model.FlatFileDetails;
import com.hcl.mdx.util.Constants;
import com.hcl.mdx.zk.ui.grid.row.renderer.FlatFileClmDetailsRowRenderer;
import com.hcl.mdx.zk.ui.listbox.builder.AbstractListboxBuilder;
import com.hcl.mdx.zk.ui.renderers.GridRenderer;

public class OnClickEventListenerForFileClmsView extends ShowPopupEventListener{

	Event event;
	FlatFileDetails flatFileDetails;
	
	public OnClickEventListenerForFileClmsView(
			String urlOfPopupPage,
			String pageTitle, 
			Component componentToAppend, 
			boolean closable, 
			AbstractListboxBuilder listboxBuilder,
			FlatFileDetails flatFileDetails) {
		
		super(urlOfPopupPage, pageTitle, componentToAppend, closable, listboxBuilder);
		this.flatFileDetails = flatFileDetails;
	}

	
	@Override
	public void onEvent(Event event) throws Exception{
				
		ArrayList<String> listOfColumns = new ArrayList<String>();
		listOfColumns.add("Column Name");
		GridRenderer gridRenderer = new GridRenderer(
				listOfColumns, 
				Constants.CSS_BLACK_LABEL_9px_CLASS, 
				new FlatFileClmDetailsRowRenderer(), 
				flatFileDetails.getColumns(), 
				null);
		Grid grid = (Grid) gridRenderer.renderComponent(null);
		
		Vbox vbox = new Vbox();
		vbox.appendChild(grid);
		
		super.componentToAppend = vbox;
		super.onEvent(event);
	}
	
}
