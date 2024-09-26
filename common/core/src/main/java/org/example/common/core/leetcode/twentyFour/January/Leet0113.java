package org.example.common.core.leetcode.twentyFour.January;

import lombok.extern.slf4j.Slf4j;
import org.example.common.core.leetcode.tree.TreeNode;
import org.example.common.core.util.CommonUtil;
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
     * <p>
     * 要求不必全部的字符。
     * 字典序最大。
     * <p>
     * <p>
     * 输入：s = "aababab"
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
    public void main() {
        String s = "aababab";
        repeatLimitedString(s, 2);
    }

    public String repeatLimitStringSolution(String s, int limit) {
        final int[] count = new int[26];
        int N = 26;
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        final StringBuilder res = new StringBuilder();
        int m = 0;
        for (int i = N - 1, j = N - 2; i >= 0 && j >= 0; ) {
            // 用完了
            if (count[i] == 0) {
                m = 0;
                i--;
            } else if (m < limit) {
                count[i]--;
                res.append((char) ('a' + i));
                m++;
                // 当前字符已经超过限制，查找可填入的其他字符
            } else if (j >= i || count[j] == 0) {
                j--;
                // 当前字符已经超过限制，填入其他字符，并且重置 m
            } else {
                count[j]--;
                res.append((char) ('a' + j));
                m = 0;
            }
        }
        return res.toString();
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 这里会有在子节点的时候。误判了。所以加上这句话
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        } else {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }
}
