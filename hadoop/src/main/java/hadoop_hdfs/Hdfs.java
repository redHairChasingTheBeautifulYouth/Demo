package hadoop_hdfs;
/*
 *一句话描述该类作用:【文件系统】
 *@Author:LB
 *
 */

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import util.IOCloseUtil;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Hdfs {



    public final static String hostName="node1";
    public final static String uri = "hdfs://"+hostName+":9000";

    /**
     * @Author LB
     * @Description 【创建一个文件夹在hdfs,注意权限，若需要需修改hadoop权限】
     * @Param
     *
     */
    public static boolean mkdir(String dir) throws IOException {
        if (StringUtils.isBlank(dir)) {
            return false;
        }
        dir = uri + dir;
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dir), conf);
        if (!fs.exists(new Path(dir))) {
            fs.mkdirs(new Path(dir));
        }
        fs.close();
        return true;
    }

    /**
     * @Author LB
     * @Description 【删除一个hdfs的目录，如果有子目录-递归删除！】
     * @Param
     *
     */
    public static boolean deleteDir(String dir,boolean delChird) throws IOException {
        if (StringUtils.isBlank(dir)) {
            return false;
        }
        dir = uri + dir;
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dir), conf);
        fs.delete(new Path(dir), delChird);
        fs.close();
        return true;
    }

    /**
     * @Author LB
     * @Description 【查看所有子文件名字】
     * @Param
     *
     */
    public static List<String> listAllName(String dir) throws IOException {
        if (StringUtils.isBlank(dir)) {
            return new ArrayList<String>();
        }
        dir = uri + dir;
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dir), conf);
        FileStatus[] stats = fs.listStatus(new Path(dir));
        List<String> names = new ArrayList<String>();
        for (int i = 0; i < stats.length; ++i) {
            if (stats[i].isFile()) {
                // regular file
                names.add(stats[i].getPath().toString());
            } else if (stats[i].isDirectory()) {
                // dir
                names.add(stats[i].getPath().toString());
            } else if (stats[i].isSymlink()) {
                // is s symlink in linux
                names.add(stats[i].getPath().toString());
            }
        }

        fs.close();
        return names;
    }


    /**
     * @Author LB
     * @Description 【查看目录所有的子文件信息】
     * @Param
     *
     */
    public static FileStatus[] listAllStatus(String dir) throws IOException {
        if (StringUtils.isBlank(dir)) {
            return new FileStatus[0];
        }
        dir = uri + dir;
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dir), conf);
        FileStatus[] stats = fs.listStatus(new Path(dir));
        fs.close();
        return stats;
    }

    /**
     * @Author LB
     * @Description 【上传文件】
     * @Param
     *
     */
    public static boolean uploadLocalFile2HDFS(String localFile, String hdfsFile) throws IOException {
        if (StringUtils.isBlank(localFile) || StringUtils.isBlank(hdfsFile)) {
            return false;
        }
        hdfsFile = uri + hdfsFile;
        Configuration config = new Configuration();
        FileSystem hdfs = FileSystem.get(URI.create(uri), config);
        Path src = new Path(localFile);
        Path dst = new Path(hdfsFile);
        hdfs.copyFromLocalFile(src, dst);
        hdfs.close();
        return true;
    }


    /**
     * @Author LB
     * @Description 【下载文件】
     * @Param
     *
     */
    public static void downLoadFileFromHDFS(String src) throws Exception {

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Path  srcPath = new Path(src);
        InputStream in = fs.open(srcPath);
        try {
            //将文件COPY到标准输出(即控制台输出)
            IOUtils.copyBytes(in, System.out, 4096,false);
        }finally{
            IOUtils.closeStream(in);
            fs.close();
        }
    }
    /**
     * @Author LB
     * @Description 【下载文件】
     * @Param
     *
     */
    public static void downLoadFileFromHDFS2(String srcPath, String destPath) throws Exception {
        FileSystem fs=null;
        try {
            Configuration conf = new Configuration();
            fs= FileSystem.get(conf);
            Path  src = new Path(srcPath);
            Path dest=new Path(destPath);

            //下载-copy=hdfs文件到本地
            fs.copyToLocalFile(true, src, dest, true);
        }finally{
            if(fs!=null){
                fs.close();
            }
        }
    }


    /**
     * @Author LB
     * @Description 【创建文件】
     * @Param
     *
     */
    public static boolean createNewHDFSFile(String newFile, String content) throws IOException {
        if (StringUtils.isBlank(newFile) || null == content) {
            return false;
        }
        newFile = uri + newFile;
        Configuration config = new Configuration();
        FileSystem hdfs = FileSystem.get(URI.create(newFile), config);
        FSDataOutputStream os = hdfs.create(new Path(newFile));
        os.write(content.getBytes("UTF-8"));
        os.close();
        hdfs.close();
        return true;
    }

    /**
     * @Author LB
     * @Description 【创建文件--定制副本数和块大小】
     * @Param
     *
     */
    public static void createNewHDFSFile1(String newFile, String content) throws IOException {
        Configuration con = new Configuration();
        FileSystem fs = FileSystem.get(con);
        Path path = new Path("hdfs://node1/user/testUesr/hadoop/hello.txt");
        FSDataOutputStream fout = fs.create(new Path("/user/testUser/a.txt"),true
                                            ,1024,(short)2,10240);
        fout.write("aaaaaaa".getBytes());
        IOCloseUtil.close(fout);
    }



    /**
     * @Author LB
     * @Description 【读取字节数据】
     * @Param
     *
     */
    public static byte[] readHDFSFile(String hdfsFile) throws Exception {
        if (StringUtils.isBlank(hdfsFile)) {
            return null;
        }
        hdfsFile = uri + hdfsFile;
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(hdfsFile), conf);
        // check if the file exists
        Path path = new Path(hdfsFile);
        if (fs.exists(path)) {
            FSDataInputStream is = fs.open(path);
            // get the file info to create the buffer
            FileStatus stat = fs.getFileStatus(path);
            // create the buffer
            byte[] buffer = new byte[(int) stat.getLen()];
            is.readFully(0, buffer);
            is.close();
            fs.close();
            return buffer;
        } else {
            throw new Exception("the file is not found .");
        }
    }

    /**
     * @Author LB
     * @Description 【读取字节数据】
     * @Param
     *
     */
    public static byte[] readHDFSFile1() throws Exception {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS","hdfs://192.168.3.1:9000/");
        FileSystem fs = FileSystem.get(configuration);
        Path path = new Path("/user/testUser/hadoop/index.html");
        FSDataInputStream is = fs.open(path);
        byte[] buf = new byte[1024];
        int len = -1;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while((len = is.read(buf)) != -1){
            baos.write(buf,0,len);
        }
        is.close();
        baos.close();
        System.out.println(new String(baos.toByteArray()));
        return baos.toByteArray();
    }

    /**
     * @Author LB
     * @Description 【读取字节数据】
     * @Param
     *
     */
    public static byte[] readHDFSFile2() throws Exception {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS","hdfs://192.168.3.1:9000/");
        FileSystem fs = FileSystem.get(configuration);
        Path path = new Path("/user/testUser/hadoop/index.html");
        FSDataInputStream is = fs.open(path);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        IOUtils.copyBytes(is,baos,1024);
        IOCloseUtil.close(is,baos);
        System.out.println(new String(baos.toByteArray()));
        return baos.toByteArray();
    }
}
