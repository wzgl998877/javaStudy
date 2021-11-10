package com.zt.javastudy.concurrent;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 一些并发编程的源码研究
 *
 * @author zhengtao on 2021/10/29
 */
public class Day1 {
    public static void main(String[] args) throws InterruptedException {
//        Random random = new Random();
//        for (int i = 0; i < 10; i++) {
//            System.out.println(random.nextInt(5));
//        }
//        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
//        for (int i = 0; i < 10; i++) {
//            System.out.println(threadLocalRandom.nextInt(5));
//        }
        Thread threadA = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread A 执行完了");
        });
        Thread threadB = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread B 执行完了");
        });
        threadA.start();
        threadB.start();
        System.out.println("123");
        threadA.join();
        System.out.println("456");
        threadB.join();
        System.out.println("主线程执行完了");
    }
}
