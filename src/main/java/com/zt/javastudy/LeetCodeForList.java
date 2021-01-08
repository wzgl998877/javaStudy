package com.zt.javastudy;

import jdk.nashorn.internal.objects.NativeUint8Array;

import java.util.*;

/**
 * @author zhengtao
 * @description 链表学习
 * @date 2020/12/21
 */
public class LeetCodeForList {
    public static void main(String[] args) {
        ListNode l1 = null, l = null,l2 = null;
        for (int i =5;i<9;i++){
            if (l1 == null){
                l1 = l = new ListNode(i);
            } else {
                l.next = new ListNode(i);
                l = l.next;
            }
        }
        for (int i =6;i<9;i++){
            if (l2 == null){
                l2 = l = new ListNode(i);
            } else {
                l.next = new ListNode(i);
                l = l.next;
            }
        }
       /* addTwoNumbers(l1,l2);
        addTwoNumbersPlus(l1,l2);
        splitListToParts(l1,3);
        splitListToParts1(l1,3);*/
        partition1(l1,6);
    }

    /**
     * 2. 两数相加
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode listNode = null , listNode1 = null;
        int carry = 0;
        while(l1!=null||l2!=null){
            int a = l1!=null?l1.val:0;
            int b = l2!=null?l2.val:0;
            if (listNode == null){
                listNode = listNode1 = new ListNode((a+b+carry)%10);
            } else {
                listNode1.next = new ListNode((a+b+carry)%10);
                listNode1 = listNode1.next;
            }
            carry = (a+b+carry)/10;
            if(l1!=null) {
                l1 = l1.next;
            }
            if(l2!=null) {
                l2 = l2.next;
            }
        }
        if (carry>0){
            listNode1.next= new ListNode(carry);
        }
        for (ListNode listNode2 = listNode;listNode2!=null;listNode2=listNode2.next){
            System.out.print(listNode2.val+" ");
        }
        System.out.println();
        return listNode;
    }
    /**
     * 445. 两数相加 II
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbersPlus(ListNode l1, ListNode l2) {
        // 先翻转两个链表然后相加最后翻转结果链表
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        while(l1!=null){
            list1.add(l1.val);
            l1 = l1.next;
        }
        while(l2!=null){
            list2.add(l2.val);
            l2 = l2.next;
        }
        int length1 = list1.size();
        int length2 = list2.size();
        int carry = 0;
        for (int i=length1-1,j=length2-1;i>=0||j>=0;i--,j--){
            int a = i>=0?list1.get(i):0;
            int b = j>=0?list2.get(j):0;
            int sum = a+b+carry;
            list3.add(sum%10);
            carry = sum/10;
        }
        if (carry>0){
            list3.add(carry);
        }
        ListNode listNode = null , listNode1 = null;
        for (int i=list3.size()-1;i>=0;i--){
            if (listNode == null){
                listNode = listNode1 = new ListNode(list3.get(i));
            } else {
                listNode1.next = new ListNode(list3.get(i));
                listNode1 = listNode1.next;
            }
        }
        /*for (ListNode listNode2 = listNode;listNode2!=null;listNode2=listNode2.next){
            System.out.print(listNode2.val+" ");
        }
        System.out.println();*/
        return listNode;
    }

    /**
     * 逆序处理想到栈，先进后出
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbersPlus2(ListNode l1, ListNode l2) {
        // 先翻转两个链表然后相加最后翻转结果链表
        Stack<Integer> stack1 = new Stack<>();

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        while(l1!=null){
            list1.add(l1.val);
            l1 = l1.next;
        }
        while(l2!=null){
            list2.add(l2.val);
            l2 = l2.next;
        }
        int length1 = list1.size();
        int length2 = list2.size();
        int carry = 0;
        for (int i=length1-1,j=length2-1;i>=0||j>=0;i--,j--){
            int a = i>=0?list1.get(i):0;
            int b = j>=0?list2.get(j):0;
            int sum = a+b+carry;
            list3.add(sum%10);
            carry = sum/10;
        }
        if (carry>0){
            list3.add(carry);
        }
        ListNode listNode = null , listNode1 = null;
        for (int i=list3.size()-1;i>=0;i--){
            if (listNode == null){
                listNode = listNode1 = new ListNode(list3.get(i));
            } else {
                listNode1.next = new ListNode(list3.get(i));
                listNode1 = listNode1.next;
            }
        }
        /*for (ListNode listNode2 = listNode;listNode2!=null;listNode2=listNode2.next){
            System.out.print(listNode2.val+" ");
        }
        System.out.println();*/
        return listNode;
    }

    /**
     * 725. 分隔链表
     * @param root
     * @param k
     * @return
     */
    public static ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] listNodes = new ListNode[k];
        // 1 算出每个部分的大小
        int length = 0;
        for (ListNode listNode2 = root;listNode2!=null;listNode2=listNode2.next){
           length++;
        }
        int partLength = length/k;
        int additional = length%k;
        for (int i=0;i<k;i++){
            ListNode listNode = new ListNode(0);
            // 此时flag和listNode的引用listNode
            ListNode flag = listNode;
            // 赋值
            for (int j = 0; j<partLength+(i<additional?1:0); j++) {
                // flag.next = new ListNode(root.val)这个意思是指将listNode引用的next指向新的节点
                // flag = flag.next 将flag的引用指向flag.next
                flag = flag.next = new ListNode(root.val);
                if (root != null) {
                    root = root.next;
                }
            }

            listNodes[i] = listNode.next;
        }
        for (ListNode listNode : listNodes){
            for (ListNode listNode2 = listNode;listNode2!=null;listNode2=listNode2.next){
                System.out.print(listNode2.val+" ");
            }
            System.out.println();
        }
        return listNodes;
    }

    public static ListNode[] splitListToParts1(ListNode root, int k) {
        ListNode[] listNodes = new ListNode[k];
        // 1 算出每个部分的大小
        int length = 0;
        for (ListNode listNode2 = root;listNode2!=null;listNode2=listNode2.next){
            length++;
        }
        int partLength = length/k;
        int additional = length%k;
        for (int i=0;i<k;i++){
            // 将listNode的引用指向root
            ListNode listNode = root;
            for (int j = 0; j<partLength+(i<additional?1:0)-1; j++) {
                if (root != null) {
                    // 把root的引用指向root.next
                    root = root.next;
                }
            }
            if (root != null){
                // 此时flag的引用是root
                ListNode flag = root;
                // root指向root.next
                root = root.next;
                flag.next = null;
            }
            listNodes[i] = listNode;
        }
        for (ListNode listNode : listNodes){
            for (ListNode listNode2 = listNode;listNode2!=null;listNode2=listNode2.next){
                System.out.print(listNode2.val+" ");
            }
            System.out.println();
        }
        return listNodes;
    }

    /**
     * 编写程序以 x 为基准分割链表，使得所有小于 x 的节点排在大于或等于 x 的节点之前。如果链表中包含 x，x 只需出现在小于 x 的元素之后(如下所示)。分割元素 x 只需处于“右半部分”即可，其不需要被置于左右两部分之间。
     * 这题目跟个睿智一样题目的意思就是把所有小于x的元素放在左边就可以了。
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode listNode = head;
        List<Integer> list = new ArrayList<>();
        while (listNode!=null){
            list.add(listNode.val);
            listNode = listNode.next;
        }
        Collections.sort(list);
        listNode = new ListNode(0);
        ListNode write = listNode;
        for (int i=0;i<list.size();i++){
            write = write.next = new ListNode(list.get(i));
        }
        return listNode.next;
    }

    /**
     * 网上说用双指针法，左链表存储小于的，右链表存储大于的，最后合并即可
     * @param head
     * @param x
     * @return
     */
    public static ListNode partition1(ListNode head, int x) {
        ListNode left = new ListNode(0);
        ListNode right = new ListNode(0);
        ListNode leftFlag = left;
        ListNode rightFlag = right;
        while (head!=null){
            if (head.val<x){
                leftFlag = leftFlag.next = new ListNode(head.val);
            } else{
                rightFlag = rightFlag.next = new ListNode(head.val);
            }
            head = head.next;
        }
        leftFlag.next = right.next;
        return left.next;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}