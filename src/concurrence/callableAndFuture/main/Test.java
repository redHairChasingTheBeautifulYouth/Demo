package concurrence.callableAndFuture.main;
/*
 *一句话描述该类作用:FutureTask类同时实现了两个接口，Future和Runnable接口，
 * 所以它既可以作为Runnable被线程执行，又可以作为Future得到Callable的返回值
 *@Author:
 *LB
 *
 */

import concurrence.callableAndFuture.MyCallableTask;
import concurrence.callableAndFuture.MyCallableTask1;

import java.util.ArrayList;
import java.util.concurrent.*;

public class Test {
    public static void main(String[] arges){
        //futureTask执行
        Callable<Integer> myCallableTask = new MyCallableTask();
        FutureTask<Integer> futureTask = new FutureTask<>(myCallableTask);
        new Thread(futureTask).start();

        //在callable未完成前做一些事
        while (!futureTask.isDone()){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("主线程干活");

            System.out.println("检测callable任务是否完成");
        }
        try {
            System.out.println("callable任务已完成，结果"+futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        //借助线程池来运行Callable任务
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<Integer> future = exec.submit(new MyCallableTask());
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        //执行多个Callable任务，有多个返回值时，我们可以创建一个Future的集合
        ExecutorService exec1 = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<Future<String>>();

        for (int i = 0; i < 5; i++) {
            results.add(exec1.submit(new MyCallableTask1(i)));
        }

        for (Future<String> fs : results) {
            if (fs.isDone()) {
                try {
                    System.out.println(fs.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("MyCallableTask1任务未完成！");
            }
        }
        exec.shutdown();

    }
}
