package com.zt.javastudy.aop;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zhengtao
 * @description cglib代理
 * @date 2021/4/29
 */
public class CgLibProxy  implements MethodInterceptor {
    // 目标对象
    private Object target;

    public CgLibProxy(Object target) {
        super();
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before test");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("after test");
        return result;
    }
    public Object getProxy(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        Object proxy = enhancer.create();
        return proxy;
    }
}
