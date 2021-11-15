package com.zt.javastudy.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * 学习longAddr类
 *
 * @author zhengtao on 2021/11/11
 */
public class LongAddrTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.forEach(i -> {
            if (i == 1) {
                list.remove(i);
            }
        });
    }

}
