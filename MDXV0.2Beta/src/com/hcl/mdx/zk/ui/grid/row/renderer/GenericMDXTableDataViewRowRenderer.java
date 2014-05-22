package com.hcl.mdx.zk.ui.grid.row.renderer;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.beanutils.DynaBean;
import org.apache.ddlutils.model.Database;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Detail;
import org.zkoss.zul.Div;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

import com.hcl.mdx.data.model.MDXDynaBeanWrapper;
import com.hcl.mdx.database.objects.MDXTableColumnDetailsObject;
import com.hcl.mdx.database.objects.MDXTableMetadata;
import com.hcl.mdx.database.objects.MDXTableRelationshipDetailsObject;
import com.hcl.mdx.util.Constants;
import com.hcl.mdx.zk.ui.event.listener.OnOpenEventListenerForMDXDataGridDetailView;
import com.hcl.mdx.zk.ui.renderers.LabelRenderer;

public class GenericMDXTableDataViewRowRenderer implements RowRenderer{
	private ArrayList<MDXTableColumnDetailsObject> listOfColumns; 
	private ArrayList<MDXTableRelationshipDetailsObject> listOfRelationships;
	private MDXTableMetadata mdxTableMetadata;
	private Database database;
	private String tableNamePrefix;
	private String parentTableName;
	
	private static Logger log = Logger.getLogger("GenericMDXTableDataViewRowRenderer");
	
	public GenericMDXTableDataViewRowRenderer(
			ArrayList<MDXTableColumnDetailsObject> listOfColumns, 
			ArrayList<MDXTableRelationshipDetailsObject> listOfRelationships,
			MDXTableMetadata mdxTableMetadata, 
			Database database,
			String tableNamePrefix,
			String parentTableName){ 
		this.listOfColumns = listOfColumns;
		this.listOfRelationships = listOfRelationships;
		this.mdxTableMetadata = mdxTableMetadata;
		this.database = database;
		this.tableNamePrefix = tableNamePrefix;
		this.parentTableName = parentTableName;
	}

	@Override
	public void render(Row row, Object data) throws Exception {
		
		MDXDynaBeanWrapper dataBeanWrapper = (MDXDynaBeanWrapper) data;		
		DynaBean dataBean = dataBeanWrapper.getDynaBean();
		
		if((listOfRelationships != null) && (listOfRelationships.size() > 0)){
			HashMap<String, Object> listOfParentKeyValuesMap = new HashMap<String, Object>();
			for(int relCounter = 0; relCounter < listOfRelationships.size(); relCounter++){
				String nextParentKeyColumn = listOfRelationships.get(relCounter).getParentColumnName();
				Object nextParentKeyValue = dataBean.get(nextParentKeyColumn);
				
				listOfParentKeyValuesMap.put(nextParentKeyColumn, nextParentKeyValue);
				log.info(relCounter+". "+nextParentKeyColumn+" "+nextParentKeyValue);
			}
			
			Detail detail = new Detail();
			detail.setOpen(false);
			detail.addEventListener(
					Events.ON_OPEN, 
					new OnOpenEventListenerForMDXDataGridDetailView(
							mdxTableMetadata, 
							database, 
							listOfParentKeyValuesMap, 
							tableNamePrefix,
							parentTableName,
							listOfRelationships));
			row.appendChild(detail);
			
		}

		for(int clmCounter = 0; clmCounter < listOfColumns.size(); clmCounter++){
			Object cellData = dataBean.get(listOfColumns.get(clmCounter).getTableColumnName());
			LabelRenderer labelRenderer = new LabelRenderer(Constants.CSS_BLACK_LABEL_9px_CLASS, null);
			Div div = new Div();
			div.appendChild(labelRenderer.createLabelWithText(checkForNull(cellData).toString()));
			row.appendChild(div);
		}
	}

	private String checkForNull(Object value){
		value = (value == null)? "": value;
		return value.toString();
	}
}
