package org.example.common.core.leetcode.daily.november;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.omg.CORBA.WCharSeqHelper;

import java.util.*;

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

    /**
     * 获取到最多2个不同字符的最长字串
     * 1,d
     * 2,滑动窗口。
     *
     * @param s
     * @return
     */
    public String maxLenOfTwoChar1(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int n = s.length();
        char[] chars = s.toCharArray();
        /**
         * i,j符合要求的最长  dp假设有1~2个
         */
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
            }
        }
        // 要求要字串。
        return "";
    }

    /**
     * <p>
     * abbcccd
     * 左右指针都需要重置和处理。这样要怎么做？
     *
     * </p>
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, right = 0;
        //不同的字符数量
        int count = 0;
        int maxLength = 0;
        final HashMap<Integer, ArrayList<int[]>> memory = new HashMap<>();
        while (right < len) {
            //在map中获取该字符出现的次数(不存在则为0),次数+1
            int rightNumber = map.getOrDefault(chars[right], 0) + 1;
            //更新map
            map.put(chars[right], rightNumber);
            //第一次出现
            if (rightNumber == 1) {
                count++;
            }
            right++;
            if (count <= 2) {
                // 记录所有的结果。只要符合要求的都需要。
                if (right == len - 1) {
                    log.info("left:{},right:{}", left, right);
                }
                if (right == len - 2) {
                    log.info("left:{},right:{}, str:{}", left, right, s.substring(left, right));
                    maxLength = Math.max(maxLength, right - left);
                    log.info("a0a1b2c3c4c5d6最大的长度{}", maxLength);
                }
                maxLength = Math.max(maxLength, right - left);
                // 只有一个记录进来了。没有其他的？
                final ArrayList<int[]> item = memory.getOrDefault(maxLength, new ArrayList<>());
                item.add(new int[]{left, right});
                memory.put(maxLength, item);
            }
            while (count > 2) {
                //左侧移除一个字符，在map中获取该字符出现的次数-1
                int leftNumber = map.get(chars[left]) - 1;
                //更新map
                map.put(chars[left], leftNumber);
                if (leftNumber == 0) {
                    count--;
                }
                left++;
            }
        }
        // 记录下左右指针获取到字串？
        final ArrayList<int[]> ints = memory.get(maxLength);
        for (int[] anInt : ints) {
            String rs = s.substring(anInt[0], anInt[1] + 1);
            log.info("{}", rs);
        }
        return maxLength;
    }


    @Test
    public void testTwo() {
        String s = "aabcccd";
        System.out.println(findMaxTwoDistinctCharsSubstring(s));
    }

    public static List<String> findMaxTwoDistinctCharsSubstring(String s) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }

        int left = 0;
        int right = 0;
        int maxLen = 0;
        int distinctCount = 0;
        Map<Character, Integer> charCount = new HashMap<>();
        List<String> result = new ArrayList<>();

        while (right < s.length()) {
            char rightChar = s.charAt(right);
            charCount.put(rightChar, charCount.getOrDefault(rightChar, 0) + 1);

            while (charCount.size() > 2) {
                char leftChar = s.charAt(left);
                charCount.put(leftChar, charCount.get(leftChar) - 1);
                if (charCount.get(leftChar) == 0) {
                    charCount.remove(leftChar);
                }
                left++;
            }

            if (charCount.size() <= 2) {
                int currentLen = right - left + 1;
                if (currentLen > maxLen) {
                    maxLen = currentLen;
                    result.clear();
                    result.add(s.substring(left, right + 1));
                } else if (currentLen == maxLen) {
                    result.add(s.substring(left, right + 1));
                }
            }

            right++;
        }

        return result;
    }

    public List<String> findTwoChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int len = s.length();
        final ArrayList<String> list = new ArrayList<>();
        int left = 0, right = 0, maxLen = Integer.MIN_VALUE;
        while (right < len) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);
            right++;
            while (map.size() > 2) {
                char d = s.charAt(left);
                map.put(d, map.get(d) - 1);
                if (map.get(d) == 0) {
                    map.remove(d);
                }
                left++;
            }
            // 更新list列表，有maxLength和
            if (map.size() <= 2) {
                // 长度的计算是right - left + 1
                if (right - left > maxLen) {
                    list.clear();
                    list.add(s.substring(left, right + 1));
                    maxLen = right - left;
                } else if (right - left == maxLen) {
                    list.add(s.substring(left, right + 1));
                }
            }
        }
        return list;
    }

}
