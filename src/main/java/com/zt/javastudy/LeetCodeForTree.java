package com.zt.javastudy;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

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

    /**
     * 144. 二叉树的前序遍历,迭代实现
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root==null){
            return list;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode treeNode = root;
        while (!queue.isEmpty()&&treeNode!=null){
            while (treeNode!=null){
                queue.add(treeNode.left);
                list.add(treeNode.val);
                treeNode = treeNode.left;
            }
            treeNode=queue.poll();
            treeNode = treeNode.right;
        }
        return list;

    }
    /**
     * 144. 二叉树的前序遍历,迭代实现
     * 思路，用栈实现就可以了
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return res;
    }

    /**
     * 迭代实现中序遍历 94、二叉树的中序遍历
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (!stack.isEmpty()||node!=null){
            while (node!=null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            while (node!=null){
                node = node.right;
                stack.push(node);
            }
            res.add(node.val);
            node = node.right;
        }
        return res;
    }

    /**
     * 145. 二叉树的后序遍历
     * 需要用一个node来记录弹出的队列，因为如果不记录，右子树就无限循环了，建议明天继续看看
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        TreeNode flag = null;
        while (!stack.isEmpty()||node!=null){
            while (node!=null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if(node.right==null||node.right==flag){
                res.add(node.val);
                flag = node;
                node = null;
            }else {
                stack.push(node);
                node = node.right;
            }
        }
        return res;
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
