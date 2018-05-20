package concurrence.condition;
/*
 *一句话描述该类作用:【可重入锁的等待与通知】
 *@Author:LB
 *
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReenterLockCondition implements Runnable{
    public static ReentrantLock lock = new ReentrantLock();

    public static Condition condition = lock.newCondition();



    @Override
    public void run() {
        lock.lock();
        try {
            //要求线程持有相关的线程锁，调用后，会释放这把锁
            condition.await();
            System.out.println("this thread is going on");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] arges) throws InterruptedException {
        ReenterLockCondition reenterLockCondition = new ReenterLockCondition();
        Thread t1 = new Thread(reenterLockCondition);

        t1.start();
        Thread.sleep(2000);

        lock.lock();
        //要求线程持有相关的线程锁，调用后，会唤醒Condition的等待队列中一个线程，然后释放锁
        condition.signal();
        lock.unlock();
    }
}
