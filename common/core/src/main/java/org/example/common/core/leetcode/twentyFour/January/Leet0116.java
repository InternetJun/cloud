package org.example.common.core.leetcode.twentyFour.January;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
     * 788. 旋转数字（题解）
     * 902. 最大为 N 的数字组合（题解）1990
     * 233. 数字 1 的个数（题解）
     * 面试题 17.06. 2 出现的次数（题解）
     * 600. 不含连续 1 的非负整数（题解）
     * 2376. 统计特殊整数（题解）2120
     * 1012. 至少有 1 位重复的数字（题解）2230
     * 357. 统计各位数字都不同的数字个数
     * 2999. 统计强大整数的数目
     * 2827. 范围中美丽整数的数目 2324
     * 2801. 统计范围内的步进数字数目 2367
     * 1397. 找到所有好字符串 2667
     * 1215. 步进数（会员题）1675
     * 1067. 范围内的数字计数（会员题）2025
     * 1742. 盒子中小球的最大数量 *请使用非暴力做法解决
     */

    static int[] check = {0,0,1,-1,-1,1,1,-1,0,1};
    int[][][] memo = new int[5][2][2];
    List<Integer> digits =  new ArrayList<Integer>();
    public int rotateDigit(int num) {
        while (num != 0) {
            digits.add(num % 10);
            num /= 10;
        }
        Collections.reverse(digits);

        return 0;
    }

    @Test
    public void testLessN() {
        String[] digit = {"1","3","5","7"};
        atMostNGivenDigitSet(digit, 100);
    }

    /**
     * <href>902</href>
     * <p>
     *     1234   1==> (012)
     *     0==> (0~9)
     *
     *     dfs要的是说i == chars[i] ? chars[i] : 9;
     *     123  和 1123 ==>
     *     <b>数位dp的模板数据</b>
     * </p>
     *
     * @param digits
     * @param n
     * @return
     */
    public int atMostNGivenDigitSet(String[] digits, int n) {
        this.digit = digits;
        s = Integer.toString(n).toCharArray();
        dp = new int[s.length];
        Arrays.fill(dp, -1);
        return f(0, true, false);
    }

    /**
     * isLimit 是来判断出可选0~9 或者234的。
     *
     * @param i
     * @param isLimit
     * @param isNum
     * @return
     */
    private int f(int i, boolean isLimit, boolean isNum) {
        if (i == s.length){
            return isNum ? 1 : 0;
        }
        // 原因是什么？ 不限制；是数字；大于0； isNum的意义是什么？
        //
        if (!isLimit && isNum && dp[i] > 0) {
            return dp[i];
        }
        int res = 0;
        if (!isNum) {
            // 前面不填数字，那么可以跳过当前数位，也不填数字
            // isLimit 改为 false，因为没有填数字，位数都比 n 要短，自然不会受到 n 的约束
            // isNum 仍然为 false，因为没有填任何数字
            res = f(i+1, false, false);
        }
        char up = isLimit ? s[i] : '9';
        for (String d : digit) {
            if (d.charAt(0) > up) {
                break;
            }
            res += f(i+1, isLimit && d.charAt(0) == up, true);
        }
        if (!isLimit && isNum) {
            // 在不受到任何约束的情况下，记录结果
            dp[i] = res;
        }
        return res;
    }

    private String[] digit;
    private char[] s;
    private int[] dp;

    /**
     * 1001 ==> 1000
     * 1000 ==> 099
     *
     * @param num
     * @return
     */
    public String sub0118(String num) {
        int len = num.length();
        final char[] chars = num.toCharArray();
        int i = len - 1;
        while (num.charAt(i) == '0') {
            i--;
        }
        chars[i]--;
        i++;
        while (i < len) {
            chars[i++] = '9';
        }
        return new String(chars);
    }

    /**
     * 1~n中1出现的个数。数位dp。
     * 123
     * 100
     * 101
     * 111
     * 01 011 001.....
     *
     * @param n
     * @return
     */
//    public int oneAppear(int n) {
//
////    }
//
//    /**
//     * for_i : n:
//     *  if (i == 1) {
//     *      count++;
//     *  }
//     *
//     * @param i
//     * @param isLimit
//     * @return
//     */
//    public int dfsOneAppear(int i, boolean isLimit) {
//
//    }

}
