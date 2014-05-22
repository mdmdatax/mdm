package com.hcl.mdx.zk.ui.event.listener;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Window;

import com.hcl.mdx.zk.ui.DataComponentBuilder;

public class ShowPopupEventListener extends AbstractEventListener{

	String urlOfPopupPage;
	Component componentToAppend;
	String pageTitle;
	boolean closable;
	DataComponentBuilder dataComponentBuilder;
	

	/**
	 * @return the dataComponentBuilder
	 */
	public DataComponentBuilder getDataComponentBuilder() {
		return dataComponentBuilder;
	}

	/**
	 * @param dataComponentBuilder the dataComponentBuilder to set
	 */
	public void setDataComponentBuilder(DataComponentBuilder dataComponentBuilder) {
		this.dataComponentBuilder = dataComponentBuilder;
	}

	public ShowPopupEventListener(
			String urlOfPopupPage, 
			String pageTitle, 
			Component componentToAppend, 
			boolean closable,
			DataComponentBuilder dataComponentBuilder){
		this.urlOfPopupPage = urlOfPopupPage;
		this.componentToAppend = componentToAppend;
		this.pageTitle = pageTitle;
		this.closable = closable;
		this.dataComponentBuilder = dataComponentBuilder;
	}
	
	@Override
	public void onEvent(Event event) throws Exception {
		Window window = (Window)Executions.createComponents(urlOfPopupPage, event.getTarget().getRoot().getParent(), null);
		window.setTitle(pageTitle);
		window.appendChild(componentToAppend);
		window.setClosable(closable);
		window.doModal();
		
	}

	public void onEvent(int height, int width, Event event) throws Exception{
		Window window = (Window)Executions.createComponents(urlOfPopupPage, event.getTarget().getRoot().getParent(), null);
		window.setHeight(""+height+"px");
		window.setWidth(""+width+"px");
		window.setSizable(true);
		window.setTitle(pageTitle);
		window.appendChild(componentToAppend);
		window.setClosable(closable);
		window.doModal();
	}
}
