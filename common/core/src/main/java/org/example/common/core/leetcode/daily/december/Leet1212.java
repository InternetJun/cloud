package org.example.common.core.leetcode.daily.december;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description: 1~1000的落差，然后进行回溯。
 * @time: 2023/12/11 10:50
 */
@Slf4j
public class Leet1212 {
    public int[] secondGreaterElement(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return new int[]{-1};
        }
        final int[] result = new int[len];
        Arrays.fill(result, result.length - 2, result.length, -1);
        deal(result, nums);
        return result;
    }

    /**
     * <p>
     * j > i
     * nums[j] > nums[i]
     * 恰好存在 一个 k 满足 i < k < j 且 nums[k] > nums[i] 。
     * 如果不存在 nums[j] ，那么第二大整数为 -1 。
     * </p>
     *
     * @param res
     * @param nums
     */
    public void deal(int[] res, int[] nums) {
        for (int i = 0; i <= nums.length - 3; i++) {
            int temp = nums[i];
            int count = 0, j = i + 1;

            while (j < nums.length && count < 2) {
                if (nums[j] > temp) {
                    ++count;
                }
                j++;
            }
            log.info("j:{}", j);
            // 如果没有第二大整数，将结果置为 -1
            res[i] = count == 2 ? nums[j - 1] : -1;
        }
    }

    @Test
    public void main() {
        int[] nums = {2, 4, 0, 9, 6};
        System.out.println(Arrays.toString(secondGreaterElementSolution(nums)));
    }

    public int[] secondGreaterElementSolution(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new ArrayDeque<Integer>();
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
//        log.info("pq的元素是：{}", pq);
        for (int i = 0; i < nums.length; ++i) {
            while (!pq.isEmpty() && pq.peek()[0] < nums[i]) {
                res[pq.poll()[1]] = nums[i];
            }
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                // nums[stack.peek()], stack.peek()有什么区别？
                pq.offer(new int[]{nums[stack.peek()], stack.peek()});
                log.info("{}", pq);
                stack.pop();
            }
            stack.push(i);
        }
        return res;
    }
}
