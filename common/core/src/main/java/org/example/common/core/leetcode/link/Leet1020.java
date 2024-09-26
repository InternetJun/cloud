package org.example.common.core.leetcode.link;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/10/20 15:05
 */
@Slf4j
public class Leet1020 {


    public ListNode rotateN() {
        return null;
    }

    public static void main(String[] args) {
        final ArrayList<Integer> list = new ArrayList<>();
        final Integer item = new Integer(1);
        list.remove(item);

    }

    @Test
    public void testDu() {
        int[] nums = {0,0,1,1,1,1,2,3,3};
        System.out.println(removeDuplicatesSolution(nums));
    }

    /**
     * 对于数组来说，要求保留2个元素，不要多留。
     *
     * @param nums
     * @return
     */
    public int removeDuplicatesSolution(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        int slow = 2, fast = 2;
        while (fast < n) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }
}
