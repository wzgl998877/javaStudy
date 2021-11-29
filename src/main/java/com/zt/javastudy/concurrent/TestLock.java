package com.zt.javastudy.concurrent;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;

/**
 * 测试NoReentrantLock锁
 *
 * @author zhengtao on 2021/11/27
 */
public class TestLock {
    final static NoReentrantLock LOCK = new NoReentrantLock();
    /**
     * 消费者条件变量
     */
    final static Condition NOT_FULL = LOCK.newCondition();
    /**
     * 生产者条件变量
     */
    final static Condition NOT_EMPTY = LOCK.newCondition();
    final static Queue<String> QUEUE = new LinkedBlockingDeque<>();
    final static int SIZE = 10;

    public static void main(String[] args) {
        Thread producer = new Thread(() -> {
            LOCK.lock();
            try {
                // 如果队列满了，则等待
                while (QUEUE.size() == SIZE) {
                    NOT_EMPTY.await();
                }
                // 添加元素到队列
                QUEUE.add("ele");
                System.out.println("生产一个元素");
                // 唤醒消息线程
                NOT_FULL.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                LOCK.unlock();
            }
        });

        Thread customer = new Thread(() -> {
            LOCK.lock();
            try {
                // 如果队列为空，消费者等待
                while (QUEUE.size() == 0) {
                    NOT_FULL.await();
                }
                // 消费一个元素
                String ele = QUEUE.poll();
                System.out.println("消费一个元素");
                // 唤醒生产者
                NOT_FULL.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                LOCK.unlock();
            }
        });
        producer.start();
        customer.start();
    }
}
