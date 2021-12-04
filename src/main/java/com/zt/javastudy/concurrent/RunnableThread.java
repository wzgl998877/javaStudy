package com.zt.javastudy.concurrent;

/**
 * @author zhengtao on 2021/12/1
 */
public class RunnableThread implements Runnable{
    @Override
    public void run() {
        System.out.println("实现Runnable接口来实现线程");
    }
}
