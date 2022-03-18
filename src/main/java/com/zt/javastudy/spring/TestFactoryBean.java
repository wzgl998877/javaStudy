package com.zt.javastudy.spring;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author zhengtao
 */
@Component
public class TestFactoryBean implements FactoryBean<Car> {
    @Override
    public Car getObject() throws Exception {
        return new Moto();
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
