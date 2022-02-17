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


    private int calcNoStatic(int x, int y) {
        return x + y;
    }

    //引用方法的基本语法 ::
    public static void main(String[] args) {
        //lambda 实现某个 函数接口/接口函数的时候  ，直接用引用 另一个函数 作为实现

        //1.引用一个静态方法作为实现，要求 和接口函数 的方法签名要一致
        FunctionReference.CalcInter staticInter1 = FunctionReference::calcStatic;
        staticInter1.calc(10, 20);
        //2.引用非静态方法 ，除了签名要和函数接口一致，还要 是某个具体的对象实例
        FunctionReference.CalcInter inter2 = new FunctionReference()::calcNoStatic;
        inter2.calc(1, 2);
        //3.引用构造函数(来实现某个具体的接口函数)
        //3.1 引用无参构造
        //要求：1.函数接口返回的就是该对象2.该对象有和接口中函数 的参数一致 的 构造函数 3.要实现的接口函数逻辑就一句话，就是new 一个 该对象
        NoneParamInter inter1 = Cat::new;//NoneParamInter inter1 = ()->new Cat();
        //3.2 引用单个参数构造函数
        SingleParamInter inter22 = Cat::new;
        //3.3 多个参数
        MuitiPleParamInter inter3 = Cat::new;

        /*
        4.对象方法的特殊引用【常用】
            接口函数有一到多个参数
            其中一个参数的的某个成员方法就能实现函数接口。
            如果其他参数可以作为该参数对象的成员方法的参数
         */
        //InterA interA = (Person person)->{return person.getName();};
        //InterA interA = x -> x.getName();
        //4.1 引用某个参数的某个函数来实现接口的写法
        InterA interA3 = Person::getName;
        //4.2 有个对象参数和另一个参数
        //InterB interB0 = (person, name) -> person.setName(name);
        InterB interB = Person::setName;//这里有两个参数都被省略了，另一个参数恰好用于 Person 的setName的参数
        //4.3 一个对象参数和其他多个参数+有返回值
        InterC interC1 = (person, a, b) -> person.calc(a, b);
        InterC interC2 = Person::calc;//引用该参数的方法，同时其他参数作为该方法的参数。
    }

    public static class Person {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int calc(int a, int b) {
            return a + b;
        }
    }

    @FunctionalInterface
    public interface InterA {
        String getA(Person person);
    }

    @FunctionalInterface
    public interface InterB {
        void getA(Person person, String name);
    }

    @FunctionalInterface
    public interface InterC {
        int getA(Person person, int a, int b);
    }

    /**
     * 引用构造
     */

    @FunctionalInterface
    public interface NoneParamInter {
        Cat getC();
    }

    @FunctionalInterface
    public interface SingleParamInter {
        Cat getC(String name);
    }

    @FunctionalInterface
    public interface MuitiPleParamInter {
        Cat getC(String name, int weight);
    }

    public static class Cat {
        public Cat() {
        }

        public Cat(String name) {
        }

        public Cat(String name, int weight) {
        }
    }
}
