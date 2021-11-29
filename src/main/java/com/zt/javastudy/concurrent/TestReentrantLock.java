package com.zt.javastudy.concurrent;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 学习可重入独占锁ReentrantLock
 *
 * @author zhengtao on 2021/11/27
 */
public class TestReentrantLock {
    // 线程不安全的list
    private ArrayList<String> array = new ArrayList<>();
    // 独占锁
    private volatile ReentrantLock lock = new ReentrantLock();

    public void add(String s) {
        lock.lock();
        try {
            array.add(s);
        } finally {
            lock.unlock();
        }
    }
    public void remove(String s) {
        lock.lock();
        try {
            array.remove(s);
        } finally {
            lock.unlock();
        }
    }
    public String get(int index) {
        lock.lock();
        try {
            return array.get(index);
        } finally {
            lock.unlock();
        }

    }
}
