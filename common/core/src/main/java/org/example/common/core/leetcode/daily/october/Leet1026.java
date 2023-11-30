package org.example.common.core.leetcode.daily.october;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/10/26 10:49
 */
public class Leet1026 {
    String[] romans = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD","D", "CM", "M"};
    String[] nums = {"1", "4", "5", "9", "10", "40", "50", "90", "100", "400", "500", "900", "1000"};
    /**
     * 整数转换为罗马字符
     * <p>
     *   9
     *   58 --> 5;8(5 + 3) ==> 有的是5 V + III
     * </p>
     *
     * @param num
     * @return
     */
    public String intToRoman(int num) {

        int length = nums.length;
        for (int i = 0; i < length / 2; i++) {
            String temp = nums[i];
            nums[i] = nums[length - i - 1];
            nums[length - i - 1] = temp;
            String temp2 = romans[i];
            romans[i] = romans[length - i - 1];
            romans[length - i - 1] = temp2;
        }

        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int val= Integer.parseInt(nums[i]);
            String symbol = romans[i];
            // 在数字中有表示符号的，就要处理的意思。
            while (num >= val) {
                num -= val;
                sb.append(symbol);
            }
            if (num == 0) {
                break;
            }
        }
        return sb.toString();
    }

    @Test
    public void main() {
        System.out.println(intToRoman(140));
    }

    /**
     * 罗马转整数
     *<p>
     * s = "MCMXCIV"
     * 输出: 1994
     * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     * 其实就是对字符进行一个简单的识别，但是我的思想是没有处理好
     * </p>
     *
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        int len = romans.length;
        final Map<String, String> map = new LinkedHashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(romans[len - 1 - i], nums[len - 1 - i]);
        }
        int num = 0;
        final StringBuilder stringBuilder = new StringBuilder(s);
        for (Map.Entry<String, String> item : map.entrySet()) {
            String roman = item.getKey();
            String numRoman = item.getValue();
            // 比如有III的时候呢？你要怎么处理呢？对吧。我这样控制不了字符的位置是哪一个！
        }
        return num;
    }

    public int romanToIntSolution(String s) {
        s = s.replace("IV","a");
        s = s.replace("IX","b");
        s = s.replace("XL","c");
        s = s.replace("XC","d");
        s = s.replace("CD","e");
        s = s.replace("CM","f");

        int result = 0;
        for (int i=0; i<s.length(); i++) {
            result += which(s.charAt(i));
        }
        return result;
    }

    public int which(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            case 'a': return 4;
            case 'b': return 9;
            case 'c': return 40;
            case 'd': return 90;
            case 'e': return 400;
            case 'f': return 900;
            default: return 0;
        }
    }
}
