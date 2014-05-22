package com.hcl.mdx.zk.ui.renderers;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;

public class ComboboxRenderer implements ComponentRenderer {

	Hashtable<Object, Object> listOfItems;

	public ComboboxRenderer(Hashtable<Object, Object> listOfItems){
		this.listOfItems = listOfItems; 
	}

	@Override
	public AbstractComponent renderComponent(String componentId) {
		Combobox combobox = new Combobox();

		if(listOfItems != null){
			Vector keyList = new Vector(listOfItems.keySet());
			Collections.sort(keyList);

			for (Enumeration e = keyList.elements(); e.hasMoreElements();){
				String nextKey = (String) e.nextElement();

				Comboitem comboitem = new Comboitem();
				comboitem.setStyle("font-size:6px;font-weight:bold;");
				comboitem.setLabel((listOfItems.get(nextKey)).toString());
				comboitem.setValue(nextKey);

				combobox.appendChild(comboitem);
			}
		}

		return combobox;
	}

}
