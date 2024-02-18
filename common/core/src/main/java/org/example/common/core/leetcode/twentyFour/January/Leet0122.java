package org.example.common.core.leetcode.twentyFour.January;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:
 * @time: 2024/1/22 10:47
 */
@Slf4j
public class Leet0122 {
    /**
     * 需满足如下的条件：
     * <p>
     *     jk最优的交换，有如下的条件：
     *
     * </p>
     *
     * @param num
     * @return
     */
    public int maximumSwap(int num) {
        char[] charArray = String.valueOf(num).toCharArray();
        int n = charArray.length;
        int maxIdx = n - 1;
        int idx1 = -1, idx2 = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (charArray[i] > charArray[maxIdx]) {
                // 最小的i坐标。
                maxIdx = i;
                log.info("{}, {}", maxIdx, charArray[maxIdx]);
            } else if (charArray[i] < charArray[maxIdx]) {
                //
                idx1 = i;
                idx2 = maxIdx;
                log.info("id1:{}, id2:{}", idx1, idx2);
            }
        }
        if (idx1 >= 0) {
            swap(charArray, idx1, idx2);
            return Integer.parseInt(new String(charArray));
        } else {
            return num;
        }
    }

    @Test
    public void main() {
//        int num = 1023;
//        int num = 1023;
//        int num = 1023;
        /**
         * 这个就不是交换首尾的情况了。
         * max = 3；
         * max = 5；
         * id1 = 4, id2 = 5;
         * 然后有进行一个比较。54；
         *
         */
        int num = 16453;
        log.info("{}", maximumSwap(num));
    }

    public void swap(char[] charArray, int i, int j) {
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
    }
}
