package archiver.zip;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 解压缩
 */
public class UnzipDemo {
     public void unZip() throws Exception {
         FileInputStream fis = new FileInputStream("d:/x.zip");
         ZipInputStream zis = new ZipInputStream(fis);

         //
         ZipEntry entry = null;
         byte[] buf = new byte[1024];
         int len = 0;
         while (zis.getNextEntry() != null) {
             String name = entry.getName();
             FileOutputStream fos = new FileOutputStream("d:/arch/unarch/" + name);
             while ((len = zis.read(buf)) != -1) {
                 fos.write(buf,0,len);
             }
             fos.close();
         }
         zis.close();
         fis.close();
     }
}
