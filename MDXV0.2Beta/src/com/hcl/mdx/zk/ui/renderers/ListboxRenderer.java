package com.hcl.mdx.zk.ui.renderers;

import java.util.ArrayList;
import java.util.HashMap;

import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Listheader;

import com.hcl.mdx.data.model.AbstractModelObject;

public class ListboxRenderer implements ComponentRenderer{


	ArrayList<String> listOfColumns; 
	String columnLabelStyleClass;
	ListitemRenderer listitemRenderer;
	ArrayList<AbstractModelObject> listboxData; 
	String columnHeaderCellStyleClass;
	HashMap<String, String> columnSortPropertyMap;

	public ListboxRenderer(
			ArrayList<String> listOfColumns, 
			String columnLabelStyleClass,
			ListitemRenderer listitemRenderer,
			ArrayList<AbstractModelObject> listboxData, 
			String columnHeaderCellStyleClass,
			HashMap<String, String> columnSortPropertyMap){
		this.listboxData = listboxData;
		this.listitemRenderer = listitemRenderer;
		this.columnHeaderCellStyleClass = columnHeaderCellStyleClass;
		this.listOfColumns = listOfColumns;
		this.columnSortPropertyMap = columnSortPropertyMap;
		this.columnLabelStyleClass = columnLabelStyleClass;
	}
	
	@Override
	public AbstractComponent renderComponent(String componentId) {
		Listbox listbox = new Listbox();
		listbox.setFixedLayout(true);
		listbox.setMold("paging");
		listbox.setPageSize(10);

		LabelRenderer labelRenderer = new LabelRenderer(columnLabelStyleClass, null);
		Listhead listHead = new Listhead();

		listHead.setSizable(true);
		for(int colCounter = 0; colCounter < listOfColumns.size();colCounter++){
		Listheader listheader = new Listheader();
		listheader.appendChild(labelRenderer.createLabelWithText((String) listOfColumns.get(colCounter)));

			if(columnHeaderCellStyleClass != null){
				listheader.setSclass(columnHeaderCellStyleClass);
			}

			/*if(columnSortPropertyMap != null){
			String sortPropertyName = columnSortPropertyMap.get(listOfColumns.get(colCounter));

			if(sortPropertyName != null){
				column.setSort("auto("+sortPropertyName+")");
			}
			else{*/
			listheader.setSort("auto");
			/*}
		}*/

			listHead.appendChild(listheader);
		}
		listbox.appendChild(listHead);

		listbox.setItemRenderer(listitemRenderer);
		if(listboxData != null){
			listbox.setModel(new ListModelList(listboxData));
		}
		else{
			listbox.setModel(new ListModelList());
		}
		return listbox;

	}
}