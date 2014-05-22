package com.hcl.mdx.zk.ui.grid.row.renderer;

import org.apache.ddlutils.model.ForeignKey;
import org.zkoss.zul.Div;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

import com.hcl.mdx.data.model.SchemaTableForeignKey;
import com.hcl.mdx.util.Constants;
import com.hcl.mdx.zk.ui.renderers.LabelRenderer;

public class SchemaTableFKeyRowRenderer implements RowRenderer{

	@Override
	public void render(Row row, Object data) throws Exception {
		ForeignKey foreignKey = ((SchemaTableForeignKey) data).getForeignKey();
		
		LabelRenderer labelRenderer = new LabelRenderer(Constants.CSS_BLACK_LABEL_9px_CLASS, null);
		Div div = new Div();
		div.appendChild(labelRenderer.createLabelWithText(foreignKey.getForeignTableName()));
		row.appendChild(div);
		
		div = new Div();
		div.appendChild(labelRenderer.createLabelWithText(""+foreignKey.getFirstReference()));
		row.appendChild(div);

	}

}
