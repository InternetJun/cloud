package org.example.common.core.leetcode.twentyFour.January;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:
 * @time: 2024/1/28 19:10
 */
@Slf4j
public class Leet0123 {
    /**
     * 交替+1、-1
     *
     * @param nums
     * @return
     */
    public int alternateSubArray(int[] nums) {
        int n = nums.length;
        boolean up = true;
        int dp = 1;
        int result = -1;
        for (int i = 1; i < n; i++) {
            if (nums[i] - 1 == nums[i - 1]) {
                if (up) {
                    up = false;
                    dp++;
                } else {
                    dp = 2;
                }
            } else if (nums[i] + 1 == nums[i - 1]) {
                if (!up) {
                    dp++;
                    log.info("nums[i]:{},nums[i-1]:{}；dp:{}",
                            nums[i], nums[i - 1], dp);
                    up = true;
                } else {
                    dp = 1;
                }
                // 为什么要有这个特殊情况？
            }
            // 因为可能是会相同的情况啊。例如有9 10 10 2 1；
            else {
                dp = 1;
                up = true;
            }
            if (dp != 1) {
                result = Math.max(result, dp);
            }
        }
        return result;
    }

    @Test
    public void test() {
//        int[] nums = {64,65,64,65,64,65,66,65,66,65}; 少了基本的上涨
//        int[] nums = {6, 12, 2, 3, 8, 9, 10, 10, 2, 1}; 少了特殊的情况；
        int[] nums = {6, 12, 2, 3, 8, 9, 10, 10, 2, 1};
        System.out.println(alternateSubArray(nums));
    }
}
