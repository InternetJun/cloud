package org.example.common.core.leetcode.twentyFour.January;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:
 * @time: 2024/1/11 11:54
 */
@Slf4j
public class Leet0111 {
    /**
     * 是以。
     * aaa --> abc abc abc
     * <p>
     * abc 最长的。举例来说呢？
     * 1， a开头
     * ab、ac ind + 2
     * 2，b bc ba   ind+2
     * 3，c ca cb ind+1开始。
     * acb abb
     * <b>abc需要跳过。</b>
     * </p>
     *
     * <p>
     * 对数据的有效切割。
     * <p>
     * 1，aabcb aabcc
     * 2，aaa
     * 3，abb
     * 一共几种情况。<br/>
     * 1,1\2\3
     * 定义一个dp。定义是什么？
     * i为结尾的进行一个判断？
     * a开头：i+2
     * 不是a开头。i+1;
     * <p>
     * dp(i+1) = dp(
     *
     * </p>
     * <p>
     * 双指针呢？
     *
     * </p>
     *
     * @param word
     * @return
     */
    public int addMinimum(String word) {
        int min = 0;
//        if (isValid(word)) {
//            return 0;
//        }
        // 思路是什么，要怎么添加？
        int len = word.length();
        final StringBuilder sb = new StringBuilder(word);
        int right = 0;
        while (right < len) {
            char c = word.charAt(right);
            // 后序是abc 直接跳过
            if (right <= len - 3 && c == 'a' &&
                    word.charAt(right + 1) == 'b' &&
                    word.charAt(right + 2) == 'c'
            ) {
                right += 3;
                continue;
            }
            // 最多是2个。
            if (c == 'a') {
                if (right < len - 1 && word.charAt(right + 1) == 'b') {
                    right++;
                    min += 1;
                } else {
                    min += 2;
                }
            }
            // 2个
            else if (c == 'b') {
                if (right < len - 1 && word.charAt(right + 1) == 'c') {
                    min += 1;
                    right++;
                } else {
                    min += 2;
                }
            } else {
                min += 2;
            }
            right++;
        }
        return min;
    }

    /**
     * <b>我是想不到啊。</b>
     *
     * @param word
     * @return
     */
    public int addMinimumSolution(String word) {
        // dp
        int len = word.length();
        int[] dp = new int[len];
        dp[0] = 0;
        for (int i = 1; i < len; i++) {
            dp[i] = dp[i - 1] + 2;
            // larger 就是一个
            if (i - 1 > 0 && word.charAt(i) > word.charAt(i - 1)) {
                dp[i] = dp[i - 1] - 1;
            }
        }
        return dp[len];
    }

    @Test
    public void min() {
        String s = "abc";
        System.out.println(addMinimum(s));
    }

    public boolean isValid(String ss) {
        String pattern = "(abc)+";
        final Pattern compile = Pattern.compile(pattern);
        final Matcher matcher = compile.matcher(ss);
        while (matcher.find()) {
            final String group = matcher.group(0);
            if (group.length() == ss.length()) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void main() {
        String s = "abcabc";
        String s1 = "abcabcd";
        System.out.println(isValid(s1));
        System.out.println(isValid(s));
    }

    /**
     * 删除AB CD直到没有这两个字符为止
     *
     * @param s
     * @return
     */
    public int minLength(String s) {
        final StringBuilder sb = new StringBuilder(s);
        while (sb.indexOf("AB") > -1 || sb.indexOf("CD") > -1) {
            int startA = sb.indexOf("AB");
            if (startA != -1) {
                sb.delete(startA, startA + 2);
            }
            int startC = sb.indexOf("CD");
            if (startC != -1) {
                sb.delete(startC, startC + 2);
            }
        }
        log.info(sb.toString());
        return sb.length();
    }

    @Test
    public void testMin() {
        System.out.println(minLength("ABFCACDB"));
    }

    public int minLengthSolution(String s) {
        List<Character> stack = new ArrayList<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            stack.add(c);
            int m = stack.size();
            if (m >= 2 &&
                    (stack.get(m - 2) == 'A' && stack.get(m - 1) == 'B' ||
                            stack.get(m - 2) == 'C' && stack.get(m - 1) == 'D')) {
                stack.remove(m - 1);
                stack.remove(m - 2);
            }
        }
        return stack.size();
    }

}
