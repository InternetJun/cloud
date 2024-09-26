package org.example.common.core.leetcode.twentyFour.march;

import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            amount = amount % coins[index];
        }

        return amount == 0 ? count : -1;
    }

    @Test
    public void test() {
        int[] coins = {1, 2, 5};
        System.out.println(coinChange(coins, 11));
    }

    public int coin(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 0; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
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

    public int findSurvivorMe(int[] num, int k) {
        // 中止的条件。
        int count = num.length;
        int len = num.length;
        final boolean[] flag = new boolean[count];
        int ind = 0, cnt = 1;
        while (count > 1) {
            while (cnt < k) {
                if (!flag[ind++ % k]) {
                    cnt++;
                }
            }
            // cnt == k;继续处理数据啊。
            count--;
            // 对ind的赋予true；
            flag[ind % len] = true;
            cnt = 1;
        }
        for (int i = 0; i < count; i++) {
            if (!flag[i]) {
                return i + 1;
            }
        }
        return -1;
    }

    @Test
    public void testCircle() {
        final int[] ints = new int[7];
        for (int i = 0; i < 7; i++) {
            ints[i] = i;
        }
        // 1~7 3;  1| 2| 3| *4* 5| 6| 7|
        System.out.println(Arrays.toString(findSurvivor(7, 3)));
    }

    public static int[] findSurvivor(int n, int k) {

        // 使用布尔数组表示每个人是否还在圈内
        boolean[] people = new boolean[n];
        for (int i = 0; i < n; i++) {
            people[i] = true;
        }

        int count = 0;
        int index = 0; // 当前报数的人的索引

        while (count < n - k + 1) { // 循环直到只剩下一个人
            int step = 0; // 记录报数的步数

            while (step < k) {
                if (people[index]) { // 如果当前位置的人还没有出圈
                    step++;
                }
                if (step < k) {
                    index = (index + 1) % n;
                }
            }

            // 找到要出圈的人，标记为已出圈
            people[index] = false;
            count++;

            // 移动到下一个还没出圈的人
            while (!people[index]) {
                index = (index + 1) % n;
            }
        }
        List<Integer> res = new ArrayList<>();
        // 找到最后剩下的人的编号
        for (int i = 0; i < n; i++) {
            if (people[i]) {
                res.add(i + 1); // 编号是从1开始的
            }
        }

        return res.stream().mapToInt(m->m).toArray(); // 出错时返回-1
    }

    public int findSurivivor(int[] num, int k) {
        int n = num.length;
        final boolean[] flag = new boolean[n];
        int count = 0;
        int index = 0;
        while (count < n - k) {
            int step = 0;
            while (step < k) {
                if (!flag[index]) {
                    step++;
                }
                // index 进行循环处理。
                if (step < k) {
                    index = (index+1)%n;
                }
            }
            flag[index] = true;
            count++;
            while (flag[index]) {
                index = (index + 1) % n;
            }
        }
        // 返回数据。
        return -1;
    }

}
