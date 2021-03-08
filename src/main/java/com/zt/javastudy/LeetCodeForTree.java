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
     * 思路，用栈实现就可以了
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
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
    public List<Integer> inorderTraversal1(TreeNode root) {
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
            res.add(node.val);
            node = node.right;
        }
        return res;
    }
    /**
     * 145. 二叉树的后序遍历
     * 需要用一个node来记录弹出的队列，因为如果不记录，右子树就无限循环了，建议明天继续看看
     * 左右中，先将左子树压入，判断右子树是否为空，如果不为空那么就压入栈，如果为空，则代表左也遍历完了，右也遍历完了，打印就可以了，
     * 由于右子树已经遍历完了，那么就需要一个node记录，否则，右子树又会压入栈重新遍历所以就加上判断，如果如果右子树为空或者右子树已经被遍历了。
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

    /**
     * 102. 二叉树的层序遍历
     * 由于要一层一层的输出，所以每一次遍历时需要先记录下这一层有多少数据，然后将这一层的数据加入列表就行
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null){
            return lists;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            List<Integer> list = new ArrayList<>();
            int length = queue.size();
            for (int i=0;i<length;i++){
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left!=null){
                    queue.add(node.left);
                }
                if (node.right!=null){
                    queue.add(node.right);
                }
            }
            lists.add(list);
        }
        return lists;
    }

    /**
     * 116. 填充每个节点的下一个右侧节点指针,递归实现。
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root==null){
            return null;
        }
        connect(root.left,root.right);
        return root;
    }
    public void connect(Node left,Node right) {
        if (left==null||right==null){
            return;
        }
        left.next = right;
        connect(left.left,left.right);
        connect(right.left,right.right);
        connect(left.right,right.left);
    }

    /**
     * 层序遍历实现，直接将每一层遍历出来，记录每一层的有多少数据
     * 然后每一层的节点指向右节点就可以了。
     * @param root
     * @return
     */
    public Node connect1(Node root) {
        if (root==null){
            return null;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int length = queue.size();
            while (length>0){
                Node node = queue.poll();
                if (length>1){
                    node.next = queue.peek();
                }
                if (node.left!=null){
                    queue.add(node.left);
                }
                if (node.right!=null){
                    queue.add(node.right);
                }
                length--;
            }
        }
        return root;
    }

    /**
     * 114. 二叉树展开为链表
     * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
     * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
     * 这道题学习到的主要是这句话
     * 递归算法的关键要明确函数的定义，对root的左右子树递归调用flatten函数即可：
     * 1、这个函数的定义就是拉平二叉树，因此在递归调用后会拉平二叉树
     * 2、通过观察发现是采用后序遍历的方法进行遍历，
     * 3、明确要干什么，就是将左子树放在右子树上，然后将原来的右子树接在当前右子树后面
     * @param root
     */
    public void flatten(TreeNode root) {
        if (root==null){
            return;
        }
        flatten(root.left);
        flatten(root.right);
        // 二叉树已经拉平了
        TreeNode node1 = root.left;
        TreeNode node2 = root.right;
        // 将左子树指向null，将右子树指向左子树
        root.left = null;
        root.right = node1;
        // 将原来右子树接在当前右子树的后面
        TreeNode treeNode = root;
        while (treeNode.right!=null){
            treeNode = treeNode.right;
        }
        treeNode.right = node2;
    }

    /**
     * 迭代解法
     * 当左子树不为空的时候，找到左子树中最右的节点然后将右子树连接到改节点的右子树，然后将左子树变为右子树
     * @param root
     */
    public void flatten1(TreeNode root) {
        if (root==null){
            return;
        }
        TreeNode cur = root;
        while (cur!=null){
            if(cur.left!=null) {
                TreeNode treeNode = cur.left;
                TreeNode right = treeNode;
                while (right.right != null) {
                    right = right.right;
                }
                right.right = cur.right;
                cur.left = null;
                cur.right = treeNode;
            }
            cur = cur.right;
        }
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
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
