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
        System.out.println(backTracking.combine(4, 2));
        int[] s = {4, 3, 2, 3, 5, 2, 1};
        System.out.println(backTracking.canPartitionKSubsets(s, 4));
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

    /**
     * 37. 解数独
     * 编写一个程序，通过填充空格来解决数独问题。
     * <p>
     * 数独的解法需 遵循如下规则：
     * <p>
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
     * 数独部分空格内已填入了数字，空白格用 '.' 表示。
     * <p>
     * <p>
     * <p>
     * 示例：
     * <p>
     * <p>
     * 输入：board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
     * 输出：[["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
     * 解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
     * <p>
     * <p>
     * <p>
     * <p>
     * 提示：
     * <p>
     * board.length == 9
     * board[i].length == 9
     * board[i][j] 是一位数字或者 '.'
     * 题目数据 保证 输入数独仅有一个解
     *
     * @param board
     */
    public void solveSudoku(char[][] board) {
        backtrackSudo(board);
    }

    public boolean backtrackSudo(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    continue;
                }
                for (char k = '1'; k <= '9'; k++) {
                    if (isValidSudo(board, i, j, k)) {
                        board[i][j] = k;
                        if (backtrackSudo(board)) {
                            return true;
                        }
                        board[i][j] = '.';
                    }

                }
                return false;
            }
        }
        return true;
    }

    public boolean isValidSudo(char[][] board, int row, int col, char k) {
        // 检查列
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == k) {
                return false;
            }
        }
        // 检查行
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == k) {
                return false;
            }
        }
        // 9方格起点
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) { // 判断9方格里是否重复
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == k) {
                    return false;
                }
            }
        }
        return true;
    }

    List<List<Integer>> combines = new ArrayList<>();
    int N;
    int K;

    /**
     * 77. 组合
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     * <p>
     * 你可以按 任何顺序 返回答案。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 4, k = 2
     * 输出：
     * [
     * [2,4],
     * [3,4],
     * [2,3],
     * [1,2],
     * [1,3],
     * [1,4],
     * ]
     * 示例 2：
     * <p>
     * 输入：n = 1, k = 1
     * 输出：[[1]]
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= n <= 20
     * 1 <= k <= n
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        LinkedList<Integer> track = new LinkedList<>();
        this.N = n;
        this.K = k;
        backtrackComBine(track, 1);
        return combines;
    }

    public void backtrackComBine(LinkedList<Integer> track, int n) {
        // 满足了结束条件，track包含了nums所有数据
        if (track.size() == K) {
            combines.add(new LinkedList<>(track));
            return;
        }
        if (track.size() + (N - n + 1) < K) {
            return;
        }
        for (int i = n; i <= N; i++) {
            if (track.contains(i)) {
                continue;
            }
            // 做选择
            track.add(i);
            // 进入下一层决策树
            backtrackComBine(track, i + 1);
            // 取消选择
            track.removeLast();
        }
    }

    /**
     * 698. 划分为k个相等的子集
     * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
     * <p>
     * 示例 1：
     * <p>
     * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
     * 输出： True
     * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= k <= len(nums) <= 16
     * 0 < nums[i] < 10000
     * 第一种思路，每一个数字都需要选择进入到一个桶中
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;
        // 有n个桶
        int[] option = new int[k];
        boolean[] used = new boolean[nums.length];
        //
//        return backtrackPartition(nums, option, 0, target);
        return backtrackPartition2(k, 0, nums,0, used, target);
    }


    /**
     * 第一种思路，以数字的视角出发，每一个数字都需要进到一个桶中，
     * for(int i : nums){
     * for(int j : option){
     * 判断。。。
     * }
     * }
     * 递归穷举 nums 中的每个数字
     *
     * @param nums
     * @param option
     * @param index
     * @param target
     * @return
     */
    public boolean backtrackPartition(int[] nums, int[] option, int index, int target) {
        // 满足了结束条件
        if (index == nums.length) {
            for (int i : option) {
                if (i != target) {
                    return false;
                }
                return true;
            }
        }
        // 穷举 nums[index] 可能装入的桶
        for (int i = 0; i < option.length; i++) {
            // 第i个桶装满了
            if (option[i] + nums[index] > target) {
                continue;
            }
            // 做选择
            option[i] += nums[index];
            // 进入下一层决策树
            if (backtrackPartition(nums, option, index + 1, target)) {
                return true;
            }
            // 取消选择
            option[i] -= nums[index];
        }
        return false;
    }

    /**
     * 以桶的视角出发，每个桶都需要装满
     *
     * @param k      桶的个数
     * @param bucket 每个桶的目前装了多少
     * @param nums
     * @param start
     * @param used
     * @param target
     * @return
     */
    public boolean backtrackPartition2(int k, int bucket, int[] nums, int start, boolean[] used, int target) {
        // 所有桶都装满了
        if (k == 0) {
            return true;
        }
        // 这个桶装满了，开始装下一个桶
        if (bucket == target) {
            return backtrackPartition2(k - 1, 0, nums, 0, used, target);
        }
        for (int i = start; i < nums.length; i++) {
            // 这个数字已经被用过了
            if (used[i]) {
                continue;
            }
            // 桶装不下了
            if (bucket + nums[i] > target) {
                continue;
            }
            // 做选择
            used[i] = true;
            bucket += nums[i];
            // 递归穷举下一个数字是否装入当前桶
            if (backtrackPartition2(k, bucket, nums, i + 1, used, target)) {
                return true;
            }
            // 撤销选择
            bucket -= nums[i];
            used[i] = false;
        }
        return false;
    }

}
