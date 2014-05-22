package com.hcl.mdx.zk.ui.status.renderers;

import org.apache.log4j.Logger;
import org.zkoss.lang.Threads;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.DesktopUnavailableException;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Label;
import org.zkoss.zul.Progressmeter;

import com.hcl.mdx.task.objects.ProgressMessageObject;
import com.hcl.mdx.util.Constants;

public class ProgressRenderer extends Thread{
	
	private static Logger log = Logger.getLogger("ProgressRenderer");
	
	Progressmeter progressMeter;

	Desktop desktop;

	Label statusLabel;
	
	Label timeElapsedLabel;
	
	Label activityLabel;
	
	Label reportLabel;

	ProgressMessageObject messageObject;

	public ProgressRenderer(
			Desktop desktop,
			Progressmeter progressMeter, 
			ProgressMessageObject messageObject,
			Label statusLabel, 
			Label activityLabel, 
			Label timeElapsedLabel,
			Label reportLabel) {
		
		this.progressMeter = progressMeter;
		this.messageObject = messageObject;
		this.statusLabel = statusLabel;
		this.activityLabel = activityLabel;
		this.timeElapsedLabel = timeElapsedLabel;
		this.reportLabel = reportLabel;
		
		this.desktop = desktop;
		
		if (!desktop.isServerPushEnabled()) {
			desktop.enableServerPush(true);
		}
		
	}


	public void updateProgressmeterValue(int count) {
		this.progressMeter.setValue(count);
	}

	@Override
	public void run() {
		try {
			while (!messageObject.isCompleted()) {
				
				Executions.activate(desktop);
				
				Threads.sleep(50);
				
				if(messageObject.getActivityName() != null){
					setActivityMessage(messageObject.getActivityName());
				}
				else{
					setActivityMessage("...");
				}
				
				updateProgressmeterValue(messageObject.getProgressPercent());
				
				if(messageObject.getProcessStatus() != null){
					setStatusMessage(messageObject.getProcessStatus());
				}
				else{
					setStatusMessage("...");
				}
				
				if(messageObject.getTimeElapsed() != null){
					setTimeElapsedMessage(messageObject.getTimeElapsed());
				}
				else{
					setTimeElapsedMessage("...");
				}
				
				if((reportLabel != null) && (messageObject.getReport() != null)){
					setReportMessage(messageObject.getReport());
				}
				
				if(messageObject.isEnteredErrorState()){				
					setErrorMessage(messageObject.getProcessStatus());
				}
				Executions.deactivate(desktop);
			}
			
		}
		catch (DesktopUnavailableException e) {
			Executions.deactivate(desktop);			
		}catch (Exception exc){
			exc.printStackTrace();
		}finally {
			if (desktop.isServerPushEnabled()) {
				desktop.enableServerPush(false);
				Executions.deactivate(desktop);
			}
		}
	}
	public void setStatusMessage(String message){
		this.statusLabel.setSclass(Constants.CSS_BLACK_LABEL_9px_CLASS);
		this.statusLabel.setValue(message);
	}
	public void setActivityMessage(String message){
		this.activityLabel.setValue(message);
	}
	public void setTimeElapsedMessage(String message){
		this.timeElapsedLabel.setValue(message);
	}
	public void setErrorMessage(String message){
		this.statusLabel.setSclass(Constants.CSS_RED_LABEL_9px_CLASS);
		this.statusLabel.setValue(message);
	}
	public void setReportMessage(String message){
		this.reportLabel.setSclass(Constants.CSS_FOREST_GREEN_LABEL_9px_CLASS);
		this.reportLabel.setMultiline(true);
		this.reportLabel.setValue(message);
	}
}

