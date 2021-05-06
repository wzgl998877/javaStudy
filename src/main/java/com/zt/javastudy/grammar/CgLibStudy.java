package com.zt.javastudy.grammar;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zhengtao
 * @description cglib学习
 * @date 2021/4/29
 */
public class CgLibStudy {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CgLibStudy.class);
        enhancer.setCallback(new CgLibInterceptorImpl());
        CgLibStudy cgLibStudy = (CgLibStudy) enhancer.create();
        cgLibStudy.test();
    }
    public void test(){
        System.out.println("test");
    }
    private static class CgLibInterceptorImpl implements MethodInterceptor{

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("before test");
            Object result = methodProxy.invokeSuper(o, objects);
            System.out.println("after test");
            return result;
        }
    }
}
