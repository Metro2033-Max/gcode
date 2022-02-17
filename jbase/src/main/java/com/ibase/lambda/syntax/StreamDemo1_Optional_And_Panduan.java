package com.ibase.lambda.syntax;

import com.sun.xml.internal.ws.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class StreamDemo1_Optional_And_Panduan {
    public static void main(String[] args) {
        String name = "";

        String s = Optional.ofNullable(name).get();
        //String s = Optional.ofNullable(name).orElseGet(()->new ArrayList<Integer>());//不行啊
        System.out.println("s = " + s);

        List<String> list = new ArrayList<>();
        list = null;
        System.out.println("list = " + list);
        List<String> list1 = Optional.ofNullable(list).orElseGet(() -> {
            System.out.println("----无法判断是否元素个数为0------");
            return new ArrayList<String>();
        }).stream().map(x -> x = x + "**").collect(Collectors.toList());

        System.out.println(list1);

        /*
        根据外部条件 stream lambda  写法,比 把条件写在外面简洁
         */

        List<String> stringList = new ArrayList<>();
        String name1 = "e";//lambda 表达式引用的 局部变量 会自动变为 final
        // name1 = "aaa";
        String name2 = "y";

        stringList.add("hello");
        stringList.add("world");
        stringList.add("fallout");
        stringList.add("yellow");

        //Java Optional 的 orElse() 和 orElseGet() 的区别_yfy的博客-CSDN博客_java optional orelse  https://blog.csdn.net/fy_java1995/article/details/90047828
        //
        List<String> list2 = Optional.ofNullable(stringList).orElseGet(new Supplier<List<String>>() {
            @Override
            public List<String> get() {
                return null;
            }
        }).stream()
                .filter(x -> name1 == null ? true : x != null && x.contains(name1))
                .filter(x -> name2 == null ? true : x != null && x.contains(name2))
                .collect(Collectors.toList());

        System.out.println("list2 = " + list2);

    }
}
