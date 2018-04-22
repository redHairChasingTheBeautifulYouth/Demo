package concurrence.callableAndFuture;
/*
 *一句话描述该类作用: 实现callable可以抛出异常且有返回值，runable不行且没有返回值
 *@Author:
 *LB
 *
 */

import java.util.concurrent.Callable;

public class MyCallableTask implements Callable<Integer>{


    @Override
    public Integer call() throws Exception {
        System.out.println("线程正在计算");
        Thread.sleep(5000);
        int sum = 0;
        for(int i=0;i<100;i++){
            sum += i;
        }
        return sum;
    }
}
