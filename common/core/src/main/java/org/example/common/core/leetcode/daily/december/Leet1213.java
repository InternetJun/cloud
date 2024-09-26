package org.example.common.core.leetcode.daily.december;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.sound.midi.spi.MidiFileWriter;
import java.util.Arrays;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:
 * @time: 2023/12/13 13:46
 */
@Slf4j
public class Leet1213 {
    /**
     * 请你执行 尽可能少的操作 ，使 s 变成一个 回文串 。
     * 如果执行 最少 操作次数的方案不止一种，则只需选取 字典序最小 的方案。
     * <b>字典序小。说明要的是a - b < 0</b>
     *
     * @param s
     * @return
     */
    public String makeSmallestPalindrome(String s) {
        int len = s.length();
        int mid = len / 2 - 1;
        char[] chars = s.toCharArray();
        // 从中心进行一个发散比较。
        int step = 0, posMid = mid;
        while (mid >= 0) {
            /*
            * 偶数：abcd mid = 1; high = 2
            * 奇数： abddf mid = 1; 需要有 high = 3;
            * */
            int high = len % 2 == 0 ? posMid + 1 + step : posMid + 2 + step;
            if (chars[high] != chars[mid]) {
                if (chars[mid] - chars[high] > 0) {
                    chars[mid] = chars[high];
                } else {
                    chars[high] = chars[mid];
                }
            }
            log.info("mid:{},high:{}", mid, high);
            step++;
            mid--;
        }
        return Arrays.toString(chars);
    }

    @Test
    public void main() {
        System.out.println(makeSmallestPalindrome("aiyfzb"));
    }
}
