package archiver.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 压缩
 */
public class ZipDemo {

    public static void main(String[] args) throws Exception {
        //文件输出流
        FileOutputStream fos = new FileOutputStream("d:/arch/xx.ar");
        //压缩流
        ZipOutputStream zos = new ZipOutputStream(fos);

        String[] arr = {"d:/1.jpg","d:/2.jpg","d:/3.jpg"};

        for (String str : arr){
            addFile(zos,str);
        }
       /* zos.putNextEntry(new ZipEntry("1.png"));
        zos.write();*/
       zos.close();
       fos.close();
       System.out.println("over");
    }

    /**
     * 循环向zos添加条目
     * @param zos
     * @param path
     */
    public static void addFile(ZipOutputStream zos ,String path ) throws Exception {
        File f = new File(path);

        zos.putNextEntry(new ZipEntry(f.getName()));
        FileInputStream fis = new FileInputStream("d:/arch/c.jpg");
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);
        fis.close();

        zos.write(bytes);
        zos.closeEntry();
    }
}
