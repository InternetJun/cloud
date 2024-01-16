package org.example.common.core.leetcode.twentyFour.January;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description: 数位的dp，数字的数字和。
 * @time: 2024/1/16 10:55
 */
public class Leet0116 {
    // 最大的长度
    static final int N = 23;
    // 最大的和
    static final int M = 401;
    static final int MOD = 1000000007;
    int[][] d;
    String num;
    int min_sum;
    int max_sum;

    public int count(String num1, String num2, int min_sum, int max_sum) {
        // dp
        d = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(d[i], -1);
        }
        this.min_sum = min_sum;
        this.max_sum = max_sum;
        return (get(num2) - get(sub(num1)) + MOD) % MOD;
    }

    public int get(String num) {
        // 1000 ==> 0001
        this.num = new StringBuffer(num).reverse().toString();
        // 理由是什么？ ans_{l,r} = ans_[o,r] - ans_[0,l-1]
        return dfs(num.length() - 1, 0, true);
    }

    /**
     * 求解 num - 1，先把最后一个非 0 字符减去 1，再把后面的 0 字符变为 9
     * @param num
     * @return
     */
    public String sub(String num) {
        char[] arr = num.toCharArray();
        int i = arr.length - 1;
        while (arr[i] == '0') {
            i--;
        }
        arr[i]--;
        i++;
        while (i < arr.length) {
            arr[i] = '9';
            i++;
        }
        return new String(arr);
    }

    @Test
    public void testGet() {
        System.out.println(sub("1001"));
    }

    /**
     * <p>
     *     主要几个关键点吧
     *     12345 高位位0，后一位0~9；高位为1，后一位为0~2
     *
     * </p>
     *
     * @param i num's length
     * @param j 记录值。
     * @param limit
     * @return
     */
    public int dfs(int i, int j, boolean limit) {
        //
        if (j > max_sum) {
            return 0;
        }
        if (i == -1) {
            return j >= min_sum ? 1 : 0;
        }
        if (!limit && d[i][j] != -1) {
            return d[i][j];
        }
        int res = 0;
        // 值位false，up = 9?
        int up = limit ? num.charAt(i) - '0' : 9;
        for (int x = 0; x <= up; x++) {
            // limit && x == up = limit
            res = (res + dfs(i - 1, j + x, limit && x == up)) % MOD;
        }
        if (!limit) {
            d[i][j] = res;
        }
        return res;
    }

    /**
     * <p>
     * 输入：num1 = "1", num2 = "12", min_num = 1, max_num = 8
     * 输出：11
     * 解释：总共有 11 个整数的数位和在 1 到 8 之间，分别是 1,2,3,4,5,6,7,8,10,11 和 12 。所以我们返回 11 。
     *
     * 位数之和的计算是要用什么？
     * 11 = 3
     * 100 = 4
     * 111 = 7
     *
     * 13 ==> 3 + 1 = 4;
     * </p>
     *
     * @param num1
     * @param num2
     * @param minNum
     * @param maxNum
     * @return
     */
    public int count(String num1, String num2, int minNum, String maxNum) {
        int res = 0;
        int mod = 10^9 + 7;

        return res % mod;
    }

    public int countByDigit(String num) {
        for (char c : num.toCharArray()) {
            int item = (int) c - '0';
            // 11 + 100 = 111
        }
        return 0;
    }

    @Test
    public void main() {
        // 3 4 5 6 ... 9 10
        System.out.println(3 ^ 4);
    }

    /**
     * 1.给定两个正整数 a,b，求在 [a,b] 中的所有整数中，每个数码（digit）各出现了多少次。
     * 2.统计一个区间内数位上不能有 4 也不能有连续的 62 的数有多少。
     * 3.给定一个区间 [l,r]，求其中满足条件不含前导 0且相邻两个数字相差至少为 2的数字个数。
     */

}
