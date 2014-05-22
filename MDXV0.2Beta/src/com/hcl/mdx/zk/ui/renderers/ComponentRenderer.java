package com.hcl.mdx.zk.ui.renderers;

import org.zkoss.zk.ui.AbstractComponent;

public interface ComponentRenderer {

	public AbstractComponent renderComponent(String componentId) throws Exception;
}
