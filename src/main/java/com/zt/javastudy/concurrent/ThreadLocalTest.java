package com.zt.javastudy.concurrent;

/**
 * ThreadLocal 测试
 *
 * @author zhengtao on 2021/11/10
 */
public class ThreadLocalTest implements Runnable {
    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    public static ThreadLocal<Integer> integerThreadLocal = new InheritableThreadLocal<>();
    public static int i = 0;

    public static void main(String[] args) {
        ThreadLocalTest threadLocalTest = new ThreadLocalTest();
        threadLocal.set(i++);
        integerThreadLocal.set(i++);
        Thread thread1 = new Thread(threadLocalTest);
        thread1.start();
        System.out.println("maint中hreadLocal值为：" + threadLocal.get());
        System.out.println("main中InheritableThreadLocal值为：" + integerThreadLocal.get());
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "threadLocal值为:" + threadLocal.get());
        System.out.println(Thread.currentThread().getName() + "InheritableThreadLocal值为:" + integerThreadLocal.get());
    }
}
