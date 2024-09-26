package org.example.common.core.leetcode.daily.september;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/9/2 21:46
 */
@Slf4j
public class Leet0902 {
    public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer();
        int pointer = 0;
        while (pointer < nums.length) {
            int left = pointer;
            while (pointer + 1 < nums.length && nums[pointer] + 1 == nums[pointer+1]) {
                pointer++;
            }
            if (pointer > left) {
                stringBuffer.append(nums[left])
                        .append("->")
                        .append(nums[pointer]);
                list.add(stringBuffer.toString());
                stringBuffer.setLength(0);
            } else {
                list.add(nums[left]+"");
            }
            pointer++;
        }
        return list;
    }

    @Test
    public void main() {
        int[] nums = {0,1,2,4,5,7};
        List<String> strings = summaryRanges(nums);
        log.info("{}", strings);
    }

    @Test
    public void test() {
        int i = 5;
        int j = 10;
        // 1010  -n=~n+1可推出~n=-n-1，所以~10=-11再加5结果为-6
        log.info("{}", ~j);
        System.out.println(i + ~j);

        String abc = "abc";
        String abc2= "a" + new String("bc");
        System.out.println(abc==abc2);
    }


}
