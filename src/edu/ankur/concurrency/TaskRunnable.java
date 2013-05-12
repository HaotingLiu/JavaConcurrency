package edu.ankur.concurrency;

import java.util.Date;

public class TaskRunnable implements Runnable {
	
	private static int runningId;
	private int id;
	
	static {
		runningId = 0;
	}
	
	public TaskRunnable(){
		id = ++runningId;
	}

	@Override
	public void run() {
		
		try {
			Thread.sleep(3000);
			System.out.println("Thread with ID: " + id + " finished execution at " + new Date());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
