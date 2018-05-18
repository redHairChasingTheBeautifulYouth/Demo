package concurrence.ReentrantLock;
/*
 *一句话描述该类作用:【】
 *@Author:LB
 *
 */

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TimeLock implements Runnable{
    public static ReentrantLock lock = new ReentrantLock();


    @Override
    public void run() {
        try{
            //等待3秒没有拿到锁就返回false
            if (lock.tryLock(3 ,TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + " 睡觉");
                Thread.sleep(4000);
            }else {
                System.out.println(Thread.currentThread().getName() + " 没有得到锁");
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (lock.isHeldByCurrentThread()) {
                System.out.println(Thread.currentThread().getName() + " 中断响应了");
                lock.unlock();
            }
        }
    }

    public static void main(String[] arges){
        TimeLock t = new TimeLock();
        Thread t1 = new Thread(t);
        t1.setName("T1");
        Thread t2 = new Thread(t);
        t2.setName("T2");
        t1.start();
        t2.start();
    }
}
