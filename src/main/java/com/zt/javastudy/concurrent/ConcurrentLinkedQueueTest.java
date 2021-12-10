package com.zt.javastudy.concurrent;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 学习队列
 *
 * @author zhengtao on 2021/12/7
 */
public class ConcurrentLinkedQueueTest {
    ConcurrentLinkedQueue<Integer> linkedQueue = new ConcurrentLinkedQueue<>();


    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();
//        blockingQueue.put();
    }
}

