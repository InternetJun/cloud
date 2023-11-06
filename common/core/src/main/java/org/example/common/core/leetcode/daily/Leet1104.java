package org.example.common.core.leetcode.daily;

import lombok.extern.slf4j.Slf4j;
import org.example.common.core.util.CommonUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 回文序列、字串
 * @time: 2023/11/4 16:58
 */
@Slf4j
public class Leet1104 {

    /**
     * 寻找回文
     * <p>
     *   有那个偶数和奇数的区别？
     *
     * </p>
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            // 以 s[i] 为中心的最长回文子串
            String s1 = palindrome(s, i, i);
            // 以 s[i] 和 s[i+1] 为中心的最长回文子串
            String s2 = palindrome(s, i, i + 1);
            // res = longest(res, s1, s2)
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }
        return res;
    }

    String palindrome(String s, int l, int r) {
        // 防止索引越界
        while (l >= 0 && r < s.length()
                && s.charAt(l) == s.charAt(r)) {
            // 向两边展开
            l--;
            r++;
        }
        // 返回以 s[l] 和 s[r] 为中心的最长回文串
        return s.substring(l + 1, r);
    }

    /**
     * 检测是否是一个回文字符串
     *
     * @param s
     * @return
     */
    public boolean checkValid(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char start = s.charAt(i);
            char end = s.charAt(len - 1 - i);
            if (start != end) {
                return false;
            }
        }
        return true;
    }

    /**
     * 最长回文<b>子序列</b>
     *<p>
     *     利用的是一个dp。那其定义是什么？
     *     dp[i,j]代表的是i~j的最长长度。
     *     转移方程是是什么？
     *     if(s(i-1) == s(j+1)) {
     *         dp[i-1,j+1] = dp[i,j] + 2;
     *     }
     *     初始状态的是 dp(0,0) = 1;
     *     要是不等呢？就是一个判断的
     *     dp(i,j) = max()
     *     
     *</p>
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        // 特殊情况
        
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }
//        for (int i = 0; i < len; i++) {
//            for (int j = i; j >= 0; j--) {
        // 反着遍历保证正确的状态转移?为什么要这样的顺序呢？
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                    CommonUtil.formatAndDisplayArray(dp);
                    log.info("=======");
//                    log.info("",);
                }
            }
        }
        log.info("{}", Arrays.deepToString(dp));
        return dp[0][len-1];
    }

    @Test
    public void main() {
        String s = "bbbab";
        String s1 = "cbbd";
        System.out.println(longestPalindromeSubseq(s));
//        System.out.println(longestPalindromeSubseq(s1));
    }


}
