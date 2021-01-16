package com.zt.javastudy;

import java.util.*;

/**
 * @author zhengtao
 * @description 链表学习
 * @date 2020/12/21
 */
public class LeetCodeForList {
    public static void main(String[] args) {
        ListNode l1 = null, l = null,l2 = null;
        for (int i =4;i<9;i++){
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
//        partition1(l1,6);
//        rotateRight(l1,2);
//        rotateRight1(l1,2);
//        swapPairs(l1);
//        removeNthFromEnd1(l1, 2);
        reverseBetween(l1,2 ,3);
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

    /**
     * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
     *
     * 示例 1:
     *
     * 输入: 1->2->3->4->5->NULL, k = 2
     * 输出: 4->5->1->2->3->NULL
     * 解释:
     * 向右旋转 1 步: 5->1->2->3->4->NULL
     * 向右旋转 2 步: 4->5->1->2->3->NULL
     * @param head
     * @param k
     * @return
     * 思路循环右移就是
     */
    public static ListNode rotateRight(ListNode head, int k) {
        if (k==0) {
            return head;
        }
        // 先算出需要移动多少位
        int length = 0;
        for (ListNode listNode2 = head;listNode2!=null;listNode2=listNode2.next){
            length++;
        }
        ListNode left = new ListNode(0);
        ListNode right = new ListNode(0);
        ListNode leftFlag = left;
        ListNode rightFlag = right;
        int flag = 0;
        while (head!=null){
            if (flag < length-k%length){
                leftFlag = leftFlag.next = new ListNode(head.val);
            } else {
                rightFlag = rightFlag.next = new ListNode(head.val);
            }
            head = head.next;
            flag++;
        }
        rightFlag.next = left.next;
        return right.next;
    }

    /**
     * 官方题解先连成环再取值
     * 详细答案可看题解61. 旋转链表
     * @param head
     * @param k
     * @return
     */
    public static ListNode rotateRight1(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode ring = head;
        int n = 1;
        while (ring.next!=null){
            ring = ring.next;
            n++;
        }
        // 先连成环
        ring.next = head;
        ListNode newHead = head;
        // 找出要断开的点
        for (int i=0;i<n-k%n-1;i++){
            newHead = newHead.next;
        }
        // 将断点后的赋值给新的节点，成为头节点
        ListNode node = newHead.next;
        // 断开
        newHead.next = null;
        return node;
    }

    /**
     * 直接暴力就可以了
     * 24. 两两交换链表中的节点
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        if (head == null||head.next == null) {
            return head;
        }
        ListNode flag = head;
        List<Integer> list = new LinkedList<>();
        while (flag!=null){
            list.add(flag.val);
            flag = flag.next;
        }
        for (int i=0;i<list.size();i=i+2){
            if (i+1<list.size()) {
                int a = list.get(i);
                list.set(i, list.get(i + 1));
                list.set(i + 1, a);
            }
        }
        ListNode node = new ListNode(0);
        flag = node;
        for (int i=0;i<list.size();i++){
            flag = flag.next = new ListNode(list.get(i));
        }
        return node.next;
    }

    /**
     * 题解 递归
     * @param head
     * @return
     */
    public static ListNode swapPairs1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }

    /**
     * 整体思路很简单，但是怎么一遍扫描就做出来很关键
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode left = head;
        int length = 0;
        while (left!=null){
            left = left.next;
            length++;
        }
        left = new ListNode(0);
        ListNode leftFlag = left;
        for (int i=0;i<length-n;i++){
            leftFlag = leftFlag.next = new ListNode(head.val);
            head = head.next;
        }
        leftFlag.next = head.next;
        return left.next;
    }

    /**
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     * 快慢指针,标准的快慢指针，先让快指针走n步，然后慢指针开始走，当快指针走完时，慢指针刚好走到倒数第n个
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode fast = head;
        ListNode node = new ListNode(-1, head);
        ListNode slow = node;
        // 快指针先走n步
        for (int i=0;i<n;i++){
            fast = fast.next;
        }
        // 快指针走完时，慢指针刚好走到倒数第n个
        while (fast!=null){
            slow = slow.next;
            fast = fast.next;
        }
        // 删除第n个节点即可
        slow.next = slow.next.next;
        return node.next;
    }

    /**
     * 最笨的方法
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates(ListNode head) {
        // 找出重复元素
        ListNode node =head;
        HashMap<Integer,Integer> hashMap = new LinkedHashMap<>();
        List<Integer> list = new ArrayList<>();
        while (node!=null){
            int val = node.val;
            if (hashMap.containsKey(val)){
                hashMap.put(val,hashMap.get(val)+1);
            } else {
                hashMap.put(val,1);
            }
            node = node.next;
        }
        for(Integer integer:hashMap.keySet()){
            if (hashMap.get(integer)==1){
                list.add(integer);
            }
        }
        node = new ListNode(0);
        ListNode flag = node;
        for (Integer integer : list){
            flag = flag.next = new ListNode(integer);
        }
        return node.next;
    }

    /**
     * 双指针法
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates1(ListNode head) {
        ListNode node = new ListNode(0,head);
        ListNode flag = node;
        while (head!=null&&head.next!=null){
            if (flag.next.val!=head.next.val){
                flag = flag.next;
                head = head.next;
            } else {
                while (head.next!=null&&head!=null&&head.next.val==flag.next.val){
                    head = head.next;
                }
                flag.next = head.next;
                head = head.next;
            }
        }
        return node.next;
    }

    public static ListNode deleteDuplicates2(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode a = dummy;
        ListNode b = head.next;
        while(b!=null) {
            if(a.next.val!=b.val) {
                a = a.next;
                b = b.next;
            }
            else {
                while(b!=null && a.next.val==b.val) {
                    b = b.next;
                }
                //这里的去重跟解法二有点差别，解法二的是
                //a.next = b.next
                a.next = b;
                //b指针在while中判断完后，可能指向了null，这里需要处理边界问题
                b = (b==null) ? null : b.next;
            }
        }
        return dummy.next;
    }

    /**
     * 92. 反转链表 II 题解又是递归啊，这谁顶的住
     * @param head
     * @param m
     * @param n
     * @return
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode left = new ListNode(-1);
        ListNode flag = left;
        ListNode node = head;
        for (int i=1;i<m;i++){
            flag = flag.next = new ListNode(node.val);
            node = node.next;
        }
        Deque<Integer> listNodes = new LinkedList<>();
        for (int i=m;i<=n;i++){
            listNodes.push(node.val);
            node = node.next;
        }
        while (!listNodes.isEmpty()){
            flag = flag.next = new ListNode(listNodes.pop());
        }
        while(node!=null){
            flag = flag.next = new ListNode(node.val);
            node = node.next;
        }
        return left.next;
    }

    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}