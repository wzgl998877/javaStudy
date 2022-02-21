package com.zt.javastudy.leetcode;

import java.util.*;

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
        int[] weights = {1, 2, 3, 1, 1};
        System.out.println(array.shipWithinDays(weights, 4));
        int[] nums = {94, 92, 90, 57, 6, 89, 63, 15, 91, 74};
        System.out.println(array.splitArray(nums, 6));
        System.out.println(array.minTime(nums, 6));
        System.out.println(array.spiltTime(nums, 59));
        int[] bloom = {1, 10, 2, 9, 3, 8, 4, 7, 5, 6};
        System.out.println(array.minDays(bloom, 4, 2));
        int[] position = {5, 4, 3, 2, 1, 1000000000};
        System.out.println(array.maxDistance(position, 2));
        System.out.println(array.divide(-2147483648, -3));
        System.out.println(array.divide1(10, 3));
        int[] searchNums = {5, 5, 5, 1, 2, 3, 4, 5};
        System.out.println(array.search(searchNums, 0));
        System.out.println(array.search2(searchNums, 0));
        System.out.println(array.searchII(searchNums, 1));
        System.out.println(array.searchII2(searchNums, 1));
        System.out.println(array.searchIII(searchNums, 5));
        System.out.println(array.searchIII2(searchNums, 5));
        System.out.println(array.searchIII3(searchNums, 5));
        int[] range = {7};
        int[] result = array.searchRange(range, 7);
        for (int i : result) {
            System.out.println(i);
        }
        System.out.println(array.minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(array.checkInclusion("ab", "eidboaoo"));
        System.out.println(array.checkInclusion1("ab", "eidboaoo"));
        System.out.println(array.checkInclusion2("hello", "ooolleoooleh"));


        int[] arg = {1, 12, -5, -6, 50, 3};
        System.out.println(array.findMaxAverage(arg, 4));
        System.out.println(array.findMaxAverage1(arg, 4));
        int[] A = {15448, 14234, 13574, 19893, 6475};
        int[] B = {14234, 6475, 19893, 15448, 13574};
        int[] c = array.advantageCount(A, B);
        int[] d = array.advantageCount1(A, B);
        for (int i : c) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : d) {
            System.out.print(i + " ");
        }
        System.out.println();
        int[] numsTarget = {2, 3, 1, 2, 4, 3};
        System.out.println(array.minSubArrayLen(7, numsTarget));
        int[] fruits = {3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4};
        System.out.println(array.totalFruit(fruits));
        int[] twoNums = {3, 2, 3};
        int[] twoSum = array.twoSum(twoNums, 6);
        for (int i : twoSum) {
            System.out.print(i + " ");
        }
        System.out.println();
        int[] threeSum = {2, 2, 2, 2, 2};
        System.out.println(array.threeSum(threeSum));
        System.out.println(array.fourSum(threeSum, 8));
        RandomizedCollection randomizedCollection = new RandomizedCollection();
        System.out.println(randomizedCollection.insert(1));
        System.out.println(randomizedCollection.remove(1));
        System.out.println(randomizedCollection.insert(1));
        int[] remove = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(array.removeElement(remove, 2));
        System.out.println(array.removeDuplicates(remove));
        int[] sort = {-7, -3, 2, 3, 11};
        System.out.println(Arrays.toString(array.sortedSquares(sort)));
        int[] nums1 = {3, 4, 6, 5};
        int[] nums2 = {9, 1, 2, 5, 8, 3};
        System.out.println(Arrays.toString(array.maxNumber(nums1, nums2, 5)));
        System.out.println(array.replaceSpace("We are happy."));
        int[] height = {4, 3, 2, 1, 4};
        System.out.println(array.trap(height));
        System.out.println(array.trap1(height));
        System.out.println(array.trap2(height));
        System.out.println(array.trap3(height));
        System.out.println(array.trap4(height));
        System.out.println(array.maxArea(height));
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        System.out.println(array.matrix(matrix));
        System.out.println(array.matrix1(matrix));
        int[] next = {1, 3, 2};
        array.nextPermutation(next);
        array.nextPermutation1(next);
        int[] miss = {1};
        System.out.println(array.firstMissingPositive(miss));
        System.out.println(array.firstMissingPositive2(miss));
        System.out.println(array.missingNumber(new int[]{2, 0}));
        int[][] path = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(array.minPathSum(path));
        System.out.println(array.minPathSum1(path));
        int[][] rotate = {{1, 2, 3}, {5, 6, 7}, {9, 10, 11}};
        array.rotate(rotate);
        String s = "wordgoodgoodgoodbestword";
        String[] words = {"word", "good", "best", "good"};
        System.out.println(array.findSubstring(s, words));
        System.out.println(array.findSubstring1(s, words));
        array.longestSubstring("ababbc",2);
        nums = new int[]{1,1,1,2,3};
        int k = 2;
        System.out.println(array.subarraysWithKDistinct(nums, k));
        nums = new int[]{1};
        k = 1;
        System.out.println(Arrays.toString(array.maxSlidingWindow(nums, k)));
    }

    /**
     * 所有的二分查找都来自于下面三个代码，1直接查找 binary_search
     *
     * @param nums
     * @param target
     * @return
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
     * 查找最左边界，
     * 因为我们需找到 target 的最左侧索引
     * 所以当 nums[mid] == target 时不要立即返回
     * 而要收紧右侧边界以锁定左侧边界
     *
     * @param nums
     * @param target
     * @return
     */
    public int left_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        // 最后要检查 left 越界的情况
        if (left >= nums.length || nums[left] != target)
            return -1;
        return left;
    }

    /**
     * 寻找最右边界
     * 因为我们需找到 target 的最右侧索引
     * 所以当 nums[mid] == target 时不要立即返回
     * 而要收紧左侧边界以锁定右侧边界
     *
     * @param nums
     * @param target
     * @return
     */
    public int right_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // 最后要检查 right 越界的情况
        if (right < 0 || nums[right] != target)
            return -1;
        return right;
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

    /**
     * 410. 分割数组的最大值
     * 给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。
     * <p>
     * 设计一个算法使得这 m 个子数组各自和的最大值最小。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [7,2,5,10,8], m = 2
     * 输出：18
     * 解释：
     * 一共有四种方法将 nums 分割为 2 个子数组。 其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
     * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
     * 示例 2：
     * <p>
     * 输入：nums = [1,2,3,4,5], m = 2
     * 输出：9
     * 示例 3：
     * <p>
     * 输入：nums = [1,4,4], m = 3
     * 输出：4
     *
     * @param nums
     * @param m
     * @return 现在题目是固定了m的值，让我们确定一个最大子数组和；所谓反向思考就是说，我们可以反过来，限制一个最大子数组和max，来反推最大子数组和为max时，至少可以将nums分割成几个子数组。
     * 因此思路就转为，如果我们找到一个最小max值，满足split(nums, max)和m相等，那么这个max值不就是符合题意的「最小的最大子数组和」吗？
     * <p>
     * 现在就简单了，我们只要对max进行穷举就行，那么最大子数组和max的取值范围是什么呢？
     * <p>
     * 显然，子数组至少包含一个元素，至多包含整个数组，所以「最大」子数组和的取值范围就是闭区间[max(nums), sum(nums)]，也就是最大元素值到整个数组和之间。
     */
    public int splitArray(int[] nums, int m) {
        int left = Arrays.stream(nums).max().getAsInt(), right = Arrays.stream(nums).sum();
        while (left <= right) {
            int middle = (left + right) / 2;
            if (spiltNums(nums, middle) <= m) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    /**
     * 在每个子数组和不超过 max 的条件下，
     * 计算 nums 至少可以分割成几个子数组
     *
     * @param nums
     * @param max
     * @return
     */
    public int spiltNums(int[] nums, int max) {
        int n = 0;
        int sum = 0;
        for (int i : nums) {
            if (sum + i > max) {
                sum = i;
                n++;
            } else {
                sum += i;
            }
        }
        return n + 1;
    }

    /**
     * LCP 12. 小张刷题计划
     * 为了提高自己的代码能力，小张制定了 LeetCode 刷题计划，他选中了 LeetCode 题库中的 n 道题，编号从 0 到 n-1，并计划在 m 天内按照题目编号顺序刷完所有的题目（注意，小张不能用多天完成同一题）。
     * <p>
     * 在小张刷题计划中，小张需要用 time[i] 的时间完成编号 i 的题目。此外，小张还可以使用场外求助功能，通过询问他的好朋友小杨题目的解法，可以省去该题的做题时间。为了防止“小张刷题计划”变成“小杨刷题计划”，小张每天最多使用一次求助。
     * <p>
     * 我们定义 m 天中做题时间最多的一天耗时为 T（小杨完成的题目不计入做题总时间）。请你帮小张求出最小的 T是多少。
     * <p>
     * 示例 1：
     * <p>
     * 输入：time = [1,2,3,3], m = 2
     * <p>
     * 输出：3
     * <p>
     * 解释：第一天小张完成前三题，其中第三题找小杨帮忙；第二天完成第四题，并且找小杨帮忙。这样做题时间最多的一天花费了 3 的时间，并且这个值是最小的。
     * <p>
     * 示例 2：
     * <p>
     * 输入：time = [999,999,999], m = 4
     * <p>
     * 输出：0
     * <p>
     * 解释：在前三天中，小张每天求助小杨一次，这样他可以在三天内完成所有的题目并不花任何时间。
     * <p>
     * <p>
     * <p>
     * 限制：
     * <p>
     * 1 <= time.length <= 10^5
     * 1 <= time[i] <= 10000
     * 1 <= m <= 1000
     *
     * @param time
     * @param m
     * @return
     */
    public int minTime(int[] time, int m) {
        int left = 0;
        int right = Arrays.stream(time).sum();
        while (left <= right) {
            int middle = (left + right) / 2;
            if (spiltTime(time, middle) <= m) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    /**
     * 在每个子数组和不超过 max 的条件下，
     * 计算 nums 至少可以分割成几个子数组
     *
     * @param nums
     * @param max
     * @return
     */
    public int spiltTime(int[] nums, int max) {
        int n = 0;
        int sum = 0;
        int time = 0;
        for (int i : nums) {
            if (time < i) {
                time = i;
            }
            if (sum + i > max + time) {
                sum = i;
                n++;
                time = i;
            } else {
                sum += i;
            }
        }
        return n + 1;
    }

    /**
     * 1482. 制作 m 束花所需的最少天数
     * 给你一个整数数组 bloomDay，以及两个整数 m 和 k 。
     * <p>
     * 现需要制作 m 束花。制作花束时，需要使用花园中 相邻的 k 朵花 。
     * <p>
     * 花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。
     * <p>
     * 请你返回从花园中摘 m 束花需要等待的最少的天数。如果不能摘到 m 束花则返回 -1 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：bloomDay = [1,10,3,10,2], m = 3, k = 1
     * 输出：3
     * 解释：让我们一起观察这三天的花开过程，x 表示花开，而 _ 表示花还未开。
     * 现在需要制作 3 束花，每束只需要 1 朵。
     * 1 天后：[x, _, _, _, _]   // 只能制作 1 束花
     * 2 天后：[x, _, _, _, x]   // 只能制作 2 束花
     * 3 天后：[x, _, x, _, x]   // 可以制作 3 束花，答案为 3
     * 示例 2：
     * <p>
     * 输入：bloomDay = [1,10,3,10,2], m = 3, k = 2
     * 输出：-1
     * 解释：要制作 3 束花，每束需要 2 朵花，也就是一共需要 6 朵花。而花园中只有 5 朵花，无法满足制作要求，返回 -1 。
     * 示例 3：
     * <p>
     * 输入：bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
     * 输出：12
     * 解释：要制作 2 束花，每束需要 3 朵。
     * 花园在 7 天后和 12 天后的情况如下：
     * 7 天后：[x, x, x, x, _, x, x]
     * 可以用前 3 朵盛开的花制作第一束花。但不能使用后 3 朵盛开的花，因为它们不相邻。
     * 12 天后：[x, x, x, x, x, x, x]
     * 显然，我们可以用不同的方式制作两束花。
     * 示例 4：
     * <p>
     * 输入：bloomDay = [1000000000,1000000000], m = 1, k = 1
     * 输出：1000000000
     * 解释：需要等 1000000000 天才能采到花来制作花束
     * 示例 5：
     * <p>
     * 输入：bloomDay = [1,10,2,9,3,8,4,7,5,6], m = 4, k = 2
     * 输出：9
     *
     * @param bloomDay
     * @param m
     * @param k
     * @return
     */
    public int minDays(int[] bloomDay, int m, int k) {
        int left = Arrays.stream(bloomDay).min().getAsInt(), right = Arrays.stream(bloomDay).max().getAsInt(), max = right;
        if (bloomDay.length < m * k) {
            return -1;
        }
        while (left <= right) {
            int middle = (left + right) / 2;
            if (bloom(bloomDay, m, k, middle)) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        if (left > max) {
            return -1;
        }
        return left;
    }

    public boolean bloom(int[] bloomDay, int m, int k, int day) {
        int sum = 0;
        for (int i : bloomDay) {
            if (i > day) {
                sum = 0;
            } else {
                sum++;
                if (sum >= k) {
                    sum = 0;
                    m--;
                }
            }
        }
        return m <= 0;
    }

    /**
     * 1552. 两球之间的磁力
     * 在代号为 C-137 的地球上，Rick 发现如果他将两个球放在他新发明的篮子里，它们之间会形成特殊形式的磁力。Rick 有 n 个空的篮子，第 i 个篮子的位置在 position[i] ，Morty 想把 m 个球放到这些篮子里，使得任意两球间 最小磁力 最大。
     * <p>
     * 已知两个球如果分别位于 x 和 y ，那么它们之间的磁力为 |x - y| 。
     * <p>
     * 给你一个整数数组 position 和一个整数 m ，请你返回最大化的最小磁力。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * <p>
     * 输入：position = [1,2,3,4,7], m = 3
     * 输出：3
     * 解释：将 3 个球分别放入位于 1，4 和 7 的三个篮子，两球间的磁力分别为 [3, 3, 6]。最小磁力为 3 。我们没办法让最小磁力大于 3 。
     * 示例 2：
     * <p>
     * 输入：position = [5,4,3,2,1,1000000000], m = 2
     * 输出：999999999
     * 解释：我们使用位于 1 和 1000000000 的篮子时最小磁力最大。
     * 跟前面一样转换思维
     * 题目是给定，分隔段数m，求能分隔出来的数组最小磁力最大
     * 可以转变为，如果能找到最大的最小磁力 max 使得distance(int[] position, int n) 刚好为m
     * 而max的取值范围就是 0 ，max(position) - min(position)
     * 这题和前面几题不一样的是求得是最大的max，即最右边界。
     *
     * @param position
     * @param m
     * @return
     */
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int length = position.length;
        int left = 0, right = position[length - 1] - position[0];
        while (left <= right) {
            int middle = (left + right) / 2;
            if (distance(position, middle) >= m) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return right;
    }

    /**
     * 最小磁力 为 n时，最多可以分为几段
     *
     * @param position
     * @param n
     * @return
     */
    public int distance(int[] position, int n) {
        int min = position[0];
        int difference = 0;
        int nums = 0;
        for (int i : position) {
            difference = i - min;
            if (difference >= n) {
                min = i;
                nums++;
            }
        }
        return nums + 1;
    }

    /**
     * 29. 两数相除
     * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
     * <p>
     * 返回被除数 dividend 除以除数 divisor 得到的商。
     * <p>
     * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入: dividend = 10, divisor = 3
     * 输出: 3
     * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
     * 示例 2:
     * <p>
     * 输入: dividend = 7, divisor = -3
     * 输出: -2
     * 解释: 7/-3 = truncate(-2.33333..) = -2
     * <p>
     * 负数
     * 提示：
     * <p>
     * 被除数和除数均为 32 位有符号整数。
     * 除数不为 0。
     * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
     * 这鸡儿是个脑瘫题
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        int left = 0, right = dividend;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (canDivide(middle, divisor) > dividend) {
                right = middle - 1;
            } else if (canDivide(middle, divisor) < dividend) {
                left = middle + 1;
            } else if (canDivide(middle, divisor) == dividend) {
                return middle;
            }
        }
        int num = 0;
        int flag = Math.abs(divisor);
        int target = Math.abs(dividend);
        while (target >= flag) {
            num++;
            target -= flag;
        }
        System.out.println(-2147483648 > 0);
        return (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0) ? num : -num;
    }

    private int canDivide(int middle, int divisor) {
        return 1;
    }

    public int divide1(int dividend, int divisor) {
        // 考虑被除数为最小值的情况
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == 1) {
                return Integer.MIN_VALUE;
            }
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
        }
        // 考虑除数为最小值的情况
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        // 考虑被除数为 0 的情况
        if (dividend == 0) {
            return 0;
        }

        // 一般情况，使用二分查找
        // 将所有的正数取相反数，这样就只需要考虑一种情况
        boolean rev = false;
        if (dividend > 0) {
            dividend = -dividend;
            rev = true;
        }
        if (divisor > 0) {
            divisor = -divisor;
            rev = !rev;
        }

        int left = 1, right = Integer.MAX_VALUE, ans = 0;
        while (left <= right) {
            // 注意溢出，并且不能使用除法
            int mid = left + ((right - left) >> 1);
            boolean check = quickAdd(divisor, mid, dividend);
            if (check) {
                ans = mid;
                // 注意溢出
                if (mid == Integer.MAX_VALUE) {
                    break;
                }
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return rev ? -ans : ans;
    }

    // 快速乘
    public boolean quickAdd(int y, int z, int x) {
        // x 和 y 是负数，z 是正数
        // 需要判断 z * y >= x 是否成立
        int result = 0, add = y;
        while (z != 0) {
            if ((z & 1) != 0) {
                // 需要保证 result + add >= x
                if (result < x - add) {
                    return false;
                }
                result += add;
            }
            if (z != 1) {
                // 需要保证 add + add >= x
                if (add < x - add) {
                    return false;
                }
                add += add;
            }
            // 不能使用除法
            z >>= 1;
        }
        System.out.println(0 & 1);
        return true;
    }

    /***
     * 33. 搜索旋转排序数组
     * 整数数组 nums 按升序排列，数组中的值 互不相同 。
     *
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
     *
     * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [4,5,6,7,0,1,2], target = 0
     * 输出：4
     * 示例 2：
     *
     * 输入：nums = [4,5,6,7,0,1,2], target = 3
     * 输出：-1
     * 示例 3：
     *
     * 输入：nums = [1], target = 0
     * 输出：-1
     *
     *
     * 提示：
     *
     * 1 <= nums.length <= 5000
     * -10^4 <= nums[i] <= 10^4
     * nums 中的每个值都 独一无二
     * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
     * -10^4 <= target <= 10^4
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int flag = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                flag = i;
            }
        }
        int leftNum = binarySearch(nums, target, 0, flag);
        if (leftNum != -1) {
            return leftNum;
        } else {
            return binarySearch(nums, target, flag + 1, nums.length - 1);
        }
    }

    public int binarySearch(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int middle = (left + right) / 2;
            if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] == target) {
                return middle;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }

    /**
     * 官方思路，
     * 对于有序数组，可以使用二分查找的方法查找元素。
     * <p>
     * 但是这道题中，数组本身不是有序的，进行旋转后只保证了数组的局部是有序的，这还能进行二分查找吗？答案是可以的。
     * <p>
     * 可以发现的是，我们将数组从中间分开成左右两部分的时候，一定有一部分的数组是有序的。拿示例来看，我们从 6 这个位置分开以后数组变成了 [4, 5, 6] 和 [7, 0, 1, 2] 两个部分，其中左边 [4, 5, 6] 这个部分的数组是有序的，其他也是如此。
     * <p>
     * 这启示我们可以在常规二分查找的时候查看当前 mid 为分割位置分割出来的两个部分 [l, mid] 和 [mid + 1, r] 哪个部分是有序的，并根据有序的那个部分确定我们该如何改变二分查找的上下界，因为我们能够根据有序的那部分判断出 target 在不在这个部分：
     * <p>
     * 如果 [l, mid - 1] 是有序数组，且 target 的大小满足 [nums[l],nums[mid])，则我们应该将搜索范围缩小至 [l, mid - 1]，否则在 [mid + 1, r] 中寻找。
     * 如果 [mid, r] 是有序数组，且 target 的大小满足 (nums[mid+1],nums[r]]，则我们应该将搜索范围缩小至 [mid + 1, r]，否则在 [l, mid - 1] 中寻找。
     *
     * @param nums
     * @param target
     * @return
     */
    public int search2(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int midlle = (left + right) / 2;
            if (nums[midlle] == target) {
                return midlle;
            }
            // 左半段为有序数组
            if (nums[0] <= nums[midlle]) {
                if (target >= nums[0] && target < nums[midlle]) {
                    right = midlle - 1;
                } else {
                    left = midlle + 1;
                }
            } else {
                if (target > nums[midlle] && target <= nums[nums.length - 1]) {
                    left = midlle + 1;
                } else {
                    right = midlle - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 81. 搜索旋转排序数组 II
     * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
     * <p>
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
     * <p>
     * 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [2,5,6,0,0,1,2], target = 0
     * 输出：true
     * 示例 2：
     * <p>
     * 输入：nums = [2,5,6,0,0,1,2], target = 3
     * 输出：false
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 5000
     * -104 <= nums[i] <= 104
     * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
     * -104 <= target <= 104
     * <p>
     * <p>
     * 进阶：
     * <p>
     * 这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
     * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
     *
     * @param nums
     * @param target
     * @return
     */
    public boolean searchII(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int midlle = (left + right) / 2;
            if (nums[midlle] == target) {
                return true;
            }
            // 左半段为有序数组
            if (nums[left] == nums[midlle] && nums[right] == nums[midlle]) {
                left++;
                right--;
            } else if (nums[left] <= nums[midlle]) {
                if (target >= nums[left] && target < nums[midlle]) {
                    right = midlle - 1;
                } else {
                    left = midlle + 1;
                }
            } else {
                if (target > nums[midlle] && target <= nums[nums.length - 1]) {
                    left = midlle + 1;
                } else {
                    right = midlle - 1;
                }
            }
        }
        return false;
    }


    public boolean searchII2(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return false;
        }
        if (n == 1) {
            return nums[0] == target;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[l] == nums[mid] && nums[mid] == nums[r]) {
                ++l;
                --r;
            } else if (nums[l] <= nums[mid]) {
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return false;
    }

    /**
     * 面试题 10.03. 搜索旋转数组
     * 搜索旋转数组。给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。若有多个相同元素，返回索引值最小的一个。
     * <p>
     * 示例1:
     * <p>
     * 输入: arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 5
     * 输出: 8（元素5在该数组中的索引）
     * 示例2:
     * <p>
     * 输入：arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 11
     * 输出：-1 （没有找到）
     * 提示:
     * <p>
     * arr 长度范围在[1, 1000000]之间
     *
     * @param arr
     * @param target
     * @return
     */
    public int searchIII(int[] arr, int target) {
        int n = arr.length;
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[l] == arr[mid]) {
                if (arr[l] != target) {                            // 如果左值不等于目标，说明还没找到，需要逐一清理重复值。
                    l++;
                } else {                                               // 如果左值等于目标，说明已经找到最左边的目标值
                    r = l - 1;                                      // 将右边界移动到left，循环结束
                }
            } else if (arr[l] < arr[mid]) {
                if (arr[l] <= target && target <= arr[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (arr[l] <= target || target <= arr[mid]) {     // 如果目标在左边，右边界移动到mid
                    r = mid - 1;
                } else {                                               // 否则目标在右半边，左边界移动到mid+1
                    l = mid + 1;
                }
            }
        }
        if (l >= n || arr[l] != target) {
            return -1;
        }
        return l;
    }


    public int searchIII2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        if (right == -1) {
            return -1;
        }
        while (left < right) {                                         // 循环结束条件left==right
            int mid = left + (right - left) / 2;
            if (nums[left] < nums[mid]) {                              // 如果左值小于中值，说明左边区间升序
                if (nums[left] <= target && target <= nums[mid]) {     // 如果目标在左边的升序区间中，右边界移动到mid
                    right = mid;
                } else {                                               // 否则目标在右半边，左边界移动到mid+1
                    left = mid + 1;
                }
            } else if (nums[left] > nums[mid]) {                       // 如果左值大于中值，说明左边不是升序，右半边升序
                if (nums[left] <= target || target <= nums[mid]) {     // 如果目标在左边，右边界移动到mid
                    right = mid;
                } else {                                               // 否则目标在右半边，左边界移动到mid+1
                    left = mid + 1;
                }
            } else if (nums[left] == nums[mid]) {                      // 如果左值等于中值，可能是已经找到了目标，也可能是遇到了重复值
                if (nums[left] != target) {                            // 如果左值不等于目标，说明还没找到，需要逐一清理重复值。
                    left++;
                } else {                                               // 如果左值等于目标，说明已经找到最左边的目标值
                    right = left;                                      // 将右边界移动到left，循环结束
                }
            }
        }
        return (nums[left] == target) ? left : -1;                     // 返回left，或者-1
    }


    public int searchIII3(int[] arr, int target) {
        int n = arr.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return arr[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[l] == arr[mid]) {
                if (arr[l] != target) {
                    l++;
                } else {
                    return l;
                }
            } else if (arr[l] < arr[mid]) {
                if (arr[l] <= target && target <= arr[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (arr[mid] <= target && target <= arr[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }


    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     * <p>
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     * <p>
     * 进阶：
     * <p>
     * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     * 示例 2：
     * <p>
     * 输入：nums = [5,7,7,8,8,10], target = 6
     * 输出：[-1,-1]
     * 示例 3：
     * <p>
     * 输入：nums = [], target = 0
     * 输出：[-1,-1]
     * <p>
     * <p>
     * 提示：
     * <p>
     * 0 <= nums.length <= 105
     * -109 <= nums[i] <= 109
     * nums 是一个非递减数组
     * -109 <= target <= 109
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        // 先找最左边界
        result[0] = left_bound(nums, target);
        // 最右边界
        result[1] = right_bound(nums, target);
        // 万万没想到这题还真的就是两遍二分
        return result;
    }


    /**
     * 滑动窗口套路 有一个字符串s，需要找到t
     * 思路为：
     * 1、定义一个双指针left,right,作为窗口 window的左右边界
     * 2、右移right（right++），使得window包含t，即找到可行解
     * 3、停止增加right，转而不断增加left指针缩小窗口[left, right)，直到窗口中的字符串不再符合要求（不包含T中的所有字符了）。同时，每次增加left，我们都要更新一轮结果。
     * 4、重复第 2 和第 3 步，直到right到达字符串S的尽头。
     *
     * @param s
     * @param t
     */
    void slidingWindow(String s, String t) {
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;
        while (right < s.length()) {
            // c 是将移入窗口的字符
            char c = s.charAt(right);
            // 右移窗口
            right++;
            // 进行窗口内数据的一系列更新...，找出可行解

            /*** debug 输出的位置 ***/
            System.out.println(left);
            System.out.println(right);
            /********************/

            // 判断左侧窗口是否要收缩，找出局部最优解
            while (valid == need.size()) {
                // d 是将移出窗口的字符
                char d = s.charAt(left);
                // 左移窗口
                left++;
                // 进行窗口内数据的一系列更新...
            }
        }
    }

    /**
     * 76. 最小覆盖子串
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * <p>
     * <p>
     * <p>
     * 注意：
     * <p>
     * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
     * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "ADOBECODEBANC", t = "ABC"
     * 输出："BANC"
     * 示例 2：
     * <p>
     * 输入：s = "a", t = "a"
     * 输出："a"
     * 示例 3:
     * <p>
     * 输入: s = "a", t = "aa"
     * 输出: ""
     * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
     * 因此没有符合条件的子字符串，返回空字符串。
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= s.length, t.length <= 105
     * s 和 t 由英文字母组成
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0) + 1);
        }
        int left = 0, right = 0, nums = 0;
        int start = 0, length = Integer.MAX_VALUE;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            // 扩大右边界把window装满t，找到可行解
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    nums++;
                }
            }
            // 收缩左边界，找到局部最优解
            while (nums == need.size()) {
                char d = s.charAt(left);
                if (right - left < length) {
                    start = left;
                    length = right - left;
                }
                left++;
                if (need.containsKey(d)) {
                    if (need.get(d).equals(window.get(d))) {
                        nums--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return length == Integer.MAX_VALUE ? "" : s.substring(start, start + length);
    }

    /**
     * 567. 字符串的排列
     * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
     * <p>
     * 换句话说，s1 的排列之一是 s2 的 子串 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s1 = "ab" s2 = "eidbaooo"
     * 输出：true
     * 解释：s2 包含 s1 的排列之一 ("ba").
     * 示例 2：
     * <p>
     * 输入：s1= "ab" s2 = "eidboaoo"
     * 输出：false
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= s1.length, s2.length <= 104
     * s1 和 s2 仅包含小写字母
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int left = 0, right = 0, nums = 0;
        int start = 0, length = Integer.MAX_VALUE;
        while (right < s2.length()) {
            char c = s2.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    nums++;
                }
            }
            while (nums == need.size()) {
                length = Math.min(right - left, length);
                char d = s2.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (need.get(d).equals(window.get(d))) {
                        nums--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
            if (length == s1.length()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkInclusion1(String s1, String s2) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int left = 0, right = 0, nums = 0;
        while (right < s2.length()) {
            char c = s2.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    nums++;
                }
            }
            while (right - left >= s1.length()) {
                if (nums == need.size()) {
                    return true;
                }
                char d = s2.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (need.get(d).equals(window.get(d))) {
                        nums--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }

        }
        return false;
    }

    public boolean checkInclusion2(String s1, String s2) {
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            need.put(s1.charAt(i), need.getOrDefault(s1.charAt(i), 0) + 1);
        }
        int valid = 0;
        int left = 0, right = 0;
        while (right < s2.length()) {
            char c = s2.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (need.get(c).equals(window.get(c))) {
                    valid++;
                }
            }
            while (valid == need.size()) {
                if (right - left == s1.length()) {
                    return true;
                }
                char d = s2.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (need.get(d).equals(window.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return false;
    }

    /**
     * 643. 子数组最大平均数 I
     * 给你一个由 n 个元素组成的整数数组 nums 和一个整数 k 。
     * <p>
     * 请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。
     * <p>
     * 任何误差小于 10-5 的答案都将被视为正确答案。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,12,-5,-6,50,3], k = 4
     * 输出：12.75
     * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
     * 示例 2：
     * <p>
     * 输入：nums = [5], k = 1
     * 输出：5.00000
     *
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverage(int[] nums, int k) {
        int left = 0, right = 0;
        double arg = Double.MIN_EXPONENT;
        double temp = 0;
        while (right < nums.length) {
            temp += nums[right];
            right++;
            if (right - left >= k) {
                arg = Math.max(temp, arg);
                temp -= nums[left];
                left++;
            }
        }
        return arg / k;
    }

    public double findMaxAverage1(int[] nums, int k) {
        double arg = 0;
        double temp;
        for (int i = 0; i < k; i++) {
            arg += nums[i];
        }
        temp = arg;
        for (int i = k; i < nums.length; i++) {
            temp = temp - nums[i - k] + nums[i];
            arg = Math.max(temp, arg);
        }
        return arg / k;
    }

    public boolean checkInclusion3(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < n; ++i) {
            ++cnt1[s1.charAt(i) - 'a'];
            ++cnt2[s2.charAt(i) - 'a'];
        }
        if (Arrays.equals(cnt1, cnt2)) {
            return true;
        }
        for (int i = n; i < m; ++i) {
            ++cnt2[s2.charAt(i) - 'a'];
            --cnt2[s2.charAt(i - n) - 'a'];
            if (Arrays.equals(cnt1, cnt2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 438. 找到字符串中所有字母异位词
     * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
     * <p>
     * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入: s = "cbaebabacd", p = "abc"
     * 输出: [0,6]
     * 解释:
     * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
     * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
     * 示例 2:
     * <p>
     * 输入: s = "abab", p = "ab"
     * 输出: [0,1,2]
     * 解释:
     * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
     * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
     * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
     * <p>
     * <p>
     * 提示:
     * <p>
     * 1 <= s.length, p.length <= 3 * 104
     * s 和 p 仅包含小写字母
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        // 使用指针在LeetCode上快了好多好多
        int[] need = new int[26];
        int[] window = new int[26];
        for (int i = 0; i < p.length(); i++) {
            need[p.charAt(i) - 'a']++;
        }
        int needSize = 0;
        for (int i : need) {
            if (i > 0) {
                needSize++;
            }
        }
        int left = 0, right = 0, nums = 0;
        List<Integer> result = new ArrayList<>();
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (need[c - 'a'] > 0) {
                window[c - 'a']++;
                if (need[c - 'a'] == window[c - 'a']) {
                    nums++;
                }
            }
            while (nums == needSize) {
                if (right - left == p.length()) {
                    result.add(left);
                }
                char d = s.charAt(left);
                left++;
                if (need[d - 'a'] > 0) {
                    if (need[d - 'a'] == window[d - 'a']) {
                        nums--;
                    }
                    window[d - 'a']--;
                }
            }
        }
        return result;
    }

    /**
     * 3. 无重复字符的最长子串
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     * <p>
     * 输入: s = "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     * <p>
     * 输入: s = "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     * 示例 4:
     * <p>
     * 输入: s = ""
     * 输出: 0
     * <p>
     * <p>
     * 提示：
     * <p>
     * 0 <= s.length <= 5 * 104
     * s 由英文字母、数字、符号和空格组成
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0, result = 0;
        Map<Character, Integer> window = new HashMap<>();
        while (right < s.length()) {
            char c = s.charAt(right);
            while (window.containsKey(c)) {
                char d = s.charAt(left);
                left++;
                window.remove(d);
            }
            right++;
            window.put(c, 1);
            result = Math.max(result, right - left);
        }
        return result;
    }

    /**
     * 395. 至少有 K 个重复字符的最长子串
     * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "aaabb", k = 3
     * 输出：3
     * 解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
     * 示例 2：
     * <p>
     * 输入：s = "ababbc", k = 2
     * 输出：5
     * 解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
     * 思路：
     * 我们枚举最长子串中的字符种类数目，它最小为 1，最大为 ∣Σ∣（字符集的大小，本题中为 26）。
     * 对于给定的字符种类数量 t，我们维护滑动窗口的左右边界 l,r、
     * 滑动窗口内部每个字符出现的次数 cnt，
     * 以及滑动窗口内的字符种类数目 total。当 total > t 时，我们不断地右移左边界 l，并对应地更新 cnt 以及 total，直到 total≤t 为止。
     * 这样，对于任何一个右边界 r，我们都能找到最小的l（记为 lmin），使得 s[lmin...r] 之间的字符种类数目不多于 t
     *对于任何一组s[lmin...r] 之间存在某个出现次数小于 k （且不为 0，下文不再特殊说明）的字符，我们可以断定：对于任何 l′∈(lmin,r) 而言，s[l'...r] 依然不可能是满足题意的子串，因为：
     *
     * 1、要么该字符的出现次数降为 0，此时子串内虽然少了一个出现次数小于 k 的字符，但字符种类数目也随之小于 t 了；
     * 2、 要么该字符的出现次数降为非 0 整数，此时该字符的出现次数依然小于 k。
     * 根据上面的结论，我们发现：当限定字符种类数目为 t 时，满足题意的最长子串，就一定出自某个s[lmin...r]。因此，在滑动窗口的维护过程中，就可以直接得到最长子串的大小
     *

     * @param s
     * @param k
     * @return
     */
    public int longestSubstring(String s, int k) {
        int ret = 0;
        int n = s.length();
        // 遍历最长子串的字符种类数目
        for (int t = 1; t <= 26; t++) {
            int l = 0, r = 0;
            // 每个字符出现的次数
            int[] cnt = new int[26];
            // 字符种类数目
            int tot = 0;
            // 当前出现次数小于 k 的字符的数量
            int less = 0;
            while (r < n) {
                cnt[s.charAt(r) - 'a']++;
                // 该字符第一次出现
                if (cnt[s.charAt(r) - 'a'] == 1) {
                    // 字符种类数目加一
                    tot++;
                    // 小于 k 的字符的数量加一
                    less++;
                }
                // 如果等于k了，less加一
                if (cnt[s.charAt(r) - 'a'] == k) {
                    less--;
                }
                // 字符种类超过了t
                while (tot > t) {
                    cnt[s.charAt(l) - 'a']--;
                    // s.charAt(l)出现次数小于k了，less加1
                    if (cnt[s.charAt(l) - 'a'] == k - 1) {
                        less++;
                    }
                    // s.charAt(l)不在窗口内了，种类tot减一和less减一
                    if (cnt[s.charAt(l) - 'a'] == 0) {
                        tot--;
                        less--;
                    }
                    l++;
                }
                if (less == 0) {
                    ret = Math.max(ret, r - l + 1);
                }
                r++;
            }
        }
        return ret;
    }

    /**
     * 992. K 个不同整数的子数组
     * 给定一个正整数数组 nums和一个整数 k ，返回 num 中 「好子数组」 的数目。
     *
     * 如果 nums 的某个子数组中不同整数的个数恰好为 k，则称 nums 的这个连续、不一定不同的子数组为 「好子数组 」。
     *
     * 例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。
     * 子数组 是数组的 连续 部分。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [1,2,1,2,3], k = 2
     * 输出：7
     * 解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
     * 示例 2：
     *
     * 输入：nums = [1,2,1,3,4], k = 3
     * 输出：3
     * 解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
     * 思路，这题目是比较鸡贼的把题目意思变了求的是恰好出现k的次数，恰好二字往最多去靠拢
     * 而「最多存在 K 个不同整数的子区间的个数」与「恰好存在 K 个不同整数的子区间的个数」的差恰好等于「最多存在 K - 1 个不同整数的子区间的个数」。
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraysWithKDistinct(int[] nums, int k) {
      return maxArraysDistance(nums, k) - maxArraysDistance(nums, k - 1);
    }

    /**
     * 最多有k个不同数字的区间有多少
     * @param nums
     * @param k
     * @return
     */
    private int maxArraysDistance(int[] nums, int k) {
        int left = 0, right = 0, result = 0;
        int valid = 0;
        int[] temp = new int[nums.length + 1];
        while(right < nums.length) {
            temp[nums[right]]++;
            if(temp[nums[right]] == 1) {
                valid++;
            }
            right++;
            while(valid > k) {
                temp[nums[left]]--;
                if(temp[nums[left]] == 0) {
                    valid--;
                }
                left++;
            }
            result += right - left;
        }
        return result;
    }

    /**
     * 239. 滑动窗口最大值
     * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     *
     * 返回 滑动窗口中的最大值 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
     * 输出：[3,3,5,5,6,7]
     * 解释：
     * 滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     * 示例 2：
     *
     * 输入：nums = [1], k = 1
     * 输出：[1]
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 维护一个递减的双端队列
        Deque<Integer> queue = new LinkedList<>();
        int left = 0, right = 0;
        int[] result = new int[nums.length - k + 1];
        while(right < nums.length) {
            // 当前元素大于队尾元素，移除队尾元素
            while (!queue.isEmpty() && nums[right] >= nums[queue.peekLast()]) {
                queue.removeLast();
            }
            // 添加元素到队尾
            queue.addLast(right);
            right++;
            // 如果队首元素不在区间内，移除队首元素
            while(queue.peekFirst() < left) {
                queue.removeFirst();
            }

            if(right - left == k) {
                // 在区间内时，队首元素为区间最大元素
                result[left++] = nums[queue.peekFirst()];
            }
        }
        return result;
    }

    /**
     * 30. 串联所有单词的子串
     * 给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
     * <p>
     * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
     * 输出：[0,9]
     * 解释：
     * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
     * 输出的顺序不重要, [9,0] 也是有效答案。
     * 示例 2：
     * <p>
     * 输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
     * 输出：[]
     * 示例 3：
     * <p>
     * 输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
     * 输出：[6,9,12]
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= s.length <= 104
     * s 由小写英文字母组成
     * 1 <= words.length <= 5000
     * 1 <= words[i].length <= 30
     * words[i] 由小写英文字母组成
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        int left = 0, right = 0;
        int n = words[0].length();
        int length = n * words.length;
        List<Integer> list = new ArrayList<>();
        HashMap<String, Integer> need = new HashMap<>();
        for (String c : words) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int vaild = 0;
        while (right < s.length()) {
            right++;
            if (right - left == length) {
                HashMap<String, Integer> window = new HashMap<>();
                for (int i = left; i <= right - n; i += n) {
                    String temp = s.substring(i, i + n);
                    if (need.containsKey(temp)) {
                        window.put(temp, window.getOrDefault(temp, 0) + 1);
                        if (need.get(temp).equals(window.get(temp))) {
                            vaild++;
                        }
                    }
                }
                if (vaild == need.size()) {
                    list.add(left);
                }
                vaild = 0;
                left++;
            }
        }
        return list;
    }

    public List<Integer> findSubstring1(String s, String[] words) {
        int left = 0, right = 0;
        int n = words[0].length();
        int length = n * words.length;
        List<Integer> list = new ArrayList<>();
        HashMap<String, Integer> need = new HashMap<>();
        for (String c : words) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int vaild = 0;
        for (int j = 0; j < n; j++) {
            left = j;
            right = j;
            vaild = 0;
            while (right + n <= s.length()) {
                right += n;
                if (right - left == length) {
                    HashMap<String, Integer> window = new HashMap<>();
                    for (int i = left; i <= right - n; i += n) {
                        String temp = s.substring(i, i + n);
                        if (need.containsKey(temp)) {
                            window.put(temp, window.getOrDefault(temp, 0) + 1);
                            if (need.get(temp).equals(window.get(temp))) {
                                vaild++;
                            }
                        }
                    }
                    if (vaild == need.size()) {
                        list.add(left);
                    }
                    vaild = 0;
                    left += n;
                }
            }
        }
        return list;
    }

    /**
     * 870. 优势洗牌
     * 给定两个大小相等的数组 A 和 B，A 相对于 B 的优势可以用满足 A[i] > B[i] 的索引 i 的数目来描述。
     * <p>
     * 返回 A 的任意排列，使其相对于 B 的优势最大化。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：A = [2,7,11,15], B = [1,10,4,11]
     * 输出：[2,11,7,15]
     * 示例 2：
     * <p>
     * 输入：A = [12,24,8,32], B = [13,25,32,11]
     * 输出：[24,32,8,12]
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= A.length = B.length <= 10000
     * 0 <= A[i] <= 10^9
     * 0 <= B[i] <= 10^9
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] advantageCount(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        boolean[] isUsed = new boolean[nums1.length];
        int[] result = new int[nums1.length];
        int flag = 0;
        int index = 0, j;
        for (int i : nums2) {
            for (j = index; j < nums2.length; j++) {
                if (nums1[j] > i && !isUsed[j]) {
                    result[flag++] = nums1[j];
                    isUsed[j] = true;
                    break;
                }
            }
            if (j == nums2.length) {
                for (int k = index; k < nums2.length; k++) {
                    if (!isUsed[k]) {
                        result[flag++] = nums1[k];
                        isUsed[k] = true;
                        index = k + 1;
                        break;
                    }
                }
            }

        }
        return result;
    }

    public int[] advantageCount1(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        int length = nums1.length;
        int[][] nums = new int[length][2];
        for (int i = 0; i < length; i++) {
            nums[i][0] = i;
            nums[i][1] = nums2[i];
        }
        Arrays.sort(nums, Comparator.comparingInt(a -> a[1]));
        int[] result = new int[length];
        int left = 0, right = length - 1;
        for (int i = length - 1; i >= 0; i--) {
            int index = nums[i][0], value = nums[i][1];
            if (value < nums1[right]) {
                result[index] = nums1[right];
                right--;
            } else {
                result[index] = nums1[left];
                left++;
            }
        }
        return result;
    }

    /**
     * 209. 长度最小的子数组
     * 给定一个含有 n 个正整数的数组和一个正整数 target 。
     * <p>
     * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：target = 7, nums = [2,3,1,2,4,3]
     * 输出：2
     * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
     * 示例 2：
     * <p>
     * 输入：target = 4, nums = [1,4,4]
     * 输出：1
     * 示例 3：
     * <p>
     * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
     * 输出：0
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= target <= 109
     * 1 <= nums.length <= 105
     * 1 <= nums[i] <= 105
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, right = 0;
        int sum = 0, result = Integer.MAX_VALUE;
        while (right < nums.length) {
            int a = nums[right];
            right++;
            sum += a;
            while (sum >= target) {
                result = Math.min(right - left, result);
                int b = nums[left];
                sum -= b;
                left++;
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    /**
     * 904. 水果成篮
     * 你正在探访一家农场，农场从左到右种植了一排果树。这些树用一个整数数组 fruits 表示，其中 fruits[i] 是第 i 棵树上的水果 种类 。
     * <p>
     * 你想要尽可能多地收集水果。然而，农场的主人设定了一些严格的规矩，你必须按照要求采摘水果：
     * <p>
     * 你只有 两个 篮子，并且每个篮子只能装 单一类型 的水果。每个篮子能够装的水果总量没有限制。
     * 你可以选择任意一棵树开始采摘，你必须从 每棵 树（包括开始采摘的树）上 恰好摘一个水果 。采摘的水果应当符合篮子中的水果类型。每采摘一次，你将会向右移动到下一棵树，并继续采摘。
     * 一旦你走到某棵树前，但水果不符合篮子的水果类型，那么就必须停止采摘。
     * 给你一个整数数组 fruits ，返回你可以收集的水果的 最大 数目。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：fruits = [1,2,1]
     * 输出：3
     * 解释：可以采摘全部 3 棵树。
     * 示例 2：
     * <p>
     * 输入：fruits = [0,1,2,2]
     * 输出：3
     * 解释：可以采摘 [1,2,2] 这三棵树。
     * 如果从第一棵树开始采摘，则只能采摘 [0,1] 这两棵树。
     * 示例 3：
     * <p>
     * 输入：fruits = [1,2,3,2,2]
     * 输出：4
     * 解释：可以采摘 [2,3,2,2] 这四棵树。
     * 如果从第一棵树开始采摘，则只能采摘 [1,2] 这两棵树。
     * 示例 4：
     * <p>
     * 输入：fruits = [3,3,3,1,2,1,1,2,3,3,4]
     * 输出：5
     * 解释：可以采摘 [1,2,1,1,2] 这五棵树。
     *
     * @param fruits
     * @return
     */
    public int totalFruit(int[] fruits) {
        int left = 0, right = 0;
        int result = 0;
        Map<Integer, Integer> need = new HashMap<>();
        while (right < fruits.length) {
            need.put(fruits[right], need.getOrDefault(fruits[right], 0) + 1);
            right++;
            while (need.size() > 2) {
                need.put(fruits[left], need.get(fruits[left]) - 1);
                if (need.get(fruits[left]) == 0) {
                    need.remove(fruits[left]);
                }
                left++;
            }
            result = Math.max(right - left, result);
        }
        return result;
    }

    /**
     * 1. 两数之和
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * <p>
     * 你可以按任意顺序返回答案。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     * 示例 2：
     * <p>
     * 输入：nums = [3,2,4], target = 6
     * 输出：[1,2]
     * 示例 3：
     * <p>
     * 输入：nums = [3,3], target = 6
     * 输出：[0,1]
     * <p>
     * <p>
     * 提示：
     * <p>
     * 2 <= nums.length <= 104
     * -109 <= nums[i] <= 109
     * -109 <= target <= 109
     * 只会存在一个有效答案
     * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /**
     * 哈希表
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int other = target - nums[i];
            if (map.containsKey(other) && !map.get(other).equals(i)) {
                return new int[]{i, map.get(other)};
            }
        }
        return null;
    }

    /**
     * 15. 三数之和
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     * <p>
     * 注意：答案中不可以包含重复的三元组。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     * 示例 2：
     * <p>
     * 输入：nums = []
     * 输出：[]
     * 示例 3：
     * <p>
     * 输入：nums = [0]
     * 输出：[]
     * <p>
     * <p>
     * 提示：
     * <p>
     * 0 <= nums.length <= 3000
     * -105 <= nums[i] <= 105
     * 这题就是憨批，自己想到了这样写，但是感觉n平方时间复杂度太高了，没写看了答案
     * 思路：
     * 1、排序，保证不会有重复元素,
     * 「不重复」的本质是什么？我们保持三重循环的大框架不变，只需要保证：
     * <p>
     * 第二重循环枚举到的元素不小于当前第一重循环枚举到的元素；
     * <p>
     * 第三重循环枚举到的元素不小于当前第二重循环枚举到的元素。
     * <p>
     * 也就是说，我们枚举的三元组 (a,b,c) 满足 a≤b≤c，保证了只有 (a,b,c) 这个顺序会被枚举到，而 (b,a,c)、(c,b,a) 等等这些不会，这样就减少了重复。
     * 要实现这一点，我们可以将数组中的元素从小到大进行排序，随后使用普通的三重循环就可以满足上面的要求。
     * <p>
     * 2、确定好一个元素后，第二个元素，顺着遍历，第三个元素逆着遍历，即n^3变成了n^2
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            // 去重，选一个就好
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1, right = length - 1;
            while (left < right) {
                // 去重
                if (left > i + 1 && nums[left] == nums[left - 1]) {
                    left++;
                    continue;
                }
                int sum = nums[i] + nums[left] + nums[right];
                while (sum > 0 && left < right) {
                    sum -= nums[right];
                    right--;
                    sum += nums[right];
                }
                if (left == right) {
                    break;
                }
                if (sum == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);
                }
                left++;
            }
        }
        return result;
    }

    /**
     * 18. 四数之和
     * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
     * <p>
     * 0 <= a, b, c, d < n
     * a、b、c 和 d 互不相同
     * nums[a] + nums[b] + nums[c] + nums[d] == target
     * 你可以按 任意顺序 返回答案 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,0,-1,0,-2,2], target = 0
     * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
     * 示例 2：
     * <p>
     * 输入：nums = [2,2,2,2,2], target = 8
     * 输出：[[2,2,2,2]]
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        int length = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            // 去重，选一个就好
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int left = j + 1, right = length - 1;
                while (left < right) {
                    // 去重
                    if (left > j + 1 && nums[left] == nums[left - 1]) {
                        left++;
                        continue;
                    }
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    while (sum > target && left < right) {
                        sum -= nums[right];
                        right--;
                        sum += nums[right];
                    }
                    if (left == right) {
                        break;
                    }
                    if (sum == target) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        result.add(list);
                    }
                    left++;
                }
            }
        }
        return result;
    }

    /**
     * 381. O(1) 时间插入、删除和获取随机元素 - 允许重复
     * 设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。
     * <p>
     * 注意: 允许出现重复元素。
     * <p>
     * insert(val)：向集合中插入元素 val。
     * remove(val)：当 val 存在时，从集合中移除一个 val。
     * getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
     * 示例:
     * <p>
     * // 初始化一个空的集合。
     * RandomizedCollection collection = new RandomizedCollection();
     * <p>
     * // 向集合中插入 1 。返回 true 表示集合不包含 1 。
     * collection.insert(1);
     * <p>
     * // 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
     * collection.insert(1);
     * <p>
     * // 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
     * collection.insert(2);
     * <p>
     * // getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
     * collection.getRandom();
     * <p>
     * // 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
     * collection.remove(1);
     * <p>
     * // getRandom 应有相同概率返回 1 和 2 。
     * collection.getRandom();
     * 这种题目的思路
     * 1：0(1)时间插入查询，用arraylist，或者hashMap
     * 2，删除，用数组删除要是o（1），则必须把要删除的元素换到最后一位
     * 3、所以需要使用map记录每个元素的索引，以便换时能找到换的元素
     */
    static class RandomizedCollection {
        private Map<Integer, Set<Integer>> map;
        private List<Integer> list;
        Random random = new Random();

        public RandomizedCollection() {
            this.map = new HashMap<>();
            this.list = new ArrayList<>();
        }

        public boolean insert(int val) {
            list.add(val);
            Set<Integer> set = map.getOrDefault(val, new HashSet<>());
            set.add(list.size() - 1);
            map.put(val, set);
            return set.size() == 1;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            Set<Integer> set = map.get(val);
            Iterator<Integer> iterator = set.iterator();
            int index = iterator.next();
            int lastNum = list.get(list.size() - 1);
            list.set(index, lastNum);
            map.get(lastNum).remove(list.size() - 1);
            set.remove(index);
            if (index < list.size() - 1) {
                map.get(lastNum).add(index);
            }
            if (set.size() == 0) {
                map.remove(val);
            }
            list.remove(list.size() - 1);
            return true;
        }

        public int getRandom() {
            return list.get(random.nextInt(list.size()));
        }
    }

    /**
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     * <p>
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * <p>
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     * <p>
     * <p>
     * <p>
     * 说明:
     * <p>
     * 为什么返回数值是整数，但输出的答案是数组呢?
     * <p>
     * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
     * <p>
     * 你可以想象内部操作如下:
     * <p>
     * // nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
     * int len = removeElement(nums, val);
     * <p>
     * // 在函数里修改输入数组对于调用者是可见的。
     * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
     * for (int i = 0; i < len; i++) {
     * print(nums[i]);
     * }
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [3,2,2,3], val = 3
     * 输出：2, nums = [2,2]
     * 解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
     * 示例 2：
     * <p>
     * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
     * 输出：5, nums = [0,1,4,0,3]
     * 解释：函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面的元素。
     * <p>
     * <p>
     * 提示：
     * <p>
     * 0 <= nums.length <= 100
     * 0 <= nums[i] <= 50
     * 0 <= val <= 100
     * Related Topics
     * 数组
     * 双指针
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            if (nums[left] == val) {
                while (nums[right] == val) {
                    right--;
                    if (right < left) {
                        return right + 1;
                    }
                }
                int tmp = nums[right];
                nums[right] = nums[left];
                nums[left] = tmp;
                right--;
            }
            left++;
        }
        return right + 1;
    }

    /**
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
     * <p>
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     * <p>
     * <p>
     * <p>
     * 说明:
     * <p>
     * 为什么返回数值是整数，但输出的答案是数组呢?
     * <p>
     * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
     * <p>
     * 你可以想象内部操作如下:
     * <p>
     * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
     * int len = removeDuplicates(nums);
     * <p>
     * // 在函数里修改输入数组对于调用者是可见的。
     * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
     * for (int i = 0; i < len; i++) {
     * print(nums[i]);
     * }
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,1,2]
     * 输出：2, nums = [1,2]
     * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
     * 示例 2：
     * <p>
     * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
     * 输出：5, nums = [0,1,2,3,4]
     * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
     * <p>
     * <p>
     * 提示：
     * <p>
     * 0 <= nums.length <= 3 * 104
     * -104 <= nums[i] <= 104
     * nums 已按升序排列
     * <p>
     * <p>
     * Related Topics
     * 数组
     * 双指针
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int low = 0;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[low] != nums[fast]) {
                nums[low + 1] = nums[fast];
                low++;
            }
        }
        return low + 1;
    }

    /**
     * 283. 移动零
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * <p>
     * 示例:
     * <p>
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * 说明:
     * <p>
     * 必须在原数组上操作，不能拷贝额外的数组。
     * 尽量减少操作次数。
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int low = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                nums[low] = nums[fast];
                low++;
            }
        }
        for (int i = low + 1; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public void moveZeroes1(int[] nums) {
        int low = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                int temp = nums[low];
                nums[low] = nums[fast];
                nums[fast] = temp;
                low++;
            }
        }
    }

    /**
     * 977. 有序数组的平方
     * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [-4,-1,0,3,10]
     * 输出：[0,1,9,16,100]
     * 解释：平方后，数组变为 [16,1,0,9,100]
     * 排序后，数组变为 [0,1,9,16,100]
     * 示例 2：
     * <p>
     * 输入：nums = [-7,-3,2,3,11]
     * 输出：[4,9,9,49,121]
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 104
     * -104 <= nums[i] <= 104
     * nums 已按 非递减顺序 排序
     *
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int left = 0, right = n - 1, index = n - 1;
        while (left <= right) {
            int a = nums[left] * nums[left];
            int b = nums[right] * nums[right];
            if (a >= b) {
                result[index--] = a;
                left++;
            } else {
                result[index--] = b;
                right--;
            }
        }
        return result;
    }

    /**
     * 321. 拼接最大数
     * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
     * <p>
     * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
     * <p>
     * 说明: 请尽可能地优化你算法的时间和空间复杂度。
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * nums1 = [3, 4, 6, 5]
     * nums2 = [9, 1, 2, 5, 8, 3]
     * k = 5
     * 输出:
     * [9, 8, 6, 5, 3]
     * 示例 2:
     * <p>
     * 输入:
     * nums1 = [6, 7]
     * nums2 = [6, 0, 4]
     * k = 5
     * 输出:
     * [6, 7, 6, 0, 4]
     * 示例 3:
     * <p>
     * 输入:
     * nums1 = [3, 9]
     * nums2 = [8, 9]
     * k = 3
     * 输出:
     * [9, 8, 9]
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return 为了找到长度为 k 的最大数，
     * 需要从两个数组中分别选出最大的子序列，
     * 1、这两个子序列的长度之和为 k，
     * 2、然后将这两个子序列合并得到最大数。
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int[] maxSubsequence = new int[k];
        int start = Math.max(0, k - n), end = Math.min(k, m);
        for (int i = start; i <= end; i++) {
            int[] subsequence1 = maxSequence(nums1, i);
            int[] subsequence2 = maxSequence(nums2, k - i);
            int[] curMaxSubsequence = merge(subsequence1, subsequence2);
            if (compare(curMaxSubsequence, 0, maxSubsequence, 0) > 0) {
                System.arraycopy(curMaxSubsequence, 0, maxSubsequence, 0, k);
            }
        }
        return maxSubsequence;
    }

    public int[] maxSequence(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[k];
        int top = -1;
        int remain = n - k;
        for (int i = 0; i < n; i++) {
            int temp = nums[i];
            while (top >= 0 && temp > result[top] && remain > 0) {
                top--;
                remain--;
            }
            if (top < k - 1) {
                result[++top] = temp;
            } else {
                remain--;
            }
        }
        return result;
    }

    public int[] merge(int[] subsequence1, int[] subsequence2) {
        int x = subsequence1.length, y = subsequence2.length;
        if (x == 0) {
            return subsequence2;
        }
        if (y == 0) {
            return subsequence1;
        }
        int mergeLength = x + y;
        int[] merged = new int[mergeLength];
        int index1 = 0, index2 = 0;
        for (int i = 0; i < mergeLength; i++) {
            if (compare(subsequence1, index1, subsequence2, index2) > 0) {
                merged[i] = subsequence1[index1++];
            } else {
                merged[i] = subsequence2[index2++];
            }
        }
        return merged;
    }

    public int compare(int[] subsequence1, int index1, int[] subsequence2, int index2) {
        int x = subsequence1.length, y = subsequence2.length;
        while (index1 < x && index2 < y) {
            int difference = subsequence1[index1] - subsequence2[index2];
            if (difference != 0) {
                return difference;
            }
            index1++;
            index2++;
        }
        return (x - index1) - (y - index2);
    }

    /**
     * 344. 反转字符串
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
     * <p>
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = ["h","e","l","l","o"]
     * 输出：["o","l","l","e","h"]
     * 示例 2：
     * <p>
     * 输入：s = ["H","a","n","n","a","h"]
     * 输出：["h","a","n","n","a","H"]
     *
     * @param s
     */
    public void reverseString(char[] s) {
        int length = s.length;
        char[] tmp = new char[length];
        for (int i = 0; i < length; i++) {
            tmp[i] = s[i];
        }
        for (int i = 0; i < length; i++) {
            s[i] = tmp[length - i - 1];
        }
    }

    /**
     * 双指针
     *
     * @param s
     */
    public void reverseString1(char[] s) {
        int left = 0, right = s.length - 1;
        while (left <= right) {
            char tmp = s[left];
            s[left++] = s[right];
            s[right--] = tmp;
        }
    }

    /**
     * 剑指 Offer 05. 替换空格
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "We are happy."
     * 输出："We%20are%20happy."
     * <p>
     * <p>
     * 限制：
     * <p>
     * 0 <= s 的长度 <= 10000
     *
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (' ' == s.charAt(i)) {
                result.append("%20");
            } else {
                result.append(s.charAt(i));
            }
        }
        return result.toString();
    }

    /**
     * 42. 接雨水
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * 示例 1：
     * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出：6
     * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
     * 示例 2：
     * <p>
     * 输入：height = [4,2,0,3,2,5]
     * 输出：9
     * 思路：
     * 对于位置i，能装下多少水呢？
     * 位置i能达到的水柱高度和其左边的最高柱子、右边的最高柱子有关，我们分别称这两个柱子高度为l_max和r_max；位置 i 最大的水柱高度就是min(l_max, r_max) - height[i]
     */
    public int trap(int[] height) {
        int length = height.length;
        int result = 0;
        for (int i = 1; i < length - 1; i++) {
            int left = 0, right = 0;
            for (int j = 0; j < i; j++) {
                left = Math.max(left, height[j]);
            }
            for (int j = i + 1; j < length; j++) {
                right = Math.max(right, height[j]);
            }
            if (Math.min(left, right) - height[i] > 0) {
                result += Math.min(left, right) - height[i];
            }
        }
        return result;
    }

    /**
     * 备忘录做法
     *
     * @param height
     * @return
     */
    public int trap1(int[] height) {
        int length = height.length;
        int result = 0;
        int[] left = new int[length];
        int[] right = new int[length];
        for (int i = 1; i < length; i++) {
            left[i] = Math.max(left[i - 1], height[i - 1]);
        }
        for (int i = length - 2; i > 0; i--) {
            right[i] = Math.max(right[i + 1], height[i + 1]);
        }
        for (int i = 1; i < length - 1; i++) {
            if (Math.min(left[i], right[i]) - height[i] > 0) {
                result += Math.min(left[i], right[i]) - height[i];
            }
        }
        return result;
    }

    /**
     * 双指针
     *
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        int left = 0, right = height.length - 1;
        int lMax = 0, rMax = 0;
        int result = 0;
        while (left < right) {
            lMax = Math.max(lMax, height[left]);
            rMax = Math.max(rMax, height[right]);
            if (lMax < rMax) {
                result += Math.max(lMax - height[left], 0);
                left++;
            } else {
                result += Math.max(rMax - height[right], 0);
                right--;
            }
        }
        return result;
    }

    /**
     * 单调递减栈来求值
     *
     * @param height
     * @return
     */
    public int trap3(int[] height) {
        Deque<Integer> stack = new LinkedList<>();
        int result = 0, i = 0;
        while (i < height.length) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int distance = i - stack.peek() - 1;
                int minHeight = Math.min(height[i], height[stack.peek()]) - height[top];
                result += distance * minHeight;
            }
            stack.push(i++);
        }
        return result;
    }

    public int trap4(int[] height) {
        int ans = 0, current = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        while (current < height.length) {
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty())
                    break;
                int distance = current - stack.peek() - 1;
                int bounded_height = Math.min(height[current], height[stack.peek()]) - height[top];
                ans += distance * bounded_height;
            }
            stack.push(current++);
        }
        return ans;
    }


    /**
     * 11. 盛最多水的容器
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * <p>
     * 说明：你不能倾斜容器。
     * 示例 1：
     * 输入：[1,8,6,2,5,4,8,3,7]
     * 输出：49
     * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
     * 示例 2：
     * <p>
     * 输入：height = [1,1]
     * 输出：1
     * 示例 3：
     * <p>
     * 输入：height = [4,3,2,1,4]
     * 输出：16
     * 示例 4：
     * <p>
     * 输入：height = [1,2,1]
     * 输出：2
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int result = 0;
        while (left < right) {
            int temp = Math.min(height[left], height[right]) * (right - left);
            result = Math.max(result, temp);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return result;
    }

    /**
     * 54. 螺旋矩阵
     * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,2,3,6,9,8,7,4,5]
     * 示例 2：
     * <p>
     * <p>
     * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
     * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
     *
     * @param matrix
     * @return
     */
    public List<Integer> matrix(int[][] matrix) {
        int m = matrix.length - 1;
        int n = matrix[0].length - 1;
        List<Integer> result = new ArrayList<>();
        int cur = 0;
        int i, j;
        int max = (m + 1) * (n + 1);
        while (result.size() < max) {
            for (j = cur; j <= n - cur && result.size() < max; j++) {
                result.add(matrix[cur][j]);
            }
            for (i = cur + 1; i <= m - cur && result.size() < max; i++) {
                result.add(matrix[i][n - cur]);
            }
            for (j = n - cur - 1; j >= cur && result.size() < max; j--) {
                result.add(matrix[m - cur][j]);
            }
            for (i = m - cur - 1; i >= cur + 1 && result.size() < max; i--) {
                result.add(matrix[i][cur]);
            }
            cur++;
        }
        return result;
    }

    public List<Integer> matrix1(int[][] matrix) {
        int m = matrix.length - 1, n = matrix[0].length - 1;
        int t = 0, f = m, l = 0, r = n;
        List<Integer> result = new ArrayList<>();
        int max = (m + 1) * (n + 1);
        while (result.size() < max) {
            for (int i = l; i <= r && result.size() < max; i++) {
                result.add(matrix[t][i]);
            }
            t++;
            for (int i = t; i <= f && result.size() < max; i++) {
                result.add(matrix[i][r]);
            }
            r--;
            for (int i = r; i >= l && result.size() < max; i--) {
                result.add(matrix[f][i]);
            }
            f--;
            for (int i = f; i >= t && result.size() < max; i--) {
                result.add(matrix[i][l]);
            }
            l++;

        }
        return result;
    }

    /**
     * 31. 下一个排列
     * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
     * <p>
     * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
     * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
     * <p>
     * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
     * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
     * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
     * 给你一个整数数组 nums ，找出 nums 的下一个排列。
     * 思路：找到一个大于当前序列的新序列，且变大的幅度尽可能小
     * 找到一个大于当前序列的新序列：
     * 我们需要将一个左边的「较小数」与一个右边的「较大数」交换，以能够让当前排列变大，从而得到下一个排列。
     * 变大的幅度尽可能小：
     * 1、在尽可能靠右的低位进行交换，需要从后向前查找
     * 2、将一个 尽可能小的「大数」 与前面的「小数」交换。比如 123465，下一个排列应该把 5 和 4 交换而不是把 6 和 4 交换
     * 3、将「大数」换到前面后，需要将「大数」后面的所有数重置为升序，升序排列就是最小的排列。以 123465 为例：首先按照上一步，交换 5 和 4，得到 123564；然后需要将 5 之后的数重置为升序，得到 123546。显然 123546 比 123564 更小，123546 就是 123465 的下一个排列
     * 具体做法：
     * 首先从后向前查找第一个顺序对 (i,i+1)(i,i+1)，满足 a[i] < a[i+1]a[i]<a[i+1]。这样「较小数」即为 a[i]a[i]。此时 [i+1,n)[i+1,n) 必然是下降序列。
     * <p>
     * 如果找到了顺序对，那么在区间 [i+1,n)[i+1,n) 中从后向前查找第一个元素 jj 满足 a[i] < a[j]a[i]<a[j]。这样「较大数」即为 a[j]a[j]。
     * <p>
     * 交换 a[i]a[i] 与 a[j]a[j]，此时可以证明区间 [i+1,n)[i+1,n) 必为降序。我们可以直接使用双指针反转区间 [i+1,n)[i+1,n) 使其变为升序，而无需对该区间进行排序。
     *
     * @param nums
     */


    public void nextPermutation(int[] nums) {
        int n = nums.length - 1;
        int i, j;
        for (i = n; i > 0; i--) {
            // 找到较小数
            if (nums[i - 1] < nums[i]) {
                Arrays.sort(nums, i, n + 1);
                for (j = i; j <= n; j++) {
                    // 找到较大数
                    if (nums[j] > nums[i - 1]) {
                        int temp = nums[i - 1];
                        nums[i - 1] = nums[j];
                        nums[j] = temp;
                        System.out.println(Arrays.toString(nums));
                        return;
                    }
                }
            }
        }
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void nextPermutation1(int[] nums) {
        int n = nums.length - 1;
        int i, j;
        for (i = n; i > 0; i--) {
            // 找到较小数
            if (nums[i - 1] < nums[i]) {
                for (j = n; j >= i; j--) {
                    // 找到较大数
                    if (nums[j] > nums[i - 1]) {
                        swap(nums, i - 1, j);
                        break;
                    }
                }
                break;
            }
        }
        reverse(nums, i, n);
        System.out.println(Arrays.toString(nums));
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int i, int j) {
        while (i <= j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    /**
     * 41. 缺失的第一个正数
     * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
     * <p>
     * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,2,0]
     * 输出：3
     * 示例 2：
     * <p>
     * 输入：nums = [3,4,-1,1]
     * 输出：2
     * 示例 3：
     * <p>
     * 输入：nums = [7,8,9,11,12]
     * 输出：1
     * 最傻逼的做法hash表
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }
        int index = 1;
        while (index++ <= nums.length) {
            if (!set.contains(index)) {
                return index;
            }
        }
        return index;
    }

    /**
     * 题目说要常量级空间，使用哈希表是不对的，因此很简单，将数组作为哈希表
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive1(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        for (int i = 0; i < n; i++) {
            // i所代表的值
            int index = Math.abs(nums[i]);
            // 将数组index下标改为负数，代表index已经出现
            if (index <= n) {
                nums[index - 1] = -Math.abs(nums[index - 1]);
            }
        }
        for (int i = 0; i < n; i++) {
            // 如果大于0代表数组中从来没出现过
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    /**
     * 把元素移到正确的位置上
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive2(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 将元素放到对应的下标去，这里虽然看起来不像O(n) 复杂度，但是确实是的
            while (nums[i] > 0 && nums[i] - 1 < n && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] - 1 != i) {
                return i + 1;
            }
        }
        return n + 1;
    }

    /**
     * 268. 丢失的数字
     * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [3,0,1]
     * 输出：2
     * 解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
     * 示例 2：
     * <p>
     * 输入：nums = [0,1]
     * 输出：2
     * 解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
     * 示例 3：
     * <p>
     * 输入：nums = [9,6,4,2,3,5,7,0,1]
     * 输出：8
     * 解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。
     * 示例 4：
     * <p>
     * 输入：nums = [0]
     * 输出：1
     * 解释：n = 1，因为有 1 个数字，所以所有的数字都在范围 [0,1] 内。1 是丢失的数字，因为它没有出现在 nums 中。
     * 一模一样
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (i != nums[i] && nums[i] < n) {
                int temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
            }

        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return n;

    }

    /**
     * 64. 最小路径和
     * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * <p>
     * 说明：每次只能向下或者向右移动一步。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
     * 输出：7
     * 解释：因为路径 1→3→1→1→1 的总和最小。
     * 示例 2：
     * <p>
     * 输入：grid = [[1,2,3],[4,5,6]]
     * 输出：12
     * <p>
     * <p>
     * 提示：
     * <p>
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 200
     * 0 <= grid[i][j] <= 100
     * <p>
     * 思路：很明显的dp题
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int[] d : dp) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int old;
                if (i == 0 && j == 0) {
                    old = 0;
                } else if (i == 0) {
                    old = dp[i][j - 1];
                } else if (j == 0) {
                    old = dp[i - 1][j];
                } else {
                    old = Math.min(dp[i][j - 1], dp[i - 1][j]);
                }
                dp[i][j] = old + grid[i][j];
            }
        }
        // 记录路径，这是最蠢的办法了
        LinkedList<Integer> res = new LinkedList<>();
        int i = m - 1, j = n - 1;
        res.addFirst(grid[i][j]);
        while (i > 0 && j > 0) {
            if (dp[i - 1][j] > dp[i][j - 1]) {
                res.addFirst(grid[i][j - 1]);
                j--;
            } else {
                res.addFirst(grid[i - 1][j]);
                i--;
            }
            if (i == 0) {
                j--;
                while (j >= 0) {
                    res.addFirst(grid[i][j--]);
                }
            }
            if (j == 0) {
                i--;
                while (i >= 0) {
                    res.addFirst(grid[i--][j]);
                }
            }
        }

        System.out.println(res);
        return dp[m - 1][n - 1];
    }


    /**
     * 一维dp优化
     *
     * @param grid
     * @return
     */
    public int minPathSum1(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = grid[0][0];
        for (int j = 1; j < n; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int old;
                if (j == 0) {
                    old = dp[j];
                } else {
                    old = Math.min(dp[j - 1], dp[j]);
                }
                dp[j] = old + grid[i][j];
            }
        }

        return dp[n - 1];
    }

    /**
     * 48. 旋转图像
     * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
     * <p>
     * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[[7,4,1],[8,5,2],[9,6,3]]
     * 示例 2：
     * <p>
     * <p>
     * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
     * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
     * <p>
     * <p>
     * 提示：
     * <p>
     * n == matrix.length == matrix[i].length
     * 1 <= n <= 20
     * -1000 <= matrix[i][j] <= 1000
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int m = matrix.length - 1;
        int n = matrix[0].length - 1;
        int flag = m % 2 == 0 ? m / 2 - 1 : m / 2;
        for (int i = 0; i <= flag; i++) {
            for (int j = 0; j <= n / 2; j++) {
                int row = j, col = m - i;
                int temp = matrix[i][j];
                while (i != row || j != col) {
                    int a = matrix[row][col];
                    matrix[row][col] = temp;
                    temp = a;
                    int b = row;
                    row = col;
                    col = m - b;
                }
                matrix[i][j] = temp;

            }
        }
        for (int[] a : matrix) {
            System.out.println(Arrays.toString(a));
        }
    }

}

