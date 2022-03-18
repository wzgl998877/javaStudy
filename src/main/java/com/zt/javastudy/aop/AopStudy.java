package com.zt.javastudy.aop;

import org.springframework.stereotype.Component;

/**
 * @author zhengtao
 * @description aop学习
 * @date 2021/4/26
 */
@Component
public class AopStudy {
    // 连接点
    public void test(){
        System.out.println("真正的方法执行啦");
    }
}
