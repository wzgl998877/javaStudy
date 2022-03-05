package com.zt.javastudy.leetcode;

/**
 * @author zhengtao
 * 算法复杂度分析
 */
public class Time {
    /**
     * 0(n) 时间复杂度,一个循环
     */
    public void test1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    /**
     * O(log n)，对数时间,常见的具有对数时间的算法有二叉树的相关操作和二分搜索。
     * 可以理解为每次都缩小了一半
     */
    public int binary_search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 直接返回
                return mid;
            }
        }
        // 直接返回
        return -1;
    }

    /**
     * o(nlogn),内循环是O(log n)
     */
    public void function3(long n) {
        System.out.println("o(nlogn)算法");
        long k = 0;
        for (long i = 0; i < n; i++) {
            // O(log n)
            for (long j = 1; j < n; j = j * 2) {
                k++;
            }
        }
    }

    /**
     * o(n^2),两层循环
     *
     * @param n
     */
    public static void function2(long n) {
        System.out.println("o(n^2)算法");
        long k = 0;
        for (long i = 0; i < n; i++) {
            for (long j = 0; j < n; j++) {
                k++;
            }
        }


    }
}
