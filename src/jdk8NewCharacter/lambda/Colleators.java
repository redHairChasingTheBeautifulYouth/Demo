package jdk8NewCharacter.lambda;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Colleators {

    static List<User> list = new ArrayList<>();

    static {
        User user1 = new User(1 ,"a");
        User user11= new User(1 ,"d");
        User user2 = new User(3 ,"b");
        User user3 = new User(4 ,"c");

        list.add(user1);
        list.add(user11);
        list.add(user2);
        list.add(user3);
    }

    public static void main(String[] args){
        groupbyTest();
        System.out.println("");
    }

    //groupingBy分类聚集器------------它创建了一个映射，其中TaskType是它的键，而包含了所有拥有相同TaskType的任务的列表是它的值
    public static void groupbyTest(){
        Map<String ,List<User>> map = list.stream().collect(Collectors.groupingBy(user -> user.getName()));
        for (Map.Entry<String, List<User>> entry : map.entrySet()) {
            System.out.println(String.format("%s =>> %s", entry.getKey(), entry.getValue()));
        }
    }



    //toList-------将数据收集进一个列表List


    public static void toListTest() {
        List<String> newList = list.stream().map(user -> user.getName()).collect(Collectors.toList());

        //指定List实现
        List<String> newList1 = list.stream().map(user -> user.getName()).collect(Collectors.toCollection(LinkedList::new));

        System.out.println(newList);
    }

    //toSet---------将数据收集进一个集合Set
    public static void toSetTest(){
        Set<String> set = list.stream().map(User::getName).collect(Collectors.toSet());
        System.out.println(set);
    }



    //toMap---------将数据收集进一个映射,存在重复的键时抛出异常,Function.identity()返回本身
    //类似于toMap收集器，也有toConcurrentMap收集器，它产生一个ConcurrentMap而不是HashMap。
    public static void toMapTest(){

        Map<Integer, String> map = list.stream().collect(Collectors.toMap(User::getId ,user -> user.getName()));

        Map<Integer, User> map1 = list.stream().collect(Collectors.toMap(User::getId , Function.identity()));

        //键值重复，解决冲突
        Map<String, Integer> map2 = list.stream().collect(Collectors.toMap(user -> user.getName() ,user -> user.getId() ,(t1,t2) -> t2));

        for (Map.Entry<String, Integer> entry : map2.entrySet()) {
            System.out.println(String.format("%s =>> %s", entry.getKey(), entry.getValue()));
        }
        System.out.println("");



    }



}

class User{
    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
