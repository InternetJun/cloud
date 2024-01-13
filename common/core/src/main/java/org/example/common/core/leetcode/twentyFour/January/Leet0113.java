package org.example.common.core.leetcode.twentyFour.January;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:
 * @time: 2024/1/13 14:15
 */
@Slf4j
public class Leet0113 {
    /**
     * <href> https://leetcode.cn/problems/construct-string-with-repeat-limit/description/?envType=daily-question&envId=2024-01-13</href>
     *
     * 要求不必全部的字符。
     * 字典序最大。
     * <p>
     *
     *   输入：s = "aababab"
     * 使用 s 中的一些字符来构造 repeatLimitedString "bbabaa"。
     * 字母 'a' 连续出现至多 2 次。
     * 字母 'b' 连续出现至多 2 次。
     * 因此，没有字母连续出现超过 repeatLimit 次，字符串是一个有效的 repeatLimitedString 。
     * 该字符串是字典序最大的 repeatLimitedString ，所以返回 "bbabaa" 。
     * 注意，尽管 "bbabaaa" 字典序更大，但字母 'a' 连续出现超过 2 次，所以它不是一个有效的 repeatLimitedString 。
     * <b>如果字符串中前 min(a.length, b.length) 个字符都相同，那么较长的字符串字典序更大。</b>
     * </p>
     *
     * @param s
     * @param repeatLimit
     * @return
     */
    public String repeatLimitedString(String s, int repeatLimit) {
        final Map<Character, Integer> map = new TreeMap<>((o1, o2) -> Character.compare(o2, o1));
        for (char c : s.toCharArray()) {
            int count = map.getOrDefault(c, 0) + 1;
            map.put(c, count);
        }
        log.info("{}", map);
        final List<Character> keys = new ArrayList<>(map.keySet());
        final StringBuilder stringBuilder = new StringBuilder();
        int len = s.length();
        int count = 0, ind = 0;
        while (stringBuilder.length() < len) {
            while (count < repeatLimit) {
                stringBuilder.append(keys.get(ind));
                count++;
            }
            // 必须下一个字符了。
            ind++;
        }
        return stringBuilder.toString();
    }

    @Test
    public void main(){
        String s = "aababab";
        repeatLimitedString(s, 2);
    }
}
