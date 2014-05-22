package com.hcl.mdx.util;

public class StopWatch  {
    
    private long startTime = 0;
    private long stopTime = 0;
    private boolean running = false;

    
    public void start() {
        this.startTime = System.currentTimeMillis();
        this.running = true;
    }

    
    public void stop() {
        this.stopTime = System.currentTimeMillis();
        this.running = false;
    }

    
    //elaspsed time in milliseconds
    public long getElapsedTime() {
        long elapsed;
        if (running)  {
             elapsed = (System.currentTimeMillis() - startTime);
        }
        else  {
            elapsed = (stopTime - startTime);
        }
        return elapsed;
    }
    
    
    //elaspsed time in seconds
    public long getElapsedTimeSecs() {
        long elapsed;
        if (running)  {
            elapsed = ((System.currentTimeMillis() - startTime) / 1000);
        }
        else  {
            elapsed = ((stopTime - startTime) / 1000);
        }
        return elapsed;
    }

    @Override
	public String toString() {
    	long seconds = getElapsedTimeSecs();
    	String timeString = "";
    	if(seconds > 60) {
    		long minutes = seconds/60;
    		//int minutesInt = (int) (seconds/60);
    		if(seconds % 60 == 0) {
    			timeString = minutes  +  " minutes and 0 seconds.";
    		}
    		else {
    			timeString = (int)minutes  +  " Minutes and "  +  (seconds % 60)  +  " seconds"; 
    		}
    	}
    	else {
    		timeString = seconds + " seconds";
    	}
    	
    	return timeString;
    }
}
