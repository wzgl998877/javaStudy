package com.zt.javastudy;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author zhengtao
 * @description 栈训练
 * @date 2021/1/19
 */
public class LeetCodeForStack {
    public static void main(String[] args) {
        String path = "/home//foo/";
//        System.out.println(simplifyPath1(path));
        System.out.println(removeKdigits("1111", 1));
    }

    /**
     * 71. 简化路径
     * @param path
     * @return
     */
    public static String simplifyPath(String path) {
        String[] strings = path.split("/");
        Deque<String> stack = new LinkedList<>();
        for (String s:strings){
            if(!"".equals(s)){
                if ("..".equals(s)){
                    if (stack.peek()!=null){
                        stack.pop();
                    }
                } else if (".".equals(s)){
                    continue;
                }else {
                    stack.push(s);
                }
            }
        }
        Deque<String> stack1 = new LinkedList<>();
        StringBuilder fianllPath = new StringBuilder();
        while (stack.peek()!=null){
            stack1.push(stack.pop());
        }
        while (stack1.peek()!=null){
            fianllPath.append("/").append(stack1.pop());
        }
        return !"".equals(fianllPath.toString())?fianllPath.toString():"/";
    }

    /**
     * 省去了再遍历一遍stack，但为什么时间反而高了那么多看不懂。
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
     * @param num
     * @param k
     * @return
     */
    public static String removeKdigits(String num, int k) {
        if (num.length()==k) {
            return "0";
        }
        char[] numArray = num.toCharArray();
        Deque<Character> stack = new LinkedList<>();
        stack.push(numArray[0]);
        for (int i=1;i<numArray.length;i++){
            if (k>0){
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
            }else {
                stack.push(numArray[i]);
            }
        }
        if (stack.isEmpty()) return "0";
        Deque<Character> stack1 = new LinkedList<>();
        while (!stack.isEmpty()){
            stack1.push(stack.pop());
        }
        while(!stack1.isEmpty()&&stack1.peek()=='0'){
            stack1.pop();
        }
        if (stack1.isEmpty()) return "0";
        String s = "";
        while(!stack1.isEmpty()){
            s +=stack1.pop();
        }
        if (k!=0){
            s=s.substring(0,s.length()-k);
        }
        return s;
    }
}
