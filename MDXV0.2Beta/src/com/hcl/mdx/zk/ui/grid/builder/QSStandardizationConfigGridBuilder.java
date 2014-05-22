package com.hcl.mdx.zk.ui.grid.builder;

import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.ddlutils.model.Database;
import org.apache.ddlutils.model.Table;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Auxhead;
import org.zkoss.zul.Auxheader;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Panel;
import org.zkoss.zul.RowRenderer;

import com.hcl.mdx.util.Constants;
import com.hcl.mdx.zk.ui.event.listener.OnChangeEventListenerForMDXDataStdizationTableList;
import com.hcl.mdx.zk.ui.event.listener.OnClickEventListenerForQSStdizationExecution;
import com.hcl.mdx.zk.ui.renderers.ComboboxRenderer;
import com.hcl.mdx.zk.ui.renderers.LabelRenderer;

public class QSStandardizationConfigGridBuilder extends StandardizationConfigGridBuilder{


	private static Logger log = Logger.getLogger("QSStandardizationConfigGridBuilder");

	public QSStandardizationConfigGridBuilder(Database database) {
		super(database);
	}
	
	@Override
	public Grid buildGrid() throws Exception {
		return null;
	}

	@Override
	public Grid buildGrid(
			String columnsLabelStyleClassString, 
			String columnHeaderCellStyleClass, 
			RowRenderer rowRenderer,
			boolean childGridsPresent) throws Exception{
		Grid grid = super.buildGrid(columnsLabelStyleClassString, columnHeaderCellStyleClass, rowRenderer, childGridsPresent);
		addAuxHeader(grid);
		return grid;
	}
	private void addAuxHeader(Grid grid){
		Auxhead auxhead = new Auxhead();
		Auxheader auxheader = new Auxheader();
		auxheader.setColspan(1);
		auxhead.appendChild(auxheader);
		auxheader = new Auxheader();
		auxheader.setColspan(1);
		Button button = new Button();
		button.setSclass("formButton");
		button.setImage(Constants.RUN_OPERATION_IMG_LOCATION);
		button.setTooltiptext("Run Standardization Routines");
		button.addEventListener(Events.ON_CLICK, 
				new OnClickEventListenerForQSStdizationExecution());
		auxheader.appendChild(button);
		auxhead.appendChild(auxheader);
		grid.insertBefore(auxhead, grid.getColumns());

		auxhead = new Auxhead();
		auxheader = new Auxheader();
		auxheader.setColspan(1);
		auxheader.appendChild(new LabelRenderer(Constants.CSS_BLACK_LABEL_9px_CLASS, null).createLabelWithText("Select Table: "));
		/*
		 * Build list of tables. 
		 */
		Hashtable<Object, Object> targetClmMap = new Hashtable<Object, Object>();
		Table[] tables = database.getTables();
		for(int counter = 0; counter < tables.length; counter++){
			String name = tables[counter].getName();

			/*
			 * Add only MST_ tables to list.
			 */
			if((name.substring(0, 4) != null) && 
					(name.substring(0, 4).compareToIgnoreCase(Constants.MASTER_TABLE_PREFIX+"_") == 0)){
				targetClmMap.put(name, name);

			}
		}
		ComboboxRenderer comboboxRenderer = new ComboboxRenderer(targetClmMap);
		Combobox combobox = (Combobox) comboboxRenderer.renderComponent(null);
		combobox.addEventListener(Events.ON_CHANGE, new OnChangeEventListenerForMDXDataStdizationTableList(database));
		auxheader.appendChild(combobox);
		auxhead.appendChild(auxheader);

		auxheader = new Auxheader();
		auxheader.setColspan(1);
		auxheader.appendChild(new LabelRenderer(Constants.CSS_BLACK_LABEL_9px_CLASS, null).createLabelWithText("Key Column: "));
		auxheader.appendChild(new ComboboxRenderer(null).renderComponent(null));

		auxhead.appendChild(auxheader);

		grid.insertBefore(auxhead, grid.getColumns());

	}
}
