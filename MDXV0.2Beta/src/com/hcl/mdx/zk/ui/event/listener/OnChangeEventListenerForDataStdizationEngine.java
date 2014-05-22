package com.hcl.mdx.zk.ui.event.listener;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;

import com.hcl.mdx.util.Constants.StandardizationAdapterType;
import com.hcl.mdx.zk.data.standardize.MDXDataStandardizationAdapter;
import com.hcl.mdx.zk.data.standardize.factory.MDXDataStandardizationAdapterFactory;

public class OnChangeEventListenerForDataStdizationEngine extends AbstractEventListener{

	Combobox entitySelectionBox;
	private static Logger log = Logger.getLogger("OnChangeEventListenerForDataStdizationEngine");
	public OnChangeEventListenerForDataStdizationEngine(Combobox entitySelectionBox){
		this.entitySelectionBox = entitySelectionBox;
		
	}
	
	@Override
	public void onEvent(Event event) throws Exception {
		
		Combobox stdizationEngineSelectionBox = (Combobox) event.getTarget();
		
		Comboitem selectedEngine = stdizationEngineSelectionBox.getSelectedItem();
		
		if(selectedEngine != null){
			MDXDataStandardizationAdapter dataStandardizationAdapter = MDXDataStandardizationAdapterFactory.getAdapterInstance(StandardizationAdapterType.valueOf(selectedEngine.getValue().toString()));
			
			Enum[] standardizableEntities = dataStandardizationAdapter.getListOfStdizableEntities();
			
			if(entitySelectionBox.getChildren() != null){
				entitySelectionBox.getChildren().clear();
			}
			
			for(int counter = 0; counter < standardizableEntities.length; counter++){
				Comboitem comboitem = new Comboitem();
				comboitem.setLabel(standardizableEntities[counter].toString());
				comboitem.setValue(standardizableEntities[counter]);
				entitySelectionBox.appendChild(comboitem);
			}
		}
	}

}
