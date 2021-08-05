package com.zt.javastudy.grammar;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 测试并发list
 *
 * @author zhengtao on 2021/8/5
 */
public class CopyList {
    private static volatile CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        list.add("hello");
        list.add("zhengtao");
        list.add("ni");
        list.add("hao");
        list.add("shuai");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                list.set(1, "wzgl");
                list.remove(2);
                list.remove(3);
            }
        });
        // 线程在子线程执行完毕后使用获取的迭代器遍历数组元素，从输出结果我们知道，在子线程里面进行的操作一个都没有生效，
        // 这就是迭代器弱一致性的体现。需要注意的是，获取迭代器的操作必须在子线程操作之前进行。
        Iterator<String> iterator = list.iterator();
        thread.start();
        thread.join();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
