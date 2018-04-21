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
import com.alibaba.fastjson.TypeReference;
import fastJson.javaBean.Course;
import fastJson.javaBean.Student;
import fastJson.javaBean.Teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public void jSONArrayToJSONStr() {

        String  jsonArrayStirng = "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";

        //已知JSONArray,目标要转换为json字符串
        JSONArray jsonArray = JSONArray.parseArray(jsonArrayStirng);
        //第一种方式
        String jsonString = JSONArray.toJSONString(jsonArray);

        // 第二种方式
        //String jsonString = jsonArray.toJSONString(jsonArray);
        System.out.println(jsonString);
    }


    /**
     * 复杂Json字符串转换到JsonObject
     */
    public void complexJsonStringToJsonObject(){
        String jsonArrayString = "{\"teacherName\":\"crystall\",\"teacherAge\":27," +
                "\"course\":{\"courseName\":\"english\",\"code\":1270}," +
                "\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";
        JSONObject jsonObject = JSONObject.parseObject(jsonArrayString);

        String teacherName = jsonObject.getString("teacherName");
        Integer teacherAge = jsonObject.getInteger("teacherAge");

        System.out.println("teacherName:  " + teacherName + "   teacherAge:  " + teacherAge);

        JSONObject jsonObjectcourse = jsonObject.getJSONObject("course");
        //获取JSONObject中的数据
        String courseName = jsonObjectcourse.getString("courseName");
        Integer code = jsonObjectcourse.getInteger("code");

        System.out.println("courseName:  " + courseName + "   code:  " + code);

        JSONArray jsonArraystudents = jsonObject.getJSONArray("students");

        //遍历JSONArray
        for (Object object : jsonArraystudents) {

            JSONObject jsonObjectone = (JSONObject) object;
            String studentName = jsonObjectone.getString("studentName");
            Integer studentAge = jsonObjectone.getInteger("studentAge");

            System.out.println("studentName:  " + studentName + "   studentAge:  " + studentAge);
        }

    }

    /**
     * JsonObject转换到复杂Json字符串的转换
     */
    public void jsonObjectToComplexJsonString(){
        String jsonArrayString = "{\"teacherName\":\"crystall\",\"teacherAge\":27," +
                "\"course\":{\"courseName\":\"english\",\"code\":1270}," +
                "\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";

        //复杂JSONObject,目标要转换为json字符串
        JSONObject jsonObject = JSONObject.parseObject(jsonArrayString);

        //第一种方式
        //String jsonString = JSONObject.toJSONString(jsonObject);

        //第二种方式
        String jsonString = jsonObject.toJSONString();

    }

    /**
     * json字符串转换到javaBean
     */
    public void jsonStringToJavaBean(){
        String jsonString = "{\"studentName\":\"lily\",\"studentAge\":12}";
        Student student = JSONObject.parseObject(jsonString, Student.class);
        System.out.println(student);

    }

    /**
     * javaBean转换到json字符串
     */
    public void javaBeanToJsonString(){
        Student stu = new Student("ceshi",22);

        String jsonString = JSONObject.toJSONString(stu);
    }


    /**
     * json数组字符串转换到javaBean
     */
    public void jsonArrayStringToJavaBean(){
        String jsonArrayStr = "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";

        List<Student> studentList1 = JSONArray.parseArray(jsonArrayStr, Student.class);

    }

    /**
     * javaBean_List转换到json数组字符串
     */
    public void javaBeanListToJsonArrayString(){
        List<Student> list = new ArrayList<>();
        list.add(new Student("11",22));
        list.add(new Student("22",33));

        String jsonArrayStr = JSONObject.toJSONString(list);
    }

    /**
     * JsonArray到JavaList的转换
     */
    public void jsonArrayToJavaList() {

        String  jsonArrayStirng = "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";

        //已知JsonArray
        JSONArray jsonArray = JSONArray.parseArray(jsonArrayStirng);

        //第一种方式,使用TypeReference<T>类,由于其构造方法使用protected进行修饰,故创建其子类
        ArrayList<Student> students = JSONArray.parseObject(jsonArray.toJSONString(),
                new TypeReference<ArrayList<Student>>() {});

        System.out.println(students);

        //第二种方式,使用Gson的思想
        List<Student> students1 = JSONArray.parseArray(jsonArray.toJSONString(), Student.class);
        System.out.println(students1);
    }

    /**
     * 复杂JavaBean_obj到json对象的转换
     */
    public void complexJavaBeanToJSONObject() {

        //已知复杂JavaBean_obj
        Student student = new Student("lily", 12);
        Student studenttwo = new Student("lucy", 15);

        List<Student> students = new ArrayList<Student>();
        students.add(student);
        students.add(studenttwo);
        Course course = new Course("english", 1270);

        Teacher teacher = new Teacher("crystall", 27, course, students);

        //方式一
        String jsonString = JSONObject.toJSONString(teacher);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        System.out.println(jsonObject);

        //方式二
        JSONObject jsonObject1 = (JSONObject) JSONObject.toJSON(teacher);
        System.out.println(jsonObject1);

    }

    /**
     * 复杂json对象到JavaBean_obj的转换
     */
    public void testComplexJSONObjectToJavaBean() {


        String jsonArrayString = "{\"teacherName\":\"crystall\",\"teacherAge\":27," +
                "\"course\":{\"courseName\":\"english\",\"code\":1270}," +
                "\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";

        //已知复杂json对象
        JSONObject jsonObject = JSONObject.parseObject(jsonArrayString);

        //第一种方式,使用TypeReference<T>类,由于其构造方法使用protected进行修饰,故创建其子类
        Teacher teacher = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<Teacher>() {});
        System.out.println(teacher);

        //第二种方式,使用Gson的思想
        Teacher teacher1 = JSONObject.parseObject(jsonObject.toJSONString(), Teacher.class);
        System.out.println(teacher1);
    }

}
