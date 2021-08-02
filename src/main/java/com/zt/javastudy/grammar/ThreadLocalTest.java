package com.zt.javastudy.grammar;

/**
 * threadLocal学习
 *
 * @author zhengtao on 2021/8/2
 */
public class ThreadLocalTest {
    static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();
    static final InheritableThreadLocal<String> INHERITABLE_THREAD_LOCAL = new InheritableThreadLocal<>();
    static void print(String s){
        System.out.println(s + ":" + THREAD_LOCAL.get());
        THREAD_LOCAL.remove();
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                THREAD_LOCAL.set("thread1 local var");
                print("thread1");
                System.out.println("thread1" + ":" + THREAD_LOCAL.get());
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                THREAD_LOCAL.set("thread2 local var");
                print("thread2");
                System.out.println("thread2" + ":" + THREAD_LOCAL.get());
            }
        });
        thread1.start();
        thread2.start();
        THREAD_LOCAL.set("main local var");
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread3" + ":" + THREAD_LOCAL.get());
            }
        });
        thread3.start();
        INHERITABLE_THREAD_LOCAL.set("main local var");
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread4" + ":" + INHERITABLE_THREAD_LOCAL.get());
            }
        });
        thread4.start();
    }
}
