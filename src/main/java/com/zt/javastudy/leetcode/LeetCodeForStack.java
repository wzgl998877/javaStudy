package com.zt.javastudy.leetcode;

import java.util.Date;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author zhengtao
 * @description 栈训练
 * @date 2021/1/19
 */
public class LeetCodeForStack {
    public static void main(String[] args) {
        String path = "/home//foo/";
//        System.out.println(simplifyPath1(path));
        System.out.println(removeKdigits1("1234567890", 9));
        System.out.println(removeKdigits2("1234567890", 9));
        String s = "1432219";
        System.out.println(s.substring(0, 0));
        StringBuilder stringBuilder = new StringBuilder("12312");
        System.out.println(stringBuilder.substring(0, 0));
        char[] c = s.toCharArray();
        for (int i = 1; i < c.length; i++) {
            System.out.println(c[i]);
        }
        LeetCodeForStack leetCodeForStack = new LeetCodeForStack();
        System.out.println(leetCodeForStack.calculate("1+2*2*2+2"));
    }

    /**
     * 71. 简化路径
     *
     * @param path
     * @return
     */
    public static String simplifyPath(String path) {
        String[] strings = path.split("/");
        Deque<String> stack = new LinkedList<>();
        for (String s : strings) {
            if (!"".equals(s)) {
                if ("..".equals(s)) {
                    if (stack.peek() != null) {
                        stack.pop();
                    }
                } else if (".".equals(s)) {
                    continue;
                } else {
                    stack.push(s);
                }
            }
        }
        Deque<String> stack1 = new LinkedList<>();
        StringBuilder fianllPath = new StringBuilder();
        while (stack.peek() != null) {
            stack1.push(stack.pop());
        }
        while (stack1.peek() != null) {
            fianllPath.append("/").append(stack1.pop());
        }
        return !"".equals(fianllPath.toString()) ? fianllPath.toString() : "/";
    }

    /**
     * 省去了再遍历一遍stack，但为什么时间反而高了那么多看不懂。
     *
     * @param path
     * @return
     */
    public static String simplifyPath1(String path) {
        Deque<String> stack = new LinkedList<>();
        for (String item : path.split("/")) {
            if (item.equals("..")) {
                if (!stack.isEmpty()) stack.pop();
            } else if (!item.isEmpty() && !item.equals(".")) stack.push(item);
        }
        String res = "";
        for (String d : stack) {
            res = "/" + d + res;
        }
        return res.isEmpty() ? "/" : res;
    }

    /**
     * 402. 移掉K位数字 跟个傻逼一样这题，自己在干嘛啊
     * 最好的就是贪心啊
     *
     * @param num
     * @param k
     * @return
     */
    public static String removeKdigits(String num, int k) {
        if (num.length() == k) {
            return "0";
        }
        char[] numArray = num.toCharArray();
        Deque<Character> stack = new LinkedList<>();
        stack.push(numArray[0]);
        for (int i = 1; i < numArray.length; i++) {
            if (k > 0) {
                if (!stack.isEmpty()) {
                    if ((int) stack.peek() > (int) numArray[i]) {
                        stack.pop();
                        stack.push(numArray[i]);
                        k--;
                    } else if ((int) stack.peek() == (int) numArray[i]) {
                        stack.push(numArray[i]);
                    } else {
                        k--;
                    }
                } else {
                    stack.push(numArray[i]);
                }
            } else {
                stack.push(numArray[i]);
            }
        }
        if (stack.isEmpty()) return "0";
        Deque<Character> stack1 = new LinkedList<>();
        while (!stack.isEmpty()) {
            stack1.push(stack.pop());
        }
        while (!stack1.isEmpty() && stack1.peek() == '0') {
            stack1.pop();
        }
        if (stack1.isEmpty()) return "0";
        String s = "";
        while (!stack1.isEmpty()) {
            s += stack1.pop();
        }
        if (k != 0) {
            s = s.substring(0, s.length() - k);
        }
        return s;
    }

    /**
     * 就差一点点啊，之前的思路是对的
     * 402. 移掉 K 位数字
     * 给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字。
     * <p>
     * <p>
     * 示例 1 ：
     * <p>
     * 输入：num = "1432219", k = 3
     * 输出："1219"
     * 解释：移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219 。
     * 示例 2 ：
     * <p>
     * 输入：num = "10200", k = 1
     * 输出："200"
     * 解释：移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
     * 示例 3 ：
     * <p>
     * 输入：num = "10", k = 2
     * 输出："0"
     * 解释：从原数字移除所有的数字，剩余为空就是 0 。
     *
     * @param num
     * @param k
     * @return
     */
    public static String removeKdigits1(String num, int k) {
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < num.length(); i++) {
            while (!stack.isEmpty() && k > 0 && stack.peekLast() > num.charAt(i)) {
                k--;
                stack.pollLast();
            }
            stack.offerLast(num.charAt(i));
        }
        // 将剩余的k位移掉
        for (int i = 0; i < k; i++) {
            stack.pollLast();
        }
        // 去掉前导0
        StringBuilder stringBuilder = new StringBuilder();
        boolean flag = true;
        while (!stack.isEmpty()) {
            if (flag && stack.peekFirst() == '0') {
                stack.pollFirst();
                continue;
            }
            flag = false;
            stringBuilder.append(stack.pollFirst());
        }
        return stringBuilder.length() == 0 ? "0" : stringBuilder.toString();
    }

    public static String removeKdigits2(String num, int k) {
        char[] c = num.toCharArray();
        StringBuilder s = new StringBuilder();
        s.append(c[0]);
        int i = 1;
        for (; i < c.length && k >= 0; i++) {
            int n = s.length() - 1;
            while (s.charAt(n) > c[i] && k > 0 && n >= 0) {
                s.deleteCharAt(n);
                k--;
                n--;
                if (n < 0) {
                    break;
                }
            }
            s.append(c[i]);
        }
        if (k != 0) {
            s = new StringBuilder(s.substring(0, s.length() - k));
        }
        if (i != c.length - 1) {
            s.append(num.substring(i));
        }
        String response = s.toString().replaceAll("^(0+)", "");
        return response.length() == 0 ? "0" : response;
    }

    /**
     * 227. 基本计算器 II
     * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
     * <p>
     * 整数除法仅保留整数部分。
     * <p>
     * 你可以假设给定的表达式总是有效的。所有中间结果将在 [-231, 231 - 1] 的范围内。
     * <p>
     * 注意：不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "3+2*2"
     * 输出：7
     * 示例 2：
     * <p>
     * 输入：s = " 3/2 "
     * 输出：1
     * 示例 3：
     * <p>
     * 输入：s = " 3+5 / 2 "
     * 输出：5
     *
     * @param s
     * @return
     */
    public int calculate(String s) {
        Deque<Integer> num = new LinkedList<>();
        Deque<Character> s1 = new LinkedList<>();
        int i = 0;
        for (; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                int temp = c - '0';
                while ((i < s.length() - 1) && (s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9')) {
                    i++;
                    temp = temp * 10 + (s.charAt(i) - '0');
                }
                num.push(temp);
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                // 栈顶优先级比当前元素优先级高，每次都要和栈顶元素进行比较！！！
                while (!s1.isEmpty() && ((s1.peek() == '*' || s1.peek() == '/') || (c == '-' || c == '+'))) {
                    // 自己的错误理解
//                    while (!s1.isEmpty()) {
//                        char c1 = s1.peek();
//                        if((s1.peek() == '*' || s1.peek() == '/') || (c == '-' || c == '+')) {
//
//                        }
//                    }
                    int a = num.pop();
                    int b = num.pop();
                    int temp;
                    char c2 = s1.pop();
                    if (c2 == '+') {
                        temp = a + b;
                    } else if (c2 == '-') {
                        temp = b - a;
                    } else if (c2 == '*') {
                        temp = a * b;
                    } else {
                        if (a == 0) {
                            temp = 0;
                        } else {
                            temp = b / a;
                        }
                    }
                    num.push(temp);
                }
            }
            s1.push(c);
        }
        while (!s1.isEmpty()) {
            int a = num.pop();
            int b = num.pop();
            int temp;
            char c1 = s1.pop();
            if (c1 == '+') {
                temp = a + b;
            } else if (c1 == '-') {
                temp = b - a;
            } else if (c1 == '*') {
                temp = a * b;
            } else {
                if (a == 0) {
                    temp = 0;
                } else {
                    temp = b / a;
                }
            }
            num.push(temp);
        }
        return num.pop();
    }
}
