package com.zt.javastudy.concurrent;

class  Wait_Notify_Odd_Even{

    private Object lock = new Object();
    private volatile int count;

    private void printOddEven() {
        synchronized (lock) {
            while (count < 10) {
                try {
                    System.out.print( Thread.currentThread().getName() + "：");
                    System.out.println(++count);
                    lock.notifyAll();
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //防止count=10后，while()循环不再执行，有子线程被阻塞未被唤醒，导致主线程不能退出
            lock.notifyAll();
        }
    }
    
    public static void main(String[] args) throws InterruptedException {

        Wait_Notify_Odd_Even waitNotifyOddEven = new Wait_Notify_Odd_Even();
        new Thread(waitNotifyOddEven::printOddEven, "奇数").start();
        Thread.sleep(10); //为了保证线程odd先拿到锁
        new Thread(waitNotifyOddEven::printOddEven, "偶数").start();
    }
}