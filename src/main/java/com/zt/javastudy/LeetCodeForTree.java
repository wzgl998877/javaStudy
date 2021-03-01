package com.zt.javastudy;

import java.util.LinkedList;

/**
 * @author zhengtao
 * @description 树学习
 * @date 2021/2/24
 */
public class LeetCodeForTree {
    public static void main(String[] args) {
        int[] num = {4,2,7,1,3,6,9};
        TreeNode treeNode = new TreeNode(-1);
        addTree(treeNode,num);
    }

    /**
     * 添加二叉树
     * @param treeNode
     * @param num
     * @return
     */
    public static TreeNode addTree(TreeNode treeNode,int[] num){
        TreeNode temp = treeNode;
       for (int i=0;i<num.length-1;i++){
           temp.left = new TreeNode(num[i]);
           i++;
           temp.right = new TreeNode(num[i]);
           TreeNode treeNode1 = temp;
           treeNode1.left = treeNode1.left.left;
           treeNode1.right = treeNode1.right.right;
       }
       return treeNode;
    }

    /**
     * 递归，深度优先遍历 226 题「翻转二叉树」
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root==null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(temp.left);
        invertTree(temp.right);
        return root;
    }

    /**
     * 迭代，广度优先遍历
     * @param root
     * @return
     */
    public TreeNode invertTree1(TreeNode root) {
        if (root == null){
            return null;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode treeNode = queue.poll();
            TreeNode temp = treeNode.left;
            treeNode.left = treeNode.right;
            treeNode.right = temp;
            if (treeNode.left!=null){
                queue.add(treeNode.left);
            }
            if (treeNode.right!=null){
                queue.add(treeNode.right);
            }
        }
        return root;
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
 }
