package com.hcl.mdx.zk.ui.grid.row.renderer;

import org.apache.ddlutils.model.Column;
import org.zkoss.zul.Div;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

import com.hcl.mdx.data.model.SchemaTableColumn;
import com.hcl.mdx.util.Constants;
import com.hcl.mdx.zk.ui.renderers.LabelRenderer;

public class SchemaTableColumnRowRenderer implements RowRenderer{

	@Override
	public void render(Row row, Object data) throws Exception {
		SchemaTableColumn schemaTableColumn = (SchemaTableColumn)data;
		Column underlyingColumn = schemaTableColumn.getColumn();
		
		LabelRenderer labelRenderer = new LabelRenderer(Constants.CSS_BLACK_LABEL_9px_CLASS, null);
		Div div = new Div();
		div.appendChild(labelRenderer.createLabelWithText(underlyingColumn.getName()));
		row.appendChild(div);

		div = new Div();
		div.appendChild(labelRenderer.createLabelWithText(underlyingColumn.getDescription()));
		row.appendChild(div);
		
		div = new Div();
		div.appendChild(labelRenderer.createLabelWithText(underlyingColumn.getType()));
		row.appendChild(div);
		
		div = new Div();
		div.appendChild(labelRenderer.createLabelWithText(underlyingColumn.getSize()));
		row.appendChild(div);
		
		div = new Div();
		div.appendChild(labelRenderer.createLabelWithText(""+underlyingColumn.isPrimaryKey()));
		row.appendChild(div);
		
		div = new Div();
		div.appendChild(labelRenderer.createLabelWithText(""+underlyingColumn.isRequired()));
		row.appendChild(div);
		
		div = new Div();
		div.appendChild(labelRenderer.createLabelWithText(""+underlyingColumn.isAutoIncrement()));
		row.appendChild(div);
		
	}

	
}
