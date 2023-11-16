package org.example.common.core.leetcode.daily;

import org.junit.Test;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/16 10:41
 */
public class Leet1116 {

    /**
     * alternate odd and even num and all num less than threshold
     * first num is even
     * 自己的分组能力偏弱，难以处理这样的问题。
     *
     * @param nums
     * @param threshold
     * @return
     */
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int len = nums.length;
        int res = 0;
        int right = 0;
        while (right < len) {
            int cnt = 0;
            while (right < len && nums[right] % 2 != 0) {
                right++;
            }

            // 有一个元素的值满足threshold条件就可以了，
            while (right + 1 < len &&
                    (nums[right] % 2 != nums[right + 1] % 2)
            ) {
                if (nums[right] <= threshold && nums[right+1]<= threshold) {
                    cnt += 2;
                    right += 2;
                } else if (nums[right] <= threshold) {
                    cnt++;
                    right++;
                }
            }
            // 特殊情况，是到最后一个元素了。不要满足情况的。
            if (right == len -1 && nums[right] <= threshold) {
                cnt++;
            }
            // 这里的元素要怎么处理?
            right++;
            res = Math.max(res, cnt);
        }

        return res;
    }

    @Test
    public void main() {
        int[][] nums = {
                {3,2,5,4},
                {1,2},
                {2,3,4,4,4}
        };
        int[] threshold = {5,2,4};
        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length-1) {
                System.out.println(longestAlternatingSubarray(nums[i], threshold[i]));
            }
        }
    }


    /**
     * <p>
     * n = len(nums)
     * i = 0
     * while i < n:
     *     start = i
     *     while i < n and ...:
     *         i += 1
     *     # 从 start 到 i-1 是一组
     *     # 下一组从 i 开始，无需 i += 1
     * </p>
     *
     * @param nums
     * @param threshold
     * @return
     */
    public int longestAlternatingSubarrayS(int[] nums, int threshold) {
        int n = nums.length;
        int ans = 0, i = 0;
        while (i < n) {
            if (nums[i]>threshold || nums[i] % 2!=0) {
                i++;
                continue;
            }
            int start = i;
            // 开始位置已经满足
            i++;
            while (i < n && nums[i] <= threshold
                    && nums[i] % 2 != nums[i - 1] % 2) {
                i++;
            }
            // start 到 i -1
            ans = Math.max(ans, i - start);
        }
        return ans;
    }

    // 这样的题目要多联系啊。


}
