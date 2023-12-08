package org.example.common.core.leetcode.daily.december;

import com.sun.media.sound.RIFFInvalidDataException;
import org.example.common.core.util.CommonUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:
 * @time: 2023/12/6 15:42
 */
public class Leet1206 {
    /**
     * <h1>最小的代价</h1>
     *
     * @param n
     * @param edges
     * @param price
     * @param trips
     * @return
     */
    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        return 0;
    }

    /**
     * [01 13 23  45 40]
     *
     * 13 23 ==> 3
     *
     * 01  ==> 1
     *
     * 40 ==> 0
     * 45 ==> 5
     * <p>
     *     可以用bfs。广度优先算法。
     *     还需要比较的是Mah.min(x1，x2)
     *    <b>要是有路径到达的话就不要处理了</b>
     * </p>
     * @param n
     * @param connections
     * @return
     */
    public int minReorder(int n, int[][] connections) {
        int change = 0;
        /**首先是什么中止条件？请你帮助重新规划路线方向，
         * 使每个城市都可以访问城市 0 。返回需要变更方向的最小路线数。
         * 终点的圈有几个： 3  0  5==    终点到0需要有几步？
         *  3 --》 0 （2）
         *  5 --》 0 （1）
         *
         *  0 2 4
         *  4--》 0 （）
         *  2 --》0 （）
         *
         *  第一个0是一进一出
         *  第二0是一个进
         *
         *  50 -> 3 √
         *  4 --> 50 ×
         * */
        dfs(connections, 0, change, n-1);
        return change;
    }

    /**
     * 用深度处理。但是是错误的递归，问题是什么？
     */
    public void dfs(int[][] connections, int start,int count, int edge) {
        // 什么时候终止
        if (edge == 0) {
            return;
        }
        // 记录下开始的
        for (int[] connection : connections) {
            int s = connection[0];
            int end = connection[1];
            if (start == s) {
                dfs(connections, end, count++, edge--);
            } else if (end == start) {
                dfs(connections, s, count, edge--);
            }
        }
    }

    private List<int[]>[] g;

    public int minReorderSolution(int n, int[][] connections) {
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : connections) {
            int a = e[0], b = e[1];
            // a 是源头，所以要加上1的
            g[a].add(new int[] {b, 1});
            g[b].add(new int[] {a, 0});
        }
        return dfs(0, -1);
    }

    // 原点开始
    private int dfs(int a, int farther) {
        int ans = 0;
        for (int[] e : g[a]) {
            int start = e[0], end = e[1];
            if (start != farther) {
                ans += end + dfs(start, a);
            }
        }
        return ans;
    }

    @Test
    public void minChange(){
        int[][] nums = CommonUtil.parseStringToArray("[[0,1],[1,3],[2,3],[4,0],[4,5]]");
        System.out.println(Arrays.deepToString(nums));
    }
}
