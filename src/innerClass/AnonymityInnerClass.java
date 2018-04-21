package innerClass;
/*
 *一句话描述该类作用:匿名内部类
 *@Author:
 *LB
 *
 */

public class AnonymityInnerClass {

    private Thread th = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("111");
        }
    });
}


