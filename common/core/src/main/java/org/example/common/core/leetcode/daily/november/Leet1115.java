package org.example.common.core.leetcode.daily.november;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/15 14:27
 */
public class Leet1115 {
    /**
     * <h1>K 个元素的最大和</h1>
     * <href>https://leetcode.cn/problems/maximum-sum-with-exactly-k-elements/?envType=daily-question&envId=Invalid%20Date</href>
     *
     * @param nums
     * @param k
     * @return
     */
    public int maximizeSum(int[] nums, int k) {
        Arrays.sort(nums);
        int sum = 0;
        int temp = nums[nums.length - 1];
        sum += temp * k;
        int count = 0;
        while (k > 0) {
            sum += count;
            count++;
            k--;
        }
        return sum;
    }

    @Test
    public void testK() {
        System.out.println(maximizeSum(new int[]{5,5, 5}, 2));
    }

    public int maximizeSumSoultion(int[] nums, int k) {
        int max = 0;
        for (int x : nums) {
            max = Math.max(max, x);
        }
        return k * (max + max + k - 1) / 2;
    }

}
