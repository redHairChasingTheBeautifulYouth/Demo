package archiver;
/*
 *一句话描述该类作用:【归档测试】
 *@Author:LB
 *
 */

import org.junit.Test;

public class TestConverter {

    @Test
    public void test(){
        byte[] arr = int2Bytes(1345);
        System.out.println(bytes2Int(arr));
    }

    //一个整数4个字节
    /**
     * @Author LB
     * @Description 【将整数转化为字节数组】
     * @Param
     *
     */
    public byte[] int2Bytes(int i){
        byte[] arr = new byte[4];
        arr[0] = (byte) i;
        //有符号移动8位
        arr[1] = (byte) (i>>8);
        arr[2] = (byte) (i>>16);
        arr[3] = (byte) (i>>24);
        return arr;
    }
    /**
     * @Author LB
     * @Description 【字节数组转换为整数】
     * @Param
     *
     */
    public int bytes2Int(byte[] arr){
        int i0 = arr[0];
        int i1 = (arr[1] & 0xFF) << 8;
        int i2 = (arr[2] & 0xFF) << 16;
        int i3 = (arr[3] & 0xFF) << 24;
        return i0 | i1 |i2 | i3;
    }
}
