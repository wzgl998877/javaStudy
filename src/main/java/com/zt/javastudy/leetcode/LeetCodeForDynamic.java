package com.zt.javastudy.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhengtao
 * @description 动态规划
 * @date 2020/12/21
 */
public class LeetCodeForDynamic {
    public static void main(String[] args) {
        String word1 = "rad";
        String word2 = "append";
        System.out.println(minDistance1(word1, word2));
        int[] nums = {10,9,2,5,3,7,101,102,103,18,19};
        System.out.println(lengthOfLIS(nums));
        System.out.println(lengthOfLIS1(nums));
        int[][] doubleNums = {{1,2},{22,3},{1,3},{21,4}};
        System.out.println(maxEnvelopes(doubleNums));
        System.out.println(maxEnvelopes2(doubleNums));

    }
    /**
     * 最暴力的解法，直接递归就可以
     * @param n
     * @return
     */
    public static int fib(int n) {
        if(n == 2 || n == 1) {
            return 1;
        }
        return fib(n-1) + fib(n-2);
    }

    /**
     * 加个备忘录，防止重复计算
     */
    static Map<Integer, Integer> hashMap = new HashMap<>();

    public static int fib1(int n) {
        if(n == 2 || n == 1) {
            return 1;
        }
       if (hashMap.containsKey(n)){
           return hashMap.get(n);
       }
       int num = fib1(n-1) + fib1(n-2);
       hashMap.put(n, num);
       return num;
    }

    /**
     * 动态规划
     * @param n
     * @return
     */
    public static int fib2(int n) {
        if(n == 2 || n == 1) {
            return 1;
        }
        int pre = 1, curr = 1;
        for(int i = 3;i <= n;i++){
            int sum = pre +curr;
            pre = curr;
            curr = sum;
        }
        return curr;
    }

    /**
     * 322. 零钱兑换
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
     *
     * 你可以认为每种硬币的数量是无限的。
     *
     * 最优子结构，就相当于每一次都把最少的硬币找出来那肯定就是最少的
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        for (int i = 0; i < amount+1; i++) {
            dp[i] = amount+1;
        }
        // 这个等于0很关键
        dp[0] = 0;
        for (int i = 0; i < dp.length; i++){
            for (int coin : coins) {
                if (i - coin < 0){
                    continue;
                }
                // 找出最小的值来，当前是最小还是当前减去该硬币数量加一为最小
                dp[i] = Math.min(dp[i], 1 + dp[i-coin]);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    /**
     * 72. 编辑距离
     * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
     *
     * 你可以对一个单词进行如下三种操作：
     *
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     *
     *
     * 示例 1：
     *
     * 输入：word1 = "horse", word2 = "ros"
     * 输出：3
     * 解释：
     * horse -> rorse (将 'h' 替换为 'r')
     * rorse -> rose (删除 'r')
     * rose -> ros (删除 'e')
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        return minDistance(word1, word2, word1.length()-1, word2.length()-1);
    }
    // 递归解法，加上备忘录防止重复计算,这个备忘录该怎么加呢。

    public int minDistance(String word1, String word2, int i, int j) {
        if (i == -1) {
            return j + 1;
        }
        if (j == -1) {
            return i + 1;
        }
        if (word1.charAt(i) == word2.charAt(j)){
            return minDistance(word1, word2, i - 1, j - 1);
        } else {
           return min(minDistance(word1, word2, i, j - 1) + 1 // 插入
                    , minDistance(word1, word2, i - 1, j) + 1,// 删除
                    minDistance(word1, word2, i - 1, j - 1) + 1// 替换
            );
        }
    }

    public static int min(int a, int b, int c){
        return Math.min(a, Math.min(b, c));
    }

    /**
     * 动态规划解法，动态规划解法其实就是自底向上的加了备忘录的递归
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance1(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m+1][n+1];
        for (int i = 1; i <= m; i++){
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; j++){
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if (word1.charAt(i - 1) == word2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = min(dp[i][j - 1] + 1 // 插入
                            , dp[i - 1][j] + 1,// 删除
                            dp[i - 1][j - 1] + 1// 替换
                             );
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 300. 最长递增子序列 又叫做LIS问题
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     *
     * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     *
     *
     * 示例 1：
     *
     * 输入：nums = [10,9,2,5,3,7,101,18]
     * 输出：4
     * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
     * 示例 2：
     *
     * 输入：nums = [0,1,0,3,2,3]
     * 输出：4
     * 示例 3：
     *
     * 输入：nums = [7,7,7,7,7,7,7]
     * 输出：1
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        // 思路，先把每一个位置的最长递增序列找出来，
        // 自己的思路其实是对的，关键在于不太会写。
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++){
            // 也就是这一步，我们只要找到前面那些结尾比自己小的子序列，然后把自己接到最后，就可以形成一个新的递增子序列，而这个新的子序列长度加一
            for (int j = 0; j < i; j++){
                if (nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
        int maxLength = 1;
        for (int i = 0; i < nums.length; i++){
            maxLength = Math.max(maxLength, dp[i]);
        }
        return maxLength;
    }
    public static int lengthOfLIS1(int[] nums) {
        // 纸牌游戏
        int[] top = new int[nums.length];
        int piles = 0;
        for (int i = 0; i < nums.length; i++){
            int poker = nums[i];
            int left = 0, right = piles;
            while (left < right){
                int mid = (left + right) / 2;
                if (poker > top[mid]){
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            if (left == piles){
                piles++;
            }
            top[left] = poker;
        }
        return piles;
    }

    /**
     * 354. 俄罗斯套娃信封问题
     * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
     *
     * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
     *
     * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
     *
     * 注意：不允许旋转信封。
     *
     *
     * 示例 1：
     *
     * 输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
     * 输出：3
     * 解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
     * 示例 2：
     *
     * 输入：envelopes = [[1,1],[1,1],[1,1]]
     * 输出：1
     * @param envelopes
     * @return
     */
    public static int maxEnvelopes(int[][] envelopes) {
        // 信封嵌套问题就需要先按特定的规则排序，之后就转换为一个最长递增子序列问题
        // 排序
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b){
                if(a[0] == b[0]){
                    return a[1] - b[1];
                }else {
                    return a[0] - b[0];
                }
            }
        });
        // 最长递增子序列问题
        int[] dp = new int[envelopes.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < envelopes.length; i++){
            for (int j = 0; j < i; j++){
                if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int maxLength = 1;
        for (int i = 0; i < envelopes.length; i++){
            maxLength = Math.max(maxLength, dp[i]);
        }
        return maxLength;
    }

    /**
     * 排序时，当i相同时j使用倒序排序，这样直接对j进行LIS即可
     * @param envelopes
     * @return
     */
    public static int maxEnvelopes2(int[][] envelopes) {
        // 信封嵌套问题就需要先按特定的规则排序，之后就转换为一个最长递增子序列问题
        // 排序
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b){
                if(a[0] == b[0]){
                    return b[1] - a[1];
                }else {
                    return a[0] - b[0];
                }
            }
        });
        // 最长递增子序列问题，使用纸牌游戏解法
        int[] top = new int[envelopes.length];
        int piles = 0;
        for (int i = 0; i < envelopes.length; i++){
            int left = 0, right = piles;
            int poker = envelopes[i][1];
            while (left < right){
                int mid = (left + right) / 2;
                if (poker > top[mid]){
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            if (left == piles){
                piles++;
            }
            top[left] = poker;
        }
        return piles;
    }



}
