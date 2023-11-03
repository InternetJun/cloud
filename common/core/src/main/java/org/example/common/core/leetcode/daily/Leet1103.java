package org.example.common.core.leetcode.daily;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.omg.CORBA.WCharSeqHelper;

import java.util.HashMap;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 最长的无重复的字串长度和对应的滑动窗口
 * @time: 2023/11/3 14:16
 */
@Slf4j
public class Leet1103 {
    /**
     * abcabcbb
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int left = 0, right = 0, maxLen = 0;
        int[] index = new int[26];
        while (right < len) {
            int ind = s.charAt(right) - 'a';
            index[ind]++;
            // 左指针的移动
            while (index[ind] > 1) {
                // left不一定是c啊！！
                char d = s.charAt(left);
                index[d - 'a']--;
                left++;
            }
            maxLen = Math.max(right - left, maxLen);
            right++;
        }
        return maxLen;
    }

    @Test
    public void testDictionary() {
        //
        String s = "aabcbccacbbcbaaba";
        String test = "cmbchina";
        System.out.println(getLargestSubStr(test));
    }

    /**
     * 这个是序列；那子串要怎么做呢？
     *
     * @param strs
     * @return
     */
    public static String getLargestSubStr(String strs) {
        if (strs == null) {
            return null;
        }
        String largestSubStr = "";
        while (!strs.isEmpty()) {
            int length = strs.length();
            // 最大数临时变量
            int maxNum = -1;
            // 最大数下标临时变量
            int maxIndex = 0;
            for (int i = 0; i < length; i++) {
                if ((int) strs.charAt(i) > maxNum) {
                    maxNum = strs.charAt(i);
                    maxIndex = i;
                }
            }
            largestSubStr += strs.charAt(maxIndex);
            // 获取到了最大的字符了。
            strs = strs.substring(maxIndex + 1);
        }
        return largestSubStr;
    }

    /**
     * s字符串中是否包含了t
     * <p>
     * s1 = "ab"， s2 = "eid||ba||ooo"
     * 过程是什么？
     *
     * </p>
     *
     * @param t
     * @param s
     * @return
     */
    public boolean checkInclusion(String t, String s) {
        final HashMap<Character, Integer> need = new HashMap<>();
        final HashMap<Character, Integer> windows = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int right = 0, valid = 0, left = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            // windows需要有移动
            while (need.containsKey(c)) {
                windows.put(c, windows.getOrDefault(c, 0) + 1);
                if (windows.get(c).equals(need.get(c))) {
                    valid++;
                }
            }
            // 比较总数
            while (right - left >= t.length()) {
                if (valid == need.size()) {
                    return true;
                }
                char d = s.charAt(left);
                left++;
                // 进行一个收缩处理。
                if (need.containsKey(d)) {
                    // windows进行处理。
                    if (need.get(d).equals(windows.get(d))) {
                        valid--;
                    }
                    // 这里不会有问题?
                    windows.put(d, windows.getOrDefault(d, 0) - 1);
                }
            }
        }
        return false;
    }

}
