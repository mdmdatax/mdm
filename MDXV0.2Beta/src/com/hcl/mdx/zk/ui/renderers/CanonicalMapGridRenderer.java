package com.hcl.mdx.zk.ui.renderers;

import java.util.ArrayList;
import java.util.Hashtable;

import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;

public class CanonicalMapGridRenderer implements ComponentRenderer{

	ArrayList<String> listOfUserColumns;
	Hashtable<Object, Object> listOfSTGTableColumns;
	
	public CanonicalMapGridRenderer(
			ArrayList<String> listOfUserColumns, 
			Hashtable<Object, Object> listOfSTGTableColumns){
		this.listOfUserColumns = listOfUserColumns;
		this.listOfSTGTableColumns = listOfSTGTableColumns;
	}

	@Override
	public AbstractComponent renderComponent(String componentId) {
		
		Grid columnMapperGrid = null;
		Rows tableRows = null;
		Row nextRow = null;
		Div nextDiv = null;
		Label nextLabel = null;

		Combobox canonicalMapList = null;

		try{	  
			columnMapperGrid = new Grid();
			tableRows = new Rows();

			ComponentRenderer renderer = new ComboboxRenderer(listOfSTGTableColumns);

			for(int counter = 0; counter < listOfUserColumns.size(); counter++){  
				String nextColumnName = listOfUserColumns.get(counter);
				nextRow = new Row();
				nextDiv = new Div();
				nextLabel = new Label(nextColumnName);
				nextLabel.setStyle("font-size:9px;font-weight:bold;");			  
				nextDiv.appendChild(nextLabel);
				nextRow.appendChild(nextDiv);
				nextDiv = new Div();
				canonicalMapList = (Combobox) renderer.renderComponent(null);
				//canonicalMapList.setId("canMapList1");
				nextDiv.appendChild(canonicalMapList);
				nextRow.appendChild(nextDiv);
				tableRows.appendChild(nextRow);
			}

			columnMapperGrid.appendChild(tableRows);
			columnMapperGrid.setMold("paging");
			columnMapperGrid.setPageSize(15);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return columnMapperGrid;
	}


}
