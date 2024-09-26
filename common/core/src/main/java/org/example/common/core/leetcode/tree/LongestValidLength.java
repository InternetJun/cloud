package org.example.common.core.leetcode.tree;

import org.junit.Test;

import java.util.Stack;

/**
 * @Author: lejun
 * @project: cloud
 * @description:数据流中的中位数
 * @time: 2023/9/14 15:48
 */
public class LongestValidLength {
    /**
     * 小顶堆：小在上；
     * 大顶堆：小在下；
     * 原生的代码要怎么写？
     */


    public int longestValidParentheses(String s) {
        Stack<Integer> stk = new Stack<>();
        // dp[i] 的定义：记录以 s[i-1] 结尾的最长合法括号子串长度
        int[] dp = new int[s.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                // 遇到左括号，记录索引
                stk.push(i);
                // 左括号不可能是合法括号子串的结尾
                dp[i + 1] = 0;
            } else {
                // 遇到右括号
                if (!stk.isEmpty()) {
                    // 配对的左括号对应索引
                    int leftIndex = stk.pop();
                    // 以这个右括号结尾的最长子串长度
                    int len = 1 + i - leftIndex + dp[leftIndex];
                    dp[i + 1] = len;
                } else {
                    // 没有配对的左括号
                    dp[i + 1] = 0;
                }
            }
        }
        // 计算最长子串的长度
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    @Test
    public void main() {
        String s = ")()())";
        System.out.println(longestValidParentheses(s));
    }
}
