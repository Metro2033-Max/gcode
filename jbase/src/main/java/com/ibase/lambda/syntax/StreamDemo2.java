package com.ibase.lambda.syntax;

import java.sql.SQLOutput;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo2 {
    public static void main(String[] args) {
        Stream<String> stream1 = Stream.of("James", "Kebon", "Devid", "Tom", "Tom", "Tom222");
        //Stream<String> parallel = stream1.parallel();//并行化流
        System.out.println("stream1.isParallel = " + stream1.isParallel());

//        String[] arr = new String[]{"James", "Kebon", "Devid", "Tom"};
//        Stream<String> stream2 = Arrays.stream(arr);

        /*
        1.常用 filter map skip limit distinct forEach sorted
         */
        //1.1
        //断言1 判断条件 实现接口
      /*  stream1.filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return false;
            }
        })*/
        // 断言2 -- 变量方式2 lambda  更好的复用性和逻辑连接
        Predicate<String> p1 = x -> x != null && x.length() > 2;
        Predicate<String> p2 = x -> x != null && x.length() > 2;
        //stream1.filter(p1).forEach(System.out::println);
        //更好的复用性和逻辑连接
        //stream1.filter(p1.and(p2).negate()/*negate,取反*/).forEach(System.out::println);


        //断言3
        //stream1.filter(x -> x != null && x.length() > 2).forEach(System.out::println);

        stream1.filter(x -> x != null && x.length() > 2)
                .map(x -> x + ".map")//一对一映射 map2Int ...没啥区别
                .skip(1)//跳过
                .limit(5)//取多少
                .distinct()//distinct：通过流中元素的 hashCode() 和 equals() 去除重复元素
                .forEach(System.out::println);

        System.out.println("--------------------------------------------------------------");
        //1.3 flatMap flat 打散，流中流--再合并为一个流----主要用于处理  list 套 list ，数组 套 数组，二维数组; 一类的情况
        //嵌套的情况，然后全部打散 成为 一个层级的 一个个的元素，
        String[] arr = new String[]{"James,AA", "Kebon,BB", "Devid,CC", "Tom,DD"};
        Stream<String> stream2 = Arrays.stream(arr);
        //stream2.flatMap(x -> Stream.of(x.split(","))).forEach(System.out::println);

        //Peek---只处理元素内部的东西，不改元素类型。。。Java 8 Stream Api 中的 peek 操作 - 掘金  https://juejin.cn/post/6844904004619616264
        //stream2.peek(System.out::println);

        //1.2 排序
        //stream2.sorted().forEach(System.out::println);//sorted()：自然排序，流中元素需实现Comparable接口
        //自定义排序接口。。。
        /*
        Set<User> userSet = Sets.newHashSet(u1, u2, u3, u4);
       Stream<User> userStream = userSet.stream().sorted(
               (obj1, obj2) -> {
                   if (obj1.getName().equals(obj2.getName())) {
                       //name相等 按age
                       return obj1.getAge() - obj2.getAge();
                   }
                   return obj1.getName().compareTo(obj2.getName());
               }
       );
         */
        stream2.sorted((x1, x2) ->
                //直接按照 长度排序
                x1.length() - x2.length()
        ).forEach(System.out::println);

        System.out.println("--------------------------------------------------------------");

        /*
        2. 流匹配:
        allMatch：接收一个 Predicate 函数，当流中每个元素都符合该断言时才返回true，否则返回false
        noneMatch：接收一个 Predicate 函数，当流中每个元素都不符合该断言时才返回true，否则返回false
        anyMatch：接收一个 Predicate 函数，只要流中有一个元素满足该断言则返回true，否则返回false
        findFirst：返回流中第一个元素
        findAny：返回流中的任意元素
        count：返回流中元素的总个数
        max：返回流中元素最大值
        min：返回流中元素最小值
         */

        List<Integer> list = Arrays.asList(1, 3, 22, 11, 55, 45, 0, 2, 1);
        Stream<Integer> stream3 = list.stream();
        //判断 断言
        /*boolean  allMatch = stream3.allMatch(x -> x > 3);//anyMatch每一个都要满足
        System.out.println("allMatch = " + allMatch);//false*/
       /* boolean noneMatch = stream3.noneMatch(x -> x > 5);//每一个都不满足 ，才是true
        System.out.println("noneMatch = " + noneMatch);//false*/

        /*boolean anyMatch = stream3.anyMatch(x -> x > 50);//任意一个元素满足
        System.out.println("anyMatch = " + anyMatch);*/

/*        Optional<Integer> firstOptional = stream3.findFirst();
//        firstOptional.orElse()
//        firstOptional.orElseGet()
//        firstOptional.get()
        Integer first = firstOptional.get();
        System.out.println("first = " + first);*/

        //System.out.println("stream3.findAny().get() = " + stream3.findAny().get());//返回任意一个

        //System.out.println("stream3.count() = " + stream3.count());
        //max min 要自己传比较器
        //System.out.println("stream3.max() = " + stream3.max((x1, x2) -> x1 - x2).get());
        System.out.println("stream3.min() = " + stream3.max((x1, x2) -> x2 - x1).get());


        /*
        3.聚合操作-reduce
         streamNums.reduce(new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return null;
            }
        })
         */
        //1.reduce 以第一个元素为起始值----元素都聚合到其中一个中，但是 不能改变元素类型
        User user1 = new User("tom", 10);
        User user2 = new User("jerk", 20);
        User user3 = new User("张三", 30);
        User user4 = new User("李四", 40);
        User user5 = new User("李四", 50);

        List<User> users = Arrays.asList(user1, user2, user3, user4);
        Stream<User> userStream = users.stream();
        /*
        User sumUserA = userStream.reduce((User sumUser, User item) -> {
            System.out.println("sumUser = " + sumUser);
            System.out.println("item.age = " + item.getAge());
            sumUser.setAge(sumUser.getAge() + item.getAge());
            return sumUser;
        }).get();
        System.out.println("sumUserA = " + sumUserA);
        */

        /*
        sum = User{name='tom', age=10}
        item.age = 20
        sum = User{name='tom', age=30}
        item.age = 30
        sum = User{name='tom', age=60}
        item.age = 40
        sumUserA = User{name='tom', age=100}
         */

        //设置一个元素作为起始值
       /* User userStart = new User("FFF", 100);
        User sumUserB = userStream.reduce(userStart, (User sumUser, User item) -> {
            System.out.println("sumUser = " + sumUser);
            System.out.println("item.age = " + item.getAge());
            sumUser.setAge(sumUser.getAge() + item.getAge());
            return sumUser;
        });
        System.out.println("sumUserB = " + sumUserB);*/


        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6);
        Stream<Integer> streamNums = nums.stream();

        //System.out.println("streamNums.reduce((sum,item)->sum+=item) = " + streamNums.reduce((sum, item) -> sum += item).get());
        //System.out.println("streamNums.reduce((sum,item)->sum+=item) = " + streamNums.reduce(100, (sum, item) -> sum += item));


        /**
         * 4. 收集转换
         */
        //4.1转为集合
        // System.out.println("userStream.filter(x->x.getAge() >0 ).collect(Collectors.toList()) = " + userStream.filter(x -> x.getAge() > 0).collect(Collectors.toList()));
        //System.out.println("userStream.filter(x->x.getAge() >0 ).collect(Collectors.toList()) = " + userStream.filter(x -> x.getAge() > 0).collect(Collectors.toSet()));
      /* Map<String, Integer> cMap = userStream.filter(x -> x.getAge() > 0).collect(Collectors.toMap(User::getName, User::getAge));
        System.out.println("cMap = " + cMap);//cMap = {李四=40, 张三=30, tom=10, jerk=20}
         */

        //4.2 averagingInt 平均值
 /*       Double collect = streamNums.filter(x -> x > 0).collect(Collectors.averagingInt(x -> x));
        System.out.println("collect = " + collect);*/
        //4.3 group by key---同一个key 会被覆盖？
        //System.out.println("userStream.filter(x -> x.getName() != null).collect(Collectors.groupingBy(x -> x.getName())) = " + userStream.filter(x -> x.getName() != null).collect(Collectors.groupingBy(x -> x.getName())));
        //4.4 counting??Count ?
        //System.out.println("userStream.filter(x -> x.getName() != null).collect(Collectors.counting) = " + userStream.filter(x -> x.getName() != null).collect(Collectors.counting()));
        //4.5 maxBy,MinBy 找出最大最小，，这不是都有吗
        //  System.out.println("userStream.filter(x -> x.getName() != null).collect(Collectors.counting) = " + userStream.filter(x -> x.getName() != null).collect(Collectors.maxBy((x1, x2) -> x1.getAge() - x2.getAge())));
        //4.6 partition By
        //  System.out.println("userStream.filter(x -> x.getName() != null).collect(Collectors.counting) = " + userStream.filter(x -> x.getName() != null).collect(Collectors.partitioningBy(x -> x.getAge() > 3)));
        //4.7 其他，还有 mapping , reducing 。。。。。不试了。。。
        //System.out.println("userStream.filter(x -> x.getName() != null).collect(Collectors.counting) = " + userStream.filter(x -> x.getName() != null).collect(Collectors.));


    }

    static class User {
        private String name;
        private int age;

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
