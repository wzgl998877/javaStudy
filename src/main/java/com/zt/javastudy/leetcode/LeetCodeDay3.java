package com.zt.javastudy.leetcode;

/**
 * @author zhengtao
 * @description 第三天，冲啊
 * 48. 旋转图像
 * @date 2020/12/19
 */
public class LeetCodeDay3 {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{5, 1, 9,11},{ 2, 4, 8,10},{13, 3, 6, 7},{15,14,12,16}};
        rotate1(matrix);
    }
    public static void rotate(int[][] matrix) {
        int[][] end = new int[matrix.length][matrix[0].length];
        for (int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[i].length;j++){
                end[j][matrix.length-i-1] = matrix[i][j];
            }
        }
        for (int [] i : end){
            for (int j : i){
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }

    /**
     * 先水平对折然后按右对角线对折
     * @param matrix
     */
    public static void rotate1(int[][] matrix) {
        int n = matrix.length;
        // 水平线对折
        for (int i=0;i<n/2;i++){
            for (int j=0;j<n;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-i-1][j];
                matrix[n-i-1][j] = temp;
            }
        }
        // 中心线对折
        for (int i=0;i<n;i++){
            for (int j=0;j<i;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int [] i : matrix){
            for (int j : i){
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }
}
