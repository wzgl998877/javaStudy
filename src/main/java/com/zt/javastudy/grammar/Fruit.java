package com.zt.javastudy.grammar;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解学习
 *
 * @author zhengtao on 2021/9/16
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Fruit {
    String genName() default "";
    String genColor() default "";
}
