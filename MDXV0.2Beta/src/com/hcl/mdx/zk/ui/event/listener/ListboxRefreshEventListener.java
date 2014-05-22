package com.hcl.mdx.zk.ui.event.listener;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Listbox;

import com.hcl.mdx.zk.ui.listbox.builder.AbstractListboxBuilder;

public class ListboxRefreshEventListener extends RefreshEventListener{

AbstractListboxBuilder abstractListboxBuilder;
	
	public ListboxRefreshEventListener(AbstractListboxBuilder abstractListboxBuilder){
	
		this.abstractListboxBuilder = abstractListboxBuilder;
	}
	
	@Override
	public void onEvent(Event event) throws Exception {
		Listbox listbox = (Listbox) event.getTarget().getParent().getParent().getParent();
		abstractListboxBuilder.refreshListBox(listbox);
	}


}
