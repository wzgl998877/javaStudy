package com.zt.javastudy.leetcode;

/**
 * 数组刷题
 *
 * @author zhengtao on 2021/12/4
 */
public class Array {
    public static void main(String[] args) {
        Array array = new Array();
        int[] piles = {312884470};
        System.out.println(array.minEatingSpeed1(piles, 968709470));
        int[] weights = {1,2,3,1,1};
        System.out.println(array.shipWithinDays(weights, 4));
    }

    /**
     * 875. 爱吃香蕉的珂珂
     * 珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
     * <p>
     * 珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
     * <p>
     * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
     * <p>
     * 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入: piles = [3,6,7,11], H = 8
     * 输出: 4
     * 示例 2：
     * <p>
     * 输入: piles = [30,11,23,4,20], H = 5
     * 输出: 30
     * 示例 3：
     * <p>
     * 输入: piles = [30,11,23,4,20], H = 6
     * 输出: 23
     * 题目可以理解为，每小时最少吃1，最大吃getMax(piles),求的就是从1开始，能吃完的最小速度是多少？
     * 这就相当于二分法求最左边界
     *
     * @param piles
     * @param h
     * @return
     */
    public int minEatingSpeed(int[] piles, int h) {
        int max = getMax(piles);
        for (int speed = 1; speed <= max; speed++) {
            if (canEat(piles, speed, h)) {
                return speed;
            }
        }
        return max;
    }

    public int minEatingSpeed1(int[] piles, int h) {
        int left = 1, right = getMax(piles);
        while (left <= right) {
            int middle = (left + right) / 2;
            if (canEat(piles, middle, h)) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    private boolean canEat(int[] piles, int speed, int h) {
        int time = 0;
        for (int i : piles) {
            if (i % speed == 0) {
                time += i / speed;
            } else {
                time += i / speed + 1;
            }
        }
        return time <= h;
    }

    private int getMax(int[] piles) {
        int max = 0;
        for (int i : piles) {
            max = Math.max(max, i);
        }
        return max;
    }

    /**
     * 1011. 在 D 天内送达包裹的能力
     * 传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
     * <p>
     * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
     * <p>
     * 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
     * 输出：15
     * 解释：
     * 船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
     * 第 1 天：1, 2, 3, 4, 5
     * 第 2 天：6, 7
     * 第 3 天：8
     * 第 4 天：9
     * 第 5 天：10
     * <p>
     * 请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。
     * 示例 2：
     * <p>
     * 输入：weights = [3,2,2,4,1,4], D = 3
     * 输出：6
     * 解释：
     * 船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
     * 第 1 天：3, 2
     * 第 2 天：2, 4
     * 第 3 天：1, 4
     * 示例 3：
     * <p>
     * 输入：weights = [1,2,3,1,1], D = 4
     * 输出：3
     * 解释：
     * 第 1 天：1
     * 第 2 天：2
     * 第 3 天：3
     * 第 4 天：1, 1
     *
     * @param weights
     * @param days
     * @return
     */
    public int shipWithinDays(int[] weights, int days) {
        int left = 1, right = sum(weights);
        while (left <= right) {
            int middle = (left + right) / 2;
            if (canWeight(weights, middle, days)) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    private boolean canWeight(int[] weights, int middle, int days) {
        int sum = 0;
        for (int i : weights) {
            if (i > middle) {
                return false;
            }
            if (sum + i > middle) {
                days--;
                sum = i;
            } else {
                sum += i;
            }
        }
        return days > 0;
    }

    private int sum(int[] weights) {
        int sum = 0;
        for (int i : weights) {
            sum += i;
        }
        return sum;
    }

}
