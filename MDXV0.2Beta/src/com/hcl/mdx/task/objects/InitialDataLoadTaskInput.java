package com.hcl.mdx.task.objects;

import com.hcl.mdx.data.model.FlatFileDetails;
import com.hcl.mdx.file.data.TableAndClmMapDetailsObject;

public class InitialDataLoadTaskInput extends AbstractTaskInput{

	String loadId;
	
	FlatFileDetails flatFileDetails;
	
	TableAndClmMapDetailsObject targetDetailsObject;

	public InitialDataLoadTaskInput(
			String loadId,
			FlatFileDetails flatFileDetails, 
			TableAndClmMapDetailsObject targetDetailsObject){
		this.loadId = loadId;
		this.flatFileDetails = flatFileDetails;
		this.targetDetailsObject = targetDetailsObject;
	}
	
	
	/**
	 * @return the loadId
	 */
	public String getLoadId() {
		return loadId;
	}


	/**
	 * @param loadId the loadId to set
	 */
	public void setLoadId(String loadId) {
		this.loadId = loadId;
	}


	/**
	 * @return the flatFileDetails
	 */
	public FlatFileDetails getFlatFileDetails() {
		return flatFileDetails;
	}

	/**
	 * @param flatFileDetails the flatFileDetails to set
	 */
	public void setFlatFileDetails(FlatFileDetails flatFileDetails) {
		this.flatFileDetails = flatFileDetails;
	}

	/**
	 * @return the targetDetailsObject
	 */
	public TableAndClmMapDetailsObject getTargetDetailsObject() {
		return targetDetailsObject;
	}

	/**
	 * @param targetDetailsObject the targetDetailsObject to set
	 */
	public void setTargetDetailsObject(TableAndClmMapDetailsObject targetDetailsObject) {
		this.targetDetailsObject = targetDetailsObject;
	}
	
	
}
