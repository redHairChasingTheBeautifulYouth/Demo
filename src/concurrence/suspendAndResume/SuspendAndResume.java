package concurrence.suspendAndResume;
/*
 *一句话描述该类作用:【线程挂起和继续执行】
 *@Author:LB
 *
 */

public class SuspendAndResume {

    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("T1");
    static ChangeObjectThread t2 = new ChangeObjectThread("T2");

    public static class ChangeObjectThread extends Thread{
        public ChangeObjectThread(String name){
            super.setName(name);
        }
        @Override
        public void run() {
            synchronized (u){
                System.out.println("in"+super.getName());
                //线程挂起，不会释放资源
                Thread.currentThread().suspend();
            }
        }
    }
    public static void main(String[] arges){
        t1.start();
        t2.start();
        //继续执行
        t1.resume();
        t2.resume();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
