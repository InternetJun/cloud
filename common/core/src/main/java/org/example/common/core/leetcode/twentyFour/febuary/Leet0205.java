package org.example.common.core.leetcode.twentyFour.febuary;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayDeque;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:
 * @time: 2024/2/5 11:40
 */
@Slf4j
public class Leet0205 {
    /**
     * @param nums
     * @param k
     * @return
     */
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        final int[] dp = new int[n];
        dp[0] = nums[0];
        // dp表示第i处的值最大。
        /**
         *
         * dp[i] = Math.max(dp[i] + nums[i+1], dp[i]+nums[i+2], dp[i] + dp[i+k-1]);
         *
         */
        final ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offerLast(0);
        for (int i = 1; i < n; i++) {
            // 用的是一个单调递增的队列。
            while (queue.peekFirst() < i - k) {
                queue.pollFirst();
            }
            dp[i] = dp[queue.peekFirst()] + nums[i];
            while (!queue.isEmpty() && dp[queue.peekLast()] <= dp[i]) {
                queue.pollLast();
            }
            queue.offerLast(i);
        }
        log.info("{}", dp);
        return dp[n - 1];
    }

    @Test
    public void main() {
        int[] nums = {1,-1,-2,4,-7,3};
        System.out.println(maxResult(nums, 2));
    }
}
