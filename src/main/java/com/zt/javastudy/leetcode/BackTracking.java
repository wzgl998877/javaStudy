package com.zt.javastudy.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 回溯算法学习
 * 回溯法（back tracking）:（探索与回溯法）是一种选优搜索法，又称为试探法，按选优条件向前搜索，以达到目标。
 * 但当探索到某一步时，发现原先选择并不优或达不到目标，就退回一步重新选择，这种走不通就退回再走的技术为回溯法，而满足回溯条件的某个状态的点称为“回溯点”。
 * 白话：回溯法可以理解为通过选择不同的岔路口寻找目的地，一个岔路口一个岔路口的去尝试找到目的地。如果走错了路，继续返回来找到岔路口的另一条路，直到找到目的地。
 *
 * @author zhengtao on 2021/9/14
 */
public class BackTracking {
    public static void main(String[] args) {
        BackTracking backTracking = new BackTracking();
        int[] nums = {1, 2, 1};
        System.out.println(backTracking.permute(nums));
        System.out.println(backTracking.permuteUnique(nums));
    }

    List<List<Integer>> res = new LinkedList<>();

    /**
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     * 示例 2：
     * <p>
     * 输入：nums = [0,1]
     * 输出：[[0,1],[1,0]]
     * 示例 3：
     * <p>
     * 输入：nums = [1]
     * 输出：[[1]]
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 6
     * -10 <= nums[i] <= 10
     * nums 中的所有整数 互不相同
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track);
        return res;
    }

    /**
     * 结束条件，nums中所有元素全都在 track 中出现
     *
     * @param nums  选择列表 里不存在于track中的元素
     * @param track 路径： 记录在track中
     */
    public void backtrack(int[] nums, LinkedList<Integer> track) {
        // 满足了结束条件，track包含了nums所有数据
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (track.contains(nums[i])) {
                continue;
            }
            // 做选择
            track.add(nums[i]);
            // 进入下一层决策树
            backtrack(nums, track);
            // 取消选择
            track.removeLast();
        }
    }

    /**
     * 51. N 皇后
     * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
     * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
     * 示例 1：
     * 输入：n = 4
     * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
     * 解释：如上图所示，4 皇后问题存在两个不同的解法。
     * 示例 2：
     * 输入：n = 1
     * 输出：[["Q"]]
     * 提示：
     * 1 <= n <= 9
     * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
     *
     * @param n
     * @return
     */
    List<List<String>> resQueens = new LinkedList<>();

    public List<List<String>> solveNQueens(int n) {
        char[][] track = new char[n][n];
        for (char[] c : track) {
            Arrays.fill(c, '.');
        }
        backtrack1(n, 0, track);
        return resQueens;
    }

    /**
     * 结束条件， track的值等于4
     *
     * @param
     * @param track 路径： 记录在track中
     */
    public void backtrack1(int n, int rows, char[][] track) {
        // 满足了结束条件，track包含了n条数据
        if (rows == n) {
            resQueens.add(charToList(track));
            return;
        }
        for (int col = 0; col < n; col++) {
            if (!isValid(rows, col, n, track)) {
                continue;
            }
            // 做选择
            track[rows][col] = 'Q';
            // 进入下一层决策树
            backtrack1(n, rows + 1, track);
            // 取消选择
            track[rows][col] = '.';
        }
    }

    public List<String> charToList(char[][] c) {
        List<String> list = new ArrayList<>();
        for (char[] s : c) {
            list.add(String.copyValueOf(s));
        }
        return list;
    }

    public boolean isValid(int row, int col, int n, char[][] chessboard) {
        // 检查列
        for (int i = 0; i < row; i++) {
            if (chessboard[i][col] == 'Q') {
                return false;
            }
        }
        // 检查 45度角是否有皇后
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        // 检查 135度角是否有皇后
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    List<List<Integer>> resUnique = new LinkedList<>();
    /**
     * 47. 全排列 II
     * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,1,2]
     * 输出：
     * [[1,1,2],
     * [1,2,1],
     * [2,1,1]]
     * 示例 2：
     * <p>
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 8
     * -10 <= nums[i] <= 10
     *
     * @param nums
     * @return
     */
    boolean[] vis;

    public List<List<Integer>> permuteUnique(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        vis = new boolean[nums.length];
        Arrays.sort(nums);
        backtrackUnique(nums, track);
        return resUnique;
    }

    public void backtrackUnique(int[] nums, LinkedList<Integer> track) {
        // 满足了结束条件，track包含了nums所有数据
        if (track.size() == nums.length) {
            resUnique.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
//          加上 !vis[i - 1]来去重主要是通过限制一下两个相邻的重复数字的访问顺序
//          举个栗子，对于两个相同的数11，我们将其命名为1a1b, 1a表示第一个1，1b表示第二个1； 那么，不做去重的话，会有两种重复排列 1a1b, 1b1a， 我们只需要取其中任意一种排列；
//          为了达到这个目的，限制一下1a, 1b访问顺序即可。 比如我们只取1a1b那个排列的话，只有当visit nums[i-1]之后我们才去visit nums[i]， 也就是如果!visited[i-1]的话则continue
            if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
                continue;
            }
            // 做选择
            track.add(nums[i]);
            vis[i] = true;
            // 进入下一层决策树
            backtrackUnique(nums, track);
            // 取消选择
            track.removeLast();
            vis[i] = false;
        }
    }
}
