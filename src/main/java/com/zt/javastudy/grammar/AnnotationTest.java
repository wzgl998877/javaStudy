package com.zt.javastudy.grammar;

/**
 * 注解测试
 *
 * @author zhengtao on 2021/9/16
 */
public class AnnotationTest {
    public static void main(String[] args) {
        Apple apple = new Apple();
        Fruit fruit = apple.getClass().getAnnotation(Fruit.class);
        System.out.println(fruit.genName());
        System.out.println(fruit.genColor());
    }
}
