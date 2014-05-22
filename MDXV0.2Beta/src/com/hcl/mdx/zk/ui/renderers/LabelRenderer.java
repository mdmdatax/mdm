package com.hcl.mdx.zk.ui.renderers;

import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zul.Label;

public class LabelRenderer implements ComponentRenderer{

	String sClass;
	String labelText;

	public LabelRenderer(String sClass, String labelText){
		this.sClass = sClass;
		this.labelText = labelText;
	}

	public String getsClass() {
		return sClass;
	}
	public void setsClass(String sClass) {
		this.sClass = sClass;
	}
	public String getLabelText() {
		return labelText;
	}
	public void setLabelText(String labelText) {
		this.labelText = labelText;
	}
	public Label createLabelWithText(String labelTextVal){
		Label label = null;
		label = new Label();
		
		if(labelTextVal == null) {
			labelTextVal = " ";	
			label.setPre(true);
		}
		
		label.setValue(labelTextVal);
		
		label.setSclass(sClass);
		return label;
	}
	@Override
	public AbstractComponent renderComponent(String componentId) {

		Label label = new Label(labelText);
		label.setSclass(sClass);
		return label;
	}


}
