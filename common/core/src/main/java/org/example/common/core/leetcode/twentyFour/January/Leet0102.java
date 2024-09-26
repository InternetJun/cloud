package org.example.common.core.leetcode.twentyFour.January;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:
 * @time: 2024/1/1 22:44
 */
@Slf4j
public class Leet0102 {

    /**
     * <b>我是这样的思路，但是没有考虑的是最小的循环数据。可以多个s1合并才有s2.必须要考虑的是顺序。</b>
     *
     * @param s1
     * @param n1
     * @param s2
     * @param n2
     * @return
     */
    public int getMaxRepetitionsMe(String s1, int n1, String s2, int n2) {
        final String targetS1 = repeatString(s1, n1);
        final String targetS2 = repeatString(s2, n2);
        // s1,s2是否是包含的。然后才去讨论的。
        int[] count1 = new int[26];
        int[] count2 = new int[26];
        for (char c : s1.toCharArray()) {
            count1[c - 'a']++;
        }
        for (char c : s2.toCharArray()) {
            count2[c - 'a']++;
        }
        int minContain = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            // acb ab | abc ab（dbe）c s1 and s2的关系包含多少个。 s1 有2个s2；
            if (count1[i] != 0 && count2[i] != 0) {
                minContain = Math.min(count1[i] / count2[i] > 1 ? count1[i] / count2[i] : count2[i] / count1[i]
                        , minContain);
            }
        }
        // minContain = 2;  s1 = 2s2(n1*s2 n2*s2  mincontain * )/s2 = 2s1; ==> n1\n2
        int m = -1;
        return m;
    }

    public String repeatString(String s, int n) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    public static int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int len1 = s1.length(), len2 = s2.length();
        int index1 = 0, index2 = 0; // Use the index of Ra and Rb directly, without taking the modulo.

        if (len1 == 0 || len2 == 0 || len1 * n1 < len2 * n2) {
            return 0;
        }

        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        // 记录个数
        int ans = 0;

        while (index1 / len1 < n1) {
            if (index1 % len1 == len1 - 1) {
                if (map1.containsKey(index2 % len2)) {
                    int cycleLen = index1 / len1 - map1.get(index2 % len2) / len1;
                    int cycleNum = (n1 - 1 - index1 / len1) / cycleLen;
                    int cycleS2Num = index2 / len2 - map2.get(index2 % len2) / len2;

                    index1 += cycleNum * cycleLen * len1;
                    ans += cycleNum * cycleS2Num;
                } else {
                    // 对s1有多少s2的设计处理。
                    map1.put(index2 % len2, index1);
                    map2.put(index2 % len2, index2);
                }
            }

            if (s1.charAt(index1 % len1) == s2.charAt(index2 % len2)) {
                if (index2 % len2 == len2 - 1) {
                    ans += 1;
                }
                index2 += 1;
            }
            index1 += 1;
        }
        return ans / n2;
    }


    @Test
    public void main() {
        String s1 = "abaaacdbac";
        String s2 = "adcbd";
        final int count = getMaxRepetitions2(s1, 100, s2, 4);
    }

    public int getMaxRepetitions2(String s1, int n1, String s2, int n2) {
        if (n1 == 0) {
            return 0;
        }
        int s1cnt = 0, index = 0, s2cnt = 0;
        // recall 是我们用来找循环节的变量，它是一个哈希映射
        // 我们如何找循环节？假设我们遍历了 s1cnt 个 s1，此时匹配到了第 s2cnt 个 s2 中的第 index 个字符
        // 如果我们之前遍历了 s1cnt' 个 s1 时，匹配到的是第 s2cnt' 个 s2 中同样的第 index 个字符，那么就有循环节了
        // 我们用 (s1cnt', s2cnt', index) 和 (s1cnt, s2cnt, index) 表示两次包含相同 index 的匹配结果
        // 那么哈希映射中的键就是 index，值就是 (s1cnt', s2cnt') 这个二元组
        // 循环节就是；
        //    - 前 s1cnt' 个 s1 包含了 s2cnt' 个 s2
        //    - 以后的每 (s1cnt - s1cnt') 个 s1 包含了 (s2cnt - s2cnt') 个 s2
        // 那么还会剩下 (n1 - s1cnt') % (s1cnt - s1cnt') 个 s1, 我们对这些与 s2 进行暴力匹配
        // 注意 s2 要从第 index 个字符开始匹配
        Map<Integer, int[]> recall = new HashMap<Integer, int[]>();
        int[] preLoop = new int[2];
        int[] inLoop = new int[2];
        while (true) {
            // 我们多遍历一个 s1，看看能不能找到循环节
            ++s1cnt;
            for (int i = 0; i < s1.length(); ++i) {
                char ch = s1.charAt(i);
                // 依次进行比较，要是
                if (ch == s2.charAt(index)) {
                    index += 1;
                    if (index == s2.length()) {
                        ++s2cnt;
                        index = 0;
                    }
                }
            }
            // 还没有找到循环节，所有的 s1 就用完了
            if (s1cnt == n1) {
                return s2cnt / n2;
            }
            // 出现了之前的 index，表示找到了循环节
            if (recall.containsKey(index)) {
                int[] value = recall.get(index);
                int s1cntPrime = value[0];
                int s2cntPrime = value[1];
                // 前 s1cnt' 个 s1 包含了 s2cnt' 个 s2
                preLoop = new int[]{s1cntPrime, s2cntPrime};
                // 以后的每 (s1cnt - s1cnt') 个 s1 包含了 (s2cnt - s2cnt') 个 s2
                inLoop = new int[]{s1cnt - s1cntPrime, s2cnt - s2cntPrime};
                break;
            } else {
                recall.put(index, new int[]{s1cnt, s2cnt});
            }
        }
        // ans 存储的是 S1 包含的 s2 的数量，考虑的之前的 preLoop 和 inLoop
        int ans = preLoop[1] + (n1 - preLoop[0]) / inLoop[0] * inLoop[1];
        // S1 的末尾还剩下一些 s1，我们暴力进行匹配
        int rest = (n1 - preLoop[0]) % inLoop[0];
        for (int i = 0; i < rest; ++i) {
            for (int j = 0; j < s1.length(); ++j) {
                char ch = s1.charAt(j);
                if (ch == s2.charAt(index)) {
                    ++index;
                    if (index == s2.length()) {
                        ++ans;
                        index = 0;
                    }
                }
            }
        }
        // S1 包含 ans 个 s2，那么就包含 ans / n2 个 S2
        return ans / n2;
    }
}
