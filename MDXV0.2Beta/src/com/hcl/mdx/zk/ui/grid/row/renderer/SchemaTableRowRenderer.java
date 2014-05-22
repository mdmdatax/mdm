package com.hcl.mdx.zk.ui.grid.row.renderer;

import org.apache.ddlutils.model.Table;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Detail;
import org.zkoss.zul.Div;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

import com.hcl.mdx.data.model.SchemaTable;
import com.hcl.mdx.util.Constants;
import com.hcl.mdx.zk.ui.event.listener.OnOpenEventListenerForSchemaGrid;
import com.hcl.mdx.zk.ui.renderers.LabelRenderer;

public class SchemaTableRowRenderer implements RowRenderer{

	@Override
	public void render(Row row, Object data) throws Exception {
		Table table = ((SchemaTable)data).getTable();
		
		Detail detail = new Detail();
		detail.setOpen(false);
		detail.addEventListener(
				Events.ON_OPEN, 
				new OnOpenEventListenerForSchemaGrid(null, (SchemaTable) data));
		row.appendChild(detail);

		LabelRenderer labelRenderer = new LabelRenderer(Constants.CSS_BLACK_LABEL_9px_CLASS, null);
		Div div = new Div();
		div.appendChild(labelRenderer.createLabelWithText(table.getName()));
		row.appendChild(div);

		div = new Div();
		div.appendChild(labelRenderer.createLabelWithText(table.getDescription()));
		row.appendChild(div);
		
		div = new Div();
		div.appendChild(labelRenderer.createLabelWithText(table.getCatalog()));
		row.appendChild(div);
	}

}
