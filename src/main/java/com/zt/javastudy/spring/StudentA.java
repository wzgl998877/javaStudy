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
public class StudentA {
    @Autowired
    private StudentB b;

    public StudentB getB() {
        return b;
    }

    public void setB(StudentB b) {
        this.b = b;
    }

    /**
     * 构造函数循环依赖
     * @param b
     */
    /*public StudentA(StudentB b) {
        this.b = b;
    }*/
}
