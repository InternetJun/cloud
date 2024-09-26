package org.example.common.core.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/2/13 21:15
 */
public class Leetcode1234 {

    @Test
    public void min() {
        int qqwe = balancedStringAnswer("QQWE");
        System.out.println(qqwe);
    }

    public int balancedStringAnswer(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            cnt[idx(c)]++;
        }

        int partial = s.length() / 4;
        int res = s.length();

        if (check(cnt, partial)) {
            return 0;
        }
        for (int l = 0, r = 0; l < s.length(); l++) {
            while (r < s.length() && !check(cnt, partial)) {
                cnt[idx(s.charAt(r))]--;
                r++;
            }
            if (!check(cnt, partial)) {
                break;
            }
            res = Math.min(res, r - l);
            cnt[idx(s.charAt(l))]++;
        }
        return res;
    }
    /**
     * {Q,W,E,R}四种字符，要求要平衡，就是说的/4的操作。
     * <p>
     * QQER--> 1 qq er
     * QWER--> 0
     *
     * </p>
     *
     * <p>
     *     answer:先固定左指针，右指针向右移动，得到的是以当前左指针为左边界的最小区间，
     *     这时候可以固定右边界改变左边界位置，向右移动左指针，在移动左指针的过程中，
     *     如果区间仍满足要求，那么该区间一定是以当前左指针为左边界的最小区间，
     *     如果不满足要求就固定左边界，继续改变右边界
     * </p>
     * @param s
     * @return
     */
    public int balancedString(String s) {
        int step = 0;
        int n = s.length();
        // todo
        int[] chars = new int[26];
        for (char item : s.toCharArray()) {
            chars[item - 'A']++;
        }
        int part = n / 4;
        int left = 0, right = 0;
        // 左右滑动窗口的处理是
        for (int l = 0, r = 0; l < s.length(); l++) {
            while (r < s.length() && !check(chars, part)) {
                chars[idx(s.charAt(r))]--;
                r++;
            }
            if (!check(chars, part)){
                break;
            }
            step = Math.min(step, r-l);
            chars[idx(s.charAt(l))]++;
        }
        return step;
    }

    public boolean check(int[] cnt, int partial) {
        if (cnt[idx('Q')] > partial || cnt[idx('W')] > partial || cnt[idx('E')] > partial || cnt[idx('R')] > partial) {
            return false;
        }
        return true;
    }

    public int idx(char c) {
        return c - 'A';
    }

    @Test
    public void main() {
        // 想要对其中的几个字段进行处理，其实就是map(e-> new HashMap(){{put();put()}})
        List<Map<String, Object>> maps = new ArrayList<>();
        maps.add(new HashMap<String, Object>(){{put("id", 1);put("name", "lejun");}});
        maps.add(new HashMap<String, Object>(){{put("id", 2);put("name", "zjl");}});
        List<HashMap<String, Object>> collect = maps.stream().map(e ->
                        new HashMap<String, Object>() {{
                            put("id", e.get("id"));
                        }})
                .collect(Collectors.toList());
        System.out.println(collect);


    }
}
