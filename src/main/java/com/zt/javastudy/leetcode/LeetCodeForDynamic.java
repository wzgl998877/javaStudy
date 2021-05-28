package com.zt.javastudy.leetcode;

import java.util.*;

/**
 * @author zhengtao
 * @description 动态规划
 * 动态规划解题基本思路
 * 1、明确是否存在最优子结构
 * 2、确定 dp 数组/函数的含义
 * 3、写出状态转移方程
 * 4、初始化状态
 * 5、根据状态转移方程选择遍历方向 就是填表
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
        int[] a = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(a));
        String text1 = "pmjghexybyrgzczy";
        String text2 = "hafcdqbgncrcbihkd";
        long start = System.currentTimeMillis();
        System.out.println(longestCommonSubsequence1(text1, text2));
        long costTimes = System.currentTimeMillis() - start;
        System.out.println("耗时:"+ costTimes);
        char s = 'e';
        int a1 = s + 'e';
        System.out.println(a1);
        System.out.println(longestPalindrome1("babad"));
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindromeSubseq("cbbd"));
        System.out.println(longestPalindromeSubseq1("cbbd"));
        System.out.println(isPalindrome("bb"));
        int[] nums1 = {0,1,1,1,1};
        int[] nums2 = {1,0,1,0,1};
        System.out.println(findLength(nums1, nums2));
        int N = 3, W = 4;
        int[] wt = {2, 1, 3}, val = {4,2,3};
        System.out.println(backpack(N, W, wt, val));
        int[] nums3 = {1,5,11,5};
        System.out.println(canPartition(nums3));
        int[] coins = {1,2,3};
        int amout = 4;
        System.out.println("开始");
        System.out.println(change1(amout, coins));
        System.out.println(combinationSum4(coins, amout));
        int[] nums4 = {1, 1, 1, 1, 1};
        int S = 3;
        System.out.println(findTargetSumWays(nums4, S));
        System.out.println(findTargetSumWays1(nums4, S));
        String s1 = "applepenapple";
        String[] strings = {"apple", "pen"};
        System.out.println(wordBreak(s1, Arrays.asList(strings)));
        List<Integer> list = findSquares(4);
        for (int i : list){
            System.out.println(i);
        }
        System.out.println(numSquares(4));
        System.out.println(Math.sqrt(4));
        int[] prices = {1,2,3,0,2};
        System.out.println(maxProfit(prices));
        System.out.println(maxProfit5Better(prices));
        int[] robs = {1,2,1,1};
        System.out.println(rob2(robs));
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

    // 动态规划解决子序列问题的两种套路
//    动态规划也是有套路的：单个数组或者字符串要用动态规划时，可以把动态规划 dp[i] 定义为 nums[0:i] 中想要求的结果；
//    当两个数组或者字符串要用动态规划时，可以把动态规划定义成两维的 dp[i][j] ，其含义是在 A[0:i] 与 B[0:j] 之间匹配得到的想要的结果
    // 1、涉及一个字符串的操作，适用于最长递增子序列 LIS问题
//    int n = array.length;
//    int[] dp = new int[n];
//
//    for (int i = 1; i < n; i++) {
//        for (int j = 0; j < i; j++) {
//            dp[i] = 最值(dp[i], dp[j] + ...)
//        }
//    }
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
        // dp的定义是dp[i] 代表nums[0..i]之间的最长递增子序列长度
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
    /**
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     *  
     *
     * 示例 1：
     * [5,4,-1,7,8]
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     * 示例 2：
     *
     * 输入：nums = [1]
     * 输出：1
     * 示例 3：
     *
     * 输入：nums = [0]
     * 输出：0
     * 示例 4：
     *
     * 输入：nums = [-1]
     * 输出：-1
     * 思路其实也是lIS问题
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int result = nums[0];
        int flag = nums[0];
        for (int i = 1; i < nums.length; i++){
            flag = Math.max(flag + nums[i], nums[i]);
            result = Math.max(result, flag);
        }
        return result;
    }

    // 2、涉及两个字符串的问题，两个字符串/数组的子序列。本思路中 dp 数组含义又分为「只涉及一个字符串」和「涉及两个字符串」两种情况
    // 涉及两个字符串/数组时 详解编辑距离 和 最长公共子序列
    //  只涉及一个字符串/数组时 最长回文子序列
//    int n = arr.length;
//    int[][] dp = new dp[n+1][n+1];
//
//    for (int i = 1; i <= n; i++) {
//        for (int j = 1; j <= n; j++) {
//            if (arr[i] == arr[j])
//                dp[i][j] = dp[i-1][j-1] + ...
//        else
//            dp[i][j] = 最值(...)
//        }
//    }
    /**
     * LCS问题
     * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
     *
     * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
     *
     * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
     * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：text1 = "abcde", text2 = "ace"
     * 输出：3
     * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
     * 示例 2：
     *
     * 输入：text1 = "abc", text2 = "abc"
     * 输出：3
     * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
     * 示例 3：
     *
     * 输入：text1 = "abc", text2 = "def"
     * 输出：0
     * 解释：两个字符串没有公共子序列，返回 0
     *
     * @param text1
     * @param text2
     * @return
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        return longestCommonSubsequence(text1, text2, text1.length() - 1, text2.length() - 1);
    }

    /**
     * 暴力递归
     * @param text1
     * @param text2
     * @param i
     * @param j
     * @return
     */
    public static int longestCommonSubsequence(String text1, String text2, int i, int j) {
        if (i == -1 || j == -1){
            return 0;
        }
        if (text1.charAt(i) == text2.charAt(j)){
            return longestCommonSubsequence(text1, text2, i - 1, j - 1) + 1;
        } else {
            return Math.max(longestCommonSubsequence(text1, text2, i - 1, j), longestCommonSubsequence(text1, text2, i, j-1));
        }
    }

    /**
     * 带备忘录的递归就是这样的
     * @param text1
     * @param text2
     * @return
     */
    public static int longestCommonSubsequence1(String text1, String text2) {
        int a = text1.length();
        int b = text2.length();
        int[][] bw = new int[a][b];
        // 就这里也是服了，自己手动循环赋值就超时
        for (int[] s : bw){
            Arrays.fill(s, -1);
        }
        return longestCommonSubsequence1(text1, text2, text1.length() - 1, text2.length() - 1,bw);
    }
    public static int longestCommonSubsequence1(String text1, String text2, int i, int j, int[][] bw) {
        if (i == -1 || j == -1){
            return 0;
        } else if(bw[i][j] != -1){
            return bw[i][j];
        }
        if (text1.charAt(i) == text2.charAt(j)){
            bw[i][j] = longestCommonSubsequence1(text1, text2, i - 1, j - 1, bw) + 1;
        } else {
            bw[i][j] = Math.max(longestCommonSubsequence1(text1, text2, i - 1, j, bw), longestCommonSubsequence1(text1, text2, i, j-1,bw));
        }
        return bw[i][j];
    }
    public static int longestCommonSubsequence2(String text1, String text2) {
        int a = text1.length();
        int b = text2.length();
        // TODO: 2021/5/10 dp的定义是什么？
        // dp[i][j]的定义是text1[0~i]与text2[0~j]之间最长公共子序列的长度
        int[][] dp = new int[a+1][b+1];
        for (int i = 1; i <= a; i++){
            for (int j = 1; j <= b; j++){
                if (text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[a][b];
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
     * 动态规划解法，动态规划解法其实就是自底向上的加了备忘录的递归，也是一个标准的LCS问题
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance1(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        // TODO: 2021/5/10 dp的定义？
        // dp[i][j]表示，word1[0..i] 和 word2[0...j]最小的编辑距离
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
     * 583. 两个字符串的删除操作
     * 给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。
     *
     *
     *
     * 示例：
     *
     * 输入: "sea", "eat"
     * 输出: 2
     * 解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
     * 这题和编辑距离不能说一模一样只能说完全一致
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance2(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        // dp的定义是 dp[i][j]表示，word1[0..i] 和 word2[0...j]最小的删除步数
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i <= m; i++){
            dp[i][0] = i;
        }
        for (int i = 0; i <= n; i++){
            dp[0][i] = i;
        }
        for (int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if (word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j] + 1, dp[i][j-1] +1);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 712. 两个字符串的最小ASCII删除和
     * 给定两个字符串s1, s2，找到使两个字符串相等所需删除字符的ASCII值的最小和。
     *
     * 示例 1:
     *
     * 输入: s1 = "sea", s2 = "eat"
     * 输出: 231
     * 解释: 在 "sea" 中删除 "s" 并将 "s" 的值(115)加入总和。
     * 在 "eat" 中删除 "t" 并将 116 加入总和。
     * 结束时，两个字符串相等，115 + 116 = 231 就是符合条件的最小和。
     * 示例 2:
     *
     * 输入: s1 = "delete", s2 = "leet"
     * 输出: 403
     * 解释: 在 "delete" 中删除 "dee" 字符串变成 "let"，
     * 将 100[d]+101[e]+101[e] 加入总和。在 "leet" 中删除 "e" 将 101[e] 加入总和。
     * 结束时，两个字符串都等于 "let"，结果即为 100+101+101+101 = 403 。
     * 如果改为将两个字符串转换为 "lee" 或 "eet"，我们会得到 433 或 417 的结果，比答案更大。
     * 这一题也是一模一样的，跟编辑距离比这题只要考虑删除的时候就行了
     * @param s1
     * @param s2
     * @return
     */
    public static int minimumDeleteSum(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        //  dp的定义是 dp[i][j]表示，word1[0..i] 和 word2[0...j]最小ASCII删除和
        int[][] dp = new int[m+1][n+1];
        for(int i = 1; i <= m; i++){
            dp[i][0] = s1.charAt(i-1) + dp[i - 1][0];
        }
        for (int j = 1; j <= n; j++){
            dp[0][j] = s2.charAt(j-1) + dp[0][j - 1];
        }
        for(int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
               if (s1.charAt(i-1) == s2.charAt(j-1)){
                   dp[i][j] = dp[i-1][j-1];
               } else {
                   dp[i][j] = Math.min(dp[i-1][j] + s1.charAt(i-1), dp[i][j-1] + s2.charAt(j-1));
               }
            }
        }
        return dp[m][n];
    }

    /**
     * 5. 最长回文子串
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     *
     *
     *
     * 示例 1：
     *
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     * 示例 2：
     *
     * 输入：s = "cbbd"
     * 输出："bb"
     * 示例 3：
     *
     * 输入：s = "a"
     * 输出："a"
     * 示例 4：
     *
     * 输入：s = "ac"
     * 输出："a"
     * 这道题的关键是dp的定义就不一样，这道题用了一个二维的dp,dp(i,j) 表示字符串 s 的第 i 到 j 个字母组成的串是否为回文串
     * 第二点就是填这个表的时候，用的方法是，固定子串长度，然后遍历所有的字符。
     * 因为什么呢，是因为当dp[i][j] = dp[i+1][j-1],求dp[i][j] 就必须知道dp[i+1][j-1],所以我们可以选择倒着或者斜着遍历,这种就属于斜着遍历。
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        int m = s.length();
        if (m < 2){
            return s;
        }
        // 我们用 dp(i,j) 表示字符串 s 的第 i 到 j 个字母组成的串是否为回文串
        boolean[][] dp = new boolean[m][m];
        // 初始化状态，所有长度为1的都是回文串
        for(int i = 0; i < m; i++){
            dp[i][i] = true;
        }
        int left = 0;
        int right = 1;
        // 进行状态转移
        // 子串长度
        for (int i = 2; i <= m; i++){
            // 子串起点
            for (int start = 0; start < m; start++){
                int end = i + start - 1;
                if (end >= m){
                    break;
                }
                if (s.charAt(start) == s.charAt(end)){
                    if (end - start < 3){
                        dp[start][end] = true;
                    } else {
                        dp[start][end] = dp[start+1][end-1];
                    }
                } else {
                    dp[start][end] = false;
                }
                if (dp[start][end] && right < end - start + 1){
                    left = start;
                    right = end - start + 1;
                }
            }
        }
        for (boolean[] d : dp){
            System.out.println(Arrays.toString(d));
        }
        return s.substring(left, right+left);
    }
    /**
     * 递归做法
     * @param s
     * @return
     */
    public static String longestPalindrome1(String s) {
        String res = new String();
        for (int i = 0; i < s.length(); i++){
            String s1 = palindrome(s, i, i);
            String s2 = palindrome(s, i,i+1);
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }
        return res;
    }
    public static String palindrome(String s, int i, int j){
        while (j < s.length() && i >= 0 && s.charAt(i) == s.charAt(j)){
            i--;
            j++;
        }
        return s.substring(i + 1, j);
    }

    /**
     * 516. 最长回文子序列
     * 给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。
     *
     *
     *
     * 示例 1:
     * 输入:
     *
     * "bbbab"
     * 输出:
     *
     * 4
     * 一个可能的最长回文子序列为 "bbbb"。
     *
     * 示例 2:
     * 输入:
     *
     * "cbbd"
     * 输出:
     *
     * 2
     * 一个可能的最长回文子序列为 "bb"。
     * 这个题目和回文子串是一模一样的，只是dp的定义不一样，
     * 这个题目也可以倒着或者斜着遍历，这是斜着遍历
     * @param s
     * @return
     */
    public static int longestPalindromeSubseq(String s) {
        int m = s.length();
        if (m < 2){
            return m;
        }
        // 我们用 dp(i,j) 表示字符串 s 的第 i 到 j 个字母组成的串中最长回文子序列的长度
        int[][] dp = new int[m][m];
        // 初始化状态，所有长度为1的最长回文子串都为1
        for(int i = 0; i < m; i++){
            dp[i][i] = 1;
        }
        for (int i = 2; i <= m; i++){
            for (int start = 0; start < m; start++){
                int end = start + i - 1;
                if (end >= m){
                    break;
                }
                if (s.charAt(start) == s.charAt(end)){
                    dp[start][end] = dp[start+1][end-1] + 2;
                } else {
                    dp[start][end] = Math.max(dp[start+1][end], dp[start][end-1]);
                }
            }
        }
       for (int[] d : dp){
           System.out.println(Arrays.toString(d));
       }
        return dp[0][m-1];
    }

    /**
     * 倒着遍历
     * @param s
     * @return
     */
    public static int longestPalindromeSubseq1(String s) {
        int m = s.length();
        // 我们用 dp(i,j) 表示字符串 s 的第 i 到 j 个字母组成的串中最长回文子序列的长度
        int[][] dp = new int[m][m];
        // 初始化状态，所有长度为1的最长回文子串都为1
        for(int i = 0; i < m; i++){
            dp[i][i] = 1;
        }
        for (int i = m - 1; i >= 0; i--){
            for (int j = i + 1; j < m; j++){
                if (s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        for (int[] d : dp){
            System.out.println(Arrays.toString(d));
        }
        return dp[0][m-1];
    }
    public static boolean isPalindrome(String s){
        StringBuilder stringBuilder = new StringBuilder(s);
        StringBuilder stringBuilder1 = new StringBuilder(s).reverse();
        return stringBuilder1.toString().equals(stringBuilder.toString());
    }

    /**
     * 718. 最长重复子数组
     * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
     *
     *
     *
     * 示例：
     *
     * 输入：
     * A: [1,2,3,2,1]
     * B: [3,2,1,4,7]
     * 输出：3
     * 解释：
     * 长度最长的公共子数组是 [3, 2, 1] 。
     *
     *
     * 提示：
     *
     * 1 <= len(A), len(B) <= 1000
     * 0 <= A[i], B[i] < 100
     * @param nums1
     * @param nums2
     * @return
     */
    public static int findLength(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        // dp[i][j] 的定义是nums1[i:] 和 nums[j:]之间的最长公共子数组
        int[][] dp = new int[n+1][m+1];
        int result = 0;
        for (int i = n -1; i >= 0; i--){
            for(int j = m -1; j >= 0; j--){
                if (nums1[i] == nums2[j]){
                    dp[i][j] = dp[i+1][j+1] + 1;
                } else {
                    dp[i][j] = 0;
                }
                result = Math.max(dp[i][j], result);
            }
        }
        return result;
    }

    /**
     * 0-1背包问题
     * 给你一个可装载重量为W的背包和N个物品，每个物品有重量和价值两个属性。其中第i个物品的重量为wt[i]，价值为val[i]，现在让你用这个背包装物品，最多能装的价值是多少？
     *  完全背包问题：
     * 完全背包与 01 背包不同就是每种物品可以有无限多个：一共有 N 种物品，每种物品有无限多个，第 i（i 从 1 开始）种物品的重量为 w[i]，价值为 v[i]。在总重量不超过背包承载上限 W 的情况下，能够装入背包的最大价值是多少？
     * 可见 01 背包问题与完全背包问题主要区别就是物品是否可以重复选取。
     * 背包问题具备的特征：
     * 是否可以根据一个 target（直接给出或间接求出），target 可以是数字也可以是字符串，再给定一个数组 arrs，问：能否使用 arrs 中的元素做各种排列组合得到 target。
     *
     * 背包问题解法：
     * 01 背包问题：
     * 如果是 01 背包，即数组中的元素不可重复使用，外循环遍历 arrs，内循环遍历 target，且内循环倒序:
     * 完全背包问题：
     * （1）如果是完全背包，即数组中的元素可重复使用并且不考虑元素之间顺序，arrs 放在外循环（保证 arrs 按顺序），target在内循环。且内循环正序。
     * （2）如果组合问题需考虑元素之间的顺序，需将 target 放在外循环，将 arrs 放在内循环，且内循环正序。
     * 换句话说就是考虑排列顺序的完全背包问题，就是求排列数，不考虑就是求组合数
     * 其中如果求组合数就是外层for循环遍历物品，内层for遍历背包。如果求排列数就是外层for遍历背包，内层for循环遍历物品。
     * 01背包
     * 举个简单的例子，输入如下：
     *
     * N = 3, W = 4
     * wt = [2, 1, 3]
     * val = [4, 2, 3]
     * 算法返回 6，选择前两件物品装进背包，总重量 3 小于W，可以获得最大价值 6。
     * @return
     */
    public static int backpack(int N, int W, int[] wt, int[] val){
        // dp的定义是dp[i][w]的定义：对于前i个物品，当前背包的容量为w，这种情况下可以装的最大价值是dp[i][w]
        int[][] dp = new int[N+1][W+1];
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= W; j++){
                if (j - wt[i-1] < 0){
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j- wt[i-1]] + val[i-1]);
                }
            }
        }
        for (int[] d : dp){
            System.out.println(Arrays.toString(d));
        }
        return dp[N][W];
    }

    /**
     * 416. 分割等和子集
     * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [1,5,11,5]
     * 输出：true
     * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
     * 示例 2：
     *
     * 输入：nums = [1,2,3,5,1]
     * 输出：false
     * 解释：数组不能分割成两个元素和相等的子集。
     *
     * @param nums
     * @return
     */
    public static boolean canPartition(int[] nums) {
        // 这个题目是0-1背包的变种意思就是说，能不能用一个背包装出指定的重量来。
        // dp 的定义是，dp[i][j]
        int w = 0;
        for (int i : nums){
            w += i;
        }
        if (w % 2 != 0){
            return false;
        } else {
            w = w / 2;
        }
        // 是dp[i][w]的定义：对于前i个物品，背包的容量为w，若为true证明正好可以装满，为false则装不满
        boolean[][] dp = new boolean[nums.length+1][w+1];
        for (int i = 0; i <= nums.length; i++){
            dp[i][0] = true;
        }
        for(int i = 1; i <= nums.length; i++){
            for(int j = 1; j <= w; j++){
                if (j - nums[i-1] < 0){
                    dp[i][j] = dp[i-1][j];
                } else {
                    // 0-1背包问题，因为物品是有限的所以，即使选择了也是dp[i-1][j-nums[i-1]]
                    dp[i][j] = dp[i - 1][j] ? dp[i - 1][j] : dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        for(boolean[] d : dp){
            System.out.println(Arrays.toString(d));
        }
        return dp[nums.length][w];
    }

    /**
     * 一维数组压缩
     * @param nums
     * @return
     */
    public static boolean canPartition1(int[] nums) {
        // 这个题目是0-1背包的变种意思就是说，能不能用一个背包装出指定的重量来。
        int w = 0;
        for (int i : nums){
            w += i;
        }
        if (w % 2 != 0){
            return false;
        } else {
            w = w / 2;
        }
        int n = nums.length;
        boolean[] dp = new boolean[w+1];
        dp[0] = true;
        for (int i = 0; i < n; i++){
            for (int j = w; j >= nums[i]; j--){
                dp[j] = dp[j] || dp[j-nums[i]];
            }
        }
        return dp[w];
    }

    /**
     * 494. 目标和
     * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
     * 0-1背包
     * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
     *
     *
     *
     * 示例：
     *
     * 输入：nums: [1, 1, 1, 1, 1], S: 3
     * 输出：5
     * 解释：
     *
     * -1+1+1+1+1 = 3
     * +1-1+1+1+1 = 3
     * +1+1-1+1+1 = 3
     * +1+1+1-1+1 = 3
     * +1+1+1+1-1 = 3
     *
     * 一共有5种方法让最终目标和为3。
     * @param nums
     * @param target
     * @return
     */
    public static int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int m = 0;
        for (int num : nums){
            m += num;
        }
        if ((m+target)%2 != 0 || target > m){
            return 0;
        } else {
            m = (m + target)/2;
        }
        // dp[i][j] 的定义dp[0..i][j],代表用nums[0..i]的数字使得目标和为j的方法数。
        int[][] dp = new int[n+1][m+1];
        for (int i = 0; i <= n; i++){
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++){
            for (int j = 0; j <= m; j++){
                if (j - nums[i-1] < 0){
                    dp[i][j] = dp[i-1][j];
                } else{
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i-1]];
                }
            }
        }
        for (int[] d : dp){
            System.out.println(Arrays.toString(d));
        }
        // 越来越感觉这东西是个玄学了。
        return dp[n][m];
    }
    public static int findTargetSumWays1(int[] nums, int S) {
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        if(S > sum || (S + sum) % 2 == 1) {
            return 0;
        }
        int target = (S + sum) / 2;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for(int num : nums){
            for(int j = target; j >= num; j--){
                dp[j] = dp[j] + dp[j - num];
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[target];
    }


    /**
     * 518. 零钱兑换 II
     * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
     * 这就是个典型的完全背包，一个有N种物品，物品数量无限，背包的重量就是总金额
     * 思路完全背包 不考虑排列顺序求的是组合数
     *
     * 示例 1:
     *
     * 输入: amount = 5, coins = [1, 2, 5]
     * 输出: 4
     * 解释: 有四种方式可以凑成总金额:
     * 5=5
     * 5=2+2+1
     * 5=2+1+1+1
     * 5=1+1+1+1+1
     * 示例 2:
     *
     * 输入: amount = 3, coins = [2]
     * 输出: 0
     * 解释: 只用面额2的硬币不能凑成总金额3。
     * 示例 3:
     *
     * 输入: amount = 10, coins = [10]
     * 输出: 1
     * @param amount
     * @param coins
     * 这种bi题目真就永远做不出呗？？
     * 背包问题，第一步要明确两点，「状态」和「选择」。
     * 状态有两个，就是「背包的容量」和「可选择的物品」，选择就是「装进背包」或者「不装进背包」。
     *
     * @param amount
     * @param coins
     * @return
     */
    public static int change(int amount, int[] coins) {
       // 这鸡儿的定义就离谱又回到了最基础的dp定义
        int m = coins.length;
        // dp[i][j] 的定义是使用coins[0..i]的硬币凑出j的方法最多有几种
        int[][] dp = new int[m+1][amount+1];
        for (int i = 0; i <= m; i++){
            dp[i][0] = 1;
        }
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= amount; j++){
                if (j - coins[i-1] < 0){
                    dp[i][j] = dp[i-1][j];
                } else{
                    // 完全背包问题，因为物品无限所以为dp[i][j-coins[i-1]]
                    dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]];
                }
            }
        }
        for(int[] d : dp){
            System.out.println(Arrays.toString(d));
        }
        return dp[m][amount];
    }
    public static int change1(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1; // base case
        for (int i = 0; i < n; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = dp[j] + dp[j-coins[i]];
            }
            System.out.println(Arrays.toString(dp));
        }

        return dp[amount];
    }

    /**
     * 139. 单词拆分
     * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
     * 思路：完全背包，考虑排列顺序求排列数
     * 说明：
     *
     * 拆分时可以重复使用字典中的单词。
     * 你可以假设字典中没有重复的单词。
     * 示例 1：
     *
     * 输入: s = "leetcode", wordDict = ["leet", "code"]
     * 输出: true
     * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
     * 示例 2：
     *
     * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
     * 输出: true
     * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
     *      注意你可以重复使用字典中的单词。
     * 示例 3：
     *
     * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
     * 输出: false
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        // 这种方法跟背包完全不搭界的啊
        int n = s.length();
        // dp[i] 表示字符串 s 前 i 个字符组成的字符串 s[0..i-1]是否能被空格拆分成若干个字典中出现的单词.
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                // 如果是s[0..j] 为true且s[j..i]为true,那么s[0..i]为true
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
    public static boolean wordBreak1(String s, List<String> wordDict) {
        // 用这种方法倒是有点像背包，要么装，要么不装
        int n = s.length();
        // dp[i] 表示字符串 s 前 i 个字符组成的字符串 s[0..i-1]是否能被空格拆分成若干个字典中出现的单词.
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for(String word : wordDict){
                int size = word.length();
                if (i - size >= 0 && s.substring(i - size, i).equals(word)){
                    dp[i] = dp[i] || dp[i-size];
                }
            }
        }
        return dp[n];
    }

    /**
     * 377. 组合总和 Ⅳ
     * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
     *
     * 题目数据保证答案符合 32 位整数范围。
     * 完全背包：考虑排列顺序求排列
     *
     *
     * 示例 1：
     *
     * 输入：nums = [1,2,3], target = 4
     * 输出：7
     * 解释：
     * 所有可能的组合为：
     * (1, 1, 1, 1)
     * (1, 1, 2)
     * (1, 2, 1)
     * (1, 3)
     * (2, 1, 1)
     * (2, 2)
     * (3, 1)
     * 请注意，顺序不同的序列被视作不同的组合。
     * 示例 2：
     *
     * 输入：nums = [9], target = 3
     * 输出：0
     * @param nums
     * @param target
     * @return
     */
    public static int combinationSum4(int[] nums, int target) {
        // dp[i] 代表用所有nums凑成i的最多组合数
        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++){
            for (int j = 0; j < nums.length; j++){
                if (i - nums[j] >= 0){
                    // 这个的状态转移方程的意思是，dp[i]是num结尾的排列，对于元素之和等于i−num 的每一种排列，在最后添加num 之后即可得到一个元素之和等于 i 的排列
                    dp[i] = dp[i] + dp[i-nums[j]];
                }
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[target];
    }

    /**
     * 279. 完全平方数
     * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
     *
     * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
     *
     * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
     *
     *思路：完全背包，不考虑排列顺序求组合数
     *
     * 示例 1：
     *
     * 输入：n = 12
     * 输出：3
     * 解释：12 = 4 + 4 + 4
     * 示例 2：
     *
     * 输入：n = 13
     * 输出：2
     * 解释：13 = 4 + 9
     * @param n
     * @return
     */
    public static int numSquares(int n) {
        // 思路没错，但是时间复杂度完全可以优化
        List<Integer> list = findSquares(n);
        if (list.contains(n)){
            return 1;
        }
        int m = list.size();
        int[] dp = new int[n+1];
        for(int i = 0; i <= n; i++){
            dp[i] = i;
        }
        for(int i = 0; i < m; i++){
            for(int j = list.get(i); j <= n; j++){
                dp[j] = Math.min(dp[j], dp[j-list.get(i)] + 1);
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[n];
    }

    /**
     * 思路没错，先找出所有的完全平方数，然后再找出组成n的最小的个数
     * @param n
     * @return
     */
    public static int numSquares1(int n) {
        // 思路没错，但是时间复杂度完全可以优化
        int[] dp = new int[n+1];
        for (int i = 0; i <= n; i++){
            dp[i] = i;
            for (int j = 0; j*j <= i; j++){
                dp[i] = Math.min(dp[i], dp[i-j*j] + 1);
            }
        }
        return dp[n];
    }
    public static int numSquares2(int n) {
        // 思路没错，但是时间复杂度完全可以优化
        int[] dp = new int[n+1];
        for(int i = 0; i <= n; i++){
            dp[i] = i;
        }
        for (int i = 0; i*i <= n; i++){
            for (int j = i*i; j<=n; j++){
                dp[j] = Math.min(dp[j], dp[j-i*i] + 1);
            }
        }
        return dp[n];
    }
    public static List findSquares(int n){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        int m = n / 2;
        for (int i = 1; i <= m; i++){
            int square = i*i;
            if (square > n){
                break;
            } else {
                if (!list.contains(square)){
                    list.add(square);
                }
            }
        }
        return list;
    }

    /**
     * 70. 爬楼梯
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     *
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     *
     * 注意：给定 n 是一个正整数。
     *
     * 示例 1：
     *
     * 输入： 2
     * 输出： 2
     * 解释： 有两种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶
     * 2.  2 阶
     * 示例 2：
     *
     * 输入： 3
     * 输出： 3
     * 解释： 有三种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶 + 1 阶
     * 2.  1 阶 + 2 阶
     * 3.  2 阶 + 1 阶
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    // 股票买卖问题
    /**
     * 121. 买卖股票的最佳时机
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     *
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     *
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：[7,1,5,3,6,4]
     * 输出：5
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
     * 示例 2：
     *
     * 输入：prices = [7,6,4,3,1]
     * 输出：0
     * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int profit = 0;
        int minPrices = prices[0];
        for (int i = 1; i < n; i++){
            minPrices = Math.min(minPrices, prices[i]);
            profit = Math.max(profit, prices[i] - minPrices);
        }
        return profit;
    }

    /**
     * 122. 买卖股票的最佳时机 II
     * 给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
     *
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     *
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     *
     *
     * 示例 1:
     *
     * 输入: prices = [7,1,5,3,6,4]
     * 输出: 7
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
     * 示例 2:
     *
     * 输入: prices = [1,2,3,4,5]
     * 输出: 4
     * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
     * 示例 3:
     *
     * 输入: prices = [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     * @param prices
     * @return
     */
    public static int maxProfit2(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }
        return dp[n-1][0];
    }
    public static int maxProfit2Better(int[] prices) {
        int n = prices.length;
        int profit0 = 0;
        int profit1 = -prices[0];
        for (int i = 1; i < n; i++){
            profit0 = Math.max(profit0, profit1 + prices[i]);
            profit1 = Math.max(profit1, profit0 - prices[i]);
        }
        return profit0;
    }

    /**
     * 123. 买卖股票的最佳时机 III
     * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     *
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
     *
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     *
     *
     * 示例 1:
     *
     * 输入：prices = [3,3,5,0,0,3,1,4]
     * 输出：6
     * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
     * 示例 2：
     *
     * 输入：prices = [1,2,3,4,5]
     * 输出：4
     * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
     * 示例 3：
     *
     * 输入：prices = [7,6,4,3,1]
     * 输出：0
     * 解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
     * 示例 4：
     *
     * 输入：prices = [1]
     * 输出：0
     * @param prices
     * @return
     */
    public static int maxProfit3(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n][3][2];
        dp[0][1][0] = 0;
        dp[0][1][1] = -prices[0];
        dp[0][2][0] = 0;
        dp[0][2][1] = -prices[0];
        for (int i = 1; i < n; i++){
            for(int j = 1; j <= 2; j++){
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i]);
            dp[i][1][1] = Math.max(dp[i - 1][1][1], dp[i - 1][0][0] - prices[i]);
            dp[i][2][0] = Math.max(dp[i - 1][2][0], dp[i - 1][2][1] + prices[i]);
            dp[i][2][1] = Math.max(dp[i - 1][2][1], dp[i - 1][1][0] - prices[i]);
        }
        return dp[n-1][2][0];
    }

    public static int maxProfit3Better(int[] prices) {
        int n = prices.length;
        int profit1 = 0;
        int profit2 = -prices[0];
        int profit3 = 0;
        int profit4 = -prices[0];
        for (int i = 1; i < n; i++){
            profit1 = Math.max(profit1, profit2 + prices[i]);
            profit2 = Math.max(profit2, -prices[i]);
            profit3 = Math.max(profit3, profit4 + prices[i]);
            profit4 = Math.max(profit4, profit1 - prices[i]);
        }
        return profit3;
    }

    /**
     * 188. 买卖股票的最佳时机 IV
     * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
     *
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
     *
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     *
     *
     * 示例 1：
     *
     * 输入：k = 2, prices = [2,4,1]
     * 输出：2
     * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
     * 示例 2：
     *
     * 输入：k = 2, prices = [3,2,6,5,0,3]
     * 输出：7
     * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
     *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
     *
     * @param prices
     * @return
     */
    public int maxProfit4(int k, int[] prices) {
        int n = prices.length;
        if (n == 0){
            return 0;
        }
        if (k >= n/2){
            return maxProfit2Better(prices);
        }
        int[][][] dp = new int[n][k + 1][2];
        for(int i = 1; i <= k; i++){
            dp[0][i][1] = -prices[0];
        }
        for(int i = 1; i < n; i++){
            for(int j = k; j > 0; j--){
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[n-1][k][0];
    }
    public int maxProfit4Better(int k, int[] prices) {
        int n = prices.length;
        if (n == 0){
            return 0;
        }
        if (k >= n/2){
            return maxProfit2Better(prices);
        }
        int[][] dp = new int[k + 1][2];
        for(int i = 1; i <= k; i++){
            dp[i][1] = -prices[0];
        }
        for(int i = 1; i < n; i++){
            for(int j = k; j > 0; j--){
                dp[j][0] = Math.max(dp[j][0], dp[j][1] + prices[i]);
                dp[j][1] = Math.max(dp[j][1], dp[j - 1][0] - prices[i]);
            }
        }
        return dp[k][0];
    }

    /**
     * 309. 最佳买卖股票时机含冷冻期
     * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
     *
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     *
     * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     * 示例:
     *
     * 输入: [1,2,3,0,2]
     * 输出: 3
     * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
     * @param prices
     * @return
     */
    public static int maxProfit5(int[] prices) {
        int n = prices.length;
        if (n == 0 || n == 1){
            return 0;
        }
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[1][0] = Math.max(0, prices[1] - prices[0]);
        dp[1][1] = Math.max(-prices[0], -prices[1]);
        for (int i = 2; i < n; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-2][0] - prices[i]);
        }
        return dp[n-1][0];
    }
    public static int maxProfit5Better(int[] prices) {
        int n = prices.length;
        if (n == 0 || n == 1){
            return 0;
        }
        int preProfit = 0;
        int profit1 = 0;
        int profit2 = -prices[0];
        for (int i = 1; i < n; i++){
            int nextProfit1 = Math.max(profit1, profit2 + prices[i]);
            int nextProfit2 = Math.max(profit2, preProfit - prices[i]);
            preProfit = profit1;
            profit1 = nextProfit1;
            profit2 = nextProfit2;
        }
        return profit1;
    }

    /**
     * 714. 买卖股票的最佳时机含手续费
     * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
     *
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     *
     * 返回获得利润的最大值。
     *
     * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
     *
     * 示例 1:
     *
     * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
     * 输出: 8
     * 解释: 能够达到的最大利润:
     * 在此处买入 prices[0] = 1
     * 在此处卖出 prices[3] = 8
     * 在此处买入 prices[4] = 4
     * 在此处卖出 prices[5] = 9
     * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
     * @param prices
     * @return
     */
    public static int maxProfit6(int[] prices, int fee) {
        int n = prices.length;
        if (n == 0 || n == 1){
            return 0;
        }
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }
        return dp[n-1][0];
    }
    public static int maxProfit6Better(int[] prices, int fee) {
        int n = prices.length;
        if (n == 0 || n == 1){
            return 0;
        }
        int profit0 = 0;
        int profit1 = -prices[0];
        for (int i = 1; i < n; i++){
            profit0 = Math.max(profit0, profit1 + prices[i] - fee);
            profit1 = Math.max(profit1, profit0 - prices[i]);
        }
        return profit0;
    }

    /**
     * 198. 打家劫舍
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     *
     *
     *
     * 示例 1：
     *
     * 输入：[1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     * 示例 2：
     *
     * 输入：[2,7,9,3,1]
     * 输出：12
     * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1){
            return nums[0];
        }
        // 其实吧，dp的定义就两种dp[0..i] 和 dp[i..n] 当dp[0..i] 不行时就应该要想到dp[i..n]
        // 这题中dp[0..i] 的定义就是nums[0..i]中能抢到的最多钱
        // dp[i..n]的定义就是从i开始能抢到的最多钱是多少
        int[] dp = new int[n];
        dp[n-1] = nums[n-1];
        dp[n-2] = Math.max(nums[n-1], nums[n-2]);
        for (int i = n - 3; i >= 0; i--){
            dp[i] = Math.max(dp[i+1], dp[i+2] + nums[i]);
        }
        return dp[0];
    }

    /**
     * 这就比较尴尬了啊，我以为正着来是不可以的，原来也是可以的啊，因为我们其实对于每一次的选择都是最优的，所以结果肯定是对的啊
     * @param nums
     * @return
     */
    public int rob1(int[] nums) {
        int n = nums.length;
        if (n == 1){
            return nums[0];
        }
        // 其实吧，dp的定义就两种dp[0..i] 和 dp[i..n] 当dp[0..i] 不行时就应该要想到dp[i..n]
        // 这题中dp[0..i] 的定义就是nums[0..i]中能抢到的最多钱，这种也可以,是自己不自信了。
        // dp[i..n]的定义就是从i开始能抢到的最多钱是多少
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++){
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        return dp[n-1];
    }
    public int robBetter(int[] nums) {
        int n = nums.length;
        int dp1 = 0;
        int dp2 = 0;
        int result = 0;
        for (int i = n - 1; i >= 0; i--){
            result = Math.max(dp2, dp1 + nums[i]);
            dp1 = dp2;
            dp2 = result;
        }
        return result;
    }

    /**
     * 213. 打家劫舍 II
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [2,3,2]
     * 输出：3
     * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
     * 示例 2：
     *
     * 输入：nums = [1,2,3,1]
     * 输出：4
     * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     * 示例 3：
     *
     * 输入：nums = [0]
     * 输出：0
     * @param nums
     * @return
     */
    public static int rob2(int[] nums) {
        int n = nums.length;
        if (n == 1){
            return nums[0];
        }
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        int[] dp1 = new int[n];
        dp1[1] = nums[1];
        for (int i = 2; i < n; i++){
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
            dp1[i] = Math.max(dp1[i-1], dp1[i-2] + nums[i]);
        }
        System.out.println(Arrays.toString(dp));
        System.out.println(Arrays.toString(dp1));
        return Math.max(dp1[n-1], dp[n-2]);
    }

    /**
     * 337. 打家劫舍 III
     * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
     *
     * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
     *
     * 示例 1:
     *
     * 输入: [3,2,3,null,3,null,1]
     *
     *      3
     *     / \
     *    2   3
     *     \   \
     *      3   1
     *
     * 输出: 7
     * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
     * 示例 2:
     *
     * 输入: [3,4,5,1,3,null,1]
     *
     *      3
     *     / \
     *    4   5
     *   / \   \
     *  1   3   1
     *
     * 输出: 9
     * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
     * @param nums
     * @return
     */
    Map<TreeNode, Integer> robMap = new HashMap<>();
    public int rob3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (robMap.containsKey(root)){
            return robMap.get(root);
        }
        // 思路层序遍历二叉树，同层的可以都选，但是如果选了这层的就必须隔层再进行选择1,2,4，8,16这种然后又回到了最初的问题了，草了，感觉前面二叉树白做那么多道题了啊，现在又不会了感觉，白干。
        // 思路错了白干
        int rob1 = root.val + (root.left == null ? 0 : rob3(root.left.left) + rob3(root.left.right)) + (root.right == null ? 0 : rob3(root.right.left) + rob3(root.right.right));
        int rob2 = rob3(root.left) + rob3(root.right);
        int res = Math.max(rob1, rob2);
        robMap.put(root, res);
        return res;
    }
    public int rob3Better(TreeNode root) {
        int[] result = robInternal(root);
        return Math.max(result[0], result[1]);
    }
    public int[] robInternal(TreeNode root) {
        if (root == null) {
            return new int[2];
        }
        int[] result = new int[2];

        int[] left = robInternal(root.left);
        int[] right = robInternal(root.right);

        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        result[1] = left[0] + right[0] + root.val;

        return result;
    }

    /**
     * 887. 鸡蛋掉落
     * 给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。
     *
     * 已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。
     *
     * 每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用 这枚鸡蛋。
     *
     * 请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
     *
     *
     * 示例 1：
     *
     * 输入：k = 1, n = 2
     * 输出：2
     * 解释：
     * 鸡蛋从 1 楼掉落。如果它碎了，肯定能得出 f = 0 。
     * 否则，鸡蛋从 2 楼掉落。如果它碎了，肯定能得出 f = 1 。
     * 如果它没碎，那么肯定能得出 f = 2 。
     * 因此，在最坏的情况下我们需要移动 2 次以确定 f 是多少。
     * 示例 2：
     *
     * 输入：k = 2, n = 6
     * 输出：3
     * 示例 3：
     *
     * 输入：k = 3, n = 14
     * 输出：4
     * @param k
     * @param n
     * @return
     */
    public int superEggDropMy(int k, int n) {
        // dp[i][j]的定义是用i枚鸡蛋扔j层楼找到最少次数
        int[][] dp = new int[k+1][n+1];
        for(int i = 0; i <= k; i++){
            dp[i][0] = 0;
        }
        for (int i = 0; i <= n; i++){
            dp[1][i] = i;
            dp[0][i] = 0;
        }
        for(int i = 2; i <= k; i++){
            for(int j = 1; j <= n; j++){
                int min = j*j;
                for (int x = 1; x <= j; x++){
                    min = Math.min(Math.max(dp[i-1][x-1], dp[i][j-x]) + 1, min);
                }
                dp[i][j] = min;
            }
        }
        return dp[k][n];
    }
    Map<Integer, Integer> cache = new HashMap<>();
    public int superEggDrop(int k, int n) {
        if (n == 0)
            return 0;
        else if (k == 1)
            return n;

        Integer key = n * 1000 + k; // K <= 100
        if (cache.containsKey(key))
            return cache.get(key);

        int low = 1, high = n;
        while (low + 1 < high) {
            int middle = (low + high) / 2;
            int lowVal = superEggDrop(k - 1, middle - 1);
            int highVal = superEggDrop(k, n - middle);

            if (lowVal < highVal)
                low = middle;
            else if (lowVal > highVal)
                high = middle;
            else
                low = high = middle;
        }
        int minimum = 1 + Math.min(
                Math.max(superEggDrop(k - 1, low - 1), superEggDrop(k, n - low)),
                Math.max(superEggDrop(k - 1, high - 1), superEggDrop(k, n - high))
        );

        cache.put(key, minimum);

        return cache.get(key);
    }

    /**
     * 这是人做的吗
     * @param k
     * @param n
     * @return
     */
    public int superEggDrop1(int k, int n) {
        if (n == 1) {
            return 1;
        }
        int[][] f = new int[n + 1][k + 1];
        for (int i = 1; i <= k; ++i) {
            f[1][i] = 1;
        }
        int ans = -1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= k; ++j) {
                f[i][j] = 1 + f[i - 1][j - 1] + f[i - 1][j];
            }
            if (f[i][k] >= n) {
                ans = i;
                break;
            }
        }
        return ans;
    }

    /**
     * 10. 正则表达式匹配
     * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     *
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
     *
     *
     * 示例 1：
     *
     * 输入：s = "aa" p = "a"
     * 输出：false
     * 解释："a" 无法匹配 "aa" 整个字符串。
     * 示例 2:
     *
     * 输入：s = "aa" p = "a*"
     * 输出：true
     * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
     * 示例 3：
     *
     * 输入：s = "ab" p = ".*"
     * 输出：true
     * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
     * 示例 4：
     *
     * 输入：s = "aab" p = "c*a*b"
     * 输出：true
     * 解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
     * 示例 5：
     *
     * 输入：s = "mississippi" p = "mis*is*p*."
     * 输出：false
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        // 思路是正确的但是写不出来啊
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        boolean match = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        if(p.length() >= 2 && p.charAt(1) == '*'){
            return isMatch(s, p.substring(2)) || (match && isMatch(s.substring(1), p));
        }
        return match && isMatch(s.substring(1), p.substring(1));
    }

    public boolean isMatch1(String s, String p) {
        // 递归加上备忘录
        int[][] memo = new int[s.length() + 1][p.length() + 1];
        return isMatch(s, 0, p, 0, memo);
    }
    public boolean isMatch(String s, int i, String p, int j, int[][] memo){
        if (j >= p.length()){
            return i >= s.length();
        }
        if (memo[i][j] != 0){
            return memo[i][j] > 0;
        }
        boolean match = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
        if (p.length() - j >= 2 && p.charAt(j+1) == '*'){
            boolean t = isMatch(s, i, p, j + 2, memo) || (match && isMatch(s, i + 1, p, j, memo));
            if (t){
                memo[i][j] = 1;
            } else {
                memo[i][j] = -1;
            }
            return t;
        }
        boolean t = match && isMatch(s, i + 1, p, j + 1, memo);
        if (t){
            memo[i][j] = 1;
        } else {
            memo[i][j] = -1;
        }
        return t;
    }

    /**
     * 动态规划解法
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch2(String s, String p) {
        // dp的定义dp[i][j] 代表从dp[0..i][0..j] 能否被匹配
        char[] str = s.toCharArray(), ptr = p.toCharArray();
        boolean[][] dp = new boolean[str.length + 1][ptr.length + 1];
        dp[0][0] = true;
        for (int i = 0; i <= str.length; i++) {
            for (int j = 1; j <= ptr.length; j++) {
                if(ptr[j - 1] != '*') {
                    if(i > 0 && (str[i - 1] == ptr[j - 1] || ptr[j - 1] == '.')) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }else { //ptr[j - 1] == '*'
                    if(j > 1) {
                        dp[i][j] |= dp[i][j - 2];   //不看
                    }
                    if(i > 0 && j > 1 && (str[i - 1] == ptr[j - 2] || ptr[j - 2] == '.')) {
                        dp[i][j] |= dp[i - 1][j];    //看
                    }
                }
            }
        }
        return dp[str.length][ptr.length];
    }









}
