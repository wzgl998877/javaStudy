package com.zt.javastudy.grammar;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhengtao
 */
public class MultiThreadOrdering implements Runnable{
    private final AtomicInteger sign = new AtomicInteger(0);
    private static final Object lock = new Object();


    private void printAlpha(int num) {
        new Thread(() -> {
//            while (sign.intValue() < 50) {
//                synchronized (s) {
//                    if (sign.intValue() % 2 == num) {
//                        System.out.print((char) (num + 'A'));
//                        sign.incrementAndGet();
//                        System.out.println(" 打印后的sign值为：" + sign);
//                        s.notifyAll();
//                        if (sign.intValue() <= 50) {
//                            try {
//                                s.wait();
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        } else {
//                            s.notifyAll();
//                        }
//
//                    }
//
//                }
//            }
            while (sign.intValue() < 50) {
                synchronized (lock) {
                    System.out.print((char) (num + 'A'));
                    sign.incrementAndGet();
                    System.out.println(" 打印后的sign值为：" + sign);

                    lock.notify(); // 唤醒另一个线程
                    if (sign.intValue() <= 50) {
                        try {
                            lock.wait(); // 释放锁，进入阻塞状态，等待被唤醒
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }).start();
    }


    public static void main(String[] args) throws InterruptedException {
//        MultiThreadOrdering demo = new MultiThreadOrdering();
//        demo.printAlpha(0);
//        Thread.sleep(50);
//        demo.printAlpha(1);
//        demo.printAlpha(2);
        new Thread(new MultiThreadOrdering(), "A").start();
        Thread.sleep(50);
        new Thread(new MultiThreadOrdering(), "B").start();
    }

    @Override
    public void run() {
        while (sign.intValue() < 50) {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + ":" + sign);
                sign.incrementAndGet();
                lock.notify(); // 唤醒另一个线程
                if (sign.intValue() <= 50) {
                    try {
                        lock.wait(); // 释放锁，进入阻塞状态，等待被唤醒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
