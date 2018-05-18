package jdk8NewCharacter.lambda;
/*
 *一句话描述该类作用:Stream与lambda表达式可以说是相伴相生的，通过Stream我们可以更好的更为流畅更为语义化的操作集合。
 * Stream api都位于java.util.stream包中。其中就包含了最核心的Stream接口，一个Stream实例可以串行或者并行操作一组元素序列，
 * Java8中，所有的流操作会被组合到一个 stream pipeline中，这点类似linux中的pipeline概念，将多个简单操作连接在一起组成一个功能强大的操作。
 * 一个 stream pileline首先会有一个数据源，这个数据源可能是数组、集合、生成器函数或是IO通道，流操作过程中并不会修改源中的数据；
 * 然后还有零个或多个中间操作，每个中间操作会将接收到的流转换成另一个流（比如filter）；
 * 最后还有一个终止操作，会生成一个最终结果（比如sum）。流是一种惰性操作，所有对源数据的计算只在终止操作被初始化的时候才会执行。
 * 总结一下流操作由3部分组成
 *1.源
 *2.零个或多个中间操作
 *3.终止操作 （到这一步才会执行整个stream pipeline计算）
 *@Author:
 *LB
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {


    public static void main(String[] args){
        testMap();
    }

    /**
     * 创建流
     */
    public void getStream(){
        //第一种 通过Stream接口的of静态方法创建一个流
        Stream<String> stream = Stream.of("hello", "world", "helloworld");
        //第二种 通过Arrays类的stream方法，实际上第一种of方法底层也是调用的Arrays.stream(values);
        String[] array = new String[]{"hello","world","helloworld"};
        Stream<String> stream3 = Arrays.stream(array);
        //第三种 通过集合的stream方法，该方法是Collection接口的默认方法，所有集合都继承了该方法
        Stream<String> stream2 = Arrays.asList("hello","world","helloworld").stream();
    }

    /**
     * map方法接收一个Function函数式接口实例，这里的map和Hadoop中的map概念完全一致，对每个元素进行映射处理。
     */
    public void testMapMethod(){
        List<String> list = Arrays.asList("hello", "world", "helloworld");
        List<String> collect = list.stream().map(s -> s.toUpperCase()).collect(Collectors.toList());
    }

    //排序sort()
    public static void testSort(){
        List<String> list = new ArrayList<>();

        list.add("a");

        list.add("ab");

        list.add("abc");

        list.add("b");

        list.add("bc");

        //sort里要求传入一个 实现了Comparator 接口实例
        list.sort( (a, b) -> a.length() - b.length());

        System.out.println(list);

    }

    // filter()的懒加载机制决定了 ，它不会对流做任何处理, 它的实际动作的取决于后续操作.
    public static void testFilter(){
        List<String> list = new ArrayList<>();

        list.add("A");

        list.add("Ab");

        list.add("abc");

        list.add("b");

        list.add("bc");

        List<String> newList = list.stream().filter(n -> n.length()>2).collect(Collectors.toList());;

        System.out.println(newList);
    }

    // 它可以把一种stream映射为另外一种
    public static void testMap(){
        List<String> list = new ArrayList<>();

        list.add("a");

        list.add("ab");

        list.add("abc");

        list.add("b");

        list.add("bc");

        List<String> newList = list.stream().map(n -> n.toLowerCase()).collect(Collectors.toList());

        System.out.println(newList);
    }

    //聚合
    public static void testJuHe(){
        List<String> list = new ArrayList<>();

        list.add("a");

        list.add("ab");

        list.add("abc");

        list.add("b");

        list.add("bc");

        List<String> newList = list.stream().map(n -> n.toLowerCase()).collect(Collectors.toList());

        ////toMap()要求传入2个Function实例,  如果用自身做key  则直接使用Function.<String>identity(),  用长度做value;
        Map<String ,Integer> map = list.stream().collect(Collectors.toMap(Function.<String>identity(), item -> item.length()));
    }
}
