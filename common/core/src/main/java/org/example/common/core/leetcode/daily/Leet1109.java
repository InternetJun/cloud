package org.example.common.core.leetcode.daily;

import lombok.extern.slf4j.Slf4j;
import org.example.common.core.util.CommonUtil;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/8 10:44
 */
@Slf4j
public class Leet1109 {
    /**
     * 在 Java 中，队列接口提供了多种方法，例如 add、offer、put 等，它们在处理元素时的阻塞行为是不同的。下面是这些方法的主要特点以及一个用于记住它们的方法：
     * add 方法：
     * add 方法用于将元素添加到队列，如果队列已满，则会抛出异常。
     * 阻塞：add 不会阻塞，而是在队列已满时立即抛出 IllegalStateException 异常。
     * 记忆方法："Add and explode" - add 添加元素，如果队列已满，会引发异常，就像一颗定时炸弹。
     * offer 方法：
     * offer 方法用于将元素添加到队列，如果队列已满，则返回 false。
     * 阻塞：offer 不会阻塞，而是在队列已满时返回 false。
     * 记忆方法："Offer and be polite" - offer 提供元素，如果队列已满，会礼貌地返回 false。
     * put 方法：
     * put 方法用于将元素添加到队列，如果队列已满，则会一直等待，直到有空间可用。
     * 阻塞：put 会阻塞，直到队列有足够的空间来容纳元素。
     * 记忆方法："Put and patiently wait" - put 放入元素，然后耐心等待，直到有足够的空间。
     */

//    public boolean offer(E e) {
//        if (null == e) {
//            throw new NullPointerException();
//        } else {
//            long mask = this.mask;
//            long producerLimit = this.lvProducerLimit(); // 获取⽣产者索引最⼤限制
//            long pIndex;
//            long offset;
//            do {
//                pIndex = this.lvProducerIndex(); // 获取⽣产者索引
//                if (pIndex >= producerLimit) {
//                    offset = this.lvConsumerIndex(); // 获取消费者索引
//                    producerLimit = offset + mask + 1L;
//                    if (pIndex >= producerLimit) {
//                        return false; // 队列已满
//                    }
//                    this.soProducerLimit(producerLimit); // 更新 producerLimit
//                }
//            } while(!this.casProducerIndex(pIndex, pIndex + 1L)); // CAS 更新⽣产
//            offset = calcElementOffset(pIndex, mask); // 计算⽣产者索引在数组中下标
//            UnsafeRefArrayAccess.soElement(this.buffer, offset, e); // 向数组中放⼊
//            return true;
//        }
//    }
    public void format(int[][] grid) {

        CommonUtil.formatAndDisplayArray(grid);
    }

    @Test
    public void main() {
        int[][] grid = {{0, 2, 0, 0, 0, 0, 0}, {0, 0, 0, 2, 2, 1, 0}, {0, 2, 0, 0, 1, 2, 0}, {0, 0, 2, 2, 2, 0, 2}, {0, 0, 0, 0, 0, 0, 0}};
        int[][] grid1 = {{0, 0, 0, 0}, {0, 1, 2, 0}, {0, 2, 0, 0}};
//
        format(grid1);
    }

    /**
     * 最多的停留时间。
     * <p>
     * 有火会进行一个蔓延。
     * 1，给出几分钟，可以生成对应的图？
     * 2，从（0,0） -> (m-1, n-1)是什么过程？有几条路径，路径分别是什么？
     * 3，时间推移和对应的图有什么联系点？？
     * </p>
     *
     * @param grid
     * @return
     */
    public int maximumMinutes(int[][] grid) {
        return 0;
    }

    @Test
    public void fireTest() {
        int[][] grid1 = {{0, 0, 0, 0}, {0, 1, 2, 0}, {0, 2, 0, 0}};
        spreadFire(grid1, 1);
    }

    public void spreadFire(int[][] grid, int minutes) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    dfsSpread(grid, i, j, minutes);
                }
            }
        }
        CommonUtil.formatAndDisplayArray(grid);
    }

    /**
     * <h1>和岛屿的数量是类似的</h1>
     * minute对每个过程进行记录。
     *
     * @param grid
     * @param row
     * @param col
     * @param minutes
     */
    public void dfsSpread(int[][] grid, int row, int col, int minutes) {
        // 特殊判断，边界，墙体
        if (grid[row][col] == 2 || !isBoundary(grid.length, grid[0].length, col, row) || minutes == 0) {
            return;
        }
        // 对周边的元素进行赋值。1min进行的扩散
        grid[row][col] = 1;
        dfsSpread(grid, row, col - 1, minutes - 1);
        dfsSpread(grid, row, col + 1, minutes - 1);
        dfsSpread(grid, row + 1, col, minutes - 1);
        dfsSpread(grid, row - 1, col, minutes - 1);
    }

    public boolean isBoundary(int m, int n, int col, int row) {
        if (row >= m || row < 0 || col >= n || col < 0) {
            return false;
        }

        return true;
    }

    @Test
    public void testArea() {
        int[][] area = {{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0}, {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
        maxAreaOfIsland(area);
    }

    public int maxAreaOfIsland(int[][] grid) {
        // 记录岛屿的最大面积
        int res = 0;
        int m = grid.length, n = grid[0].length;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (grid[i][j] == 1) {
                    // 淹没岛屿，并更新最大岛屿面积
                    res = Math.max(res, dfs(grid, i, j, 0));
                }
            }
        }
        return res;
    }

    // 淹没与 (i, j) 相邻的陆地，并返回淹没的陆地面积
    int dfs(int[][] grid, int i, int j, int minutes) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n) {
            // 超出索引边界
            return 0;
        }
        if (grid[i][j] == 0) {
            // 已经是海水了
            return 0;
        }
        // 将 (i, j) 变成海水
        grid[i][j] = 0;
        format(grid);
        // 怎么进行一个切面。对第n次修改的所有位置进行记录
        log.info("位置i:{},j:{}已被修改；第{}次修改中", i, j, minutes);
        System.out.println();
        return dfs(grid, i + 1, j, minutes + 1)
                + dfs(grid, i, j + 1, minutes + 1)
                + dfs(grid, i - 1, j, minutes + 1)
                + dfs(grid, i, j - 1, minutes + 1) + 1;
    }

    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int n, m;
    int[][] g;
    public int maximumMinutesSoulution(int[][] grid) {
        g = grid;
        n = g.length; m = g[0].length;
        int[][] fg = new int[n][m];
        int[][] pg = new int[n][m];
        final ArrayDeque<int[]> fire = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (g[i][j] == 1) {
                    fg[i][j] = 1;
                    fire.addLast(new int[]{i, j});
                }
            }
        }
//        bfs(fire, fg);
        final ArrayDeque<int[]> people = new ArrayDeque<>();
        people.addLast(new int[]{0,0});

        return 0;
    }
}
