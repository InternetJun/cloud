package org.example.common.core.leetcode.algorithm;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/9/10 16:25
 */
@Slf4j
public class ZhaoShang {

    public ArrayList<String> timeSort (ArrayList<String> times) {
        List<String> collect = times.stream().sorted(((Comparator<String>) (o1, o2) -> Integer.parseInt(o1.split(":")[2]) - Integer.parseInt(o2.split(":")[2]))
                        .thenComparing(o->Integer.parseInt(o.split(":")[1]))
                        .thenComparing(o->Integer.parseInt(o.split(":")[0])))
                .collect(Collectors.toCollection(LinkedList::new));
        ArrayList<String> res = new ArrayList<>(collect);
        return res;
    }

    @Test
    public void main() {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("12:30:10");
        strings.add( "12:15:12");
        strings.add("15:20:14");
        System.out.println(timeSort(strings));
    }

    public String maxDictionaryOrder (String s) {
        // write code here
        int[] china = new int[26];
        Map<Character, Integer> indexMap = new HashMap<>();
        StringBuffer stringBuffer = new StringBuffer(s).reverse();
        // 初始化最大的字符。
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            china[c - 'a']++;
            indexMap.put(c, Math.max(indexMap.getOrDefault(c, 0), i));
        }
        StringBuilder res = new StringBuilder();
        int pre = Integer.MIN_VALUE;
        for (int i = 25; i >= 0; i--) {
            // 还有一个约束，下标一定要符合要求。
            if (china[i] > 0) {
                char temp = (char)(i + 'a');
                Integer localIndex = indexMap.get(temp);
                if (Integer.MIN_VALUE == pre) {
                    pre = localIndex;
                } else {
                    pre = indexMap.get(res.charAt(res.length()-1));
                }
                // 初始化 第一个不为0的值 后续要进行一个更新

                // 比较值
                if (localIndex < pre) {
                    continue;
                }

                // 什么情况是要比较的呢?
                if (china[i] + localIndex > s.length()) {
                    for (int j = 0; j < s.length() - localIndex; j++) {
                        res.append(temp);
                    }
                } else {
                    for (int j = 0; j < china[i]; j++) {
                        res.append(temp);
                    }
                }
            }
        }
        return res.toString();
    }

    public String maxDictionarySubStrOrder(String s) {
        String result = null;
        StringBuilder resultBuilder = new StringBuilder();
        if (s == null || s == "") {
            return result;
        }
        char[] arr = s.toCharArray();
        int maxIndex = 0;
        //一次for循环找到当前最大的char，循环一次则子串要从当前最大串往后找
        //如abcacacabaaaba，一次循环c最大,下标为2，则第二次循环从c后开始,下标为3，找到最大值c，下标为4
        for (int i = maxIndex; i < arr.length; i++) {
            //最大值初始化为子串首字母
            char max = arr[i];
            //下次for循环从最大char往后找
            //每次循环完了要把最大值的index往后挪
            maxIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] > max) {
                    max = arr[j];
                    maxIndex = j;
                }
            }
            //要修改i的值，即改变外层循环
            i = maxIndex;
            resultBuilder.append(max);
        }

        result = resultBuilder.toString();
        return result;
    }

    /**
     * <h1>数字进行字典排序，求第k个的数据</h1>
     * <p>
     *   给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
     * </p>
     *
     * @param n
     * @param k
     * @return m
     */
    public int findKthNumber(int n, int k) {
        int ans = 1;
        while (k > 1) {
            int cnt = getSteps(ans, n);
            // cnt的意思为？
            if (cnt < k) {
                k -= cnt;
                ans++;
            } else {
                k--;
                ans *= 10;
            }
        }
        return ans;
    }
    public int getSteps(int curr, long n) {
        int steps = 0;
        long first = curr;
        long last = curr;
        while (first <= n) {
            steps += Math.min(last, n) - first + 1;
            first = first * 10;
            last = last * 10 + 9;
        }
        return steps;
    }

    /**
     * 这个的for循环的意思在哪里？
     *
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ret = new ArrayList<Integer>();
        int number = 1;
        for (int i = 0; i < n; i++) {
            ret.add(number);
            if (number * 10 <= n) {
                number *= 10;
            } else {
                while (number % 10 == 9 || number + 1 > n) {
                    number /= 10;
                }
                number++;
            }
        }
        return ret;
    }

    @Test
    public void kthTest() {
        final ArrayList<Integer> list = new ArrayList<>();
        val list1 = findKthNumber(13, 5);
        log.info("{}", list1);
    }

    public boolean canJump(int[] nums) {
        int n = nums.length;
        int farthest = 0;
        for (int i = 0; i < n - 1; i++) {
            // 不断计算能跳到的最远距离
            farthest = Math.max(farthest, i + nums[i]);
            // 可能碰到了 0，卡住跳不动了
            if (farthest <= i) {
                return false;
            }
        }
        return farthest >= n - 1;
    }

    @Test
    public void  testJump() {
        int[] nums = {3,2,1,0,4};
        System.out.println(canJump(nums));
    }

    public List<String> generateParenthesis(int n) {
        if(n == 0){
            return new ArrayList();
        }
        List<String> list = new ArrayList<>();
        dfs(n, n, list, "");
        return list;
    }

    public void dfs(int left, int right, List<String> list, String s) {
        if (left < 0 || right < 0) {
            return;
        }
        if(left == 0 && right == 0) {
            list.add(s);
        }
        if(left > right) {
            return;
        }
        dfs(left - 1, right, list, s+"(");
        dfs(left, right - 1, list, s+")");
    }

    @Test
    public void testPa() {
        System.out.println(generateParenthesis(3));
    }


}
