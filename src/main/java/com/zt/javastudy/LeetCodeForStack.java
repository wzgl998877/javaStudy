package com.zt.javastudy;

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
        System.out.println(simplifyPath1(path));
    }
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
}
