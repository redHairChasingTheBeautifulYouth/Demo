package util;
/*
 *一句话描述该类作用:【】
 *@Author:LB
 *
 */

import java.io.Closeable;
import java.io.IOException;

public class IOCloseUtil {
    /**
     * 关闭功能:可以关闭传入的多个流
     * 参数：可以接收多个流
     * --》数组实现-》为了灵活性-》可变参数类型
     * 元素类型-》Closeable
     */
    public static void close(Closeable... clsObjs){
        for (Closeable closeable : clsObjs) {
            if(closeable!=null){
                try {
                    closeable.close();
                    System.err.println("关闭了："+closeable);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
