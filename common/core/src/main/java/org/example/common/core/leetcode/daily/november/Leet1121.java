package org.example.common.core.leetcode.daily.november;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/21 14:13
 */
@Slf4j
public class Leet1121 {
    /**
     * <h1>美丽数组</h1>
     * <p>
     * 1.nums.length is even<br>
     * 2.even index的i,satify the condition: nums[i] != nums[i+1]<br>
     * 3.删除的元素有右侧的元素向左移动一个单位补上空缺。左侧的元素保持不变。
     * </p>
     *
     * <p>
     * 但是答案呢;
     * </p>
     *
     * @param nums
     * @return
     */
    public int minDeletion(int[] nums) {
        int len = nums.length, cnt = 0;
        // 同一个数最多不能操作2个的
        // 记录每一个元素出现的个数
        for (int i = 0; i < len; i++) {
            if ((i - cnt) % 2 == 0 && i + 1 < len && nums[i] == nums[i + 1]) {
                cnt++;
            }
        }
        return (len - cnt) % 2 != 0 ? cnt + 1 : cnt;
    }

    public int minDeletionSolution(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                int j = i + 1;
                while (j < nums.length && nums[j] == nums[i]) {
                    j++;
                }
                log.info("i:{};j:{}", i, j);
                res += j - i - 1;
                i = j;
            } else {
                i++;
            }
        }
        return ((nums.length - res) % 2) != 0 ? res + 1 : res;
    }

    @Test
    public void main() {
        int[] nums = {1,1,1,2,2,3,3};
        minDeletionSolution(nums);
    }
}
