package org.example.common.core.leetcode.daily.november;

import lombok.extern.slf4j.Slf4j;
import org.example.common.core.util.CommonUtil;
import org.junit.Test;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/20 14:04
 */
@Slf4j
public class Leet1120 {
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = nums[i];
        }

        for (int j = len - 1; j > 0; j--) {
            for (int i = j; i < len; i++) {
                // 要的是一个i < j的
                dp[i][j] = Math.max(dp[i][j - 1] + nums[j], dp[i - 1][j] + nums[i]);
            }
        }
        // 获取dp(0,len)最大的值
        CommonUtil.formatAndDisplayArray(dp);
        return 0;
    }

    @Test
    public void main() {
        int[] nums = {5, 4, -1, 7, 8};
        maxSubArray(nums);
    }

    @Test
    public void testUpperThreeAngle() {
        int[][] nums = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int len = nums.length;
        CommonUtil.formatAndDisplayArray(nums);
//        for (int i = 0; i < len; i++) {
//            for (int j = i; j < len; j++) {
//                System.out.printf("%d\t", nums[i][j]);
//            }
//            System.out.println();
//        }
        for (int i = len - 1; i >= 0; i++) {
            for (int j = i + 1; j < len; j++) {
                System.out.printf("%d\t", nums[i][j]);
            }
            System.out.println();
        }
    }

}
