package com.hcl.mdx.zk.ui.event.listener;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;

public class ShowBusyEventListener extends AbstractEventListener{

	String busyMessage;
	
	public ShowBusyEventListener(String busyMessage){
		this.busyMessage = busyMessage;
	}
	
	@Override
	public void onEvent(Event event) throws Exception {
		Clients.showBusy(busyMessage, true);
		
		Events.echoEvent(Events.ON_NOTIFY, event.getTarget(), null);
 
	}

}
