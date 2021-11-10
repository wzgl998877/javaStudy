package com.zt.javastudy.concurrent;

/**
 * yield学习
 *
 * @author zhengtao on 2021/11/10
 */
public class YieldTest implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            if (i % 5 == 0) {
                System.out.println(Thread.currentThread().getName()+ "yiled");
                Thread.yield();
            }
        }
        System.out.println("完成");
    }

    public static void main(String[] args) {
        YieldTest yieldTest = new YieldTest();
        Thread threadA = new Thread(yieldTest);
        Thread threadB = new Thread(yieldTest);
        threadA.start();
        threadB.start();
    }
}

