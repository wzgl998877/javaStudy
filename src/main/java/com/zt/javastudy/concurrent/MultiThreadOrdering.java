package com.zt.javastudy.concurrent;

/**
 * n个线程按顺序打印从0 到N
 *
 * @author zhengtao
 */
public class MultiThreadOrdering {
    private int sign = 0;
    private static final Object lock = new Object();
    private int k;
    private int flag;

    public MultiThreadOrdering(int k, int flag) {
        this.k = k;
        this.flag = flag;
    }

    private void printAlpha(int num) {
        new Thread(() -> {
            while (sign < flag) {
                synchronized (lock) {
                    if (sign % k != num) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.print((char) (num + 'A'));
                        sign++;
                        System.out.println(" 打印后的sign值为：" + sign);
                        lock.notifyAll();
                    }
                }

            }
        }
        ).start();
    }


    public static void main(String[] args) throws InterruptedException {
        int k = 5;
        MultiThreadOrdering demo = new MultiThreadOrdering(k, 50);
        for (int i = 0; i < k; i++) {
            demo.printAlpha(i);
        }
    }

}
