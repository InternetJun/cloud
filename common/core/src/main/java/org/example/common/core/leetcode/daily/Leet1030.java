package org.example.common.core.leetcode.daily;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.concurrent.*;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/10/30 10:43
 */
@Slf4j
public class Leet1030 {
    /**
     * <B>已经是一个升序的队列。</B>
     * <p>
     * 由于研究者
     * 1) 有3篇论文每篇 至少 被引用了 3 次，</br>
     * 2) 其余两篇论文每篇被引用 不多于 3 次，</br>
     * 所以她的 h 指数是 3 。
     * 我的思想是正确的
     * </p>
     *
     * @param citations
     * @return
     */
    public int hIndexMe(int[] citations) {
        if (citations == null) {
            return -1;
        }
        int len = citations.length;
        if (len == 1) {
            return -1;
        }
        for (int i = 0; i < len; i++) {
            // 什么情况要有更新的操作呢？0,1,3,5,6
            int index = i + 1;
            if (citations[i] < index) {
                continue;
            } else {
                if (len - index < index) {
                    return citations[i];
                }
            }
        }
        return -1;
    }

    @Test
    public void main() {
        int[] nums = {100};
        System.out.println(hIndex(nums));
    }

    public int hIndex(int[] citations) {
        int n = citations.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 其余两篇论文每篇被引用 不多于 3 次
            if (citations[mid] >= n - mid) {
                right = mid - 1;
            } else {
                // if (citations[i] < index) 等价于ci【i】 < index{
                left = mid + 1;
            }
        }
        return n - left;
    }

    /**
     * 找出最多几套的QWER
     * <B>有一个方法说明是表明是一套QWER的约束条件。要是一套。就记录下来left++；
     * 要不是一套的话，就需要有【righ++】--
     *
     * </B>
     *
     * @param s
     * @return
     */
    public int balancedString(String s) {
        // 使用数组来存储四个字符的出现次数（使用数组便于代码书写）
        int[] counts = new int[26];
        int len = s.length();
        int limit = len / 4;
        char ch;
        // 初始化不替换内容字符出现次数数组，即初始滑动窗口维护一个空串
        for (int i = 0; i < len; i++) {
            ch = s.charAt(i);
            counts[ch - 'A']++;
        }
        // 初始化滑动窗口左右指针，维护的子串是[left,right]的内容
        // 初始化子串为空，因此left=0，right=-1表示一个空子串
        int left = 0;
        int right = -1;
        // 最小替换子串长度，初始为整个字符串长度
        int minLength = len;
        // 滑动窗口
        while (left < len) {
            // 校验通过
            if (check(counts, limit)) {
                // 记录当前合法子串的长度并更新最小长度
                // 左指针右移，那么原本左指针指向的字符就变成不替换的内容，不替换内容多了一个字符，对应count数组中的值加1
                minLength = Math.min(minLength, right - left + 1);
                counts[s.charAt(left++) - 'A']++;
                log.info("由于平衡，左指针推进 Q:{},w:{},e:{},r:{}，right:{}， left:{}", counts['Q' - 'A'], counts['W' - 'A'], counts['E' - 'A'], counts['R' - 'A'], right, left);
            } else if (right < len - 1) {
                // 当前子串不合法且右指针还没到头
                // 右指针右移，移动后的右指针指向的字符变成了子串的内容，不替换的内容少了一个字符，对应count数组中的值减1
                counts[s.charAt(++right) - 'A']--;
                log.info("由于不平衡，右指针推进{Q:{},w:{},e:{},r:{}}", counts['Q' - 'A'], counts['W' - 'A'], counts['E' - 'A'], counts['R' - 'A']);
            } else {
                // 右指针到头，搜索结束
                break;
            }
        }
        return minLength;

    }

    /**
     * 校验函数，校验当前counts中四个字符的出现次数是否都小于等于limit；
     * 是返回true，否返回false
     */
    private boolean check(int[] counts, int limit) {
        if (counts['Q' - 'A'] > limit || counts['W' - 'A'] > limit || counts['E' - 'A'] > limit || counts['R' - 'A'] > limit) {
            return false;
        }
        return true;
    }

    @Test
    public void testBalancedString() {
        String s = "QWER";
        System.out.println(balancedString(s));
        final HashMap<String, String> map = new HashMap<>();
        map.get("id");
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(10);
    }
}