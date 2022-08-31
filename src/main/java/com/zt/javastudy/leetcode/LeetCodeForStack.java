package com.zt.javastudy.leetcode;

import java.util.*;

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
        System.out.println(leetCodeForStack.calculate2("1-(-2)"));
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        myQueue.push(3);
        myQueue.push(4);
        System.out.println(leetCodeForStack.decodeString("3[z]2[2[y]pq4[2[jk]e1[f]]]ef"));
        System.out.println(leetCodeForStack.decodeString1("3[z]2[2[y]pq4[2[jk]e1[f]]]ef"));
        System.out.println(leetCodeForStack.decodeString2("abc3[cd]xyz"));

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
                s1.push(c);
            }
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

    /**
     * 224. 基本计算器
     * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
     * <p>
     * 注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "1 + 1"
     * 输出：2
     * 示例 2：
     * <p>
     * 输入：s = " 2-1 + 2 "
     * 输出：3
     * 示例 3：
     * <p>
     * 输入：s = "(1+(4+5+2)-3)+(6+8)"
     * 输出：23
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= s.length <= 3 * 105
     * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
     * s 表示一个有效的表达式
     * '+' 不能用作一元运算(例如， "+1" 和 "+(2 + 3)" 无效)
     * '-' 可以用作一元运算(即 "-1" 和 "-(2 + 3)" 是有效的)
     * 输入中不存在两个连续的操作符
     * 每个数字和运行的计算将适合于一个有符号的 32位 整数
     * 这题不同的就是可以有括号()
     *
     * @param s
     * @return
     */
    public int calculate2(String s) {
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
            } else if (c == '(') {
                s1.push(c);
            } else if (c == ')') {
                // 遇到右括号则计算到第一个左括号为止
                while (!s1.isEmpty() && s1.peek() != '(') {
                    eval(num, s1);
                }
                s1.pop();
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                // 相邻的元素都是符号，则放入一个0避免错误，解决了(-2)
                if (i > 0 && (s.charAt(i - 1) == '(' || s.charAt(i - 1) == '+' || s.charAt(i - 1) == '-')) {
                    num.push(0);
                }
                // 栈顶优先级比当前元素优先级高，每次都要和栈顶元素进行比较！！！
                while (!s1.isEmpty() && s1.peek() != '(' && ((s1.peek() == '*' || s1.peek() == '/') || (c == '-' || c == '+'))) {
                    eval(num, s1);
                }
                s1.push(c);
            }
        }
        while (!s1.isEmpty()) {
            eval(num, s1);
        }
        return num.pop();
    }

    public void eval(Deque<Integer> num, Deque<Character> s1) {
        int a = num.pop();
        int b = num.isEmpty() ? 0 : num.pop();
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

    /**
     * 20. 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * <p>
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "()"
     * 输出：true
     * 示例 2：
     * <p>
     * 输入：s = "()[]{}"
     * 输出：true
     * 示例 3：
     * <p>
     * 输入：s = "(]"
     * 输出：false
     * 示例 4：
     * <p>
     * 输入：s = "([)]"
     * 输出：false
     * 示例 5：
     * <p>
     * 输入：s = "{[]}"
     * 输出：true
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == '}') {
                if (stack.isEmpty() || stack.peek() != '{') {
                    return false;
                } else {
                    stack.pop();
                }
            } else if (c == ']') {
                if (stack.isEmpty() || stack.peek() != '[') {
                    return false;
                } else {
                    stack.pop();
                }
            } else if (c == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
     * <p>
     * 实现 MyStack 类：
     * <p>
     * void push(int x) 将元素 x 压入栈顶。
     * int pop() 移除并返回栈顶元素。
     * int top() 返回栈顶元素。
     * boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
     *  
     * <p>
     * 注意：
     * <p>
     * 你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
     * 你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
     */
    class MyStack {
        private Queue<Integer> stack;

        public MyStack() {
            stack = new LinkedList<>();
        }

        public void push(int x) {
            if (stack.isEmpty()) {
                stack.offer(x);
            } else {
                Queue<Integer> temp = new LinkedList<>();
                while (!stack.isEmpty()) {
                    temp.offer(stack.poll());
                }
                stack.offer(x);
                while (!temp.isEmpty()) {
                    stack.offer(temp.poll());
                }
            }
        }

        public void push1(int x) {
            int n = stack.size();
            stack.offer(x);
            for (int i = 0; i < n; i++) {
                stack.offer(stack.poll());
            }
        }

        public int pop() {
            return stack.poll();
        }

        public int top() {
            return stack.peek();
        }

        public boolean empty() {
            return stack.isEmpty();
        }
    }

    /**
     * 232. 用栈实现队列
     * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
     * <p>
     * 实现 MyQueue 类：
     * <p>
     * void push(int x) 将元素 x 推到队列的末尾
     * int pop() 从队列的开头移除并返回元素
     * int peek() 返回队列开头的元素
     * boolean empty() 如果队列为空，返回 true ；否则，返回 false
     * 说明：
     * <p>
     * 你 只能 使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
     * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
     */
    static class MyQueue {
        private Deque<Integer> queue;

        public MyQueue() {
            queue = new LinkedList<>();
        }

        public void push(int x) {
            Deque<Integer> temp = new LinkedList<>();
            while (!queue.isEmpty()) {
                temp.push(queue.pop());
            }
            temp.push(x);
            while (!temp.isEmpty()) {
                queue.push(temp.pop());
            }
        }

        public int pop() {
            return queue.pop();
        }

        public int peek() {
            return queue.peek();
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }

    /**
     * 155. 最小栈
     * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
     * <p>
     * 实现 MinStack 类:
     * <p>
     * MinStack() 初始化堆栈对象。
     * void push(int val) 将元素val推入堆栈。
     * void pop() 删除堆栈顶部的元素。
     * int top() 获取堆栈顶部的元素。
     * int getMin() 获取堆栈中的最小元素。
     */
    class MinStack {
        private Deque<Integer> stack;
        private Deque<Integer> minStack;

        public MinStack() {
            stack = new LinkedList<>();
            minStack = new LinkedList<>();
            minStack.push(Integer.MAX_VALUE);
        }

        public void push(int val) {
            stack.push(val);
            minStack.push(Math.min(val, minStack.peek()));
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    class MinStack1 {
        private Deque<Integer> stack;

        public MinStack1() {
            stack = new LinkedList<>();
        }

        public void push(int val) {
            if (stack.isEmpty()) {
                stack.push(val);
                stack.push(val);
            } else {
                int temp = stack.peek();
                temp = Math.min(temp, val);
                stack.push(val);
                stack.push(temp);
            }
        }

        public void pop() {
            stack.pop();
            stack.pop();
        }

        public int top() {
            int temp = stack.pop();
            int result = stack.peek();
            stack.push(temp);
            return result;
        }

        public int getMin() {
            return stack.peek();
        }
    }

    /**
     * 394. 字符串解码
     * 给定一个经过编码的字符串，返回它解码后的字符串。
     * <p>
     * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
     * <p>
     * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
     * <p>
     * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "3[a]2[bc]"
     * 输出："aaabcbc"
     * 示例 2：
     * <p>
     * 输入：s = "3[a2[c]]"
     * 输出："accaccacc"
     * 示例 3：
     * <p>
     * 输入：s = "2[abc]3[cd]ef"
     * 输出："abcabccdcdcdef"
     * 示例 4：
     * <p>
     * 输入：s = "abc3[cd]xyz"
     * 输出："abccdcdcdxyz"
     *
     * @param s
     * @return
     */
    public String decodeString(String s) {
        StringBuilder result = new StringBuilder();
        Deque<String> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ']') {
                StringBuilder temp = new StringBuilder();
                LinkedList<String> sub = new LinkedList<>();
                int k;
                // 在拼接元素时，需要根据压入栈中的元素倒序，而不是所有元素倒序，例如，出栈元素为a,bc,de,拼接好后应为，debca，而不是edcba
                while (!stack.isEmpty() && !Objects.equals(stack.peek(), "[")) {
                    sub.add(stack.pop());
                }
                Collections.reverse(sub);
                for (String s1 : sub) {
                    temp.append(s1);
                }
                stack.pop();
                k = Integer.parseInt(stack.pop());
                StringBuilder s1 = new StringBuilder();
                while (k > 0) {
                    s1.append(temp);
                    k--;
                }
                stack.push(s1.toString());
            } else if (c >= '0' && c <= '9') {
                // 为数字时要计算出数字，比如99，放入栈中需要为99 而不是9
                int temp = c - '0';
                while (i < s.length() - 1 && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9') {
                    i++;
                    temp = temp * 10 + (s.charAt(i) - '0');
                }
                stack.push(String.valueOf(temp));
            } else {

                stack.push(String.valueOf(c));

            }

        }
        // 出栈时应该倒过来出栈
        while (!stack.isEmpty()) {
            result.append(stack.pollLast());
        }
        return result.toString();
    }

    int ptr;

    public String decodeString1(String s) {
        LinkedList<String> stk = new LinkedList<String>();
        ptr = 0;

        while (ptr < s.length()) {
            char cur = s.charAt(ptr);
            if (Character.isDigit(cur)) {
                // 获取一个数字并进栈
                String digits = getDigits(s);
                stk.addLast(digits);
            } else if (Character.isLetter(cur) || cur == '[') {
                // 获取一个字母并进栈
                stk.addLast(String.valueOf(s.charAt(ptr++)));
            } else {
                ++ptr;
                LinkedList<String> sub = new LinkedList<>();
                while (!"[".equals(stk.peekLast())) {
                    sub.addLast(stk.removeLast());
                }
                Collections.reverse(sub);
                // 左括号出栈
                stk.removeLast();
                // 此时栈顶为当前 sub 对应的字符串应该出现的次数
                int repTime = Integer.parseInt(stk.removeLast());
                StringBuffer t = new StringBuffer();
                String o = getString(sub);
                // 构造字符串
                while (repTime-- > 0) {
                    t.append(o);
                }
                // 将构造好的字符串入栈
                stk.addLast(t.toString());
            }
        }

        return getString(stk);
    }

    public String getDigits(String s) {
        StringBuffer ret = new StringBuffer();
        while (Character.isDigit(s.charAt(ptr))) {
            ret.append(s.charAt(ptr++));
        }
        return ret.toString();
    }

    public String getString(LinkedList<String> v) {
        StringBuffer ret = new StringBuffer();
        for (String s : v) {
            ret.append(s);
        }
        return ret.toString();
    }

    public String decodeString2(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        LinkedList<Integer> stack_multi = new LinkedList<>();
        LinkedList<String> stack_res = new LinkedList<>();
        for (Character c : s.toCharArray()) {
            if (c == '[') {
                stack_multi.addLast(multi);
                stack_res.addLast(res.toString());
                multi = 0;
                res = new StringBuilder();
            } else if (c == ']') {
                StringBuilder tmp = new StringBuilder();
                int cur_multi = stack_multi.removeLast();
                for (int i = 0; i < cur_multi; i++) {
                    tmp.append(res);
                }
                res = new StringBuilder(stack_res.removeLast() + tmp);
            } else if (c >= '0' && c <= '9') {
                multi = multi * 10 + Integer.parseInt(c + "");
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

}
