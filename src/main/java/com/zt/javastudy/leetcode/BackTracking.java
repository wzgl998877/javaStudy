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
//        System.out.println(backTracking.subsets(new int[]{1,2,3,4}));
        System.out.println(backTracking.subsetsWithDup(new int[]{1, 2, 3, 2}));
        System.out.println(backTracking.letterCombinations("23"));
        StringBuilder stringBuilder = new StringBuilder("123");
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        System.out.println(stringBuilder);
        System.out.println(backTracking.combinationSum3(3, 9));
        System.out.println(backTracking.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
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
        return backtrackPartition2(k, 0, nums, 0, used, target);
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

    List<List<Integer>> subList = new LinkedList<>();

    /**
     * 78. 子集
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * <p>
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,2,3]
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     * 示例 2：
     * <p>
     * 输入：nums = [0]
     * 输出：[[],[0]]
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 10
     * -10 <= nums[i] <= 10
     * nums 中的所有元素 互不相同
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        LinkedList<Integer> list = new LinkedList<>();
        backtrackSubset(nums, list, 0);
        return subList;
    }

    public void backtrackSubset(int[] nums, LinkedList<Integer> track, int index) {
        // 满足了结束条件，track包含了nums所有数据
        subList.add(new LinkedList<>(track));
        for (int i = index; i < nums.length; i++) {
            // 做选择
            track.add(nums[i]);
            // 进入下一层决策树
            backtrackSubset(nums, track, i + 1);
            // 取消选择
            track.removeLast();
        }
    }

    /**
     * 90. 子集 II
     * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
     * <p>
     * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,2,2]
     * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
     * 示例 2：
     * <p>
     * 输入：nums = [0]
     * 输出：[[],[0]]
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 10
     * -10 <= nums[i] <= 10
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        LinkedList<Integer> list = new LinkedList<>();
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
//        backtrackSubset2(nums, list, 0);
        backtrackSubset3(nums, list, 0, used);
        return subList;
    }

    /**
     * 暴力去重
     *
     * @param nums
     * @param track
     * @param index
     */
    public void backtrackSubset2(int[] nums, LinkedList<Integer> track, int index) {
        // 满足了结束条件，track包含了nums所有数据
        if (!subList.contains(track)) {
            subList.add(new LinkedList<>(track));
        }
        for (int i = index; i < nums.length; i++) {
            // 做选择
            track.add(nums[i]);
            // 进入下一层决策树
            backtrackSubset2(nums, track, i + 1);
            // 取消选择
            track.removeLast();

        }
    }

    /**
     * 剪枝去重
     *
     * @param nums
     * @param track
     * @param index
     * @param used
     */
    public void backtrackSubset3(int[] nums, LinkedList<Integer> track, int index, boolean[] used) {
        // 满足了结束条件，track包含了nums所有数据
        subList.add(new LinkedList<>(track));
        for (int i = index; i < nums.length; i++) {
            // used[i - 1] == true，说明同一树支candidates[i - 1]使用过
            // used[i - 1] == false，说明同一树层candidates[i - 1]使用过
            // 而我们要对同一树层使用过的元素进行跳过
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            // 做选择
            track.add(nums[i]);
            used[i] = true;
            // 进入下一层决策树
            backtrackSubset3(nums, track, i + 1, used);
            // 取消选择
            track.removeLast();
            used[i] = false;

        }
    }

    List<String> resultLetter = new LinkedList<>();

    /**
     * 17. 电话号码的字母组合
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * <p>
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：digits = "23"
     * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
     * 示例 2：
     * <p>
     * 输入：digits = ""
     * 输出：[]
     * 示例 3：
     * <p>
     * 输入：digits = "2"
     * 输出：["a","b","c"]
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        if (digits == null || "".equals(digits)) {
            return resultLetter;
        }
        String[] letters = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> temp = new ArrayList<>();
        for (char c : digits.toCharArray()) {
            temp.add(letters[Integer.parseInt(String.valueOf(c)) - 2]);
        }
        backtrackLetter(temp, new StringBuilder(), 0);
        return resultLetter;
    }

    public void backtrackLetter(List<String> temp, StringBuilder letter, int row) {
        if (letter.length() == temp.size()) {
            resultLetter.add(letter.toString());
        }
        if (row >= temp.size()) {
            return;
        }
        for (int i = 0; i < temp.get(row).length(); i++) {
            letter.append(temp.get(row).charAt(i));
            backtrackLetter(temp, letter, row + 1);
            letter.deleteCharAt(letter.length() - 1);
        }
    }

    List<List<Integer>> relustSum3 = new ArrayList<>();
    int sum3K;
    int sum3N;

    /**
     * 216. 组合总和 III
     * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
     * <p>
     * 说明：
     * <p>
     * 所有数字都是正整数。
     * 解集不能包含重复的组合。
     * 示例 1:
     * <p>
     * 输入: k = 3, n = 7
     * 输出: [[1,2,4]]
     * 示例 2:
     * <p>
     * 输入: k = 3, n = 9
     * 输出: [[1,2,6], [1,3,5], [2,3,4]]
     *
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        this.sum3K = k;
        this.sum3N = n;
        LinkedList<Integer> temp = new LinkedList<>();
//        backtrackSum3(temp, 1, 0);
        backtrackSum3_2(temp, 1, 0);
        return relustSum3;
    }

    public void backtrackSum3(LinkedList<Integer> temp, int index, int sum) {
        if (sum > sum3N) {
            return;
        }
        if (sum3K == temp.size()) {
            if (sum == sum3N) {
                relustSum3.add(new ArrayList<>(temp));
            }
            return;
        }
        for (int i = index; i < 10; i++) {
            temp.add(i);
            sum += i;
            backtrackSum3(temp, i + 1, sum);
            // 错误代码
//            backtrackSum3(temp, index + 1, sum);
            temp.removeLast();
            sum -= i;
        }
    }

    private void backtrackSum3_2(LinkedList<Integer> path, int startIndex, int sum) {
        // 减枝
        if (sum > sum3N) {
            return;
        }

        if (path.size() == sum3K) {
            if (sum == sum3N) {
                relustSum3.add(new ArrayList<>(path));
            }
            return;
        }

        // 减枝 9 - (k - path.size()) + 1
        for (int i = startIndex; i <= 9 - (sum3K - path.size()) + 1; i++) {
            path.add(i);
            sum += i;
            backtrackSum3_2(path, i + 1, sum);
            //回溯
            path.removeLast();
            //回溯
            sum -= i;
        }
    }

    List<List<Integer>> sumList = new ArrayList<>();
    int sumTarget;

    /**
     * 39. 组合总和
     * 给定一个无重复元素的正整数数组 candidates 和一个正整数 target ，找出 candidates 中所有可以使数字和为目标数 target 的唯一组合。
     * <p>
     * candidates 中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。
     * <p>
     * 对于给定的输入，保证和为 target 的唯一组合数少于 150 个。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入: candidates = [2,3,6,7], target = 7
     * 输出: [[7],[2,2,3]]
     * 示例 2：
     * <p>
     * 输入: candidates = [2,3,5], target = 8
     * 输出: [[2,2,2,2],[2,3,3],[3,5]]
     * 示例 3：
     * <p>
     * 输入: candidates = [2], target = 1
     * 输出: []
     * 示例 4：
     * <p>
     * 输入: candidates = [1], target = 1
     * 输出: [[1]]
     * 示例 5：
     * <p>
     * 输入: candidates = [1], target = 2
     * 输出: [[1,1]]
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.sumTarget = target;
        backtrackSum(candidates, new LinkedList<>(), 0, 0);
        return sumList;
    }

    private void backtrackSum(int[] candidates, LinkedList<Integer> path, int sum, int index) {
        // 减枝
        if (sum > sumTarget) {
            return;
        }
        if (sum == sumTarget) {
            sumList.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (sum + candidates[i] > sumTarget) {
                break;
            }
            path.add(candidates[i]);
            sum += candidates[i];
            backtrackSum(candidates, path, sum, i);
            //回溯
            path.removeLast();
            //回溯
            sum -= candidates[i];
        }
    }

    /**
     * 40. 组合总和 II
     * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * <p>
     * candidates 中的每个数字在每个组合中只能使用一次。
     * <p>
     * 注意：解集不能包含重复的组合。
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
     * 输出:
     * [
     * [1,1,6],
     * [1,2,5],
     * [1,7],
     * [2,6]
     * ]
     * 示例 2:
     * <p>
     * 输入: candidates = [2,5,2,1,2], target = 5,
     * 输出:
     * [
     * [1,2,2],
     * [5]
     * ]
     *
     * @param candidates
     * @param target
     * @return
     */
    List<List<Integer>> sum2List = new ArrayList<>();
    int sum2Target;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.sum2Target = target;
        boolean[] used = new boolean[candidates.length];
        backtrackSum2(candidates, new LinkedList<>(), 0, 0, used);
        return sum2List;
    }

    private void backtrackSum2(int[] candidates, LinkedList<Integer> path, int sum, int index, boolean[] used) {
        // 减枝
        if (sum > sum2Target) {
            return;
        }
        if (sum == sum2Target) {
            sum2List.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (sum + candidates[i] > sum2Target) {
                break;
            }
            if (i > 0 && candidates[i] == candidates[i - 1] && !used[i - 1]) {
                continue;
            }
            path.add(candidates[i]);
            sum += candidates[i];
            used[i] = true;
            backtrackSum2(candidates, path, sum, i + 1, used);
            //回溯
            path.removeLast();
            //回溯
            sum -= candidates[i];
            used[i] = false;
        }
    }
}
