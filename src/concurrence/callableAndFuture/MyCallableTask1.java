package concurrence.callableAndFuture;/*
 *一句话描述该类作用:
 *@Author:
 *LB
 *
 */

import java.util.concurrent.Callable;

public class MyCallableTask1 implements Callable<String>{
    private int id;
    public MyCallableTask1(int id){
        this.id = id;
    }
    @Override
    public String call() throws Exception {
        for(int i = 0;i<5;i++){
            System.out.println("Thread"+ id);
            Thread.sleep(1000);
        }
        return "Result of callable: "+id;

    }
}
