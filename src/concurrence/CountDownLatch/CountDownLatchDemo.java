package concurrence.CountDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo implements Runnable{
    static CountDownLatch latch = new CountDownLatch(10);
    static CountDownLatchDemo demo = new CountDownLatchDemo();

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(10)*1000);
            System.out.println("check complete");
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i=0;i<10;i++) {
            exec.submit(demo);
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Fire");
        exec.shutdown();
    }
}
