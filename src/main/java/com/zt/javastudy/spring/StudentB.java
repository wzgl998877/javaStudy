package com.zt.javastudy.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @author zhengtao
 * @description 测试循环依赖
 * @date 2021/4/9
 */
// 原型模式
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Service
public class StudentB {
    @Autowired
    private StudentA a;

    public StudentA getA() {
        return a;
    }

    public void setA(StudentA a) {
        this.a = a;
    }

    /**
     * 构造函数循环依赖
     * @param a
     */
   /* public StudentB(StudentA a) {
        this.a = a;
    }*/
}
