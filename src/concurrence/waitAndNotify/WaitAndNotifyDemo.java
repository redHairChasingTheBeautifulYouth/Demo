package concurrence.waitAndNotify;

public class WaitAndNotifyDemo {
    final static Object obejct = new Object();

    public static class T1 implements Runnable{

        @Override
        public void run() {
            synchronized (obejct) {
                System.out.println(System.currentTimeMillis()+":T1 start");
                try {
                    System.out.println(System.currentTimeMillis()+"T1 wait for object");
                    obejct.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis()+":T1 end");
            }
        }
    }

    public static class T2 implements Runnable{
        @Override
        public void run() {
            synchronized (obejct) {
                System.out.println(System.currentTimeMillis()+":T2 start!notify one thread");
                obejct.notifyAll();
                System.out.println(System.currentTimeMillis()+":T2 end");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args){
        Thread t1 = new Thread(new T1());
        Thread t2 = new Thread(new T2());
        t1.start();
        t2.start();
    }
}
