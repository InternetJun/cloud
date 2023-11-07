package org.example.common.core.leetcode.daily;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/6 11:10
 */
@Slf4j
public class Leet1106 {
    public static void main(String[] args) {
        final Integer a = new Integer(130);
        final Integer b = new Integer(128);
        final Integer c = new Integer(127);
        final Integer d = new Integer(127);
        final int e = 130;
        final int s = 127;
        System.out.println(a == e);
        System.out.println(c== s);
    }

    /**
     * 求出words中最大的乘积最大值。
     * 对字符串转化为二进制？
     *
     * @param words
     * @return
     */
    public int maxProduct(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        /**
         * 如下所示。
         */
        for (String word : words) {
            int t = 0, m = word.length();
            for (int i = 0; i < m; i++) {
                int u = word.charAt(i) - 'a';
                t |= (1<<u);
            }
            if (map.containsKey(t) || map.get(t) < m) {
                map.put(t, m);
            }
        }
        int max = Integer.MIN_VALUE;
        for (Integer a : map.keySet()) {
            for (Integer b : map.keySet()) {
                // 不同的时候，才有计算
                if ((a & b) == 0) {
                    max = Math.max(max, map.get(a) * map.get(b));
                }
            }
        }

        return max;
    }

    @Test
    public void testMaxLength() {
//        String[] words = {"a","aa","aaa","aaaa"};
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 25; i++) {
            char c = (char) (i + 'a');
            sb.append(c);
        }
        System.out.println(sb);
//        String[] words = {"a","ab","abc","d","cd","bcd","abcd"};
//        maxProduct(words);
    }
}
