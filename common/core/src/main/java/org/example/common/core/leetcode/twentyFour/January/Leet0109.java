package org.example.common.core.leetcode.twentyFour.January;

import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:
 * @time: 2024/1/9 10:37
 */
@Slf4j
public class Leet0109 {
    /**
     * leetscode 【leet code  scode】
     * <p>
     * leet code --> s 或 leet scode -->  null
     *
     * </p>
     *
     * @param s
     * @param dictionary
     * @return
     */
    public int minExtractChar(String s, String[] dictionary) {
        // 什么条件是一个中止条件？要怎么选择这个呢？
        int n = dictionary.length;
        final List<String> collect = Arrays.stream(dictionary)
                .sorted(Comparator.comparing(String::length, Comparator.reverseOrder()))
                .collect(Collectors.toList());
        StringBuilder stringBuilder = new StringBuilder(s);
        for (String d : collect) {
            int len = d.length();
            final int index = stringBuilder.indexOf(d);
            // 这只是一个轮回，但是你需要的是还有没有的情况。所以是dfs
            if (index != -1) {
                stringBuilder.delete(index, index + len);
            }
        }
        return stringBuilder.length();
    }


    @Test
    public void main() {
        // leet sco(2) scod(1) 那如果是 sco de呢 dict = {"scode", sco, de}
        System.out.println(minExtractCharDp("leetscode", new String[]{"leet", "code", "leetcode"}));
    }

    /**
     *
     *
     * @param s
     * @param dictionary
     * @return
     */
    public int minExtractCharDp(String s, String[] dictionary) {
        int n = s.length();
        int[] d = new int[n + 1];
        Arrays.fill(d, Integer.MAX_VALUE);
        Trie trie = new Trie();
        for (String str : dictionary) {
            StringBuilder sb = new StringBuilder(str).reverse();
            trie.insert(sb.toString());
        }
        d[0] = 0;
        for (int i = 1; i <= n; i++) {
            d[i] = d[i - 1] + 1;
            Trie node = trie;
            for (int j = i - 1; j >= 0; j--) {
                if (node != null) {
                    node = node.track(s.charAt(j));
                    log.info("{}", node);
                    if (node != null && node.isEnd()) {
                        d[i] = Math.min(d[i], d[j]);
                    }
                }
            }
        }
        return d[n];
    }

    @Test
    public void testTrie() {
        final Trie trie = new Trie();
        trie.insert("leetcode");
        trie.insert("code");
        trie.insert("scode");
        System.out.println(trie.toString());
//        System.out.println(minExtractCharDp("leetscode", new String[]{"leet", "code", "leetcode"}));
    }


    // 字典树。
    public class Trie {
        private Trie[] children;
        private boolean isEnd;

        @Override
        public String toString() {
            return toStringHelper("");
        }

        public Trie getChild(char ch) {
            return children[ch - 'a'];
        }

        private String toStringHelper(String prefix) {
            StringBuilder result = new StringBuilder(prefix + (isEnd ? "*" : "") + "\n");

            for (char ch = 'a'; ch <= 'z'; ch++) {
                Trie child = getChild(ch);
                if (child != null) {
                    result.append(child.toStringHelper(prefix + ch + " -> "));
                }
            }

            return result.toString();
        }

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        /**
         * 对word的值进行树化
         *
         * @param word
         */
        public void insert(String word) {
            Trie node = this;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    node.children[c-'a'] = new Trie();
                }
                node = node.children[c - 'a'];
            }
            node.isEnd = true;
        }

        public Trie track(char ch) {
            Trie node = this;
            if (node == null || node.children[ch - 'a'] == null) {
                return null;
            }
            node = node.children[ch - 'a'];
            return node;
        }

        public boolean isEnd() {
            return isEnd;
        }
    }
}
