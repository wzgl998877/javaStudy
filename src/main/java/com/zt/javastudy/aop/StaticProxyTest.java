package com.zt.javastudy.aop;

import com.zt.javastudy.aop.StaticProxy;

/**
 * @author zhengtao
 * @description
 * @date 2021/5/6
 */
public class StaticProxyTest implements StaticProxy {
    private StaticProxy staticProxy;

    public StaticProxyTest(StaticProxy staticProxy) {
        this.staticProxy = staticProxy;
    }

    @Override
    public void test() {
        System.out.println("before test");
        this.staticProxy.test();
        System.out.println("after test");
    }
}
