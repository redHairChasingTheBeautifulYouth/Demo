package concurrence.CylicBarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CylicBarrierDemo {

    public static class Soldier implements Runnable{

        private String soldier;
        private CyclicBarrier cyclicBarrier;

        Soldier(String soldier ,CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
            this.soldier = soldier;
        }
        void dowork(){
            try {
                Thread.sleep(Math.abs(new Random().nextInt()%10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务完成");
        }
        @Override
        public void run() {
            try {
                cyclicBarrier.await();
                dowork();
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

    }

    public static class Barrier implements Runnable{
        boolean flag;
        int N;
        public Barrier(boolean flag ,int N){
            this.flag = flag;
            this.N = N;
        }
        @Override
        public void run() {
            if (flag) {
                System.out.println("司令：【士兵" + N + "个，任务完成】");
            }else {
                System.out.println("司令：【士兵" + N + "个，集合完毕】");
                flag = true;
            }

        }
    }

    public static void main(String[] args){
        final int N= 10;
        Thread[] allSoldier = new Thread[N];
        boolean flag = false;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N ,new Barrier(flag ,N));
        System.out.println("集合队伍");
        for (int i=0;i<N;++i) {
            System.out.println("士兵"+i+"报道");
            allSoldier[i] = new Thread(new Soldier( "士兵"+i ,cyclicBarrier));
            allSoldier[i].start();
        }

    }
}

































