package com.hcl.mdx.zk.ui.event.listener;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.Clients;

public class ClearBusyEventListener extends AbstractEventListener{

	@Override
	public void onEvent(Event event) throws Exception {
		Clients.showBusy("", false); // close the message
		
	}

}
