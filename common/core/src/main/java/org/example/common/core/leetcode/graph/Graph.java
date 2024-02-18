package org.example.common.core.leetcode.graph;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/8/31 15:39
 */
public class Graph {
    /**
     * 小岛的数量
     * <p>
     * 什么情况是说明是可以一个陆地的？
     * 其实就是说1的连片的数据是多少的。
     * </p>
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    res++;
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }

    public void dfs(char[][] grid, int r, int c) {
        if (r >= grid.length || c >= grid[0].length) {
            return;
        }
        if (grid[r][c] == '0') {
            return;
        }
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
        grid[r][c] = 0;
    }

    public int islandPerimeter(int[][] grid) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    // 题目限制只有一个岛屿，计算一个即可
                    return dfs(grid, r, c);
                }
            }
        }
        return 0;
    }

    int dfs(int[][] grid, int r, int c) {
        // 函数因为「坐标 (r, c) 超出网格范围」返回，对应一条黄色的边
        if (!inArea(grid, r, c)) {
            return 1;
        }
        // 函数因为「当前格子是海洋格子」返回，对应一条蓝色的边
        if (grid[r][c] == 0) {
            return 1;
        }
        // 函数因为「当前格子是已遍历的陆地格子」返回，和周长没关系
        if (grid[r][c] != 1) {
            return 0;
        }
        grid[r][c] = 2;
        return dfs(grid, r - 1, c)
                + dfs(grid, r + 1, c)
                + dfs(grid, r, c - 1)
                + dfs(grid, r, c + 1);
    }

    // 判断坐标 (r, c) 是否在网格中
    boolean inArea(int[][] grid, int r, int c) {
        return 0 <= r && r < grid.length
                && 0 <= c && c < grid[0].length;
    }

    // 记录下个数。
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int area = dfsArea(grid, i, j);
                    res = Math.max(area, res);
                }
            }
        }
        return res;
    }

    int dfsArea(int[][] grid, int c, int r) {
        if (!inArea(grid, r, c)) {
            return 0;
        }
        if (grid[c][r] == '0') {
            return 0;
        }
        grid[c][r] = '0';
        return
                dfsArea(grid, c + 1, r) +
                        dfsArea(grid, c - 1, r) +
                        dfsArea(grid, c, r + 1) +
                        dfsArea(grid, c, r - 1) + 1;
    }


}
