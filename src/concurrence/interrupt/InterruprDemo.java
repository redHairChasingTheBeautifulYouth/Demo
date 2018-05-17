package concurrence.interrupt;

public class InterruprDemo {
    public static void main(String[] args){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Interrupted");
                        break;
                    }
                    try {
                        //sleep()方法会由于中断抛出异常，但会清除中断标记
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted when is sleep");
                        //设置中断异常
                        Thread.currentThread().interrupt();
                    }
                    Thread.yield();
                }
            }
        });
        t1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();

    }
}
