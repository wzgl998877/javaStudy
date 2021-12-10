package com.zt.javastudy.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Lock_ABC {

    private int num;   // 当前状态值：保证三个线程之间交替打印
    private Lock lock = new ReentrantLock();


    private void printABC(int targetNum) {
        for (int i = 0; i < 10; ) {
            lock.lock();
            if (num % 3 == targetNum) {
                num++;
                i++;
                System.out.print(Thread.currentThread().getName());
            }
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Lock_ABC lockABC = new Lock_ABC();

        new Thread(() -> {
            lockABC.printABC(0);
        }, "A").start();

        new Thread(() -> {
            lockABC.printABC(1);
        }, "B").start();

        new Thread(() -> {
            lockABC.printABC(2);
        }, "C").start();
    }
}