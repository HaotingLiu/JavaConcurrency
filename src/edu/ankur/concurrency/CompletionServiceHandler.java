package edu.ankur.concurrency;

import java.util.ArrayList;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletionServiceHandler {

    private static final int NO_OF_TASKS = 5000;

    public static void main(String[] a) {

        //Create a completion service concurrency handler first
        ExecutorService executorService = Executors.newFixedThreadPool(500);//这个是执行池
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);//这个其实相当于一种装饰模式，这个接口只有这么一个api。。。
        ArrayList<Future<?>> futures = new ArrayList<Future<?>>();

        //Create 100 tasks fist;
        TaskCallable[] tasks = new TaskCallable[NO_OF_TASKS];//这里注意一点：CompletionService只能使用Future，意思就是只能使用Callable,而不能使用

        for (int i = 0; i < NO_OF_TASKS; ++i)
            tasks[i] = new TaskCallable();//初始化所有的任务

        System.out.println("Created " + NO_OF_TASKS + " tasks.. Submitting..");

        for (int i = 0; i < NO_OF_TASKS; ++i)
            completionService.submit(tasks[i]);//提交任务

        System.out.println("Finished submission. Waiting for threadpool to finish..");

        //now, wait for each of them to finish and then print out their ids...
        int completedTasks = 0;
        do {
            try {
                System.out.println("Finished task with ID: " + completionService.take().get());
                completedTasks++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        } while (completedTasks < NO_OF_TASKS);

        executorService.shutdown();
        System.out.println("Shutdown executors successfully");

    }
}
