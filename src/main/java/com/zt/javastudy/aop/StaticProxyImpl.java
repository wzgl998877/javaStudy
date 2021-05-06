package com.zt.javastudy.aop;

import com.zt.javastudy.aop.StaticProxy;

/**
 * @author zhengtao
 * @description
 * @date 2021/5/6
 */
public class StaticProxyImpl implements StaticProxy {
    @Override
    public void test() {
        System.out.println("test");
    }
}
