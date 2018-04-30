package fourReference;

/*
 *一句话描述该类作用:【强引用】
 *@Author:LB
 *
 */

public class StrongReference {

    public void strongReference() {
        /**
         * 如果内存不足，JVM会抛出OOM错误也不会回收object指向的对象。不过要注意的是，当strongReference运行完之后，object都已经不存在了，
         * 所以它们指向的对象都会被JVM回收。
         * 如果想中断强引用和某个对象之间的关联，可以显示地将引用赋值为null，这样一来的话，JVM在合适的时间就会回收该对象
         */
        Object obj = new Object();
    }
}
