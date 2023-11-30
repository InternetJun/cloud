package org.example.common.core.leetcode.daily.november;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.example.common.core.util.CommonUtil;
import org.junit.Test;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 用位运算获取每一位的和。因为题目中有限制，在数组的位置中加入2个最大、次最大的数
 * @time: 2023/11/18 14:52
 */
@Slf4j
public class Leet1118 {
    /**
     * <h1>获取和相同的最大和</h1>
     * <p>要有各个位数和相同</p>
     *
     * @param nums
     * @return
     */
    public int maximumSum(int[] nums) {
        if (ObjectUtil.isEmpty(nums) || nums.length < 2) {
            return -1;
        }
        //
        return -1;
    }

    public boolean isSameSum(int num1, int num2) {
        String s1 = num1 + "";
        String s2 = num2 + "";
        int lenMin = Math.min(s1.length(), s2.length());
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < lenMin; i++) {
            sum1 += ((int)s1.charAt(i) - '0');
            sum2 += ((int)s2.charAt(i) - '0');
        }
        if (s1.length() > lenMin) {
            for (int i = lenMin; i < s1.length(); i++) {

            }
        }
        if (s2.length() > lenMin){
            for (int i = lenMin; i < s1.length(); i++) {

            }
        }
        return sum1 == sum2;
    }

    @Test
    public void testAnd() {
        int a = 46;
        int b = 0;
        System.out.println(a | b);
    }

    public int maximumSumSolution(int[] nums) {
        int[][] val = new int[100][2];
        for (int x : nums) {
            int t = x, cur = 0;
            while (t != 0) {
                cur += t % 10;
                t /= 10;
            }
            // 最大沦为次大, 更新最大
            if (x >= val[cur][1]) {
                val[cur][0] = val[cur][1];
                val[cur][1] = x;
            }
            // 更新次大
            else if (x > val[cur][0]) {
                val[cur][0] = x;
            }
        }
        CommonUtil.formatAndDisplayArray(val);
//        log.info("{}", val);
        int ans = -1;
        for (int i = 0; i < 100; i++) {
            if (val[i][0] != 0 && val[i][1] != 0) {
                ans = Math.max(ans, val[i][0] + val[i][1]);
            }
        }
        return ans;
    }

    @Test
    public void testMax() {
        int[] nums = {18,43,36,13,7};
        maximumSumSolution(nums);
    }
}
