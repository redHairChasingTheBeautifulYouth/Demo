package fourReference.SoftReferenceExample;
/*
 *一句话描述该类作用:【】
 *@Author:LB
 *
 */

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Hashtable;

public class EmployeeCache {
    static private EmployeeCache cache;// 一个Cache实例
    private Hashtable< String,EmployeeRef> employeeRefs;// 用于Chche内容的存储
    private ReferenceQueue< Employee> q;// 垃圾Reference的队列

    // 继承SoftReference，使得每一个实例都具有可识别的标识。
    private class EmployeeRef extends SoftReference< Employee> {
        private String _key = "";
        public EmployeeRef(Employee em, ReferenceQueue< Employee> q) {
            super(em, q);
            _key = em.getId();
        }
    }

    // 构建一个缓存器实例
    private EmployeeCache() {
        employeeRefs = new Hashtable<String,EmployeeRef>();
        q = new ReferenceQueue<Employee>();
    }

    // 依据所指定的ID号，重新获取相应Employee对象的实例
    public Employee getEmployee(String ID) {
        Employee em = null;
        // 缓存中是否有该Employee实例的软引用，如果有，从软引用中取得。
        if (employeeRefs.containsKey(ID)) {
            EmployeeRef ref = (EmployeeRef) employeeRefs.get(ID);
            em = (Employee) ref.get();
        }
        // 如果没有软引用，或者从软引用中得到的实例是null，重新构建一个实例，
        // 并保存对这个新建实例的软引用
        if (em == null) {
            em = new Employee(ID);
            System.out.println("Retrieve From EmployeeInfoCenter. ID=" + ID);
           // this.cacheEmployee(em);
        }
        return em;
    }
    private void cleanCache() {
        EmployeeRef ref = null;
        while ((ref = (EmployeeRef) q.poll()) != null) {
            employeeRefs.remove(ref._key);
        }
    }
    // 清除Cache内的全部内容
    public void clearCache() {
        cleanCache();
        employeeRefs.clear();
        System.gc();
        System.runFinalization();
    }
}
