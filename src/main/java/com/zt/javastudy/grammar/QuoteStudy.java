package com.zt.javastudy.grammar;

/**
 * @author zhengtao
 * @description java中方法参数传递方式是按值传递。
 * 如果参数是基本类型，传递的是基本类型的字面量值的拷贝。
 * 如果参数是引用类型，传递的是该参量所引用的对象在堆中地址值的拷贝。
 * @date 2021/4/25
 */
public class QuoteStudy {
    public static void main(String[] args) {
        int a = 0;
        add(a);
        System.out.println(a);
        String b = "hello";
        add(b);
        System.out.println(b);
        StringBuilder c = new StringBuilder("hello");
        add(c);
        System.out.println(c);
        StringBuilder d = new StringBuilder("hello");
        move(d);
        System.out.println(d);
    }

    /**
     * 基本类型传递的值的拷贝，所以不影响原值
     * @param a
     */
    private static void add(int a){
        a = 1;
    }

    /**
     * 对象传递的是对象的引用的拷贝，所以如果改变对象的属性是可以改变，如果将引用赋值给另一个对象，则不会改变原对象的引用
     * @param b
     */
    private static void add(String b){
        // 在字符中 = ，就相当于重新new对象，因为string类型是不可变的，等价于b = new String("helloWorld")
        b = "helloWorld";
    }

    /**
     * 对象传递的是对象的引用的拷贝,改变对象属性可以成功
     * @param c
     */
    private static void add(StringBuilder c){
       c = c.append("world!");
    }

    /**
     * 改变引用的指向，不成功
     * @param c
     */
    private static void move(StringBuilder c){
        c = new StringBuilder("helloworld!");
    }
}
