package org.example.common.core.leetcode.daily.november;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/8 10:44
 */
@Slf4j
public class Leet1108 {
    /**
     * 最长的平衡字串 0、1平衡。
     *
     * @param s
     * @return
     */
    public int findTheLongestBalancedSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        String zeroMatch = "0+";
        String oneMatch = "1+";
        if (s.matches(zeroMatch) || s.matches(oneMatch)) {
            return 0;
        }
        // 正常处理获取最长的字串。
        int left = 0, right = 0;
        int maxLen = Integer.MIN_VALUE;
        final HashMap<Character, Integer> map = new HashMap<>();
        while (right < s.length()) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);
            right++;

            // 可能为空的情况
            if (map.getOrDefault("1", 0).equals(map.getOrDefault("0", -1))) {
                maxLen = Math.max(maxLen, right - left);
                // 在不同的时候会有一个处理？00001111;
//            while () {
//
//            }
            }
        }
        return maxLen;
    }

    @Test
    public void main() {
        // 获取到最后的位置
        String s = "0000111";
        String zeroMatch = "0+1?";
        String oneMatch = "1+";
        final Pattern compile = Pattern.compile(zeroMatch);
        final boolean b = compile.matcher(s).find();
        if (b) {
            final String group = compile.matcher(s).group();
            System.out.println(group);
        }
    }

    public boolean isValid(Map<Character, Integer> map) {
        if (map.getOrDefault("1", 0).equals(map.getOrDefault("0", -1))) {
            return true;
        }
        return false;
    }

    /**
     * 子字符串中 所有的 0 都在 1 之前 且其中 0 的数量等于 1 的数量
     *
     * @param s
     * @return
     */
    public int findTheLongestBalancedSubstringSolution(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        String zeroMatch = "0+";
        String oneMatch = "1+";
        if (s.matches(zeroMatch) || s.matches(oneMatch)) {
            return 0;
        }
        // 正常处理获取最长的字串。
        int countZero = 0, indexZero = 0, countOne = 0, indexOne = 0;
        int maxLen = Integer.MIN_VALUE;
        final HashMap<Character, Integer> mapZero = new HashMap<>();
        final HashMap<Character, Integer> mapOne = new HashMap<>();
        // 用的是dp的思想。在其中要是相同的话就处理。i~i-2, j~j+2,或者i-1，j+1 dp[i,j] i~j为一个有效的 初始化为？01、1100,0101、1010、0011
        int i = 0;
       while (i < s.length()) {
           char c = s.charAt(i);
           // 单独计算0、1个数，并且有断开的
           /**
            * <p>
            *     01000111 1 1 3 3并且有链接；
            *     00111 2 3
            *     说明的是前一个值才能进行
            *     map0 【1,2】
            *     map1【1,3】
            * </p>
            */
           if (indexOne == indexZero) {
               maxLen = Math.max(Math.min(countZero, countOne), maxLen);
               countOne = 0;
               countZero = 0;
           }
           // 什么情况下，index不用加一了。
           while (c == '0') {
               if (countZero == 0) {
                   indexZero++;
               }
               log.info("坐标0：{}；", i);
               countZero++;
               i++;
               c = s.charAt(i);
           }
           while (i < s.length() && countZero > 0 && s.charAt(i) == '1') {
               if (countOne == 0) {
                   indexOne++;
               }
               log.info("坐标1：{}；", i);
               countOne++;
               i++;
           }
       }

        return maxLen;
    }

    @Test
    public void testBalance() {
        String s = "100011";
        System.out.println(findTheLongestBalancedSubstringA(s));
    }

    public int findTheLongestBalancedSubstringA(String s) {
        int n = s.length(), idx = 0, ans = 0;
        while (idx < n) {
            // 因为是有每一轮都进行了初始化的。ab同时进行。
            int a = 0, b = 0;
            while (idx < n && s.charAt(idx) == '0' && ++a >= 0) {
                idx++;
            }
            while (idx < n && s.charAt(idx) == '1' && ++b >= 0) {
                idx++;
            }
            log.info("a:{},b:{}", a,b);
            ans = Math.max(ans, Math.min(a, b) * 2);
        }
        return ans;
    }
}
