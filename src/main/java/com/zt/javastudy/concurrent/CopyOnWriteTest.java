package com.zt.javastudy.concurrent;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * copyonwritelist 学习
 *
 * @author zhengtao on 2021/11/26
 */
public class CopyOnWriteTest extends Test{
    public void test() {
        Test.Test1 test1 = new Test.Test1();
    }
    public static void main(String[] args) {
        // 初始化一个list，放入5个元素
        final CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
//        final List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        CopyOnWriteArrayList<Integer> copy = new CopyOnWriteArrayList<>();
        copy.add(123);
        copy.get(0);
        copy.set(0, 456);
        copy.remove(0);
        // 线程一：通过Iterator遍历List
        new Thread(new Runnable() {
            @Override
            public void run() {
                list.forEach(item -> {
                            System.out.println("遍历元素：" + item);
                            // 由于程序跑的太快，这里sleep了1秒来调慢程序的运行速度
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                );

            }
        }).start();

        // 线程二：remove一个元素
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 由于程序跑的太快，这里sleep了1秒来调慢程序的运行速度
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                list.remove(4);
                System.out.println("list.remove(4)");
            }
        }).start();
    }
}
