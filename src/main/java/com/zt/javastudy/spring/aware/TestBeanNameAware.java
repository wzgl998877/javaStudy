package com.zt.javastudy.spring.aware;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 测试BeanNameAware，
 * @author zhengtao
 */
@Component("测试beanName")
public class TestBeanNameAware implements BeanNameAware {
    @Override
    public void setBeanName(String s) {
        System.out.println(s);
    }
}
