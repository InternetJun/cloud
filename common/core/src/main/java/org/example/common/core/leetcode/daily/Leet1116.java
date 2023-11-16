package org.example.common.core.leetcode.daily;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/16 10:41
 */
@Slf4j
public class Leet1116 {

    /**
     * alternate odd and even num and all num less than threshold
     * first num is even
     * 自己的分组能力偏弱，难以处理这样的问题。
     *
     * @param nums
     * @param threshold
     * @return
     */
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int len = nums.length;
        int res = 0;
        int right = 0;
        while (right < len) {
            int cnt = 0;
            while (right < len && nums[right] % 2 != 0) {
                right++;
            }

            // 有一个元素的值满足threshold条件就可以了，
            while (right + 1 < len &&
                    (nums[right] % 2 != nums[right + 1] % 2)
            ) {
                if (nums[right] <= threshold && nums[right + 1] <= threshold) {
                    cnt += 2;
                    right += 2;
                } else if (nums[right] <= threshold) {
                    cnt++;
                    right++;
                }
            }
            // 特殊情况，是到最后一个元素了。不要满足情况的。
            if (right == len - 1 && nums[right] <= threshold) {
                cnt++;
            }
            // 这里的元素要怎么处理?
            right++;
            res = Math.max(res, cnt);
        }

        return res;
    }

    @Test
    public void main() {
        int[][] nums = {
                {3, 2, 5, 4},
                {1, 2},
                {2, 3, 4, 4, 4}
        };
        int[] threshold = {5, 2, 4};
        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length - 1) {
                System.out.println(longestAlternatingSubarray(nums[i], threshold[i]));
            }
        }
    }


    /**
     * <p>
     * n = len(nums)
     * i = 0
     * while i < n:
     * start = i
     * while i < n and ...:
     * i += 1
     * # 从 start 到 i-1 是一组
     * # 下一组从 i 开始，无需 i += 1
     * </p>
     * <p>
     * 练习指南：
     * 1446. 连续字符<br>
     * 1447. 哪种连续子字符串更长<br>
     * 1448. 删除字符使字符串变好<br>
     * 1449. 如果相邻两个颜色均相同则删除当前颜色<br>
     * 1450. 统计同质子字符串的数目<br>
     * 1451. 股票平滑下跌阶段的数目<br>
     * 1452. 使绳子变成彩色的最短时间<br>
     * 1453. 所有元音按顺序排布的最长子字符串<br>
     * 1454. 汇总区间<br>
     * 1455. 最长交替子序列<br>
     * </p>
     *
     * @param nums
     * @param threshold
     * @return
     */
    public int longestAlternatingSubarrayS(int[] nums, int threshold) {
        int n = nums.length;
        int ans = 0, i = 0;
        while (i < n) {
            if (nums[i] > threshold || nums[i] % 2 != 0) {
                i++;
                continue;
            }
            int start = i;
            // 开始位置已经满足
            i++;
            while (i < n && nums[i] <= threshold
                    && nums[i] % 2 != nums[i - 1] % 2) {
                i++;
            }
            // start 到 i -1
            ans = Math.max(ans, i - start);
        }
        return ans;
    }

    // 这样的题目要多联系啊。

    /**
     * 连0、1最长的长度比较，1比0多！
     *
     * @param s
     * @return
     */
    public boolean checkZeroOnes(String s) {
        int len = s.length();
        int point = 0, zeroMax = 0, oneMax = 0;
        while (point < len) {
            int zeroCount = 0, oneCount = 0;
            while (point < len && s.charAt(point) == '0') {
                zeroCount++;
                point++;
            }
            zeroMax = Math.max(zeroCount, zeroMax);
            while (point < len && s.charAt(point) == '1') {
                oneCount++;
                point++;
            }
            oneMax = Math.max(oneCount, oneMax);
        }
        return zeroMax < oneMax;
    }

    /**
     * 元音字母的问题
     * <p>
     * AEIOU都必须至少出现一次。<br>
     * 1）按照这个顺序来的 ==> 就是说的一个指针就好<br>
     * 2）包含aeiou的写法是不一样的。<br>
     * 3）一组元素。
     *
     * </p>
     *
     * @param word
     * @return
     */
    public int longestBeautifulSubstring(String word) {
        int len = word.length();
        int res = 0;
        int i = 0;
        String s = "uoiea";
        while (i < len) {
            int vowelCount = 5;
            int start = i;
            if (word.charAt(i) != 'a') {
                i++;
                continue;
            }
            // 这里有多个字符都满足的情况，有遗漏！
            vowelCount--;
            while (word.charAt(i) == 'a') {
                i++;
            }
            // 到了o i为10的时候，怎么就位置不对呢？
            // 这里会有直到满足情况为止，都是bug的存在。所以需要一个优化。
            while (i < len && vowelCount > 0) {
                // a --> e --> i --> o --> u
                char cur = s.charAt(vowelCount - 1);
                // 1个或者多个的情况
                char d = word.charAt(i);
                // 这里会有 d = 'a'的特殊情况，所有  4 2
                if (s.indexOf(d)+1 > vowelCount || vowelCount - s.indexOf(d)-1 >= 1) {
                    break;
                }
                boolean first = true;
                // 这里是有不一样。导致没有固定比较值。要的是所有的值都在一轮里比较玩，要不然会影响下一波的操作的。
                // 为什么坐标会有飘逸呢？
                if (d == cur) {
                    while (i < word.length() - 1 && d == cur) {
                        if (first) {
                            vowelCount--;
                        }
                        first = false;
                        i++;
                        d = word.charAt(i);
                    }
                    // 这里还是会遗漏数
                }
            }

            // 符合开始的情况。
            if (vowelCount == 0) {
                res = Math.max(res, i - start);
            }
        }
        return res;
    }

    @Test
    public void testVowel() {
//        String word = "aaaaeiiii||o（i）uuu";
        String word = "aeiaaioaaaaeiiiiouuuooaauuaeiu";
        System.out.println(longestBeautifulSubstring(word));
    }

    /**
     * <h1>总结</h1>
     * <p>
     *     要是有指针是要和下一个位置进行处理的时候，是有
     *     char d = s.charAt(i);
     *     while(i < <b>s.length()-1</b> && **) {
     *         i++;
     *         d = s.charAt(i);
     *     }
     * </p>
     *
     * @param word
     * @return
     */
    public int longestBeautifulSubstringS(String word) {
        List<Character> window=new ArrayList<Character>();
        Set<Character> set = new HashSet<Character>();
        int left=0;
        int res = 0;
        int right=0;
        while(right<word.length()) {
            if (window.isEmpty() || word.charAt(right)>=window.get(window.size()-1)) {
                window.add(word.charAt(right));
                set.add(word.charAt(right));
                if (set.size()==5) {
                    res=Math.max(res, window.size());
                }
            }else {
                window.clear();
                set.clear();
                left=right;
                window.add(word.charAt(left));
                set.add(word.charAt(left));
            }
            right++;
        }
        return res;
    }

    /**
     * <h1>总结</h1>
     * <p>
     *     要是有指针是要和下一个位置进行处理的时候，是有
     *     char d = s.charAt(i);
     *     while(i < <b>s.length()-1</b> && **) {
     *         i++;
     *         d = s.charAt(i);
     *     }
     * </p>
     *
     */
}
