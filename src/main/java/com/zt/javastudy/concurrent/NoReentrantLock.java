package com.zt.javastudy.concurrent;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 基于aqs实现一个不可重入的独占锁
 *
 * @author zhengtao on 2021/11/27
 */
public class NoReentrantLock implements Lock, Serializable {
    /**
     * 继承aqs
     */
    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        /**
         * 判断锁是否被持有
         */
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        @Override
        /**
         * 如果state为0，则尝试获取锁
         */
        public boolean tryAcquire(int acquires) {
            assert acquires == 1;
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        /**
         * 尝试释放锁，设置state为0
         */
        protected boolean tryRelease(int arg) {
            assert arg == 1;
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        /**
         * 提供条件变量接口
         *
         * @return
         */
        Condition newCondition() {
            return new ConditionObject();
        }

    }

    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }
}
