package com.zt.javastudy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengtao
 * @description 第二天冲呀
 * 714. 买卖股票的最佳时机含手续费
 * @date 2020/12/18
 */
public class LeetCodeDay2 {
    public static void main(String[] args) {
        int[] prices = new int[]{1, 3, 2, 8, 4, 9};
        System.out.println(maxProfit2(prices, 2));
    }

    /**
     *  自己的思路
     * @param prices
     * @param fee
     * @return
     */
    public static int maxProfit(int[] prices, int fee) {
        List<Integer> buy = new ArrayList<>();
        List<Integer> sell = new ArrayList<>();
        // 1.找出所有可以买入和卖出的点,一定要先买入才能卖出，。。找不出的。
        if (prices[0]<prices[1]) {
            buy.add(prices[0]);
        }
        for (int i=1;i<prices.length-1;i++){
            if (prices[i]<prices[i+1] && prices[i]<prices[i-1] && buy.size()==sell.size()){
                buy.add(prices[i]);
            }
            if (prices[i]>prices[i+1] && prices[i]>prices[i-1] && buy.size()>sell.size()){
                sell.add(prices[i]);
            }
        }
        if (prices[prices.length-1]>prices[prices.length-2] && buy.size()>sell.size()){
            sell.add(prices[prices.length-1]);
        }
        // 2.找出在何时买入何时卖出最赚钱，这是很明显一道贪心题
        int max = 0;
        return max;
    }

    /**
     * 将问题抽象为，有股票和没有股票两个概念，d[i][0]没股票，d[i][1]有股票
     * 如果有股票则代表前一天可能已经有、也可能没有股票如果没有股票则需要出prices[i]买
     * 如果没股票则代表前一天可能已经有这时需要卖掉今天就会赚prices[i]但需要出手续费fee,也可能没有股票
     * @param prices
     * @param fee
     * @return
     */
    public static int maxProfit1(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    /**
     * 只需要维持两个变量即可
     * @param prices
     * @param fee
     * @return
     */
    public static int maxProfit1Plus(int[] prices, int fee) {
        int n = prices.length;
        int buy = -prices[0];
        int sell = 0;
        for (int i = 1; i < n; ++i) {
            sell = Math.max(sell, buy + prices[i] - fee);
            buy = Math.max(buy, sell - prices[i]);
        }
        return sell;
    }

    /**
     * 核心在于，首先买的要以最低价买，卖的时候需要保证自己不亏，但是这时候出手可能赚的不是最多，这个地方赚的是 price[i]-buy, 但是如果是第二天卖的话price[i+1]-buy，就相当于是卖了的当天以当天的价格买入了，price[i+1]-price[i]+(price[i]-buy)
     * @param prices
     * @param fee
     * @return
     */
    public static int maxProfit2(int[] prices, int fee) {
        int n = prices.length;
        int buy = prices[0]+fee;
        int profit = 0;
        for (int i = 1; i < n; ++i) {
           if (buy > prices[i]+fee){
               buy = prices[i]+fee;
           } else if (buy < prices[i]){
               profit += prices[i]-buy;
               buy = prices[i];
           }
        }
        return profit;
    }
}
