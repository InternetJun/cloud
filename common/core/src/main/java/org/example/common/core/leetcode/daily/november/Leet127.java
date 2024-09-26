package org.example.common.core.leetcode.daily.november;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/26 11:04
 */
@Slf4j
public class Leet127 {
    private int count = 0;

    /**
     * 获取到每个字符的唯一性。
     * <p>
     * 输入: s = "ABC"
     * 输出: 10
     * 解释: 所有可能的子串为："A","B","C","AB","BC" 和 "ABC"。
     * 其中，每一个子串都由独特字符构成。
     * 所以其长度总和为：1 + 1 + 1 + 2 + 2 + 3 = 10
     * </p>
     * <p>
     * A        B           c
     * ______   ______  ______
     * A | 1   |  1  |   1   |
     * B |    |  1  |  1    |
     * C |    |    |  1    |
     * <p>
     * A        B        A
     * ______   ______  ______
     * A | 1   |  1  |   0   |
     * B |    |  1  |  1    |
     * A |    |    |  1    |
     * 要怎么计算呢？有的是     *
     * </p>
     * <p>
     * <p>
     * A_{3}^{3} = 3* 2*1 = 6; ==> "A","AB","ABC"。
     * A_{2}^{2} = 2 * 1 = 2;==> "B","BC"
     * 最后是1 ==>  "C"
     * <p>
     * 题要的是 1 +1 +1  +2+2 +3 = 10；
     * </p>
     * <p>
     * <p>
     * ABA ==> a b AB BA A = 7
     * ab ==> 3
     * ba ==> 3
     * a ==> 1
     * </p>
     *
     * @param s
     * @return
     */
    public int uniqueLetterStringMe(String s) {
        int n = s.length();
        // 记录从0 ~ n可以最大的长度。
        final int[] res = new int[n];
        // 获取从i开头的最长的长度;

        for (int i = 0; i < n; i++) {
            count += sumLen(res[i]);
        }
        return count;
    }

    /**
     * 给定的len ==> 可以合成的长度
     *
     * @param len
     * @return
     */
    public int sumLen(int len) {
        return (len + 1) * len / 2;
    }

    public int uniqueLetterString(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length, ans = 0;
        int[] l = new int[n], r = new int[n];
        int[] idx = new int[26];
        Arrays.fill(idx, -1);
        for (int i = 0; i < n; i++) {
            int u = cs[i] - 'A';
            l[i] = idx[u];
            idx[u] = i;
        }
        Arrays.fill(idx, n);
        for (int i = n - 1; i >= 0; i--) {
            int u = cs[i] - 'A';
            r[i] = idx[u];
            idx[u] = i;
        }
        for (int i = 0; i < n; i++) {
            int ans1 = (i - l[i]) * (r[i] - i);
            log.info("{}", ans1);
            ans += ans1;
        }
        return ans;
    }

    @Test
    public void main() {
        String s = "ABC";
        uniqueLetterString(s);
    }

    /**
     * 获取最长满足要求的数组。
     *
     * @param nums
     * @param threshold
     * @return
     */
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 0 && nums[i] <= threshold) {
                int start = i + 1;
                int value = nums[i];
//                log.info("开始的值：{}", nums[i]);
                while (start < n && nums[start] % 2!= value%2 && nums[start] <= threshold) {
                    value = nums[start];
                    start++;
                }
                // 比较start ~ i
                count = Math.max(count, start - i);
                log.info("{}", count);
            }
        }
        return count;
    }

    @Test
    public void test() {
        int[] num = {2,4};
        int threshold = 7;
        System.out.println(longestAlternatingSubarray(num, threshold));
    }

    public int longestAlternatingSubarrayS(int[] nums, int threshold) {
        int n = nums.length, ans = 0, i = 0;
        while (i < n) {
            if ((nums[i] % 2 != 0 || nums[i] > threshold) && ++i >= 0) continue;
            int j = i + 1, cur = nums[i] % 2;
            while (j < n) {
                if (nums[j] > threshold || nums[j] % 2 == cur) break;
                cur = nums[j++] % 2;
            }
            ans = Math.max(ans, j - i);
            i = j;
        }
        return ans;
    }
}
