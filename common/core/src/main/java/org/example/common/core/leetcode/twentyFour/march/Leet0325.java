package org.example.common.core.leetcode.twentyFour.march;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:
 * @time: 2024/3/25 11:23
 */
@Slf4j
public class Leet0325 {
    /**
     * 最长回文字串。i 开始；
     * i, i+1 ==>
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        return "";
    }

    /**
     * 对一个字符串是否可以删除一个字符形成
     *
     * @return
     */
    public boolean palindromeDeleteOne() {
        return false;
    }

    public int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public int caculate(int n) {
        int lcm = 1;
        for (int i = 1; i <= n; i++) {
            lcm = lcm * i / gcd(lcm, i);
        }
        return lcm;
    }

    /**
     * 转移的方程是什么？
     * g_{i,j} = g_{i+1, j-1} && s[i] == s[j]
     *
     * @param s
     * @return
     */
    public int minCut(String s) {
        int n = s.length();
        boolean[][] g = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(g[i], true);
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = s.charAt(i) == s.charAt(j) && g[i + 1][j - 1];
            }
        }

        //
        final int[] f = new int[n];
        Arrays.fill(f, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            if (g[0][i]) {
                f[i] = 0;
            } else {
                for (int j = 0; j < i; j++) {
                    //设 g(i,j) 表示 s[i..j]是否为回文串，那么有状态转移方程：
                    if (g[j + 1][i]) {
                        f[i] = Math.min(f[i], f[j] + 1);
                    }
                }
            }
        }
        return f[n - 1];
    }

    @Test
    public void testS() {
        System.out.println(partition("aab"));
    }

    public boolean[][] f;
    public List<List<String>> res = new ArrayList<>();
    public List<List<String>> item = new ArrayList<>();
    int n;

    public List<List<String>> partition(String s) {
//        int len = s.length();
//        f = new boolean[len][len];
//        for (int i = 0; i < len; i++) {
//            Arrays.fill(f[i], true);
//        }
////        log.info("{}", f);
//        for (int i = len - 1; i >= 0; --i) {
//            for (int j = i + 1; j < len; ++j) {
//                // s[i] == s[j] ==>
//                f[i][j] = s.charAt(i) == s.charAt(j) && f[i + 1][j - 1];
//            }
//        }
        n = s.length();
        f = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(f[i], true);
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            }
        }
        log.info("{}", f);
        return res;
    }
}
