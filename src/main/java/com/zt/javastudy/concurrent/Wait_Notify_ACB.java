package com.zt.javastudy.concurrent;

class Wait_Notify_ACB {

    private int num;
    private static final Object LOCK = new Object();

    private void printABC(int targetNum) {
        for (int i = 0; i < 10; i++) {
            synchronized (LOCK) {
                while (num % 3 != targetNum) { //想想这里为什么不能用if代替，想不起来可以看公众号上一篇文章
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                num++;
                System.out.print(Thread.currentThread().getName());
                LOCK.notifyAll();
            }
        }
    }
    public static void main(String[] args) {
        Wait_Notify_ACB  wait_notify_acb = new Wait_Notify_ACB ();
        new Thread(() -> {
            wait_notify_acb.printABC(0);
        }, "A").start();
        new Thread(() -> {
            wait_notify_acb.printABC(1);
        }, "B").start();
        new Thread(() -> {
            wait_notify_acb.printABC(2);
        }, "C").start();
    }    
}