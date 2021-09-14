package com.zt.javastudy.grammar;


import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zhengtao
 * @description 比较器学习
 * @date 2021/5/6
 */
public class ComparatorStudy {


    public static void main(String[] args) {
        int[][] doubleNums = {{1, 2}, {22, 3}, {1, 3}, {21, 4}};
        // 对于二维数组需要将每一个元素都看作一个数组进行排序，输入的是两个数组，,按照第二个元素升序则是return a[0]
        // 如果按照第一个元素升序则是return a[0] - b[0]
        Arrays.sort(doubleNums, (int[] a, int[] b) -> (a[0] - b[0]));
        for (int i = 0; i < doubleNums.length; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(doubleNums[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        // 如果按照第二个元素升序则是a[1] - b[1]
        Arrays.sort(doubleNums, Comparator.comparingInt((int[] a) -> a[1]));
        for (int i = 0; i < doubleNums.length; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(doubleNums[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        // 按照第一个元素升序，第二个元素降序
        Arrays.sort(doubleNums, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });
        for (int i = 0; i < doubleNums.length; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(doubleNums[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        int[] num = {12, 41, 213, 12, 3, 12};
        Arrays.sort(num);
        System.out.println(Arrays.toString(num));
        // 对于基本类型需要转为包装类型，才能使用comparator接口,原有的sort只是升序如果自己想要降序的话那就必须使用包装类了
        Integer[] nums = {12, 41, 213, 12, 3, 12};
        Arrays.sort(nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println(Arrays.toString(nums));
    }

}
