package org.example.common.core.leetcode.math;

import org.junit.Test;

import java.util.List;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/8/31 17:53
 */
public class MathAlg {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        System.out.println(res);
        return res;
    }

    @Test
    public void main() {
        singleNumber(new int[]{4,1,2,1,2});
    }

    /**
     * 3数和的概念？？
     * <p>
     *     基本的思路是什么？有
     *     【-1,0,1,2,-1,-4】
     *
     * </p>
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        return null;
    }
}
