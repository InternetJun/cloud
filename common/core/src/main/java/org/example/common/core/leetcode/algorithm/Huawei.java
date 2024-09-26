package org.example.common.core.leetcode.algorithm;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/9/7 21:46
 */
@Slf4j
public class Huawei {
    public static void main1() {
        Scanner scanner = new Scanner(System.in);
        int[] ints = Arrays.stream(scanner.nextLine().split(","))
                .mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(ints));
    }

    /**
     * 因为是输入的。要明确的表达出
     */
    public static String getResult(int[] priority) {
        int n = priority.length;
        LinkedList<int[]> link = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            link.add(new int[]{priority[i], i});
        }

        Arrays.sort(priority);
        int maxI = n - 1;

        int[] ans = new int[n];

        int printIdx = 0;
        while (link.size() > 0) {
            int[] tmp = link.removeFirst();
            int p = tmp[0], i = tmp[1];

            if (p == priority[maxI]) {
                ans[i] = printIdx;
                printIdx++;
                maxI--;
            } else {
                link.add(tmp);
            }
        }

        StringJoiner sj = new StringJoiner(",");
        for (int an : ans) {
            sj.add(an + "");
        }
        return sj.toString();
    }

    public static void main2(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        // 统计字符的个数。
        int[] chars = new int[26];
        String sl = s.toLowerCase();
        for (int i = 0; i < sl.length(); i++) {
            int c = sl.charAt(i) - 'a';
            chars[c]++;
        }
        // 输出对应的结果；
        StringBuffer stringBuffer = new StringBuffer();
        // 0~25 分别代表了26个字母。
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < 26; i++) {
            if (chars[i] > 0) {
                map.put((char) ('a' + i), chars[i]);
            }
        }
        LinkedHashMap<Character, Integer> sortedMap = map.entrySet()
                .stream()
                .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
        List<Map.Entry<Character, Integer>> entryList = new ArrayList<>(map.entrySet());
        Collections.sort(entryList, (entry1, entry2) -> {
            // 首先按照value倒序排序
            int valueComparison = entry2.getValue().compareTo(entry1.getValue());

            if (valueComparison != 0) {
                return valueComparison;
            } else {
                // 如果value相同，按字典序升序排序
                return entry1.getKey().compareTo(entry2.getKey());
            }
        });
        // 第二种写法
        /**
         * LinkedHashMap<Character, Integer> sortedMap = map.entrySet()
         *                 .stream()
         *                 .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed()
         *                         .thenComparing(Map.Entry.comparingByKey()))
         *                 .collect(Collectors.toMap(
         *                         Map.Entry::getKey,
         *                         Map.Entry::getValue,
         *                         (e1, e2) -> e1,
         *                         LinkedHashMap::new
         *                 ));
         */

//        // 将排序后的结果重新放回LinkedHashMap
//        LinkedHashMap<Character, Integer> sortedMap = entryList.stream()
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

//        for (Map.Entry<Character, Integer> item : entryList) {
//            stringBuffer
//                    .append(item.getKey())
//                    .append(item.getValue());
//        }
        for (Map.Entry<Character, Integer> item : sortedMap.entrySet()) {
            stringBuffer
                    .append(item.getKey())
                    .append(item.getValue());
        }

        System.out.println(stringBuffer.toString());
    }

    public static String generateSummary(String inputString) {
        // 去除字符串中的非字母符号
        inputString = inputString.replaceAll("[^a-zA-Z]", "");

        // 初始化结果列表
        List<String> result = new ArrayList<>();

        int i = 0;
        while (i < inputString.length()) {
            char currentChar = Character.toLowerCase(inputString.charAt(i));
            int count = 1;

            // 检查连续字符
            while (i + 1 < inputString.length() && Character.toLowerCase(inputString.charAt(i + 1)) == currentChar) {
                count++;
                i++;
            }

            // 如果是连续字符，添加字符和次数
            if (count > 1) {
                result.add(currentChar + "" + count);
            } else {
                // 否则，查找字符之后的出现次数
                String substring = inputString.substring(i + 1).toLowerCase();
                int countAfter = substring.length() - substring.replace(currentChar + "", "").length();
                result.add(currentChar + "" + countAfter);
            }

            i++;
        }

        // 使用自定义排序函数对结果进行排序
        Collections.sort(result, (s1, s2) -> {
            if (s1.substring(1).matches("\\d+") && s2.substring(1).matches("\\d+")) {
                // 比较数字部分
                int num1 = Integer.parseInt(s1.substring(1));
                int num2 = Integer.parseInt(s2.substring(1));
                if (num1 != num2) {
                    return Integer.compare(num2, num1); // 降序排序
                } else {
                    // 如果数字相同，比较字母部分
                    return Character.compare(s1.charAt(0), s2.charAt(0));
                }
            } else if (s1.substring(1).matches("\\d+")) {
                return -1; // s1是数字，放在前面
            } else if (s2.substring(1).matches("\\d+")) {
                return 1; // s2是数字，放在前面
            } else {
                // 比较字母部分
                return Character.compare(s1.charAt(0), s2.charAt(0));
            }
        });

        // 将排序后的结果组合成最终字符串
        StringBuilder summary = new StringBuilder();
        for (String s : result) {
            summary.append(s);
        }

        return summary.toString();
    }

    @Test
    public void min() {
        int[] m = {4, 2, 3, 1};
        int cars = 10;
        System.out.println(repairCars(m, cars));
    }

    /**
     * 修车的最少时间
     * RANK * N^2 = m  n = m / x
     *
     * @param ranks
     * @param cars
     * @return
     */
    public long repairCars(int[] ranks, int cars) {
        long l = 1, r = 1L * ranks[0] * cars * cars;
        while (l < r) {
            long m = l + r >> 1;
            if (check(ranks, cars, m)) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    public boolean check(int[] ranks, int cars, long m) {
        long cnt = 0;
        for (int x : ranks) {
            cnt += (long) Math.sqrt(m / x);
        }
        return cnt >= cars;
    }

    public boolean isValid(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (char c : str.toCharArray()) {
            if (isSymbol(c)) {
                stringBuffer.append(c);
            }
        }
        String s = stringBuffer.toString();
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if ((c == ')' || c == ']' || c == '}') && !stack.isEmpty()) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public boolean isSymbol(char c) {
        if (c == '(' || c == '{' || c == '['
                || c == ')' || c == ']' || c == '}') {
            return true;
        }
        return false;
    }

    public static void main1(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int m = Integer.parseInt(s.split(" ")[0]);
        int n = Integer.parseInt(s.split(" ")[1]);
        int[][] array = new int[m][n];
        int line = 0;
        while (line < m) {
            String item = scanner.nextLine();
            String[] s1 = item.split(" ");
            for (int i = 0; i < s1.length; i++) {
                int ele = Integer.parseInt(s1[i]);
                array[line][i] = ele;
            }
            // 表示矩阵；
            line++;
        }

        log.info("{}", array);
        System.out.println(maxArray(array));
    }

    @Test
    public void testMatrix() {
        int[][] matrix = {{-3, 5, -1, 5},
                        {2, 4, -2, 4},
                        {-1, 3, -1, 3}};
        maxArray(matrix);

    }

    /**
     * ---**--|--**---|--------------------
     * **  |  (i,j)   |
     * -----|-----|--------------------
     *
     * @param matrix
     * @return
     */
    public static int[] maxArray(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        //二维前缀和
        int[][] preSum = new int[n + 1][m + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                // 为什么要这么用呢？ 对角的用原来的；首先要清楚他的定义是什么？
                preSum[i][j] = matrix[i - 1][j - 1] + preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1];
            }
        }
        //开始最大子序和global
        int global = Integer.MIN_VALUE;
        int[] ret = new int[4];
        //先固定上下两条边
        for (int top = 0; top < n; top++) {
            for (int bottom = top; bottom < n; bottom++) {
                int localMax = 0, left = 0;
                //然后从左往右一遍扫描找最大子序和
                for (int right = 0; right < m; right++) {
                    //利用presum快速求出localMax
                    localMax = preSum[bottom + 1][right + 1] + preSum[top][left] - preSum[bottom + 1][left] - preSum[top][right + 1];
                    //如果比 gobal大，更新
                    if (global < localMax) {
                        global = localMax;
                        //记录下坐标位置！！
                        ret[0] = top;
                        ret[1] = left;
                        ret[2] = bottom;
                        ret[3] = right;
                    }
                    //如果不满0，前面都舍弃，从新开始计算，left更新到right+1，right下一轮递增之后left==right
                    if (localMax < 0) {
                        localMax = 0;
                        left = right + 1;
                    }
                }
            }
        }
        return ret;
    }

    public static int findMinMoveDistance(int[] heights) {
        int n = heights.length;
        int[][] dp = new int[n][2]; // dp[i][0]表示以"高"开头的最小移动距离，dp[i][1]表示以"矮"开头的最小移动距离

        dp[0][0] = 0; // 以"高"开头的第一个位置不需要移动
        dp[0][1] = 0; // 以"矮"开头的第一个位置不需要移动

        for (int i = 1; i < n; i++) {
            if (i % 2 == 0) {
                // 当前位置要求是"高"，前一个位置必须是"矮"
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = dp[i - 1][1] + 1;
            } else {
                // 当前位置要求是"矮"，前一个位置必须是"高"
                dp[i][0] = dp[i - 1][0] + 1;
                dp[i][1] = dp[i - 1][1];
            }
        }

        // 最小移动距离为以"高"或"矮"开头的最小移动距离中的较小值
        return Math.min(dp[n - 1][0], dp[n - 1][1]);
    }

    @Test
    public void minHeight() {
        int[] arr = {4, 3, 5, 7, 8};
        log.info("{}", findMinMoveDistance(arr));
    }

    public static void main3(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = Integer.parseInt(scanner.nextLine());
        if (m <= 1 || m >= 100){
            System.out.println("ERROR");
            return;
        }
        System.out.println(yuesefu(100, 3));
    }

    public static Object[] josfer(int m) {
        boolean[] used = new boolean[100];
        int count = 100, index = 0;
        while (count >= m) {
            int cnt = 0;
            while (cnt < m-1) {
                if (!used[index]) {
                    cnt++;
                }
                if (cnt < m) {
                    index = (index+1)%100;
                }
            }
            used[index] = true;
            count--;
            while (used[index]) {
                index = (index+1)%100;
            }
        }
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < used.length; i++) {
            if (!used[i]) {
                integers.add(i);
            }
        }
        return integers.toArray();
    }

    public static List<Integer> yuesefu(int totalNum, int countNum) {
        // 初始化人数
        List<Integer> start = new ArrayList<Integer>();
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 1; i <= totalNum; i++) {
            start.add(i);
        }
        // 从索引0开始计数
        int i = 0;
        while (start.size() > 0) {
            //从0位置计算第三个元素位置在哪里
            i = i + countNum - 1;
            // 通过取模与运维获得下一个索引位置在哪里，避免索引越界
            i = i % (start.size());
            // 判断是否只剩下二个
            if (start.size() < countNum) {
                res.addAll(start);
//                System.out.println("start= " + start);
                break;
            } else {
                start.remove(i);
            }
        }
        return res;
    }

    public static int[] findSurvivor(int n, int k) {

        boolean[] people = new boolean[n]; // 使用布尔数组表示每个人是否还在圈内
        for (int i = 0; i < n; i++) {
            people[i] = true; // 初始化所有人都在圈内
        }

        int count = 0; // 记录出圈的人数
        int index = 0; // 当前报数的人的索引

        while (count < n - k + 1) { // 循环直到只剩下一个人
            int step = 0; // 记录报数的步数

            while (step < k) {
                if (people[index]) { // 如果当前位置的人还没有出圈
                    step++;
                }
                if (step < k) {
                    index = (index + 1) % n; // 移动到下一个人
                }
            }

            // 找到要出圈的人，标记为已出圈
            people[index] = false;
            count++;

            // 移动到下一个还没出圈的人
            while (!people[index]) {
                index = (index + 1) % n;
            }
        }
        List<Integer> res = new ArrayList<>();
        // 找到最后剩下的人的编号
        for (int i = 0; i < n; i++) {
            if (people[i]) {
                res.add(i + 1); // 编号是从1开始的
            }
        }

        return res.stream().mapToInt(m->m).toArray(); // 出错时返回-1
    }

    @Test
    public void testSwap() {
        int a  = 100;
        int b = 3;
        System.out.println(Arrays.toString(findSurvivor(a, b)));
    }

    /**
     * 跳个子最大的分数
     *
     * @param arr
     * @param n
     * @return
     */
    public int solveI(int[] arr,int n) {
        int[] dp = new int[n];
        dp = arr;
        /**
         * 初始的状态是原来的分数
         * 转移的方程是
         * dp[i] = dp(i-2) + arr[i];
         *
         */
        for (int i = 0; i < n; i++) {
            if (i -  2 >= 0) {
                dp[i] = dp[i-2] + arr[i];
            }
        }
        log.info("{}", dp);
        return Arrays.stream(dp).max().getAsInt();
    }

    @Test
    public void testJumpI() {
//        int[] arr = {1,2,3,1};
        int[] arr = {2,7,9,3,1};
        System.out.println(solveIS(arr, 5));
    }

    public int solveIS(int[] nums, int n) {
        if (n <= 2) {
            // 如果格子数量小于等于2，直接返回最大的分数
            return n == 1 ? nums[0] : Math.max(nums[0], nums[1]);
        }

        int[] dp = new int[n];
        dp[0] = nums[0]; // 第一个格子的得分就是它本身
        dp[1] = Math.max(nums[0], nums[1]); // 第二个格子的得分是两个格子中的较大分数

        for (int i = 2; i < n; i++) {
            // 对于每个格子，有两种选择：跳过它或者跳到它
            // 如果跳过它，得分等于前一个格子的得分
            // 如果跳到它，得分等于前两个格子的得分加上当前格子的分数
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[n - 1];
    }

    /**
     * 最大的
     *
     * @param nums
     * @param n
     * @return
     */
    public int solveJumpII(int[] nums, int n) {
        return n;
    }

    public int maxHandle() {
        return 0;
    }

    @Test
    public void testHandle() {
//        String s = "128";
//        int i = Integer.parseInt(s, 2);
//        System.out.println(i);
//        int i = 128<<24 + 255 << 8 + 255;
        String res = convertToDec(new long[]{128L, 0L, 255L, 255L});
        System.out.println(res);
        String s = Long.toHexString(Long.parseLong(res));
        System.out.println("ox"+s.toUpperCase());
    }

    private static String convertToDec(long[] ipParts) {
        long result = (ipParts[0] << 24) | (ipParts[1] << 16) | (ipParts[2] << 8) | ipParts[3];
        return result+"";
    }

    public static boolean isValidIp(String[] strings) {
        int first = Integer.parseInt(strings[0]);
        int second = Integer.parseInt(strings[1]);
        int third = Integer.parseInt(strings[2]);
        int four = Integer.parseInt(strings[3]);
        if (isBetween(first, second, third, four)) {
            return true;
        }
        return false;
    }

    public static boolean isBetween(int first, int second, int third, int four) {
        return (first>= 1 && first <= 128) && isBetween255(second)
                && isBetween255(third) && isBetween255(four);
    }

    private static boolean isBetween255(int third) {
        return third>=1 && third<=255;
    }

    // 前k个最小值？

    // 第三大的数
    public int thirdMax(int[] nums) {
        int third = Integer.MIN_VALUE;
        for (int num : nums) {

        }
        return third;
    }

    /**
     * 2个字符必须要马努01.要是不满足。判断是否第一个为0;1)是加入。2）不加入；
     *
     * @param s
     * @return
     */
    public int maxLengthOfPattern(String s) {
        if (s.length() < 3 || s.length() > 1024) {
            return -1;
        }
        // 判断是否有010
        if (!s.contains("010")) {
            return -1;
        }
        // 把01作为一个整体来进行处理，有的是
        return -1;
    }

    /**
     * 一个数字，多个字母；
     *
     * @param s
     * @return
     */
    public int maxLengthOfSpecial(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        String patternDigit = "\\d+";
        String patternChar = "\\w+";
        Pattern compile = Pattern.compile(patternChar);
        Pattern compileDigit = Pattern.compile(patternDigit);
        if (compile.matcher(s).find() ||  compileDigit.matcher(s).find()) {
            return -1;
        }
        // 处理逻辑。需要有什么呢？
        int left = 0, right = 0, max = Integer.MIN_VALUE;
        while (right < s.length()) {
            while (right < s.length() && isChar(s.charAt(right)) &&
                    isChar(s.charAt(right))) {
                right++;
            }
            left = right;

        }

        return 4;
    }

    public boolean isChar(char c) {
        return (c >= 'a' && c <= 'z') || (c <= 'Z' && c >= 'A');
    }

    @Test
    public void testChar() {
//        System.out.println(findLongestSubstring("111ab"));
    }

    //其实就是滑动的窗口，要获取到所有的值呢？

    /**
     * 最小 --> 最大的
     *
     * @return
     */
    public int maxWindow() {
        return 0;
    }

    // 全排列；
    // 高矮个子的排列。

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        int len = maxLengthWith(line);
        System.out.println(len);
    }

    /**
     *1、 只包含1个字母(a~z, A~Z)，其余必须是数字；
     *2、 字母可以在子串中的任意位置；
     *
     * @param line
     * @return
     */
    public static int maxLengthWith(String line) {
        char[] chars = line.toCharArray();

        ArrayList<Integer> integers = new ArrayList<>();
        // 关键点：第一个初始值，防止出现只有一个字母或者没有字母，无法进入循环的情况
        integers.add(-1);
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if ((65 <= aChar && aChar <= 90) || (97 <= aChar && aChar <= 122)) {
                integers.add(i); // 存放所有字母出现的索引值
            }
        }
        // 关键点：第一个初始值，防止出现只有一个字母或者没有字母，无法进入循环的情况
        integers.add(chars.length);

        int len = -1;
        for (int i = 0; i < integers.size() - 2; i++) {
            int temp = integers.get(i + 2) - integers.get(i); // 每隔一个字母差值，即可判断可以放多少个数字
            // 这里的意思是最少是要存放一个一个数字的。
            if (temp > 2) {
                // 这里要去除一个字母的。仅仅保留一个字母
                if (temp - 1 > len) {
                    len = temp - 1; // 出现更长的长度时才替换
                }
            }
        }
        return len;
    }

    @Test
    public void testLen() {
        String s = "abcdef";
        String s1 = "111av";
        String s2 = "sss";
        System.out.println(maxLengthWith(s2));
    }

    public static void salu(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (i % 2 == 0 && nums[i] < nums[i + 1]) {
                //如果位置是高位置，但下一个位置大于当前位置，替换
                int tmp = nums[i];
                nums[i] = nums[i + 1];
                nums[i + 1] = tmp;
            }
            if (i % 2 == 1 && nums[i] > nums[i + 1]) {
                //如果位置是矮位置，但下一个位置小于当前位置，替换
                int tmp = nums[i];
                nums[i] = nums[i + 1];
                nums[i + 1] = tmp;
            }
        }
    }
}
