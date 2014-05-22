package com.hcl.mdx.zk.ui.listbox.builder;

import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.ListitemRenderer;

import com.hcl.mdx.zk.data.manager.AbstractDataManager;
import com.hcl.mdx.zk.ui.DataComponentBuilder;
import com.hcl.mdx.zk.ui.renderers.ListboxRenderer;

public abstract class AbstractListboxBuilder implements DataComponentBuilder{

	AbstractDataManager dataManager;

	/**
	 * @return the dataManager
	 */
	public AbstractDataManager getDataManager() {
		return dataManager;
	}

	/**
	 * @param dataManager the dataManager to set
	 */
	public void setDataManager(AbstractDataManager dataManager) {
		this.dataManager = dataManager;
	}

	public void refreshListBox(Listbox listbox){
		if(dataManager.getData() != null){
			listbox.setModel(new ListModelList(dataManager.getData()));
		}
	}

	public abstract Listbox buildListBox();
	
	public Listbox buildListbox(
			String columnsLabelStyleClassString, 
			String columnHeaderCellStyleClass, 
			ListitemRenderer listitemRenderer){
		ListboxRenderer listboxRenderer = new ListboxRenderer(
				dataManager.getListOfDisplayHeaders(), 
				columnsLabelStyleClassString,
				listitemRenderer,	
				dataManager.getData(),
				columnHeaderCellStyleClass,
				dataManager.getColumnSortPropertyMap());
		
		Listbox listbox =  (Listbox) listboxRenderer.renderComponent(null);
				
		return listbox;
	}
}
