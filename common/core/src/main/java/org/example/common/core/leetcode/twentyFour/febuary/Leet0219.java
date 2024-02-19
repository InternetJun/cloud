package org.example.common.core.leetcode.twentyFour.febuary;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:
 * @time: 2024/2/19 14:03
 */
@Slf4j
public class Leet0219 {
    /**
     * 最长不重复的字串
     *
     * @param s
     * @return
     */
    public String repeatString(String s) {
        int len = s.length();
        int left = 0;
        int maxLen = Integer.MIN_VALUE;
        int preMax = -1;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < len; i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) +1);
            }
            map.put(s.charAt(i), i);
            // 记录下最长的。
            maxLen = Math.max(maxLen, i - left+1);
            preMax = maxLen;
            if (preMax <= maxLen) {

            }
            log.info("left:{},maxLen:{}", left, maxLen);
        }
        return s.substring(left, maxLen);
//        while (right < len) {
//            char c = s.charAt(right);
//            map.put(c, map.getOrDefault(c, 0) + 1);
//            right++;
//            while (map.get(c) > 1) {
//                char d = s.charAt(left);
//                if (map.get(d) > 1) {
//                    map.put(d, map.get(d) - 1);
//                }
//                left++;
//            }
//            // left = 4; right = 5;
//            maxLen = Math.max(maxLen, right - left);
//        }
//        return s.substring(left, maxLen);
    }

    @Test
    public void main() {
        String s = "abcdd";
        System.out.println(repeatString(s));
    }

    public static String longestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        HashMap<Character, Integer> charIndex = new HashMap<>();
        int maxLength = 0;
        int start = 0;
        int startIndex = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (charIndex.containsKey(c) && charIndex.get(c) >= start) {
                start = charIndex.get(c) + 1;
            } else {
                //i - start +1 > maxLength ==> 就是有可以记录的。
                if (i - start + 1 > maxLength) {
                    maxLength = i - start + 1;
                    startIndex = start;
                }
            }
            charIndex.put(c, i);
        }

        return s.substring(startIndex, startIndex + maxLength);
    }

    public static void main(String[] args) {
        String s1 = "abcabcbb";
        System.out.println(longestSubstring(s1)); // Output: "abc"

        String s2 = "bbbbb";
        System.out.println(longestSubstring(s2)); // Output: "b"

        String s3 = "pwwkew";
        System.out.println(longestSubstring(s3)); // Output: "wke"
    }
}
