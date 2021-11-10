package com.zt.javastudy.concurrent;

/**
 * wait test
 *
 * @author zhengtao on 2021/11/3
 */
public class WaitTest implements Runnable {
    public static final Object lock = new Object();
    public static int i = 1;
    public static int j = 1;

    @Override
    public void run() {
        while (i <= 100) {
            synchronized (lock) {
                System.out.println(j++);
                System.out.println(Thread.currentThread().getName() + ":" + i);
                i++;
                lock.notify();
                if (i <= 100) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new WaitTest(), "奇数").start();
        new Thread(new WaitTest(), "偶数").start();
    }


}
