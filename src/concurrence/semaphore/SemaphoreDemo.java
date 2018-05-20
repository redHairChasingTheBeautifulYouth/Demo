package concurrence.semaphore;
/*
 *一句话描述该类作用:【信号量控制线程】
 *@Author:LB
 *
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo implements Runnable{

    final Semaphore semaphore = new Semaphore(5);
    @Override
    public void run() {
        try {
            //尝试获取一个准入的许可，若无法获得，线程会等待，直到一个线程释放许可
            semaphore.acquire();
            //模拟耗时操作
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId()+":done");
            //释放许可
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] arges){
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        final SemaphoreDemo demo = new SemaphoreDemo();
        for (int i=0;i<20;i++) {
            executorService.submit(demo);
        }
    }
}
