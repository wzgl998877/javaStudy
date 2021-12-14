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
        int[] searchNums = {5, 5, 5, 1, 2, 3, 4, 5};
        System.out.println(array.search(searchNums, 0));
        System.out.println(array.search2(searchNums, 0));
        System.out.println(array.searchII(searchNums, 1));
        System.out.println(array.searchII2(searchNums, 1));
        System.out.println(array.searchIII(searchNums, 5));
        System.out.println(array.searchIII2(searchNums, 5));
        int[] range = {7};
        int[] result = array.searchRange(range, 7);
        for (int i : result) {
            System.out.println(i);
        }
        System.out.println(array.minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(array.checkInclusion("ab", "eidboaoo"));
        System.out.println(array.checkInclusion1("ab", "eidboaoo"));
        int[] arg = {1,12,-5,-6,50,3};
        System.out.println(array.findMaxAverage(arg, 4));
        System.out.println(array.findMaxAverage1(arg, 4));
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
            rev = !rev;
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

    /**
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
     * <p>
     * <p>
     * 提示：
     * <p>
     * n == nums.length
     * 1 <= k <= n <= 105
     * -104 <= nums[i] <= 104
     * Related Topics
     * 数组
     * 滑动窗口
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
     *
     *
     *
     * 示例 1:
     *
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     *
     * 输入: s = "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     *
     * 输入: s = "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     * 示例 4:
     *
     * 输入: s = ""
     * 输出: 0
     *
     *
     * 提示：
     *
     * 0 <= s.length <= 5 * 104
     * s 由英文字母、数字、符号和空格组成
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0, result = 0;
        Map<Character, Integer> window = new HashMap<>();
        while(right < s.length()) {
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


}
