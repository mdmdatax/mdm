package com.hcl.mdx.zk.ui.event.listener;

import org.zkoss.zk.ui.event.EventListener;

import com.hcl.mdx.data.model.SessionDetailsObject;

public abstract class AbstractEventListener implements EventListener{

	SessionDetailsObject sessionDetailsObject;

	/**
	 * @return the sessionDetailsObject
	 */
	public SessionDetailsObject getSessionDetailsObject() {
		return sessionDetailsObject;
	}

	/**
	 * @param sessionDetailsObject the sessionDetailsObject to set
	 */
	public void setSessionDetailsObject(SessionDetailsObject sessionDetailsObject) {
		this.sessionDetailsObject = sessionDetailsObject;
	}
	
}
