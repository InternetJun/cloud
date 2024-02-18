package org.example.common.core.leetcode.daily.december;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:
 * @time: 2023/12/9 15:31
 */
@Slf4j
public class Leet1209 {
    /**
     * 1 --> 22
     * 1000 --> 1333  (1022错误的)
     *
     * @param n
     * @return
     */
    public int minBeautifulNum(int n) {
        String s = n + "";
        final int length = s.length();
        if (length == 1) {
            // 9 --> 22
            return 22;
        }
        dfs(n, length, 0);
        return min;
    }

    private int min = Integer.MAX_VALUE;

    public void dfs(int n, int len, int num) {
        min = Math.min(min, num);
        //什么条件可以跳出来？32 ==> 122   12 ==> 22 100 =>> 122  200 221
        for (int i = 1; i <= 9; i++) {

        }
    }

    /**
     * 1~n生成的所有的数值。
     * <p>
     * 1 22 333 4444
     * 122 121 221 212
     * <p>
     * 1~10^6 155555
     * </p>
     *
     * @return
     */
    public static List<Integer> generateCombinations(int n) {
        List<Integer> result = new ArrayList<>();

        int[] nums = new int[n];
        for (int i = 1; i <= n; i++) {
            int temp = 0;
            int fixNum = i, count = i;
            while (count > 0) {
                temp = temp * 10 + fixNum;
                count--;
            }
            log.info("{}", temp);
            nums[i - 1] = temp;
        }
        dfs(nums, result, n);
        return result;
    }

    public static void dfs(int[] nums, List<Integer> result, int maxLength) {
        for (int i = 0; i < nums.length; i++) {

        }
    }


    @Test
    public void main() {
        generateCombinations(5);
    }

    private static void generateCombinationsRecursively(List<String> result, int[] nums, String current, int maxLength) {
        if (current.length() > maxLength) {
            return;
        }

        if (current.length() > 0) {
            // 再次递归，获取所有的组合情况数据。
            result.add(current);
        }

        for (int number : nums) {
            generateCombinationsRecursively(result, nums, current + number, maxLength);
        }
    }

    /**
     * 此回溯获取了所有的组合。但是没有实现，任意位置的插入。
     *
     * @param numbers
     * @param current
     * @param result
     */
    private static void backtrack(List<String> numbers, String current, List<String> result) {
        if (numbers.isEmpty()) {
            result.add(current);
            return;
        }

        for (int i = 0; i < numbers.size(); i++) {
            List<String> newNumbers = new ArrayList<>(numbers);
            newNumbers.remove(i);
            backtrack(newNumbers, current + numbers.get(i), result);
        }
    }

    @Test
    public void testGenerate() {
        ArrayList<String> strings = new ArrayList<>();
        backtrack(Arrays.asList("1", "22", "333"), "", strings);
        System.out.println(strings);
    }

    static void work(int[] x) {
        for (int i = 0; i < x.length; i++) {
            for (int k = 0; k < x[i]; k++) {
                System.out.print(i);
            }
        }
        System.out.println();
    }

    /**
     * data: 不动, 限制条件
     * x: 取法
     * k: 当前考虑的位置
     * goal: 距离目标的剩余数量
     */
    static void f(int[] data, int[] x, int k, int goal) {
        if (k == x.length) {
            if (goal == 0) {
                work(x);
            }
            return;
        }

        for (int i = 0; i <= Math.min(data[k], goal); i++) {
            x[k] = i;
            f(data, x, k + 1, goal - i);
        }
        //回溯
        x[k] = 0;
    }

    @Test
    public void all() {
        int[] nums = {1, 22};
        int[] x = new int[nums.length];
        // AAABBCCCCDD
        f(nums, x, 0, 3);
    }

    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[n];
    }

    @Test
    public void testClimb() {
        int n = 3;
        System.out.println(climbStairs(3));
    }
}
