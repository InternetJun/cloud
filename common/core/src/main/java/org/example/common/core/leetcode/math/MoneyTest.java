package org.example.common.core.leetcode.math;

import cn.hutool.core.util.NumberUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/10/17 15:28
 */
public class MoneyTest {
    public static void main(String[] args) {
        BigDecimal sum = new BigDecimal("1.13");
        /*
         2.5   3
        -2.5  -3
        -5.5  -6*/
        sum.setScale(2, RoundingMode.HALF_UP);
        /**
         * 银行的业务三大类：存、贷、汇。
         * 假如有10笔存款，利息分别是0.000、0.001、0.002、0.003、0.004，0.005、0.006、0.007、0.008、0.009。
         *
         * 真实的银行家算法处理金额的计算处理是如下的：
         *
         * <p>
         *  5的情况比较复杂，如果5后面还有数字，则进位后舍去；如果5后面没有数字了，那么如果5前面是奇数，则进1，若5前面是偶数直接舍去5。
         *  举几个例子：
         *
         *  3.015 ≈ 3.02 //5后没有数字，其前面是奇数1，则进1；
         *
         *  3.045 ≈ 3.04 //5后没有数字，其前面是偶数，直接舍去5；
         *
         *  3.04501 ≈ 3.05 //5后面还有数字，则进位后舍去。
         * </p>
         **/
        sum.setScale(2, RoundingMode.HALF_EVEN);
        String[] strings = {"3.015", "3.045", "3.04501"};
        for (String string : strings) {
            BigDecimal temp = new BigDecimal(string);
        /*
         2.5   3
        -2.5  -3
        -5.5  -6*/
            temp.setScale(3, RoundingMode.HALF_EVEN);
            BigDecimal round = NumberUtil.round(temp, 2);
            System.out.println(round.doubleValue());
        }

        System.out.println(new BigDecimal("1.25").setScale(1,BigDecimal.ROUND_HALF_EVEN).toString());
    }
}
