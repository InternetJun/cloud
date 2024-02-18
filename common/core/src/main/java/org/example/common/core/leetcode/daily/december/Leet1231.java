package org.example.common.core.leetcode.daily.december;

import org.junit.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:
 * @time: 2023/12/31 16:48
 */
public class Leet1231 {
    public final static String[] weekday = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};


    public String dayOfTheWeek(int day, int month, int year) throws ParseException {
        String dateString = year + "-" + month + "-" + day;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);

        // Get the day of the week (DayOfWeek enum, where MONDAY is 1, TUESDAY is 2, ..., SUNDAY is 7)
        int dayOfWeekValue = date.getDayOfWeek().getValue();

        // Print the day of the week
        return weekday[dayOfWeekValue - 1];
    }

    /**
     * 给定日期获取是当年的第几天
     * 闰年的判断方法：year / 400 or (year /4 and year not / 100)
     *
     * @param day
     * @return
     */
    public int dayOfYear(String day) {
        return 0;
    }

    @Test
    public void dayTest() throws ParseException {
        final String s = dayOfTheWeek(31, 12, 2023);
        System.out.println(s);
    }

    /**
     * 只交换一次，获取最长的长度。
     * <p>
     * aaabaaa--> 6 (3 + 3) 0、5
     * abcdef --都是不同的，所以有1
     * abababb --> 把最后的a和中间的b替换。这时有最长的是b而不是a。最长的是4不是3.<br>
     * <b>每一个字符所有位置和长度。
     * a {list[0-0,2-2,4-4]}
     * b {1-1, 3-3,5-6} 只有间隔的才能连接加入。
     * 对值替换一次，获取最大。
     * </b>
     *
     * <
     * ./p>
     * 和最长的无重复的区别。
     *
     * @param text
     * @return
     */

    public int maxRepOpt1(String text) {
	/** 统计每个字符的个数的数组*/
        int[] count = new int[26];          
	/** 字符串长度*/
        int n = text.length();              
         // 统计每个字符的个数
        for (int i = 0; i < n; i++) {
            count[text.charAt(i) - 'a']++;
        }

        int i = 0;
	/** 滑动窗口右边界，指向当前搜索的重复单字符子串的最后一个字符的下一位*/
        int j = 0;  
	/** 搜索最近的相同字符重复子串*/
        int k;      
	/** 记录结果*/
        int res = 0;    
        while (i < n) {
            // 搜索当前字符text[i]的重复子串
            while (j < n && text.charAt(j) == text.charAt(i)) {
                j++;
            }
            if (count[text.charAt(i) - 'a'] == j - i) {
	/** 没有可交换的字符，直接更新res*/
                res = Math.max(j - i, res);     
            } else {
	/** 交换一个字符，原长度 + 1*/
                res = Math.max(j - i + 1, res); 
	/** 从j之后搜索间隔一个字符的相同字符重复子串*/
                k = j + 1;  
                while (k < n && text.charAt(k) == text.charAt(i)) {
                    k++;
                }
                // 比较拼接后的重复子串长度和字符个数，取最小值；然后再更新res；原因是什么？
                // 回答：因为
                res = Math.max(res, Math.min(k - i, count[text.charAt(i) - 'a']));
            }
	    /** 更新滑动窗口起点，搜索下一个字符的重复子串*/
            i = j;  
        }
        return res;
    }

    @Test
    public void testLong(){
        String s1 = "aaabaaac";
        String s2 = "abc";
        String s = "aaabaaca";
        System.out.println(maxRepOpt1(s1));
    }


}
