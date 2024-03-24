package org.example.common.core.leetcode.twentyFour.march;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:
 * @time: 2024/3/24 10:56
 */
public class Leet0324 {
    /**
     * 最小的。所以有
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return -1;
        }
        int count = 0;
        int index = coins.length - 1;
        Arrays.sort(coins);
        while (amount >= 0) {
            // index需要怎么处理？
//            while (coins[index] > amount) {
//                index--;
//            }
            int tempCount = amount / coins[index];
            if (tempCount > 0) {
                count += tempCount;
            }
            amount =  amount % coins[index];
        }

        return amount == 0 ? count : -1;
    }

    @Test
    public void test() {
        int[] coins = {1,2,5};
        System.out.println(coinChange(coins, 11));
    }

    public int coin(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 0; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j]<=i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    int coinChangeDfs(int[] coins, int amount) {
        // 题目要求的最终结果是 dp(amount)
        return dp(coins, amount);
    }

    // 定义：要凑出金额 n，至少要 dp(coins, n) 个硬币
    int dp(int[] coins, int amount) {
        // base case
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            // 计算子问题的结果
            int subProblem = dp(coins, amount - coin);
            // 子问题无解则跳过
            if (subProblem == -1) {
                continue;
            }
            // 在子问题中选择最优解，然后加一
            res = Math.min(res, subProblem + 1);
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }

}
