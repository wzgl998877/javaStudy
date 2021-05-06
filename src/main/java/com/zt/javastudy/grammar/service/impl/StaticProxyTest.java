package com.zt.javastudy.grammar.service.impl;

import com.zt.javastudy.grammar.StaticProxy;

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
