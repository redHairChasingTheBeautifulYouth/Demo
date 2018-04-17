package fastJson;
/*
 *一句话描述该类作用:测试fastJson
 *@Author:
 *LB
 *
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FastJsonDemo {

    public static void main(String[] arges){
        //mapToJsonString();
        //jsonStringToJsonObject();
        jsonObjectToJsonString();
    }

    /**
     * 将普通map对象转化为json字符串
     *
     */
    public static void mapToJsonString(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key1", "One");
        map.put("key2", "Two");
        String mapJson = JSON.toJSONString(map);
        System.out.println(mapJson);    //{"key1":"One","key2":"Two"}
    }

    /**
     * 将json字符串转化为jsonObject
     */
    public static void jsonStringToJsonObject(){
        String  str = "{\"studentName\":\"lily\",\"studentAge\":12}";
        JSONObject jsonObject = JSONObject.parseObject(str);
        System.out.println("studentName:  " + jsonObject.getString("studentName") + "--------" + "  studentAge:  "
                + jsonObject.getInteger("studentAge"));     //studentName:  lily--------  studentAge:  12
    }


    /**
     * 将jsonObject转化为json字符串
     */
    public static void jsonObjectToJsonString(){
        String  str = "{\"studentName\":\"lily\",\"studentAge\":12}";
        JSONObject jsonObject = JSONObject.parseObject(str);
        // 第一种方式
        String jsonString = JSONObject.toJSONString(jsonObject);

        // 第二种方式
        //String jsonString = jsonObject.toJSONString();
        System.out.println(jsonString); //{"studentAge":12,"studentName":"lily"}

    }

    /**
     * json数组字符串转化为JSONArray
     */
    public void jsonArrayStringToJSONArray() {

        String  jsonArrayStirng = "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";

        JSONArray jsonArray = JSONArray.parseArray(jsonArrayStirng);

        //遍历方式1
        int size = jsonArray.size();
        for (int i = 0; i < size; i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            System.out.println("studentName:  " + jsonObject.getString("studentName") + ":" + "  studentAge:  "
                    + jsonObject.getInteger("studentAge"));
        }

        //遍历方式2
        for (Object obj : jsonArray) {

            JSONObject jsonObject = (JSONObject) obj;
            System.out.println("studentName:  " + jsonObject.getString("studentName") + ":" + "  studentAge:  "
                    + jsonObject.getInteger("studentAge"));
        }
    }

    /**
     * JSONArray转化为json数组字符串
     */
    public void testJSONArrayToJSONStr() {

        String  jsonArrayStirng = "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";

        //已知JSONArray,目标要转换为json字符串
        JSONArray jsonArray = JSONArray.parseArray(jsonArrayStirng);
        //第一种方式
        String jsonString = JSONArray.toJSONString(jsonArray);

        // 第二种方式
        //String jsonString = jsonArray.toJSONString(jsonArray);
        System.out.println(jsonString);
    }




}
