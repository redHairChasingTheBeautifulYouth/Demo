package concurrence.ReentrantLock;
/*
 *一句话描述该类作用:【默认锁是非公平的，公平锁实现成本比较高，维护了一个有序队列，性能比较低下】
 *@Author:LB
 *
 */

import java.util.concurrent.locks.ReentrantLock;

public class FairLock implements Runnable{

    private static ReentrantLock fairLock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            try{
                fairLock.lock();
                System.out.println(Thread.currentThread().getName() + " 获得锁");
            }finally {
                fairLock.unlock();
            }


        }
    }

    public static void main(String[] arges){
        FairLock r1 = new FairLock();
        Thread t1 = new Thread(r1 ,"T1");
        Thread t2 = new Thread(r1 ,"T2");
        t1.start();
        t2.start();
    }
}
