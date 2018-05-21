package archiver;
/*
 *一句话描述该类作用:【文件归档】
 *@Author:LB
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Archiver {
    public static void main(String[] arges){
        try (FileOutputStream fos = new FileOutputStream("d:/python/")) {

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] precessFile(String path){
        //文件
        File f = new File(path);
        //文件名
        String fName = f.getName();
        //文件名数组
        byte[] fnameBytes = fName.getBytes();
        //文件内容长度
        int len = (int) f.length();
        //计算总长
        int total = 4 + fName.getBytes().length + 4 + len;

        //初始化总长度
        byte[] bytes = new byte[total];

        //1.写入文件名长度
        byte[] fnameLenArr = Util.int2Bytes(fnameBytes.length);
        System.arraycopy(fnameLenArr,0,bytes,0,4);

        //2.写入文件本身
        System.arraycopy(fnameBytes,0,bytes,4,fnameBytes.length);

        return null;
    }

}
