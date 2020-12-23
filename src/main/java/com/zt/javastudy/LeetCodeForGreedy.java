package com.zt.javastudy;

/**
 * @author zhengtao
 * @description 贪心算法学习
 * @date 2020/12/21
 */
public class LeetCodeForGreedy {
    public static void main(String[] args) {
        System.out.println(integerBreak(6));
    }
    /**
     * 因为两个数相乘，当相等的时候是最大的，2n,n*n是最大的，这个时候去考虑n要不要继续拆分，即（n/2)*(n/2)和n的大小，即可得到n>4时都会大于,即n>4时把所有的绳子都切成3,n<=4就不要切了。
     * 343. 整数拆分
     * @return
     */
    public static int integerBreak(int n) {
        if (n<=3) {
            return n-1;
        }
        int remainder = n%3;
        int quotient = n/3;
        if (remainder == 0){
            return (int) Math.pow(3,quotient);
        } else if (remainder == 1){
            return (int) (Math.pow(3,quotient-1)*4);
        } else{
            return (int) (Math.pow(3,quotient)*2);
        }
    }
}

