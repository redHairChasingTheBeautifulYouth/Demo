package concurrence.ReentrantLock;
/*
 *一句话描述该类作用:【可重入锁】
 *@Author:LB
 *
 */

import java.util.concurrent.locks.ReentrantLock;

public class ReenterLock implements Runnable{

    private static ReentrantLock lock = new ReentrantLock();
    private static int i = 0;
    @Override
    public void run() {
        for (int j=0;j<1000000;j++) {
            //叫可重入锁的原因
            lock.lock();
            lock.lock();
            try {
                i++;
            }finally {
                lock.unlock();
                lock.unlock();
            }
        }
    }

    public static void main(String[] arges){
        ReenterLock  t = new ReenterLock();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i);
    }
}
