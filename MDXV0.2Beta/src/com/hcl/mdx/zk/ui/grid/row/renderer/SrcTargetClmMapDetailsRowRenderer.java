package com.hcl.mdx.zk.ui.grid.row.renderer;

import java.util.ArrayList;
import java.util.Hashtable;

import org.zkoss.zul.Div;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

import com.hcl.mdx.data.model.DataLoadClmMapperObject;
import com.hcl.mdx.util.Constants;
import com.hcl.mdx.zk.ui.renderers.ComboboxRenderer;
import com.hcl.mdx.zk.ui.renderers.LabelRenderer;

public class SrcTargetClmMapDetailsRowRenderer implements RowRenderer{

	@Override
	public void render(Row row, Object data) throws Exception {
		DataLoadClmMapperObject clmMapperObject = (DataLoadClmMapperObject) data;

		LabelRenderer labelRenderer = new LabelRenderer(Constants.CSS_BLACK_LABEL_9px_CLASS, null);
		Div div = new Div();
		div.appendChild(labelRenderer.createLabelWithText((String) clmMapperObject.getSourceColumnName()));
		row.appendChild(div);


		Hashtable<Object, Object> targetClmMap = new Hashtable<Object, Object>();
		ArrayList<String> targetClmList = clmMapperObject.getTargetColumnList();
		if(targetClmList != null){
			for(int counter = 0; counter < targetClmList.size(); counter++){
				targetClmMap.put(targetClmList.get(counter), targetClmList.get(counter));
			}
		}
		ComboboxRenderer comboboxRenderer = new ComboboxRenderer(targetClmMap);
		div = new Div();
		div.appendChild(comboboxRenderer.renderComponent(null));
		row.appendChild(div);

	}

}
