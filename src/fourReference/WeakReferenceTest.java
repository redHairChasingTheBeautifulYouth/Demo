package fourReference;
/*
 *一句话描述该类作用:【弱引用】
 *@Author:LB
 *
 */

import java.lang.ref.WeakReference;

public class WeakReferenceTest {
    public static void main(String[] arges) {

        WeakReference<People> reference = new WeakReference<>(new People("zhouqian", 20));
        System.out.println(reference.get());
        System.gc();//通知GVM回收资源
        //如果存在强引用同时与之关联，则进行垃圾回收时也不会回收该对象
        System.out.println(reference.get());
    }
}

class People{
    public String name;
    public int age;
    public People(String name,int age) {
        this.name=name;
        this.age=age;
    }
    @Override
    public String toString() {
        return "[name:"+name+",age:"+age+"]";
    }
}
