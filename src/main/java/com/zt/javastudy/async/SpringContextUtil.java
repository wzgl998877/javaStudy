package com.zt.javastudy.async;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author lujianyuan
 * @date 2019-07-28
 * @desc spring容器工具类
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext APPLICATIONCONTEXT;
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        APPLICATIONCONTEXT = context;
    }

    /**
     * 根据名字获取bean对象
     * @param name
     * @param <T>
     * @return
     * @throws BeansException
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) throws BeansException {
        return (T) APPLICATIONCONTEXT.getBean(name);
    }

    public static <T> T getBean(Class<T> requiredType){
        return  APPLICATIONCONTEXT.getBean(requiredType);
    }

    /**
     * 获取所有的实现接口
     * @param requiredType
     * @return
     */
    public static <T> Map<String,T> getBeanOfTypes(Class<T> requiredType){

        return APPLICATIONCONTEXT.getBeansOfType(requiredType);
    }



}
