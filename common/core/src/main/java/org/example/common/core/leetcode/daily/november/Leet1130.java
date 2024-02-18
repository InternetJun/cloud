package org.example.common.core.leetcode.daily.november;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 现有一个包含所有正整数的集合 [1, 2, 3, 4, 5, ...].每次都移开最小的值。
 * @time: 2023/11/25 10:12
 */
@Slf4j
public class Leet1130 {
    /**
     * <p>
     *     1,长度要equals
     *     2，计数
     *     3，每个字符出现的情况下，123 123这种是要相同的。
     * </p>
     *
     * @param word1
     * @param word2
     * @return
     */
    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        /**
         * a3b2c1   b3a2c1
         *
         * word1 = "cabbba", word2 = "aabbss"
         * a2b3c1  a2b2s2
         * 只能有2个相互处理
         */
        // 每个独一无二的数字要有。 1 3 2   1 2 3
        boolean flag = false;
        int[] snt = new int[26];
        int[] tnt = new int[26];
        int len1 = word1.length();
        int[] wordCount = new int[26];
        for (int i = 0; i < len1; i++) {
            snt[word1.charAt(i) - 'a']++;
            wordCount[word1.charAt(i) - 'a']++;
        }
        for (int i = 0; i < len1; i++) {
            tnt[word2.charAt(i) - 'a']++;
            wordCount[word2.charAt(i) - 'a']--;
        }
        log.info("{}", wordCount);
        // 下面的是一个条件。
        Arrays.sort(snt);
        Arrays.sort(tnt);
        /**
         * 有的问题：
         * 在值要有一样的情况下的。
         * 例如有uaa  ssx
         */
        // 如下的要写成
        for (int i = 0; i < 26; i++) {
            //
            if ((snt[i] == 0) != (tnt[i] == 0)) {
                log.info("word1中的{}与word2中的{}不同", (char)(i+'a'), (char)(i+'a'));
                return false;
            }
        }
        return Arrays.equals(snt, tnt);
    }

    public boolean sum2Zero(int[] arrays){
        int sum = 0;
        for (int array : arrays) {
            sum += array;
        }
        return sum == 0;
    }

    @Test
    public void min() {
        // "cabbba"a2b3c1  ""a2b2s2

        // b 1 c1 s -2因为有s2个，和谁进行一个交换都会多一个？
        /**
         * 步骤是
         * 1.交换位置
         * 2.交换个数 c-b c有3个b有1个
         * 3.交换个数
         *
         * 出现的个数必须都要有的。要不然不好替换。
         * "cabbba"
         * "abbccc"aabbss
         */
        //
        System.out.println(closeStringsSolution("abc", "def"));

    }

    public boolean closeStringsSolution(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        if(n != m) return false;
        // 只有小写
        int[] cnt1 = new int[26], cnt2 = new int[26];
        for(int i = 0; i < n; i++){
            int x = word1.charAt(i) - 'a';
            cnt1[x]++;
        }
        for(int i = 0; i < m; i++){
            int x = word2.charAt(i) - 'a';
            cnt2[x]++;
        }
        // 出现的字母必须一样
        for(int i = 0; i < 26; i++){
            if((cnt1[i] == 0) != (cnt2[i] == 0)){
                log.info("word1中的{}与word2中的{}不同", (char)(i+'a'), (char)(i+'a'));
                return false;
            }
        }
        Arrays.sort(cnt1);
        Arrays.sort(cnt2);
        // 出现的次数必须一样
        for(int i = 0; i < 26; i++){
            if(cnt1[i] != cnt2[i]) return false;
        }
        return true;
    }
}
