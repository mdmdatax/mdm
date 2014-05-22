package com.hcl.mdx.zk.ui.event.listener;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Auxhead;
import org.zkoss.zul.Auxheader;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;

import com.hcl.mdx.database.objects.CanonicalColumnObject;
import com.hcl.mdx.file.data.ColumnToColumnMapObject;
import com.hcl.mdx.file.data.TableAndClmMapDetailsObject;
import com.hcl.mdx.zk.data.standardize.qs.QualityStageStandardizationAdapter;
import com.hcl.mdx.zk.ui.composer.StandardizeDataPageComposer;

public class OnClickEventListenerForQSStdizationExecution extends AbstractEventListener{
	
	public OnClickEventListenerForQSStdizationExecution(){
	}
	
	@Override
	public void onEvent(Event event) throws Exception {
		try{
			Button button = (Button) event.getTarget();
								
			Tabbox mapDetailsParentTabBox = 
				(Tabbox) button.getParent(). /*aux header*/
								getParent(). /*aux head*/
								getParent(). /*grid*/
								getParent(). /*tab panel*/
								getParent(). /*tab panels*/
								getParent(); /*tab box*/

			Tabpanel inputTabPanel = (Tabpanel) mapDetailsParentTabBox.getTabpanels().getChildren().get(0);
			Tabpanel outputTabPanel = (Tabpanel) mapDetailsParentTabBox.getTabpanels().getChildren().get(1);

			Grid inputMapGrid = (Grid) inputTabPanel.getChildren().get(0);
			Grid outputMapGrid = (Grid) outputTabPanel.getChildren().get(0);
			
			
			Auxhead inputGridAuxhead = (Auxhead) inputMapGrid.getChildren().get(2);
			Auxheader inputGridAuxheader1 = (Auxheader) inputGridAuxhead.getChildren().get(0);
			Combobox srcTableSelectionBox = (Combobox) inputGridAuxheader1.getChildren().get(1);
			Auxheader inputGridAuxheader2 = (Auxheader) inputGridAuxhead.getChildren().get(1);
			Combobox srcTableKeyClmSelectionBox = (Combobox) inputGridAuxheader2.getChildren().get(1);
			
			Auxhead outputGridAuxhead = (Auxhead) inputMapGrid.getChildren().get(2);
			Auxheader outputGridAuxheader1 = (Auxheader) outputGridAuxhead.getChildren().get(0);
			Combobox targetTableSelectionBox = (Combobox) outputGridAuxheader1.getChildren().get(1);
			Auxheader outputGridAuxheader2 = (Auxheader) outputGridAuxhead.getChildren().get(1);
			Combobox targetTableKeyClmSelectionBox = (Combobox) outputGridAuxheader2.getChildren().get(1);

			if((srcTableSelectionBox.getSelectedItem() == null) || 
					(targetTableSelectionBox.getSelectedItem() == null)){
				throw new Exception("MDXStdizationConfigException: Cannot proceed without selecting a source/target table.");
			}
			else{			
				if((srcTableKeyClmSelectionBox.getSelectedItem() == null) ||
						(targetTableKeyClmSelectionBox.getSelectedItem() == null)){
					throw new Exception("MDXStdizationConfigException: Cannot proceed without selecting a key column for the source/target table.");
				}
				else{
					ArrayList<ColumnToColumnMapObject>  inputMapDetails = parseMapDetailsGrid(inputMapGrid, "Input");
					ArrayList<ColumnToColumnMapObject>  outputMapDetails = parseMapDetailsGrid(outputMapGrid, "Output");
					
										
					if((inputMapDetails == null) || (outputMapDetails == null)){
						throw new Exception("MDXStdizationConfigException: " +
								"Cannot proceed without setting at least one column-map each" +
								" for the source/target.");
					}
					else{
											
						TableAndClmMapDetailsObject srcTableAndClmMapDetailsObject = 
							new TableAndClmMapDetailsObject(
									(String) srcTableSelectionBox.getSelectedItem().getValue(), inputMapDetails);
						srcTableAndClmMapDetailsObject.setKeyColumnName(
								srcTableKeyClmSelectionBox.getSelectedItem().getValue().toString());
						TableAndClmMapDetailsObject targetTableAndClmMapDetailsObject = 
							new TableAndClmMapDetailsObject(
									(String) targetTableSelectionBox.getSelectedItem().getValue(), outputMapDetails);
						targetTableAndClmMapDetailsObject.setKeyColumnName(targetTableKeyClmSelectionBox.getSelectedItem().getValue().toString());
						
						QualityStageStandardizationAdapter dataStandardizationAdapter = new QualityStageStandardizationAdapter(srcTableAndClmMapDetailsObject, targetTableAndClmMapDetailsObject);
						dataStandardizationAdapter.setEntityToStandardize(dataStandardizationAdapter.getEnumValueOfEntity((String) mapDetailsParentTabBox.getAttribute("SELECTED_ENTITY")));
						StandardizeDataPageComposer pageComposer = (StandardizeDataPageComposer) mapDetailsParentTabBox.getAttribute("PAGE_COMPOSER");
						pageComposer.doStandardization(srcTableAndClmMapDetailsObject, targetTableAndClmMapDetailsObject, dataStandardizationAdapter);
					}
				}
			}
		}
		catch (Exception e) {
				Messagebox.show(
						e.getMessage(), "Error!", 
						Messagebox.OK, 
						Messagebox.ERROR,
						null);
		}
	}

	private ArrayList<ColumnToColumnMapObject> parseMapDetailsGrid(
			Grid mapDetailsGrid,
			String gridType
	) throws Exception{
		try{
			Rows rowsObj = mapDetailsGrid.getRows();
			List rows = rowsObj.getChildren();

			ArrayList<ColumnToColumnMapObject> columns = new ArrayList<ColumnToColumnMapObject>();
			boolean NO_MAPS_PRESENT_FOR_STDIZATION = true;

			for(int counter = 0; counter < rows.size(); counter++){
				Row nextRow = (Row)rows.get(counter);
				List rowElements = nextRow.getChildren();

				Div qualityStageColumnDiv = (Div)rowElements.get(0);
				Div mdxTableColumnDiv = (Div)rowElements.get(1);

				Label qualityStageColumnLabel = (Label)qualityStageColumnDiv.getChildren().get(0);
				String nextColumnName = qualityStageColumnLabel.getValue();
				Combobox mdxTableColumnComboBox = (Combobox)mdxTableColumnDiv.getChildren().get(0);
				String nextComboboxValue = getComboBoxValue(mdxTableColumnComboBox); 

				if(nextComboboxValue != null){								 						  											 									
					NO_MAPS_PRESENT_FOR_STDIZATION = false;
					ColumnToColumnMapObject mapObject = 
						new ColumnToColumnMapObject(
								nextColumnName, 
								new CanonicalColumnObject(nextComboboxValue, null, null));

					columns.add(mapObject);
				}

			}

			if(NO_MAPS_PRESENT_FOR_STDIZATION){
				return null;
			}
			else{
				return columns;
			}
		}
		catch(Exception e){
			throw new Exception("MDXStdizationConfigException: No column maps set for the " +gridType+
			". Set maps and try again.");
		}
	}

	/**
	 * Fetches the selected value of the combobox. If the value is null, it returns null.
	 */
	String getComboBoxValue(Combobox combobox){
		try{
			return combobox.getSelectedItem().getValue().toString();
		}
		catch(Exception e){
			return null;
		}
	} 
}

