package com.hcl.mdx.zk.ui.composer;

import javax.servlet.http.HttpSession;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;

import com.hcl.mdx.data.model.SessionDetailsObject;
import com.hcl.mdx.database.jdbc.QueryExecutor;
import com.hcl.mdx.util.Constants;


public class IndexPageComposer extends AbstractPageComposer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int PAGE_ID = 1;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	
		HttpSession httpSession = (HttpSession)Sessions.getCurrent().getNativeSession();
        
        if(httpSession.getAttribute(Constants.SESSION_DETAILS_OBJECT_NAME) == null){
        	SessionDetailsObject sessionDetailsObject = new SessionDetailsObject();
        	sessionDetailsObject.setMdxTableMetadata(QueryExecutor.getMDXTableMetadata());
        	httpSession.setAttribute(Constants.SESSION_DETAILS_OBJECT_NAME, sessionDetailsObject);
        }
	}

	public boolean checkSessionParams(){
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
	
	@Override
	public int getPageId() {
		// TODO Auto-generated method stub
		return PAGE_ID;
	}
}
