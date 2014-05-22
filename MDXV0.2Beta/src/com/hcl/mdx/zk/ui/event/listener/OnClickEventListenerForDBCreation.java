package com.hcl.mdx.zk.ui.event.listener;

import org.zkoss.zk.ui.event.Event;

import com.hcl.mdx.zk.ui.composer.CreateSchemaPageComposer;

public class OnClickEventListenerForDBCreation extends AbstractEventListener{

	CreateSchemaPageComposer composer;

	public OnClickEventListenerForDBCreation(CreateSchemaPageComposer composer){
		this.composer = composer;
	}
	@Override
	public void onEvent(Event event) throws Exception {

		composer.onClick$executeDatabaseScriptsButton(event);
	}

}
