package fourReference;

/*
 *一句话描述该类作用:【软引用】
 *@Author:LB
 *
 */

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class SoftReferenceTest {
    /**
     * @Author LB
     * @Description 【如果内存空间不足了，就会回收这些对象的内存。只要垃圾回收器没有回收它，该对象就可以被程序使用
     * 软引用可用来实现内存敏感的高速缓存,比如网页缓存、图片缓存等。使用软引用能防止内存泄露，增强程序的健壮性。
     *  SoftReference的特点是它的一个实例保存对一个Java对象的软引用， 该软引用的存在不妨碍垃圾收集线程对该Java对象的回收。
     *  一旦垃圾线程回收该Java对象之 后，get()方法将返回null
     * 】
     * @Param
     *
     */
    public void softReference() {
        Object obj = new Object();
        SoftReference soft = new SoftReference(obj);
        //obj赋值为null,只有软引用
        obj = null;
        //重新获得强引用
        Object newObj = (Object) soft.get();

        List<String> list = new ArrayList<>();
        ReferenceQueue referenceQueue = new ReferenceQueue();
        //ReferenceQueue中保存的对象是Reference对象，而且是已经失去了它所软引用的对象的Reference对象
        SoftReference softReference = new SoftReference(list ,referenceQueue);
        list = null;
        //在恰当的时机清楚无效的软引用，避免太多的SoftReference对象,当我们调用它的poll()方法的时候，如果这个队列中不是空队列，
        // 那么将返回队列前面的那个Reference对象
        Boolean ref = null;
        while (ref = (List)referenceQueue.poll() != null){
        }
    }
}
