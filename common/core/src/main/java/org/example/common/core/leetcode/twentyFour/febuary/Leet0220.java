package org.example.common.core.leetcode.twentyFour.febuary;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:递增的序列
 * @time: 2024/2/20 14:27
 */
public class Leet0220 {
    /**
     * 1 3 5 4 7
     * dp表示i位置之前的最长长度
     *
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums){
        int n = nums.length;
        final int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n-1; i++) {
            if (nums[i+1] > nums[i]) {
                dp[i+1] = dp[i]+1;
            }
        }
        return Arrays.stream(dp)
                // 使用 max 方法获取最大值
                .max()
                // 如果数组为空，返回默认值，这里是0
                .orElse(1);
    }

    /**
     * 在数组中获取最长的子序列。
     * dp的定义是什么呢？i之前的最大的值。
     * <p>
     *     [10,9,2,5,3,7,101,18]
     *  101为止最长的值是多少？
     * </p>
     *
     * @param nums
     * @return
     */
    public int lengthOfLISME(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            // 有的缺陷，不能对之前的值进行一个判别的。
            if (nums[i] > nums[i-1]) {
                dp[i] = dp[i-1]++;
            }
            // 获取到的值是i之前最大的值。
            for (int j = 0; j < i; j++) {
                dp[i] = Math.max(dp[i], dp[j]);
            }
        }
        return dp[n-1];
    }
    @Test
    public void main() {
        System.out.println(lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }

    public int lengthOfLIS(int[] nums) {
        // dp[i] 表示以 nums[i] 这个数结尾的最长递增子序列的长度
        int[] dp = new int[nums.length];



        // base case：dp 数组全都初始化为 1
        Arrays.fill(dp, 1);

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
