package com.hcl.mdx.zk.ui.event.listener;

import org.zkoss.zk.ui.event.Event;

import com.hcl.mdx.zk.ui.composer.FlatFileDataLoadPageComposer;

public class OnClickEventListenerForDBLoad extends AbstractEventListener{

	private FlatFileDataLoadPageComposer composer;
	
	public OnClickEventListenerForDBLoad(FlatFileDataLoadPageComposer composer){
		this.composer = composer;
	}
	
	@Override
	public void onEvent(Event event) throws Exception {
		composer.onClick$databaseLoadButton(event);		
	}

}
