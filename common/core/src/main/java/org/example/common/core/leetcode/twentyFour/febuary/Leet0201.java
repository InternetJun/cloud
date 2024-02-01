package org.example.common.core.leetcode.twentyFour.febuary;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

@Slf4j
public class Leet0201 {
    /**
     * <p>
     * 需要整成递增的序列 满足 a[n] + 1 = a[n+1]
     * 所以有记录前一个就好了。即有dp的定义为dp[0] = 0
     * dp_{i+1} = dp_{i} + f(x);
     * i = 3，将 [3,4,5,1] 操作成 [3,4,5,6], 最少 5 次操作；
     * i = 4，将 [3,4,5,1,6] 操作成 [3,4,5,6,7], 最少 6 次操作；
     * </p>
     *
     * @param nums
     * @return res 结果数组数据。
     */
    public int[] numsGame(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, 0);
        int mod = 1000000007;
        int dp = 0;
        int initial = nums[0];
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            if (num == initial + 1) {
                initial = num + 1;
                res[i] = dp % mod;
            } else {
                log.info("num的值是：{}", initial);
                int operate = Math.abs(num - initial) + 1;
                log.info("要处理的值是：{}", operate);
                // dp要表示出之前所有的值，而不是说上一个的值啊。
                dp += operate % mod;
                initial++;
                res[i] = dp + res[i - 1];
            }
        }
        return res;
    }

    @Test
    public void main() {
        log.info("=========2024年2月1日10:14:33测试开始==============");
        int[] nums = {3, 4, 5, 1, 6, 7};
        int[] ints = numsGame(nums);
        System.out.println(Arrays.toString(ints));
        log.info("=========2024年2月1日10:14:43测试开始==============");
    }

    public int[] numsGameSolution(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        PriorityQueue<Integer> lower = new PriorityQueue<Integer>((a, b) -> b - a);
        PriorityQueue<Integer> upper = new PriorityQueue<Integer>((a, b) -> a - b);
        final int MOD = 1000000007;
        long lowerSum = 0, upperSum = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[i] - i;
            if (lower.isEmpty() || lower.peek() >= x) {
                lowerSum += x;
                lower.offer(x);
                if (lower.size() > upper.size() + 1) {
                    upperSum += lower.peek();
                    upper.offer(lower.peek());
                    lowerSum -= lower.peek();
                    lower.poll();
                }
            } else {
                upperSum += x;
                upper.offer(x);
                // 要的是lower的size的值是比upper值多一个的。
                if (lower.size() < upper.size()) {
                    lowerSum += upper.peek();
                    lower.offer(upper.peek());
                    upperSum -= upper.peek();
                    upper.poll();
                }
            }
            if ((i + 1) % 2 == 0) {
                res[i] = (int) ((upperSum - lowerSum) % MOD);
            } else {
                res[i] = (int) ((upperSum - lowerSum + lower.peek()) % MOD);
            }
        }
        return res;
    }
}
