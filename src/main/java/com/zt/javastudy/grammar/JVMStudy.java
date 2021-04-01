package com.zt.javastudy.grammar;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author zhengtao
 * @description jvm学习代码
 * @date 2021/3/4
 */
public class JVMStudy {
    public static void main(String[] args) {
        // s位于常量池在class文件中，在类加载后就到了运行时常量池，jdk1.6后就属于了字符串常量池，所以是在堆中的
        String s ="1234";
        // 在jvm中第一次出现，于是s.intern会返回一个指向字符串常量池的引用指向s
        System.out.println(s.intern()==s);
        // 字符串常量池已经有了,不会新建对象，直接返回引用
        String s1 = "1234";
        // s1不是首次出现的，所以s1返回的就是指向堆里字符串常量池的引用也就是第一次出现的s
        System.out.println(s1.intern()==s1);
        // 因为在jvm运行时，java这个字符串就已经在字符串常量池了
        String s2 = new StringBuilder("ja").append("va").toString();
        System.out.println(s2.intern()==s2);
        // 在jvm中第一次出现，于是s3.intern只会在运行时常量池里保存第一个的引用指向s3
        String s3 = new StringBuilder("123456").append("789").toString();
        // 这已经不是第一次出现了，不会保存这个引用，而是继续指向s3
        String s4 =  new StringBuilder("123456").append("789").toString();
        System.out.println(s3.intern()==s3);
        System.out.println(s4.intern()==s4);
        System.out.println(s4.intern()==s3);
        // 创建了两个对象，在直接new指令前先去字符串常量池去检查是否有这个对象如果没有那么先在字符串常量池创建这个对象然后再去堆上创建对象
        String s5 = new String("hello");
        // Byte,Short,Integer,Long,Character这5种整型的包装类也实现了常量池技术，很显然在堆上，默认-128 ~ 127 缓存
        Integer a = 127;
        Integer b = 127;
        System.out.println(a==b);
        Integer c = new Integer(127);
        System.out.println(c==a);
        // 超出了包装类的缓存区，所以在常量池里没有
        Integer d = 128;
        Integer e = 128;
        System.out.println(d==e);

        List<String> strings = Arrays.asList("1","2","3");
        strings.forEach(System.out::print);
        strings.forEach(s6 -> {
            System.out.println(s6);
        });
        Stream<String> stringStream = strings.stream();
        stringStream.forEach(System.out::print);
        stringStream.forEach(System.out::print);
    }
}
