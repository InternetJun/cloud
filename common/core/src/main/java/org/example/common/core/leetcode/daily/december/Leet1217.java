package org.example.common.core.leetcode.daily.december;

import cn.hutool.core.util.NumberUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:
 * @time: 2023/12/17 13:34
 */
public class Leet1217 {
    /**
     * <href>https://leetcode.cn/problems/min-cost-climbing-stairs/?envType=daily-question&envId=2023-12-17</href>
     * 花费的最小费用。可以爬取1 或者 2个
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
//        int n = cost.length;
//        final int[] dp = new int[n+1];
//        dp[0] = 0;
//        dp[1] = cost[1];
//        // n = 3;  dp3 = dp1 +
//        for (int i = 2; i < n; i++) {
//            // {10,15,20} 0 + 10, 10 + 15
//            // dp2 = dp(0) +  cost0, dp1 +  dp1
//            dp[i] = Math.min(dp[i-2] + cost[i-2], dp[i-1] + cost[i-1]);
//        }
//        System.out.println(Arrays.toString(dp));
//        return dp[n];
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }

    @Test
    public void min() {
        int[] cost = {10, 15, 20};
        System.out.println(minCostClimbingStairs(cost));
    }

    public double maxAverageRatio(int[][] classes, int extraStudent) {
        return 0.0;
    }

    public double average(int[][] classes) {
        int n = classes.length;
        //
        double sum = 0.0d;
        for (int i = 0; i < n; i++) {
            sum += NumberUtil.div(classes[i][0], classes[i][1]);
        }
        double item = NumberUtil.round(sum, 5).doubleValue();
        return item;
    }

    /**
     * 1 5 15 60
     *
     * @param current
     * @param correct
     * @return
     */
    public int convertTime(String current, String correct) {
        if (current.equals(correct)) {
            return 0;
        }
        int step = 0;
        int gap = Integer.parseInt(correct.split(":")[0]) - Integer.parseInt(current.split(":")[0]);
        final int correctMin = Integer.parseInt(correct.split(":")[1]);
        int currrentMin = Integer.parseInt(current.split(":")[1]);
        int minutes = gap * 60;
        if (correctMin >= currrentMin) {
            minutes += correctMin - currrentMin;
        } else {
            // 2:35  3:30; = 60 +
            minutes -= currrentMin - correctMin;
        }
        // minutes的处理。
        int[] steps = {1, 5, 15, 60};
        step = dpSolution(minutes);
        return step;
    }

    /**
     * // 执行60分钟的次数+执行15分钟的次数+执行5分钟的次数+执行1分钟的次数
     *         return total/60 + total%60/15 + total%60%15/5 + total%60%15%5;
     * @param minutes
     * @return
     */
    private int dpSolution(int minutes) {
        int step = 0;
        // 60 15 5 1;
        int count60 = minutes / 60;
        int count15 = (minutes - count60 * 60) / 15;
        int count5 = (minutes - count60 * 60 - count15 * 15) / 5;
        int count1 = (minutes - count5 * 5 - count60 * 60 - count15 * 15);
        return count1 + count5 + count60 + count15;
    }

    @Test
    public void testMinute() {
        System.out.println(convertTime("02:30","04:35"));
    }
}


