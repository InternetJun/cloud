package org.example.common.core.leetcode.daily.december;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description: 美丽塔
 * @time: 2023/12/21 11:03
 */
@Slf4j
public class Leet1221 {
    /**
     * i开始满足条件：
     * 1，高峰
     * 2，i的值比以后的元素都高。
     * [6,5,3,9,2,7] --> [3,3,3,9,2,2] = 22 sum(nums)
     * [5,3,4,1,1] --> [5,3,3,1,1]  = 13  sum(nums)
     * 左边一个递增，右边一个递减的。</br>
     * <b>要在所有的组合中获取最大的值。</b>
     * max(n*maxHeight, maxHeight + n*left + n*right)
     * <p>
     * 左边的是prefix[j] + (i-j) * maxheight
     * 右边的是类似的。
     * max(pref + suffix - maxHeight)
     *
     * @param maxHeights
     * @return
     */
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();
        long res = 0;
        long[] prefix = new long[n];
        long[] suffix = new long[n];
        Deque<Integer> stack1 = new ArrayDeque<Integer>();
        Deque<Integer> stack2 = new ArrayDeque<Integer>();
        for (int i = 0; i < n; i++) {
            // 因为有左边的元素是递增的。 （2）5 （3）6 maxHeight=4；
            while (!stack1.isEmpty() && maxHeights.get(i) < maxHeights.get(stack1.peek())) {
                stack1.pop();
            }
            if (stack1.isEmpty()) {
                prefix[i] = (long) (i + 1) * maxHeights.get(i);
            } else {
                // 此时在栈顶元素都比height大。
                prefix[i] = prefix[stack1.peek()] + (long) (i - stack1.peek()) * maxHeights.get(i);
            }
            stack1.push(i);
        }
        log.info("{}", prefix);
        for (int i = n - 1; i >= 0; i--) {
            while (!stack2.isEmpty() && maxHeights.get(i) < maxHeights.get(stack2.peek())) {
                stack2.pop();
            }
            if (stack2.isEmpty()) {
                // 包含自己 +1 6 -- 4 （4,5,6） ==》
                suffix[i] = (long) (n - i) * maxHeights.get(i);
            } else {
                suffix[i] = suffix[stack2.peek()] + (long) (stack2.peek() - i) * maxHeights.get(i);
            }
            stack2.push(i);
            res = Math.max(res, prefix[i] + suffix[i] - maxHeights.get(i));
        }
        log.info("suffix：{}", suffix);
        return res;
    }

    @Test
    public void max() {
        List<Integer> nums = Arrays.asList(6, 5, 3, 9, 2, 7);
        System.out.println(maximumSumOfHeights(nums));
    }
}
