package com.zt.javastudy.leetcode;

import java.util.Arrays;

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

}
