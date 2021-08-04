package com.zt.javastudy.grammar;

import java.util.concurrent.atomic.AtomicLong;

/**
 * java中一些线程安全的类测试
 *
 * @author zhengtao on 2021/8/4
 */
public class Atomic {
    private static AtomicLong atomicLong = new AtomicLong();
    private static int a = 0;
    private static int[] one = {0,1,0,1,1,0};
    private static int[] two = {0,1,0,0,0,0,1,1,0};

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i : one){
                    if (i == 0){
                        atomicLong.incrementAndGet();
                        a++;
                    }
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i : two){
                    if (i == 0){
                        atomicLong.incrementAndGet();
                        a++;
                    }
                }
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(atomicLong.get());
        System.out.println(a);
    }
}
