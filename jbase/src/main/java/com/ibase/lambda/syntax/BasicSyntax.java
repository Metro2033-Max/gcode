package com.ibase.lambda.syntax;

import com.ibase.lambda.inters.*;

/***
 *
 *NoneReturnNoneParameter lambda1 = () -> {
 *             System.out.println("无参无返回值的lambad");
 * };
 *lambda1 本身是一个接口的引用，lambda 表达式 实现的是一个 函数。而接口是一个函数式接口.
 * 函数式编程：
 *  1.实现一个函数，调用一个函数，函数作为参数；但其实 语法 上 是实现的一个接口[函数接口-有且只有一个函数必须实现=那么可以理解为 该接口只为了该函数而存在。],传递和引用的都是该接口的实现类。
 *  2.函数引用
 */
public class BasicSyntax {
    public static void main(String[] args) {
        // basicSyntax();
        /**
         * 精简语法
         */
        //1.参数精简
        //1.1 参数类型可以省略，因为在接口中已经定义，JVM可以推断类型。多个参数，要么都省略要么都不省略
        NoneReturnMutipleParameter nmp1 = (x, y) -> {
            //return x + y;
            System.out.println("x = " + x + ",y=" + y);
        };

        //1.2 只有一个参数，参数类型和参数名可以省略
        NoneReturnSinglePrameter nsp = x -> {
            System.out.println("x = " + x);
        };
        //1.3 无参,括号不能省略
        NoneReturnNoneParameter nsp2 = () -> {
            System.out.println();
        };

        //2.方法体部分 精简
        //2.1 如果 方法体只有一句 大括号可以省略
        //2.1.1 没有返回值 直接一句方法体
        NoneReturnSinglePrameter nrsp1 = x -> System.out.println("x = " + x);
        //2.1.2 有返回值，方法体只有一句，就是返回那句，那return 也可以省略
        SingleReturnMutipleParameter nrmp1 = (x, y) -> x + y;

    }

    private static void basicSyntax() {
        //1.无参无返回值
        NoneReturnNoneParameter lambda1 = () -> {
            System.out.println("无参无返回值的lambad");
        };


        //函数式编程
        lambda1.test();
        //2.一个参数，无返回值的
        NoneReturnSinglePrameter lambda2 = (int a) -> {
            System.out.println("一个参数，无返回值" + 10);
        };

        lambda2.test(10);
        //3.多个参数，无返回值
        NoneReturnMutipleParameter lambda3 = (int a, int b) -> {
            System.out.println("a = " + a + ",b=" + b);
        };
        lambda3.test(22, 33);


        //4.有返回值，无参
        SingleReturnNoneParameter lambda4 = () -> {
            System.out.println("有返回值，无参");
            return 10;
        };
        System.out.println("lambda4.test() = " + lambda4.test());


        //5.有返回值，1个参数
        SingleReturnSingleParameter lambda5 = (int a) -> {
            System.out.println("a = " + a);
            return a;
        };
        int test5 = lambda5.test(100);
        System.out.println("test5 = " + test5);

        //6.有返回值，多个参数
        SingleReturnMutipleParameter lambda6 = (int a, int b) -> {
            System.out.println("a = " + a);
            return a + b;
        };
        int test = lambda6.test(10, 100);
        System.out.println("lambda6 = " + test);
    }
}
