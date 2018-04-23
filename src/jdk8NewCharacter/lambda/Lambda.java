package jdk8NewCharacter.lambda;
/*
 *一句话描述该类作用:lambda测试
 *@Author:
 *LB
 *
 */

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Lambda {

    public static void main(String[] arges){
        jdk8Runable();
    }

    /**
     * lambda表达式实现Runnable
     */
    public static void jdk8Runable(){
        new Thread(() -> {
                            System.out.println("sss");
                            System.out.println("ddd");}).start();
    }

    /**
     * 使用lambda表达式对列表进行迭代
     */
    public static void jdk8Collection(){
        List<String> features = Arrays.asList("1","2","4");
        features.forEach(n -> System.out.println(n));

    }

    /**
     * 使用lambda表达式和函数式接口Predicate
     */
    public static void jdk8Predicate(){
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        filter(languages ,(str) -> str.startsWith("J"));
    }
    private static void filter(List<String> names , Predicate<String> condition){
        names.forEach(n -> {
            if(condition.test(n)){
                System.out.println(n);
            }
        });
    }
    // 更好的办法
    public static void filter1(List names, Predicate condition) {
        names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
            System.out.println(name + " ");
        });
    }

    /**
     * java.util.function.Predicate 允许将两个或更多的 Predicate 合成一个。
     * 它提供类似于逻辑操作符AND和OR的方法，名字叫做and()、or()和xor()，用于将传入 filter() 方法的条件合并起来。
     * 例如，要得到所有以J开始，长度为四个字母的语言，可以定义两个独立的 Predicate 示例分别表示每一个条件，
     * 然后用 Predicate.and() 方法将它们合并起来
     */
    public static void jdk8Predicate1(){
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        Predicate<String> startWith = (str) -> str.startsWith("J");
        Predicate<String> fourLetterLong = (str) -> str.length() == 4;
        languages.stream().filter(startWith.and(fourLetterLong)).forEach(n -> System.out.println(n));
    }


    /**
     * 本例介绍最广为人知的函数式编程概念map。它允许你将对象进行转换。
     * 例如在本例中，我们将 costBeforeTax 列表的每个元素转换成为税后的值。
     * 我们将 x -> x*x lambda表达式传到 map() 方法，后者将其应用到流中的每一个元素。
     * 然后用 forEach() 将列表元素打印出来。使用流API的收集器类，可以得到所有含税的开销。
     * 有 toList() 这样的方法将 map 或任何其他操作的结果合并起来。由于收集器在流上做终端操作，因此之后便不能重用流了。
     * 你甚至可以用流API的 reduce() 方法将所有数字合成一个
     */
    public static void jdk8Map(){
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        costBeforeTax.stream().map((cost) -> cost+cost*1.2).forEach(n -> System.out.println(n));
    }

    /**
     * 在上个例子中，可以看到map将集合类（例如列表）元素进行转换的。
     * 还有一个 reduce() 函数可以将所有值合并成一个。
     * Map和Reduce操作是函数式编程的核心操作，因为其功能，reduce 又被称为折叠操作。
     * 另外，reduce 并不是一个新的操作，你有可能已经在使用它。SQL中类似 sum()、avg() 或者 count() 的聚集函数，
     * 实际上就是 reduce 操作，因为它们接收多个值并返回一个值。流API定义的 reduceh() 函数可以接受lambda表达式，并对所有值进行合并。
     * IntStream这样的类有类似 average()、count()、sum() 的内建方法来做 reduce 操作，也有mapToLong()、mapToDouble() 方法来做转换。
     * 这并不会限制你，你可以用内建方法，也可以自己定义。在这个Java 8的Map Reduce示例里，我们首先对所有价格应用 12% 的VAT，然后用 reduce() 方法计算总和。
     */
    public static void jdk8MapAndReduce(){
        // 为每个订单加上12%的税
        // 老方法：
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double total = 0;
        for (Integer cost : costBeforeTax) {
            double price = cost + .12*cost;
            total = total + price;
        }
        System.out.println("Total : " + total);

        // 新方法：
        double bill = costBeforeTax.stream().map((cost) -> cost + .12*cost).reduce((sum, cost) -> sum + cost).get();
        System.out.println("Total : " + bill);
    }

    /**
     *  通过过滤创建一个String列表
     */
    public static void jdk8StringAray(){
        List<String> oldStrs = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        List<String> newStrs = oldStrs.stream().filter(x -> x.length()>2).collect(Collectors.toList());
    }

    /**
     * 对列表的每个元素应用函数
     */
    public static void jdk8Menthod(){
        // 将字符串换成大写并用逗号链接起来
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
        String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
        System.out.println(G7Countries);
    }

    /**
     * 复制不同的值，创建一个子列表
     */
    public static void jdk8Distinct(){
        List<Integer> nums = Arrays.asList(1,2,3,3,4,5,3);
        List<Integer> disticntNums = nums.stream().map(i -> i*i).distinct().collect(Collectors.toList());
    }

    /**
     * 计算集合元素的最大值、最小值、总和以及平均值
     * IntStream、LongStream 和 DoubleStream 等流的类中，
     * 有个非常有用的方法叫做 summaryStatistics() 。
     * 可以返回 IntSummaryStatistics、LongSummaryStatistics 或者
     * DoubleSummaryStatistic s，描述流中元素的各种摘要数据。
     * 在本例中，我们用这个方法来计算列表的最大值和最小值。它也有 getSum() 和 getAverage() 方法来获得列表的所有元素的总和及平均值
     */
    public static void jdk8getMax(){
        //获取数字的个数、最小值、最大值、总和以及平均值
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());
    }





}
