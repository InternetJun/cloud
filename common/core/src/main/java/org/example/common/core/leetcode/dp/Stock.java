package org.example.common.core.leetcode.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/10/22 21:38
 */
@Slf4j
public class Stock {
    /**
     * <h1>最大的利润：只能买入以后才能卖出</h1>
     * 基本的定义是什么呢？dp[i][2]
     * i为第i天的最大的数值
     *
     * @param prices
     * @return
     */
    public int maxProfitI(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                // base case
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            // 0 shi sell 1 is buy
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 前一天有了或者今天买
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        log.info("{}", Arrays.deepToString(dp));
        return dp[n - 1][0];
    }

    @Test
    public void test() {
        int[] nums = {8, 9, 2, 5, 4, 7, 1};
        maxProfitI(nums);
    }

    /**
     * <h1>每天都可以操作stock</h1>
     *
     * @param prices
     * @return
     */
    int maxProfit_k_inf(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                // base case
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            // 那么可能的转移状态为前一天已经持有一支股票，
            // 即 dp[i−1][1]\textit{dp}[i-1][1]dp[i−1][1]，
            // 或者前一天结束时还没有股票，即 dp[i−1][0]\textit{dp}[i-1][0]dp[i−1][0]，这时候我们要将其买入，并减少 prices[i]\textit{prices}[i]prices[i] 的收益。
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
        }
        return dp[n - 1][0];
    }

    /**
     * <h1>交易k次的方程</h1>
     * <p>
     * 原始的状态转移方程，没有可化简的地方
     * 卖 +price
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     * 买 -price
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     * </p>
     *
     * @param prices
     * @return
     */
    public int maxProfitWithK(int[] prices) {
        int max_k = 2, n = prices.length;
        int[][][] dp = new int[n][max_k + 1][2];
        for (int i = 0; i < n; i++) {
            /**
             * 这个目的是为什么？
             * <p>
             *     <b>因为是符合现实</b>
             * </p>
             *
             */
            for (int k = max_k; k >= 1; k--) {
                if (i - 1 == -1) {
                    // 初始化数据
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                // 状态转移方程 买、 卖
                dp[i][k][0] = Math.max(dp[i-1][k][1]+prices[i], dp[i-1][k][0]);

                dp[i][k][1] = Math.max(dp[i-1][k][0]-prices[i], dp[i-1][k][1]);
            }
        }
        return dp[n-1][max_k][0];
    }

    public static void main(String[] args) {
        char c = 127;
        int sum = 200;
        c += 1;
        sum += c;
        System.out.println(sum);
    }
}
