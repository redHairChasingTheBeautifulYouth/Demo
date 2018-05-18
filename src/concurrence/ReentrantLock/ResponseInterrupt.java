package concurrence.ReentrantLock;
/*
 *一句话描述该类作用:【】
 *@Author:LB
 *
 */

import java.util.concurrent.locks.ReentrantLock;

public class ResponseInterrupt implements Runnable{

    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();
    int lock;
    public ResponseInterrupt(int lock){
        this.lock = lock;
    }
    @Override
    public void run() {
        try{
            if (lock == 1){
                lock1.lockInterruptibly();
                Thread.sleep(500);
                lock2.lockInterruptibly();
                System.out.println(Thread.currentThread().getName() + "得到lock2");
            }else {
                lock2.lockInterruptibly();
                Thread.sleep(500);
                lock1.lockInterruptibly();
                System.out.println(Thread.currentThread().getName() + "得到lock1");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
                System.out.println(Thread.currentThread().getName() + "释放锁lock1");
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
                System.out.println(Thread.currentThread().getName() + "释放锁lock2");
            }
        }
    }

    public static void main(String[] arges){
        ResponseInterrupt r1 = new ResponseInterrupt(1);
        ResponseInterrupt r2 = new ResponseInterrupt(2);

        Thread t1 = new Thread(r1);
        t1.setName("T1");
        Thread t2 = new Thread(r2);
        t2.setName("T2");

        t1.start();
        t2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.interrupt();
    }
}
