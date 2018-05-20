package concurrence.daemonThread;
/*
 *一句话描述该类作用:【守护线程--若主线程执行完，则随之退出】
 *@Author:LB
 *
 */

public class DaemonDemo {

    public static class DaemonT implements Runnable{

        @Override
        public void run() {
            while (true) {
                System.out.println("I am alive");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] arges){
        Thread t = new Thread(new DaemonT());
        t.setDaemon(true);
        t.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
