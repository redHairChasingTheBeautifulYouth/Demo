package archiver.fileUnarchiver;

import archiver.util.Util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 解档器
 */
public class Unarchiver {

    public static void main(String[] args) throws Exception {
        List<FileBean> files = new ArrayList<>();
        FileInputStream fie = new FileInputStream("d:/arch/x.xar");

        FileBean fileBean = null;

        while ((fileBean = readNextFile(fie)) != null) {

            files.add(fileBean);
        }
        fie.close();
        for (FileBean fb : files) {
            FileOutputStream os = new FileOutputStream("d:/unarch/" + fb.getFileName());
            os.write(fb.getFileContent());
            os.close();
        }

    }

    /**
     * 从流中读取下一个文件
     * @param fis
     * @return
     */
    public static FileBean readNextFile(FileInputStream fis) throws IOException {

        byte[] bytes4 = new byte[4];
        //读取4个字节
        int rea = fis.read(bytes4);
        if (rea == -1) {
            return null;
        }
        //文件名长度
        int fnameLen = Util.bytes2Int(bytes4);

        //文件名数组
        byte[] filenameBytes = new byte[fnameLen];
        fis.read(filenameBytes);

        //得到文件名
        String fname = new String(filenameBytes);

        //再读取四个字节，作为文件内容的长度
        fis.read(bytes4);
        int fileContLen = Util.bytes2Int(bytes4);

        //读取文件内容
        byte[] fileContBytes = new byte[fileContLen];
        fis.read(fileContBytes);
        return new FileBean(fname ,fileContBytes);
    }
}

