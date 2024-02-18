package org.example.common.core.leetcode.dp;

import java.util.Arrays;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 最长的公共子序列
 * @time: 2023/9/25 14:46
 */
public class LongSubList {
    /**
     * 最长的公共子序列
     * dp的定义是什么？ 1~m,1~n的最长
     * int[][] dp = new int[m][n];
     初始状态：dp[0][0] = ?
     转移方程：dp[i][j] =
     最后的结果
     * @param str1
     * @param str2
     * @return
     */
    public int longestSubArr(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        int dp1[] = new int[2];
        dp1[0] = 1;
        dp1[1] = 2;
        System.out.println(Arrays.toString(dp1));
    }

    public int longestCommonSubsequence(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        // 定义：s1[0..i-1] 和 s2[0..j-1] 的 lcs 长度为 dp[i][j]
        int[][] dp = new int[m + 1][n + 1];
        // 目标：s1[0..m-1] 和 s2[0..n-1] 的 lcs 长度，即 dp[m][n]
        // base case: dp[0][..] = dp[..][0] = 0

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 现在 i 和 j 从 1 开始，所以要减一
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    // s1[i-1] 和 s2[j-1] 必然在 lcs 中
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    // s1[i-1] 和 s2[j-1] 至少有一个不在 lcs 中
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return dp[m][n];
    }

    /**
     * 小堆
     */

    /**
     * 大堆
     */
    public void buildMaxHeap(int[] a, int heapSize) {
        for (int i = heapSize / 2; i >= 0; --i) {
            maxHeapify(a, i, heapSize);
        }
    }

    /**
     * 构建最大堆，思想是什么？
     *
     * @param a
     * @param i
     * @param heapSize
     */
    public void maxHeapify(int[] a, int i, int heapSize) {
        int l = i*2+1, r = i*2+2,  largest = i;
        if (l < heapSize && a[l] > a[largest]) {
            largest = l;
        }
        if (r < heapSize && a[r] > a[largest]) {
             largest = r;
        }
        if (largest != i) {
            swap(a, i, largest);
            maxHeapify(a, largest, heapSize);
        }
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
