package org.example.common.core.leetcode.daily.november;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 比较元素的值和计算
 * @time: 2023/11/5 14:28
 */
@Slf4j
public class Leet1105 {
    /**
     * 返回长度为10的子串
     * <p>
     *     我这个写法和对链表的去重有一定的相同的地方
     *     但是链表为啥要用while去写呢，因为他是有指针的元素，只能一个个去加上处理。for中的处理了。
     * </p>
     *
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences(String s) {
        if (s.length() < 10) {
            return new ArrayList<>();
        }

        char[] chars = s.toCharArray();
        // 转化为A+数字的情况。
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char c = s.charAt(i);
            // 原来是这样，有一轮结束后，i++了。
            log.info("开始对比的字符：{}，位置为：{}", c, i);
            // 要是相同，就记录
            if (i + 1 < chars.length && c == s.charAt(i + 1)) {
                int count = 0;
                final ArrayList<Integer> list = new ArrayList<>();
                while (i < s.length() && s.charAt(i) == c) {
                    count++;
                    // 所有的都加入进去
                    list.add(i);
                    i++;
                }
                // 说明下一轮的循环开始点了，有什么办法在里面进行处理呢？
                --i;
                log.info("{}", i);
                log.info("{}", list);
                log.info("{}字母轮循环结束", c);
                stringBuilder.append(c).append(count);
            } else {
                stringBuilder.append(c).append(1);
            }
        }
        log.info("{}", stringBuilder.toString());
        return new ArrayList<>();
    }

    @Test
    public void min() {
        String s = "AAAAACCCCCAAAAACCCCCAAAAAGGGTTT";
//        System.out.println(s.length());
        System.out.println(compressString(s));
    }

    public static String compressString(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder compressed = new StringBuilder();
        char currentChar = input.charAt(0);
        int count = 1;

        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == currentChar) {
                count++;
            } else {
                compressed.append(currentChar);
                compressed.append(count);
                currentChar = input.charAt(i);
                count = 1;
            }
        }

        compressed.append(currentChar);
        compressed.append(count);

        return compressed.toString();
    }

    /**
     * 数组的值最多2次重复；
     * 用O(1)的空间度
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return n;
        }
        int slow = 0, fast = 0;
        int count = 0;
        while (fast < n) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            } else if (slow < fast && count < 2) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
            count++;
            if (fast < n && nums[fast] != nums[fast - 1]) {
                // fast 遇到不同的元素，重置count；
                count = 0;
            }
        }
        return slow + 1;
    }

    @Test
    public void main() {
        int[] nums = {1, 1, 1, 2, 2, 3};
        final int i = removeDuplicates(nums);
        log.info("{}", i);
    }


}