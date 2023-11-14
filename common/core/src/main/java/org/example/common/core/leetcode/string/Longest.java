package org.example.common.core.leetcode.string;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/8/24 11:01
 */
@Slf4j
public class Longest {
    /**
     * 最长的字串
     * 注意是字串。不是序列。
     * 所以要用双指针的写法。
     * 用框架！！
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubString(String s) {
        Map<Character, Integer> windows = new HashMap<>();
        int left = 0, right = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            windows.put(c, windows.getOrDefault(c, 0) + 1);
            // 加大
            right++;
            // 进行更新数据。
            System.out.printf("window: [%d, %d)\n", left, right);
            // 判断要不要收缩
//            while (left < right && windows need shrink) {
//                char d = s.charAt(left);
//                windows.put(d, windows.get(d) -1 );
//                left++;
            // 进行更新
//            }
        }
        return windows.keySet().size();
    }


    public int lengthOfLongestSubStringSolution(String s) {
        Map<Character, Integer> windows = new HashMap<>();
        int left = 0, right = 0;
        int res = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            windows.put(c, windows.getOrDefault(c, 0) + 1);
            while (windows.get(c) > 1) {
                char d = s.charAt(left);
                left++;
                windows.put(d, windows.get(d) - 1);
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

    /**
     * 最小
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow1001(String s, String t) {
        String res = "";
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> windows = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int left = 0, right = 0, len = Integer.MAX_VALUE, start = 0;
        int valid = 0;
        // 左右指针的移动。
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (windows.containsKey(c)) {
                windows.put(c, windows.getOrDefault(c,0) + 1);
                if (windows.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            while (valid == need.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                char d = s.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (windows.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    windows.put(d, windows.get(d) - 1);
                }
            }

        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    LinkedList<String> track = new LinkedList<>();
    /** 93. 复原 IP 地址
     * https://leetcode.cn/problems/restore-ip-addresses/description/
     *<p>
     *     "0.011.255.245"
     *</p>
     *
     * @param s
     * @return
     */
    public List<String> restoreIpAddressesMe(String s) {
        List<String> list = new ArrayList<>();
        dfs(0, s, list);
        return list;
    }

    public void dfs(int start, String s, List<String> list) {
        if (track.size() == 4 && start == s.length()) {
            list.add(String.join(".", track));
            return;
        }

        for (int i = start; i < s.length(); i++) {
            if (!isValid(s, start, i)) {
                // 进入下一个循环
                continue;
            }
            if (track.size() >= 4) {
                // 退出；
                break;
            }
            track.addLast(s.substring(start, i+1));
            dfs(i+1, s, list);
            track.removeLast();
        }
    }

    @Test
    public void testIp() {
        System.out.println(restoreIpAddressesMe("101023"));
    }

    boolean isValid(String s, int start, int end) {
        int length = end - start + 1;

        if (length == 0 || length > 3) {
            return false;
        }

        if (length == 1) {
            // 如果只有一位数字，肯定是合法的
            return true;
        }

        if (s.charAt(start) == '0') {
            // 多于一位数字，但开头是 0，肯定不合法
            return false;
        }

        if (length <= 2) {
            // 排除了开头是 0 的情况，那么如果是两位数，怎么着都是合法的
            return true;
        }

        // 现在输入的一定是三位数
        if (Integer.parseInt(s.substring(start, start + length)) > 255) {
            // 不可能大于 255
            return false;
        } else {
            return true;
        }

    }

    List<String> res = new LinkedList<>();

    public List<String> restoreIpAddresses(String s) {
        backtrack(s, 0);
        return res;
    }

    // 回溯算法框架
    void backtrack(String s, int start) {
        if (start == s.length() && track.size() == 4) {
            // base case，走到叶子节点
            // 即整个 s 被成功分割为合法的四部分，记下答案
            res.add(String.join(".", track));
        }
        for (int i = start; i < s.length(); i++) {
            if (!isValid(s, start, i)) {
                // s[start..i] 不是合法的 ip 数字，不能分割
                continue;
            }
            if (track.size() >= 4) {
                // 已经分解成 4 部分了，不能再分解了
                break;
            }
            // s[start..i] 是一个合法的 ip 数字，可以进行分割
            // 做选择，把 s[start..i] 放入路径列表中
            track.addLast(s.substring(start, i + 1));
            // 进入回溯树的下一层，继续切分 s[i+1..]
            backtrack(s, i + 1);
            // 撤销选择
            track.removeLast();
        }
    }

    @Test
    public void mainWindow() {
        String s = "ADOBECODEBANC", t = "ABC";
        System.out.println(minWindow(s, t));
    }

    /**
     * 找出最小的字符串
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        String res = "";
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> windows = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int left = 0, right = 0, len = Integer.MAX_VALUE, start = 0;
        int valid = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (need.containsKey(c)) {
                windows.put(c, windows.getOrDefault(c, 0) + 1);
                if (windows.get(c).equals(need.get(c))) {
                    valid++;
                }
            }
            // 判断是否要收缩
            while (valid == need.size()) {
                // update
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                char d = s.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    // 只有相同的时候，才可以
                    if (windows.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    windows.put(d, windows.get(d) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    @Test
    public void main() {
        // 测试出该有的数据
        int s = 2 << 3;
        System.out.println(s);
    }

    public List<Integer> findSubstring(String s, String[] words) {
        return null;
    }

    /**
     * 所有的组合一起。
     *
     * @param words
     * @return
     */
    public List<String> dfs(String[] words) {
        int len = words.length;
        boolean[] used = new boolean[len];
        final ArrayList<String> list = new ArrayList<>();
        dfs(words, used, new LinkedList<String>(), list);
        return list;
    }

    /**
     * 有3个元素的时候。是怎么结束的呢？
     *
     * @param words
     * @param used
     * @param connect
     * @param list
     * @return
     */
    public void dfs(String[] words, boolean[] used, LinkedList<String> connect, List<String> list) {
        if (connect.size() == words.length) {
            final String collect = connect.stream().collect(Collectors.joining());
            list.add(collect);
            return;
        }
        int len = words.length;
        for (int i = 0; i < len; i++) {
            if (used[i]) {
                continue;
            }
            String s = words[i];
            used[i] = true;
            connect.add(s);
            dfs(words, used, connect, list);
            used[i] = false;
            connect.removeLast();
        }
    }

    void backtrack(int[] nums, LinkedList<Integer> track, boolean[] used) {
        // 触发结束条件
        if (track.size() == nums.length) {
//            res.add(new LinkedList(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 排除不合法的选择
            if (used[i]) {
                // nums[i] 已经在 track 中，跳过
                continue;
            }
            // 做选择
            track.add(nums[i]);
            used[i] = true;
            // 进入下一层决策树
            backtrack(nums, track, used);
            // 取消选择
            track.removeLast();
            used[i] = false;
        }
    }

    @Test
    public void testGroup() {
        String[] words = {"ab", "cd", "ef"};
        // "abcdef"， "abefcd"，"cdabef"， "cdefab"，"efabcd"， 和 "efcdab"
        System.out.println(dfs(words));
    }

    public static List<String> generatePermutations(String[] words) {
        List<String> result = new ArrayList<>();
        backtrack(result, words, 0);
        return result;
    }

    private static void backtrack(List<String> result, String[] words, int index) {
        if (index == words.length - 1) {
            result.add(String.join("", words));
            return;
        }

        for (int i = index; i < words.length; i++) {
            // 交换元素
            log.info("i:" + i + "\tindex:" + index);
            swap(words, index, i);
            backtrack(result, words, index + 1);
            // 恢复原始顺序，回溯
            swap(words, index, i);
        }
    }

    private static void swap(String[] words, int i, int j) {
        String temp = words[i];
        words[i] = words[j];
        words[j] = temp;
    }

    public static List<String> generatePermutationsGeneral(String[] words) {
        List<String> result = new ArrayList<>();
        backtrack(result, words, new boolean[words.length], new StringBuilder(), 0);
        return result;
    }

    private static void backtrack(List<String> result, String[] words, boolean[] used, StringBuilder current, int count) {
        if (count == words.length) {
            result.add(current.toString());
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (!used[i]) {
//                for (int j = 0; j < words[i].length(); j++) {
                current.append(words[i]);
                used[i] = true;
                backtrack(result, words, used, current, ++count);
                current.deleteCharAt(words[i].length() - 1);
                used[i] = false;
            }
        }
    }
}
