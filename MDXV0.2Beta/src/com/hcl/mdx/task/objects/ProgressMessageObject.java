package com.hcl.mdx.task.objects;

import com.hcl.mdx.util.StopWatch;

public class ProgressMessageObject {

	private int progressPercent = 0;
	private String processStatus;
	private String activityName;
	private String report;
	private StopWatch stopWatch;
	private boolean isCompleted;
	private boolean enteredErrorState;
	
	
	public synchronized boolean isEnteredErrorState() {
		return enteredErrorState;
	}

	public synchronized void setEnteredErrorState(boolean enteredErrorState) {
		this.enteredErrorState = enteredErrorState;
	}

	public ProgressMessageObject(){
		this.stopWatch = new StopWatch();
		stopWatch.start();
	}
	
	public synchronized String getTimeElapsed() {
		return stopWatch.toString();
	}

	public synchronized String getActivityName() {
		return activityName;
	}

	public synchronized void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public synchronized int getProgressPercent() {
		return progressPercent;
	}
	
	public synchronized void setProgressPercent(int progressPercent) {
		this.progressPercent = progressPercent;
	}
	
	public synchronized String getProcessStatus() {
		return processStatus;
	}
	
	public synchronized void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}
	
	public synchronized boolean isCompleted() {
		return isCompleted;
	}
	
	public synchronized void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	
	public synchronized void setStopWatch(StopWatch stopWatch){
		this.stopWatch = stopWatch;
	}

	/**
	 * @return the report
	 */
	public String getReport() {
		return report;
	}

	/**
	 * @param report the report to set
	 */
	public void setReport(String report) {
		this.report = report;
	}
	
	
}
