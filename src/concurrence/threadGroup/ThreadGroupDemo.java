package concurrence.threadGroup;
/*
 *一句话描述该类作用:【】
 *@Author:LB
 *
 */

public class ThreadGroupDemo implements Runnable{

    public static void main(String[] arges){
        ThreadGroup tg = new ThreadGroup("PrintGroup");
        Thread t1 = new Thread(tg ,new ThreadGroupDemo() ,"T1");
        Thread t2 = new Thread(tg ,new ThreadGroupDemo() ,"T2");
        t1.start();
        t2.start();
        //得到活动线程数，并不精确
        System.out.println(tg.activeCount());
        //打印线程中线程信息
        tg.list();
    }
    @Override
    public void run() {
        String groupAndName = Thread.currentThread().getThreadGroup().getName()
                + "-"
                +Thread.currentThread().getName();
        while (true) {
            System.out.println("i am " + groupAndName);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
