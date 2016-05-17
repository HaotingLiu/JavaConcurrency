package edu.ankur.concurrency;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorsHandler {
	
private static final int NO_OF_TASKS = 100;
	
	public static void main(String [] a){
		
		//Create a concurrency handler first
		ExecutorService executorSerivce = Executors.newFixedThreadPool(10);
		ArrayList<Future<?>> futures = new ArrayList<Future<?>>();
		
		//Create 100 tasks fist;
		TaskRunnable [] tasks = new TaskRunnable[NO_OF_TASKS];
		//TaskCallable [] tasks = new TaskCallable[NO_OF_TASKS];
		
		for(int i = 0 ; i < NO_OF_TASKS ; ++i)
			//tasks[i] = new TaskCallable();
			tasks[i] = new TaskRunnable();
		
		System.out.println("Created " + NO_OF_TASKS + " tasks.. Submitting..");
		
		for(int i = 0 ; i < NO_OF_TASKS ; ++i)
			futures.add(executorSerivce.submit(tasks[i]));
		
		System.out.println("Finished submission. Waiting for threadpool to finish..");
		
		//now, wait for each of them to finish and then print out their ids...
		int completedTasks = 0;
		do {
			for(int i = 0 ; i < NO_OF_TASKS ; ++i){
				try {
					if(!futures.get(i).get().equals(0))
						System.out.println("the result of this thread is" + futures.get(i).get());
						completedTasks++;
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
			
		} while(completedTasks < NO_OF_TASKS);
		
		//now shutdown the executors.
		executorSerivce.shutdown();
		System.out.println("Shutdown executors successfully");
	}
}
