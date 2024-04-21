package org.example.common.core.leetcode.twentyFour.april;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

@Slf4j
public class Leet0421 {
    private List<List<Integer>> lists = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        /**
         * 1 -9 特殊情况，只能使用一次的。
         * 不能一样的组合。
         * k = 4;n = 1 ==> 就会返回空列表啊。
         * [[1, 2, 6], [1, 3, 5], [1, 3, 4]]
         * [1, 1, 6], [1, 2, 4], [1, 1, 5], [2, 3, 4], [2, 2, 3], [1, 1, 3], [1, 1, 2]
         */
        if (minSumByk(k) > n) {
            return new ArrayList<>();
        }

        /**必须要有唯一性啊。要记住*/
        boolean[] used = new boolean[9];
        dfs(k, n, new LinkedList<>(), used, 1);
        return lists;
    }

    @Test
    public void test() {
        List<List<Integer>> lists = combinationSum3(9, 45);
        log.info("{}", lists);
//        List<List<Integer>> lists1 = combinationSum3(4, 1);
//        log.info("{}", lists1);
    }

    public void dfs(int k, int n, LinkedList<Integer> list, boolean[] used, int ind) {
        if (list.size() == k && n == 0) {
            // 去重处理啊。还有需要的是
            Collections.sort(list);
            if (!lists.contains(list)) {
                lists.add(new ArrayList<>(list));
            }
            return;
        }
        if (list.size() >= 3 || ind > n) {
            return;
        }
        for (int i = ind; i <= 9; i++) {
            // 会加入很多的元素。需要做的一件事情是什么呢？有个数的限制，还有sum的限制，所以要做的是？？
            /**
             *  [[1, 2, 4], [1, 4, 2], [2, 1, 4], [2, 4, 1], [4, 1, 2], [4, 2, 1]]
             *  就是没有控制好方向感的。
             * */
            if (!used[i - 1]) {
                used[i - 1] = true;
                list.add(i);
                dfs(k, n - i, list, used, ind++);
                list.removeLast();
                used[i - 1] = false;
            }
        }
    }

    public int minSumByk(int k) {
        int sum = 0;
        for (int i = 1; i <= k; i++) {
            sum += i;
        }
        return sum;
    }

    public List<List<Integer>> combinationSum3Solution(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        dfs(1, k, n, path, res);
        return res;
    }

    private void dfs(int j, int k, int n, Deque<Integer> path, List<List<Integer>> res) {
        if (n == 0 && k == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        if (k < 1) {
            return;
        }

        /**
         * 这里的i=j可以防止一个回马枪。就是不会重复。
         *  要是想重复不同的顺序的话就用
         * for(int i = 0; i < 10; i++) {
         *
         * }
         * 2.j > n的话直接剪枝
         * 3，用k作为选择，n作为sum。
         *
          */
        for (int i = j; i < 10; i++) {
            if (j > n) {
                break;
            }
            path.add(i);
            dfs(i + 1, k - 1, n - i, path, res);
            path.removeLast();
        }
    }
}
