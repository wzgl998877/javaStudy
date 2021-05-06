package com.zt.javastudy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author zhengtao
 * @description 使用aop相关的注解
 * @date 2021/4/26
 */
@Component
@Aspect
public class AopRun {
    // 定义切面
    @Pointcut("execution(* com.zt.javastudy.grammar.*.test(..))")
    public void test(){

    }

    @Before("test()")
    public void beforeTest(){
        System.out.println("beforeTest");
    }

    @After("test()")
    public void afterTest(){
        System.out.println("afterTest");
    }

    @Around("test()")
    public void arountTest(ProceedingJoinPoint point){
        System.out.println("around1");
        try {
            point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("around2");
    }

}
