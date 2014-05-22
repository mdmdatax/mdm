package com.hcl.mdx.zk.ui.composer;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Window;

import com.hcl.mdx.util.Constants;
import com.hcl.mdx.zk.data.manager.ApplicationProgressManager;

public abstract class AbstractPageComposer extends GenericForwardComposer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger("AbstractComposer");

	Panel progressPanel;

	protected Window window; 
	
	
	public abstract int getPageId();
	
	public boolean checkSessionParameters(Event event, String pageURL){

		HttpSession httpSession = (javax.servlet.http.HttpSession)Sessions.getCurrent().getNativeSession();
		boolean paramsAreSet = false;
		if(httpSession.getAttribute(Constants.SESSION_DETAILS_OBJECT_NAME) == null){
			Executions.sendRedirect("index.zul");

		}
		
		else{
			paramsAreSet = true;
		}

		return paramsAreSet;
	}

	/**
	 * Fetches the selected value of the combobox. If the value is null, it returns null.
	 */
	String getComboBoxValue(Combobox combobox){
		try{
			return combobox.getSelectedItem().getValue().toString();
		}
		catch(Exception e){
			return null;
		}
	} 

	/**
	 * Fetches the selected value of the Radiogroup. If the value is null, it returns null.
	 */
	String getRadioGroupValue(Radiogroup radiogroup){
		try{
			return radiogroup.getSelectedItem().getValue().toString();
		}
		catch(Exception e){
			return null;
		}
	}    

	public void onClick$progressHideButton(Event event){
		progressPanel.setVisible(false);
	}

	void goForward(int currentPageIndex){    
		Executions.sendRedirect(ApplicationProgressManager.getForwardPage(currentPageIndex));
	}

	void goBackward(int currentPageIndex){
		Executions.sendRedirect(ApplicationProgressManager.getBackwardPage(currentPageIndex));
	}
	
	public void onClick$wfBackButton(Event event){
		AbstractPageComposer pageComposer = (AbstractPageComposer) window.getAttribute(Constants.PAGE_COMPOSER_PROPERTY_NAME);
		goBackward(pageComposer.getPageId());
	}
	
	public void onClick$wfFwdButton(Event event){
		AbstractPageComposer pageComposer = (AbstractPageComposer) window.getAttribute(Constants.PAGE_COMPOSER_PROPERTY_NAME);
		goForward(pageComposer.getPageId());
	}
}
