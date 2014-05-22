package com.hcl.mdx.task.objects;

public abstract class AbstractTaskOutput implements TaskOutput{

	private String errorMessage;

	@Override
	public String getErrorMessageString() {
		return errorMessage;
	}

	@Override
	public void setErrorMessageString(String errorMessage) {
		this.errorMessage = errorMessage;
		
	}
}
