package innerClass;
/*
 *一句话描述该类作用:局部内部类
 *@Author:
 *LB
 *
 */

public class PartInnerClass {

    protected String str = "ee";

    public void innerClass(){
        int a = 10;
        class InnerClass{

        }
        InnerClass innerClass = new InnerClass();
        String s = "ssss";
        new Thread(){
            public void run() {
                System.out.println(a);
            };
        }.start();
    }
}
