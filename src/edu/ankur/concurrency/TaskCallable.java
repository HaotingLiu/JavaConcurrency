package edu.ankur.concurrency;

import java.util.Date;
import java.util.concurrent.Callable;

public class TaskCallable implements Callable<Integer> {
	
	private static int runningId;
	private int id;
	
	static {
		runningId = 0;
	}
	
	public TaskCallable(){
		id = ++runningId;
	}

	@Override
	public Integer call() throws Exception {
		
		Thread.sleep(5000);
		
		return id;
	}

}
