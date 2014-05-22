package com.hcl.mdx.task.executor;

import javax.servlet.http.HttpSession;

import com.hcl.mdx.data.model.SessionDetailsObject;
import com.hcl.mdx.task.objects.ProgressMessageObject;
import com.hcl.mdx.task.objects.TaskInput;
import com.hcl.mdx.task.objects.TaskOutput;

public abstract class AbstractTaskExecutor implements TaskExecutor{

	ProgressMessageObject progressMessageObject;
	TaskInput taskInput;
	TaskOutput taskOutput;
	SessionDetailsObject sessionDetailsObject;
	HttpSession session;
	
	/**
	 * @return the sessionDetailsObject
	 */
	public SessionDetailsObject getSessionDetailsObject() {
		return sessionDetailsObject;
	}

	/**
	 * @param sessionDetailsObject the sessionDetailsObject to set
	 */
	public void setSessionDetailsObject(SessionDetailsObject sessionDetailsObject) {
		this.sessionDetailsObject = sessionDetailsObject;
	}

	/**
	 * @return the session
	 */
	public HttpSession getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */
	public void setSession(HttpSession session) {
		this.session = session;
	}

	public void setProgressMessageObject(ProgressMessageObject progressMessageObject){
		this.progressMessageObject = progressMessageObject;
	}
	
	public ProgressMessageObject getProgressMessageObject(){
		return progressMessageObject;
	}

	/**
	 * @return the taskInput
	 */
	public TaskInput getTaskInput() {
		return taskInput;
	}

	/**
	 * @param taskInput the taskInput to set
	 */
	public void setTaskInput(TaskInput taskInput) {
		this.taskInput = taskInput;
	}

	/**
	 * @return the taskOutput
	 */
	public TaskOutput getTaskOutput() {
		return taskOutput;
	}

	/**
	 * @param taskOutput the taskOutput to set
	 */
	public void setTaskOutput(TaskOutput taskOutput) {
		this.taskOutput = taskOutput;
	}	
	
}
