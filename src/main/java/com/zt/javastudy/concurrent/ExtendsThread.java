package com.zt.javastudy.concurrent;

/**
 * @author zhengtao on 2021/12/1
 */
public class ExtendsThread extends Thread {
    @Override
    public void run() {
        System.out.println("继承Thread类来实现线程");
    }
}

