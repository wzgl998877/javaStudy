package com.zt.javastudy.grammar;

/**
 * 有序性测试
 *
 * @author zhengtao on 2021/8/3
 */
public class OrderTest {
    private static int num = 0;
    private static boolean ready = false;

    public static void main(String[] args) throws InterruptedException {
        Thread read = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    if (ready) { // 1
                        System.out.println(num + num);// 2
                    }
                }
            }
        });
        Thread write = new Thread(new Runnable() {
            @Override
            public void run() {
                num = 2;// 3
                ready = true;// 4
            }
        });
        read.start();
        write.start();
        Thread.sleep(1);
        read.interrupt();
        System.out.println("exit");
    }
}
