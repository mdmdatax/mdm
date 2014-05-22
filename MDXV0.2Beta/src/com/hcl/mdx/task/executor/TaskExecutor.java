package com.hcl.mdx.task.executor;

import com.hcl.mdx.task.objects.ProgressMessageObject;
import com.hcl.mdx.task.objects.TaskInput;
import com.hcl.mdx.task.objects.TaskOutput;

public interface TaskExecutor extends Runnable {
	
	public TaskInput getTaskInput();
	
	public void setTaskInput(TaskInput input);
	
	public TaskOutput getTaskOutput();
	
	public void setTaskOutput(TaskOutput output);
	
	public void setProgressMessageObject(ProgressMessageObject progressMessageObject);
	
	public ProgressMessageObject getProgressMessageObject();
}
