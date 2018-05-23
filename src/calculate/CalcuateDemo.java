package calculate;
/*
 *一句话描述该类作用:【与或非计算】
 *@Author:LB
 *
 */

import org.junit.Test;

public class CalcuateDemo {

    @Test
    public void test(){
        //有零为零
        System.out.println(5 & 10);
        //有1位1
        System.out.println(5 | 10);
        System.out.println(-1 | 99994);
        System.out.println(0 & 43423523);
        //取反
        System.out.println(~0);
        System.out.println(~-1);
        System.out.println(~1);
    }
}
