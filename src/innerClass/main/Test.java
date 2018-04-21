package innerClass.main;
/*
 *一句话描述该类作用:
 *@Author:
 *LB
 *
 */

import innerClass.MemberInnerClass;
import innerClass.PartInnerClass;

public class Test {
    public static void main(String[] arges){
        MemberInnerClass memberInnerClass = new MemberInnerClass();
        //要new成员内部类对象依赖外部类
        MemberInnerClass.InnerClass innerClass = memberInnerClass.new InnerClass();


        PartInnerClass partInnerClass = new PartInnerClass();


    }
}
