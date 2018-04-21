package innerClass;
/*
 *一句话描述该类作用:成员内部类
 *@Author:
 *LB
 *
 */

public class MemberInnerClass {

    private String str = "str";

    private void outerMethod(){
        System.out.println("外部方法");
        InnerClass innerClass = new InnerClass();
        innerClass.innerMethod();
        System.out.println(innerClass.str1);
    }



    public class InnerClass{
        private String str1 = "str1";

        private void innerMethod(){
            System.out.println("内部方法"+str);
            outerMethod();
        }
    }
}
