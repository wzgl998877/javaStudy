package com.zt.javastudy.leetcode;

//import com.sun.org.apache.xpath.internal.operations.String;

import java.util.*;

/**
 * @author zhengtao
 * @description 队列
 * @date 2020/12/12
 */
public class LeetCodeDay1 {
    public static void main(String[] args) {
        String s = "RDD";
        char [] strings = new char[]{'A','A','A','A','A','A','B','C','D','E','F','G'};
//        System.out.println(leastInterval(strings, 2));
        int[] a = new int[]{1,2,3,4,5,6,7};
        for (int i : a){
            if (i<4) {
                i+=4;
                continue;
            } else if (i>4){
                continue;
            }


        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.offer(1);
    }

    static class Ddd {
        public   Integer d;

        public Integer getD() {
            return d;
        }

        public void setD(Integer d) {
            this.d = d;
        }
    }

    /**
     * 649. Dota2 参议院
     * @param senate
     * @return
     */
    public static String predictPartyVictory(String senate) {
        Queue<Integer> rQueue = new LinkedList<>();
        Queue<Integer> dQueue = new LinkedList<>();
        for(int i=0;i<senate.length();i++){
            if (senate.charAt(i) == 'R'){
                rQueue.offer(i);
            } else {
                dQueue.offer(i);
            }
        }
        while (rQueue.peek()!=null && dQueue.peek()!=null) {
            if (rQueue.peek() < dQueue.peek()) {
                rQueue.offer(rQueue.peek() + senate.length());
                rQueue.poll();
                dQueue.poll();
            } else {
                dQueue.offer(dQueue.peek() + senate.length());
                dQueue.poll();
                rQueue.poll();
            }
        }

        return rQueue.peek() == null ? "Dire" : "Radiant";
    }



        public static int leastInterval(char[] tasks, int n) {
            Map<Character, Integer> freq = new HashMap<Character, Integer>(26);
            for (char ch : tasks) {
                freq.put(ch, freq.getOrDefault(ch, 0)+1);
            }

            // 任务总数
            int m = freq.size();
            List<Integer> nextValid = new ArrayList<Integer>();
            List<Integer> rest = new ArrayList<Integer>();
            Set<Map.Entry<Character, Integer>> entrySet = freq.entrySet();
            for (Map.Entry<Character, Integer> entry : entrySet) {
                int value = entry.getValue();
                nextValid.add(1);
                rest.add(value);
            }

            int time = 0;
            for (int i = 0; i < tasks.length; ++i) {
                ++time;
                int minNextValid = Integer.MAX_VALUE;
                // 找出空闲时间的最小值
                for (int j = 0; j < m; ++j) {
                    if (rest.get(j) != 0) {
                        minNextValid = Math.min(minNextValid, nextValid.get(j));
                    }
                }
                // 将time值置为最小的等待时间
                time = Math.max(time, minNextValid);

                int best = -1;
                // 找出离空闲时间最近且值最大的任务
                for (int j = 0; j < m; ++j) {
                    if (rest.get(j) != 0 && nextValid.get(j) <= time) {
                        if (best == -1 || rest.get(j) > rest.get(best)) {
                            best = j;
                        }
                    }
                }
                // 任务数减一，更新等待时间
                nextValid.set(best, time + n + 1);
                rest.set(best, rest.get(best) - 1);
            }

            return time;
        }



}
