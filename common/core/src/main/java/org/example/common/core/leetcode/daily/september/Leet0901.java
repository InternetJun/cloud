package org.example.common.core.leetcode.daily.september;

import org.junit.Test;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/9/1 15:59
 */
public class Leet0901 {
    public int lengthOfLastWord(String s) {
        char[] chars = s.toCharArray();
        int len = s.length();
        int rightBound = len-1, right = len - 1;
        while(right >= 0) {
            // 最后一个单词要怎么确认呢？
            if(chars[right] == ' ') {
                break;
            } else {
                right--;
            }
        }
        return rightBound - right;
    }

    @Test
    public void main() {
        System.out.println(lengthOfLastWord("Hello World"));
    }
}
