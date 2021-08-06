package com.zt.javastudy.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author zhengtao
 * @description 贪心算法学习
 * @date 2020/12/21
 */
public class LeetCodeForGreedy {
    public static void main(String[] args) {
        System.out.println(integerBreak(6));
        int[][] clips = {{0, 2}, {4, 6}, {8, 10}, {1, 9}, {1, 5}, {5, 9}};
        int[][] clips2 = {{0, 2}, {4, 6}, {8, 10}, {1, 9}, {1, 5}, {5, 9}};
        System.out.println(merge(clips));
        System.out.println(videoStitching(clips, 10));
        System.out.println(intervalIntersection1(clips, clips2));
        int[] ratings = {1, 2, 87, 87, 87, 2, 1};
        System.out.println(candy(ratings));
    }

    /**
     * 因为两个数相乘，当相等的时候是最大的，2n,n*n是最大的，这个时候去考虑n要不要继续拆分，即（n/2)*(n/2)和n的大小，即可得到n>4时都会大于,即n>4时把所有的绳子都切成3,n<=4就不要切了。
     * 343. 整数拆分
     *
     * @return
     */
    public static int integerBreak(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int remainder = n % 3;
        int quotient = n / 3;
        if (remainder == 0) {
            return (int) Math.pow(3, quotient);
        } else if (remainder == 1) {
            return (int) (Math.pow(3, quotient - 1) * 4);
        } else {
            return (int) (Math.pow(3, quotient) * 2);
        }
    }

    /**
     * 435. 无重叠区间
     * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
     * <p>
     * 注意:
     * <p>
     * 可以认为区间的终点总是大于它的起点。
     * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
     * 示例 1:
     * <p>
     * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
     * <p>
     * 输出: 1
     * <p>
     * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
     * 示例 2:
     * <p>
     * 输入: [ [1,2], [1,2], [1,2] ]
     * <p>
     * 输出: 2
     * <p>
     * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
     * 示例 3:
     * <p>
     * 输入: [ [1,2], [2,3] ]
     * <p>
     * 输出: 0
     * <p>
     * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt((int[] a) -> a[1]));
        int n = 0;
        int flag = intervals[0][1];
        for (int[] d : intervals) {
            if (d[0] < flag) {
                n++;
            } else {
                flag = d[1];
            }
        }
        return n - 1;
    }

    /**
     * 452. 用最少数量的箭引爆气球
     * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以纵坐标并不重要，因此只要知道开始和结束的横坐标就足够了。开始坐标总是小于结束坐标。
     * <p>
     * 一支弓箭可以沿着 x 轴从不同点完全垂直地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
     * <p>
     * 给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：points = [[10,16],[2,8],[1,6],[7,12]]
     * 输出：2
     * 解释：对于该样例，x = 6 可以射爆 [2,8],[1,6] 两个气球，以及 x = 11 射爆另外两个气球
     * 示例 2：
     * <p>
     * 输入：points = [[1,2],[3,4],[5,6],[7,8]]
     * 输出：4
     * 示例 3：
     * <p>
     * 输入：points = [[1,2],[2,3],[3,4],[4,5]]
     * 输出：2
     * 示例 4：
     * <p>
     * 输入：points = [[1,2]]
     * 输出：1
     * 示例 5：
     * <p>
     * 输入：points = [[2,3],[2,3]]
     * 输出：1
     *
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt((int[] a) -> a[1]));
        int n = 0;
        int right = points[0][1];
        for (int[] d : points) {
            if (d[0] > right) {
                n++;
                right = d[1];
            }
        }
        return n;
    }

    /**
     * 1024. 视频拼接
     * 你将会获得一系列视频片段，这些片段来自于一项持续时长为 T 秒的体育赛事。这些片段可能有所重叠，也可能长度不一。
     * <p>
     * 视频片段 clips[i] 都用区间进行表示：开始于 clips[i][0] 并于 clips[i][1] 结束。我们甚至可以对这些片段自由地再剪辑，例如片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。
     * <p>
     * 我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, T]）。返回所需片段的最小数目，如果无法完成该任务，则返回 -1 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
     * 输出：3
     * 解释：
     * 我们选中 [0,2], [8,10], [1,9] 这三个片段。
     * 然后，按下面的方案重制比赛片段：
     * 将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
     * 现在我们手上有 [0,2] + [2,8] + [8,10]，而这些涵盖了整场比赛 [0, 10]。
     * 示例 2：
     * <p>
     * 输入：clips = [[0,1],[1,2]], T = 5
     * 输出：-1
     * 解释：
     * 我们无法只用 [0,1] 和 [1,2] 覆盖 [0,5] 的整个过程。
     * 示例 3：
     * <p>
     * 输入：clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], T = 9
     * 输出：3
     * 解释：
     * 我们选取片段 [0,4], [4,7] 和 [6,9] 。
     * 示例 4：
     * <p>
     * 输入：clips = [[0,4],[2,8]], T = 5
     * 输出：2
     * 解释：
     * 注意，你可能录制超过比赛结束时间的视频。
     *
     * @param clips
     * @param time
     * @return
     */
    public static int videoStitching(int[][] clips, int time) {
        Arrays.sort(clips, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });
        // 记录选择的短视频个数
        int res = 0;
        int curEnd = 0, nextEnd = 0;
        int i = 0, n = clips.length;
        while (i < n && clips[i][0] <= curEnd) {
            // 在第 res 个视频的区间内贪心选择下一个视频
            while (i < n && clips[i][0] <= curEnd) {
                nextEnd = Math.max(nextEnd, clips[i][1]);
                i++;
            }
            // 找到下一个视频，更新 curEnd
            res++;
            curEnd = nextEnd;
            if (curEnd >= time) {
                // 已经可以拼出区间 [0, T]
                return res;
            }
        }
        // 无法连续拼出区间 [0, T]
        return -1;
    }

    /**
     * 1288. 删除被覆盖区间
     * 给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。
     * <p>
     * 只有当 c <= a 且 b <= d 时，我们才认为区间 [a,b) 被区间 [c,d) 覆盖。
     * <p>
     * 在完成所有删除操作后，请你返回列表中剩余区间的数目。
     * <p>
     * <p>
     * <p>
     * 示例：
     * <p>
     * 输入：intervals = [[1,4],[3,6],[2,8]]
     * 输出：2
     * 解释：区间 [3,6] 被区间 [2,8] 覆盖，所以它被删除了。
     * <p>
     * <p>
     * <p>
     * <p>
     * 1 <= intervals.length <= 1000
     * 0 <= intervals[i][0] < intervals[i][1] <= 10^5
     * 对于所有的 i != j：intervals[i] != intervals[j]
     *
     * @param intervals
     * @return
     */
    public int removeCoveredIntervals(int[][] intervals) {
        // 起点升序，终点降序
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });
        int left = intervals[0][0];
        int right = intervals[0][1];
        int n = 0;
        for (int[] d : intervals) {
            if (d[0] >= left && d[1] <= right) {
                n++;
            } else if (d[0] >= right) {
                left = d[0];
                right = d[1];
            } else if (d[0] >= left && d[1] >= right) {
                right = d[1];
            }
        }
        return intervals.length - n + 1;
    }

    /**
     * 56. 合并区间
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * 示例 2：
     * <p>
     * 输入：intervals = [[1,4],[4,5]]
     * 输出：[[1,5]]
     * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= intervals.length <= 104
     * intervals[i].length == 2
     * 0 <= starti <= endi <= 104
     *
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });
        List<int[]> list = new ArrayList<>();
        int left = intervals[0][0];
        int right = intervals[0][1];
        for (int[] d : intervals) {
            if (d[0] > right) {
                int[] tmp = new int[2];
                tmp[0] = left;
                tmp[1] = right;
                list.add(tmp);
                left = d[0];
                right = d[1];
            } else if (d[0] > left) {
                right = d[1] > right ? d[1] : right;
            }
        }
        int[] tmp = new int[2];
        tmp[0] = left;
        tmp[1] = right;
        list.add(tmp);
        return list.toArray(new int[0][]);
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
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // zuobuchulai
        return null;
    }

    /**
     * 986. 区间列表的交集
     * 给定两个由一些 闭区间 组成的列表，firstList 和 secondList ，其中 firstList[i] = [starti, endi] 而 secondList[j] = [startj, endj] 。每个区间列表都是成对 不相交 的，并且 已经排序 。
     * <p>
     * 返回这 两个区间列表的交集 。
     * <p>
     * 形式上，闭区间 [a, b]（其中 a <= b）表示实数 x 的集合，而 a <= x <= b 。
     * <p>
     * 两个闭区间的 交集 是一组实数，要么为空集，要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3] 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
     * 输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
     * 示例 2：
     * <p>
     * 输入：firstList = [[1,3],[5,9]], secondList = []
     * 输出：[]
     * 示例 3：
     * <p>
     * 输入：firstList = [], secondList = [[4,8],[10,12]]
     * 输出：[]
     * 示例 4：
     * <p>
     * 输入：firstList = [[1,7]], secondList = [[3,10]]
     * 输出：[[3,7]]
     *
     * @param firstList
     * @param secondList
     * @return
     */
    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> list = new ArrayList<>();
        // 先合并再排序
        for (int[] d : firstList) {
            list.add(d);
        }
        for (int[] d : secondList) {
            list.add(d);
        }
        int[][] temp = list.toArray(new int[0][]);
        Arrays.sort(temp, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });
        int left = temp[0][0];
        int right = temp[0][1];
        list.clear();
        for (int[] d : temp) {
            if (d[0] >= left && d[1] <= right) {
                list.add(d);
            } else if (d[0] >= left && d[1] >= right && d[0] <= right) {
                int[] a = new int[2];
                a[0] = d[0];
                a[1] = right;
                right = d[1];
                list.add(a);
            } else if (d[0] > right) {
                left = d[0];
                right = d[1];
            }
        }
        list.remove(0);
        return list.toArray(new int[0][]);
    }

    public static int[][] intervalIntersection1(int[][] firstList, int[][] secondList) {
        int i = 0, j = 0;
        List<int[]> list = new ArrayList<>();
        int m = firstList.length, n = secondList.length;
        while (i < m && j < n) {
            int lo = Math.max(firstList[i][0], secondList[j][0]);
            int hi = Math.min(firstList[i][1], secondList[j][1]);
            if (lo <= hi)
                list.add(new int[]{lo, hi});

            // Remove the interval with the smallest endpoint
            if (firstList[i][1] < secondList[j][1])
                i++;
            else
                j++;
        }
        return list.toArray(new int[0][]);
    }

    /**
     * 134. 加油站
     * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
     * <p>
     * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
     * <p>
     * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
     * <p>
     * 说明:
     * <p>
     * 如果题目有解，该答案即为唯一答案。
     * 输入数组均为非空数组，且长度相同。
     * 输入数组中的元素均为非负数。
     * 示例 1:
     * <p>
     * 输入:
     * gas  = [1,2,3,4,5]
     * cost = [3,4,5,1,2]
     * <p>
     * 输出: 3
     * <p>
     * 解释:
     * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
     * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
     * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
     * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
     * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
     * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
     * 因此，3 可为起始索引。
     * 示例 2:
     * <p>
     * 输入:
     * gas  = [2,3,4]
     * cost = [3,4,3]
     * <p>
     * 输出: -1
     * <p>
     * 解释:
     * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
     * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
     * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
     * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
     * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
     * 因此，无论怎样，你都不可能绕环路行驶一周。
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int sum = 0, min = Integer.MAX_VALUE;
        int start = 0;
        for (int i = 0; i < n; i++) {
            sum += gas[i] - cost[i];
            // 找出最低点，如果存在环路那一定是从最低点出发的
            if (sum < min) {
                min = sum;
                start = i + 1;
            }
        }
        if (sum < 0) {
            return -1;
        }
        return start == n ? 0 : start;
    }

    public int canCompleteCircuit1(int[] gas, int[] cost) {
        int n = gas.length;
        int sum = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            sum += gas[i] - cost[i];
        }
        if (sum < 0) {
            return -1;
        }
        int tank = 0;
        for (int i = 0; i < n; i++) {
            tank += gas[i] - cost[i];
            if (tank < 0) {
                // 如果走不到，那么就以下一个点为起点
                tank = 0;
                start = i + 1;
            }
        }

        return start == n ? 0 : start;
    }

    /**
     * 406. 根据身高重建队列
     * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
     * <p>
     * 请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
     * 输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
     * 解释：
     * 编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
     * 编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
     * 编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
     * 编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
     * 编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
     * 编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
     * 因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列。
     * 示例 2：
     * <p>
     * 输入：people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]]
     * 输出：[[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= people.length <= 2000
     * 0 <= hi <= 106
     * 0 <= ki < people.length
     * 题目数据确保队列可以被重建
     *
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        // 按身高降序，排名升序
        Arrays.sort(people, (int[] a, int[] b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return b[0] - a[0];
        });
        // 使用list来存储数据
        List<int[]> list = new ArrayList<>();
        for (int[] p : people) {
            // 将自己放入到应该在的排名上，因为排了序，所以位置之前的数一定是比自己小的
            list.add(p[1], p);
        }
        return list.toArray(new int[0][]);
    }

    /**
     * 135. 分发糖果
     * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
     * <p>
     * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
     * <p>
     * 每个孩子至少分配到 1 个糖果。
     * 评分更高的孩子必须比他两侧的邻位孩子获得更多的糖果。
     * 那么这样下来，老师至少需要准备多少颗糖果呢？
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：[1,0,2]
     * 输出：5
     * 解释：你可以分别给这三个孩子分发 2、1、2 颗糖果。
     * 示例 2：
     * <p>
     * 输入：[1,2,2]
     * 输出：4
     * 解释：你可以分别给这三个孩子分发 1、2、1 颗糖果。
     * 第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
     *
     * @param ratings
     * @return
     */
    public static int candy(int[] ratings) {
        int length = ratings.length;
        int[] temp = new int[length];
        Arrays.fill(temp, 1);
        // 这种题目先确定一边，比如这里是先确定右边的比左边大的
        for (int i = 1; i < length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                temp[i] = temp[i - 1] + 1;
            }
        }
        // 再确定左边比右边大
        for (int i = length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                temp[i] = Math.max(temp[i], temp[i + 1] + 1);
            }
        }
        int n = 0;
        for (int i : temp) {
            n += i;
        }
        return n;
    }
    public int candy1(int[] ratings) {
        int n = ratings.length;
        int ret = 1;
        int inc = 1, dec = 0, pre = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] >= ratings[i - 1]) {
                dec = 0;
                pre = ratings[i] == ratings[i - 1] ? 1 : pre + 1;
                ret += pre;
                inc = pre;
            } else {
                dec++;
                if (dec == inc) {
                    dec++;
                }
                ret += dec;
                pre = 1;
            }
        }
        return ret;
    }
}

