package fourReference;
/*
 *一句话描述该类作用:【虚引用】
 *@Author:LB
 *
 */

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class PhantReferencetest {
    public static void main(String[] arges){
        ReferenceQueue<String> queue = new ReferenceQueue<String>();
        PhantomReference<String> pr = new PhantomReference<String>(new String("hello"), queue);
        System.out.println(pr.get());
    }
}
