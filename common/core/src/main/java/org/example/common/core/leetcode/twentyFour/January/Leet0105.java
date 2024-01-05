package org.example.common.core.leetcode.twentyFour.January;

import lombok.extern.slf4j.Slf4j;
import org.example.common.core.leetcode.link.ListNode;
import org.example.common.core.util.CommonUtil;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;

/**
 * 因为是有可以看到比他矮的人，或者第一个比他高的人。所以有使用的是单调栈。
 * 从右向左，是一个单调递减的。
 */
@Slf4j
public class Leet0105 {
    /**
     * <p>
     * i可以看见j的条件如下：
     * i < j 且 min(heights[i], heights[j]) > max(heights[i+1], heights[i+2], ..., heights[j-1]) 。
     * 思路是单调栈
     *
     * </p>
     *
     * @param height
     * @return
     */
    public int[] canSeePersonCount(int[] height) {
        int n = height.length;
        final int[] res = new int[n];
        /**
         * 以j为末尾的值，表示了什么？【i，j】最大的值。
         *
         */
        final int[][] dp = new int[n][n];
        dp[0][0] = height[0];
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                // dp(i-1, j), height[i-1]  || dp[i,j-1], height[j-1]
                final int leftMax = Math.max(dp[i - 1][j], height[i - 1]);
                final int rightMax = Math.max(dp[i][j - 1], height[j - 1]);
                dp[i][j] = Math.max(leftMax, rightMax);
            }
        }
        CommonUtil.formatAndDisplayArray(dp);
//        log.info("{}", Arrays.deepToString(dp));
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = i + 1; j < n; j++) {
                // 在中间或者间隔的
                /**
                 * 10 [1, 2] 条件错误。
                 *
                 */
//                if () {
//                    count++;
//                }
            }
            res[i] = count;
        }
        return res;
    }

    @Test
    public void main() {
        // 3, 1, 2, 1, 1, 0 对8来说，到j是11的时候。stack有11
        int[] height = {10, 6, 8, 5, 11, 9};
        System.out.println(Arrays.toString(canSeePersonsCountSolution(height)));
    }

    /**
     * 极限的条件是height[i] > height[j] 就是count了。
     * dp[i,j+1] = max(dp[i,j], height[j+1])
     * <p>
     * 所以思路有，dp的定义是什么？
     * <p>
     * 定义：以j为末尾。最大的值是多少。
     * dp[j] =
     * </p>
     *
     * @param height
     * @param i
     * @param j
     * @return
     */
    public boolean canSee(int[] height, int i, int j) {
        return false;
    }

    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        int[] dp = new int[n];
        int[] res = new int[n];

        Arrays.fill(dp, n);

        for (int i = n - 2; i >= 0; i--) {
            int next = i + 1;

            while (next < n && heights[i] > heights[next]) {
                res[i]++;
                next = dp[next];
            }

            if (next != n) {
                res[i]++;
            }

            dp[i] = next;
        }

        return res;
    }

    public int[] canSeePersonsCountSolution(int[] heights) {
        int n = heights.length;
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int[] res = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            int h = heights[i];
            while (!stack.isEmpty() && stack.peek() < h) {
                stack.pop();
                res[i]++;
            }
            // 原因是什么？1.因为有小于的是可以看到；2.第一个大于的也可以被看到的。
            if (!stack.isEmpty()) {
                res[i]++;
            }
            stack.push(h);
        }
        return res;
    }
}
