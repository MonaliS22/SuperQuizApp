package linkcode.superquizapp;

public class TimeTracker {
    private long startTime;
    private long endTime;

    public void start() { 
    	startTime = System.currentTimeMillis(); 
    	}
    
    public void stop() { 
    	endTime = System.currentTimeMillis(); 
    	}

    public long getElapsedSeconds() { 
    	return (endTime - startTime) / 1000; 
    	} 
    
    public long getElapsedMillis() { 
    	return endTime - startTime;
    	}
}
