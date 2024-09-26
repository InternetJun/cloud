package org.example.common.core.leetcode.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/8/30 21:19
 */
@Slf4j
public class MinJump {
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        // 太难了。不能处理
        return 0;
    }

    /**
     * 机器人的路径有多少条？
     *
     * @param obstacleGrid
     * @return
     */
    public int unquePathWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] mem = new int[row][col];
        for (int i = 0; i < row; i++) {
            Arrays.fill(mem[i], -1);
        }
        int res = 0;
        int count = dfsUnique(obstacleGrid, 0, 0, mem);
        log.info("{}", count);
        return res;
    }

    public boolean dfs(int[][] grid, int c, int r, int res) {
        if (c == grid.length - 1 && r == grid[0].length - 1) {
            res++;
            return true;
        }
        if (c >= grid.length || r >= grid[0].length || grid[c][r] == 1) {
            return false;
        }
        if (dfs(grid, c++, r, res)) {
            return true;
        }
        if (dfs(grid, c, r++, res)) {
            return true;
        }
        grid[c][r] = 1;
        return false;
    }

    @Test
    public void test() {
        int[][] obs = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(unquePathWithObstacles(obs));
    }

    private int dfsUnique(int[][] grid, int c, int r, int[][] mem) {
        if (c == grid.length - 1 && r == grid[0].length - 1) {
            return 1;
        }
        if (c >= grid.length || r >= grid[0].length || grid[c][r] == 1) {
            return 0;
        }
        int total = 0;
        if (mem[c][r] != -1) {
            return mem[c][r];
        }
        total += dfsUnique(grid, c++, r, mem);
        total += dfsUnique(grid, c, r++, mem);
        mem[c][r] = total;
        return total;
    }
// 最优的子结构是说的什么呢？

    /**
     * 最长的回文子序列的实现有：
     * int longestPalindromeSubseq(String s) {
     * int n = s.length();
     * // base case：一维 dp 数组全部初始化为 1
     * int[] dp = new int[n];
     * Arrays.fill(dp, 1);
     * <p>
     * for (int i = n - 2; i >= 0; i--) {
     * int pre = 0;
     * for (int j = i + 1; j < n; j++) {
     * int temp = dp[j];
     * // 状态转移方程
     * if (s.charAt(i) == s.charAt(j))
     * dp[j] = pre + 2;
     * else
     * dp[j] = Math.max(dp[j], dp[j - 1]);
     * pre = temp;
     * }
     * }
     * return dp[n - 1];
     * }
     */
    int longestPalindromeSubseq(String s) {
        int n = s.length();
        // base case：一维 dp 数组全部初始化为 1
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = n - 2; i >= 0; i--) {
            int pre = 0;
            for (int j = i + 1; j < n; j++) {
                int temp = dp[j];
                // 状态转移方程
                if (s.charAt(i) == s.charAt(j)) {
                    dp[j] = pre + 2;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                pre = temp;
            }
        }
        return dp[n - 1];
    }

    public void twodp(String str) {
        int n = str.length();
        char[] s = str.toCharArray();
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s[i] == s[j]) {
                    // dp[i][j] = dp[i+1][j-1] +2;
                } else {
                    // dp[i][j] = max(dp[i+1][j], dp[i][j-1])
                }
            }
        }
    }

    /**
     * 最长公共前缀
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) {
            return "";
        }
        int n =  strs[0].length();
        int len = strs.length;
        for (int i = 0; i < n; i++) { // n
            for (int j = 1; j < len; j++) { // len O(mn)
                String thisStr = strs[j];
                String preStr = strs[j - 1];
                // 判断每个字符串的 col 索引是否都相同
                if (i >= thisStr.length() || j >= preStr.length() ||
                        thisStr.charAt(i) != preStr.charAt(i)) {
                    // 发现不匹配的字符，只有 strs[row][0..col-1] 是公共前缀
                    return strs[j].substring(0, i);
                }
            }
        }
        return "";
    }

    @Test
    public void longTest() {
        String[] strings = {"flower","flow","flight"};
        longestCommonPrefix(strings);
    }

    /**
     * 第一次出现的下标
     *
     * @param str
     * @param needle
     * @return
     */
    public int firstAppearIndex(String str, String needle) {
        char[] ss = str.toCharArray();
        char[] nn = needle.toCharArray();
        int m = ss.length;
        int n = needle.length();
        for (int i = 0; i < m-n; i++) {
            int a = i, b = 0;
            while (ss[a] == nn[b] && b<n) {
                b++;
                a++;
            }
            if (b == n) {
                return i;
            }
        }
        return -1;
    }

    @Test
    public void testLong() {
        String s = "0P";
        String s1 = s.replaceAll("[^a-zA-Z]", "").toLowerCase();
        log.info("{}",s1);
        System.out.println(1/2);
    }

    public boolean isHuiWen(String s) {
        // even 6 ==> 3  7 ==> 3;
        int length = s.length();
        for (int i = 0; i <= length/2; i++) {
            if (s.charAt(i) != s.charAt(length-1 - i)) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testHuiwen() {
        boolean palindrome = isPalindrome("0P");
        System.out.println(palindrome);
    }

    public boolean isPalindrome(String s) {
        StringBuffer sgood = new StringBuffer();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                sgood.append(Character.toLowerCase(ch));
            }
        }
        int n = sgood.length();
        int left = 0, right = n - 1;
        while (left < right) {
            if (Character.toLowerCase(sgood.charAt(left)) != Character.toLowerCase(sgood.charAt(right))) {
                return false;
            }
            ++left;
            --right;
        }
        return true;
    }

    // 要的是小写的字母和数字的，不是说就一个小写字母。
    private boolean isValid(char c){
        return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    }

    public boolean isSubsequence(String s, String t) {
        if(t == null) {
            return false;
        }
        if(s.length() == 0 && t.length() == 0) {
            return true;
        }
        // 利用栈
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = s.length()-1; i >= 0; i--) {
            stack.push(s.charAt(i));
        }
        for (char c : t.toCharArray()) {
            if (!stack.isEmpty() &&stack.peek() ==  c) {
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    @Test
    public void main() {
        isSubsequence("", "");
    }

    /**
     *
     * 最长的字串。
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, right = 0, res = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);
            right++;
            // 判断是否是进行一个收缩。
            while (map.get(c) > 1) {
                char d = s.charAt(left);
                left++;
                map.put(d, map.get(d) -1);
            }
            // 更新答案；
            res = Math.max(res, right - left);
        }
        return res;
    }

    /**
     * 定义是什么？dp
     * 初始状态：
     * 转移的方程：
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        int len = nums.length;
        boolean[] dp = new boolean[len];
        for (int i = 0; i < len; i++) {
            int maxJump = nums[i];

        }
        return dp[len - 1];
    }

    /**
     * 最小的跳跃次数。
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int n = nums.length;
        int end = 0, farthest = 0;
        int jumps = 0;
        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(nums[i] + i, farthest);
            if (end == i) {
                jumps++;
                end = farthest;
            }
        }
        return jumps;
    }

    @Test
    public void testMinJump() {
        int[] nums = {2,3,0,1,4};
        System.out.println(jump(nums));
    }

    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            // 以 s[i] 为中心的最长回文子串
            String s1 = palindrome(s, i, i);
            // 以 s[i] 和 s[i+1] 为中心的最长回文子串
            String s2 = palindrome(s, i, i + 1);
            // res = longest(res, s1, s2)
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }
        return res;
    }

    String palindrome(String s, int l, int r) {
        // 防止索引越界
        while (l >= 0 && r < s.length()
                && s.charAt(l) == s.charAt(r)) {
            // 向两边展开
            l--;
            r++;
        }
        // 返回以 s[l] 和 s[r] 为中心的最长回文串
        return s.substring(l + 1, r);
    }

    public static void main(String[] args) {
        
    }
}