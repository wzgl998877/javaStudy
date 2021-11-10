package com.zt.javastudy.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * n个线程按顺序打印从0 到N
 * @author zhengtao
 */
public class MultiThreadOrdering {
    private final AtomicInteger sign = new AtomicInteger(0);
    private static final Object lock = new Object();
    private int k;
    private int flag;

    public MultiThreadOrdering(int k, int flag) {
        this.k = k;
        this.flag = flag;
    }

    private void printAlpha(int num) {
        new Thread(() -> {
            while (sign.intValue() < flag) {
                synchronized (lock) {
                    if (sign.intValue() % k != num) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.print((char) (num + 'A'));
                        sign.incrementAndGet();
                        System.out.println(" 打印后的sign值为：" + sign);
                        lock.notifyAll();
                    }
                }

            }
        }
        ).start();
    }


    public static void main(String[] args) throws InterruptedException {
        int k = 5;
        MultiThreadOrdering demo = new MultiThreadOrdering(k, 11);
        for (int i = 0; i < k; i++) {
            demo.printAlpha(i);
        }
    }

}
