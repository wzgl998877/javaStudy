package com.zt.javastudy.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池学习
 *
 * @author zhengtao on 2021/11/30
 */
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        Thread thread =  new Thread(new RunnableThread());
        ExtendsThread thread1 = new ExtendsThread();
        thread.start();
        thread1.start();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(thread);
    }
}
