package com.ibase.lambda.syntax;

public class FunctionReference {


    /**
     * //某个类的静态接口----其实相当于 一个函数，也叫 接口函数
     * //一个函数，函数式接口--->接口就是为了函数而定义的。就是方便使用 lambda 表示的语法
     * FunctionReference.CalcInter staticInterfaceFunction_2 = (x, y) -> x + y;
     * FunctionReference.CalcInter staticInterfaceFunction_1 = (x, y) -> {
     * return x + y;
     * };
     * System.out.println("staticInterfaceFunction_1.calc(10, 20) = " + staticInterfaceFunction_1.calc(10, 20));
     * <p>
     * 这样就能直接实现某个接口，其实 是实现了某个方法。。。函数做为基本对象 编程的方式。
     */
    @FunctionalInterface
    private static interface CalcInter {
        int calc(int x, int y);
    }

    private static int calcStatic(int x, int y) {
        return x + y;
    }

    private static int calcNoStatic(int x, int y) {
        return x + y;
    }

    //引用方法的基本语法 ::
    public static void main(String[] args) {
        //lambda 实现某个 函数接口/接口函数的时候  ，直接用引用 另一个函数 作为实现

        //1.引用一个静态方法作为实现，要求 和接口函数 的方法签名要一致
        FunctionReference.CalcInter staticInter1 = FunctionReference::calcStatic;

        //2.引用非静态方法


    }
}
