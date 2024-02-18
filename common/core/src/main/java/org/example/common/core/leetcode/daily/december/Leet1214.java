package org.example.common.core.leetcode.daily.december;

import lombok.extern.slf4j.Slf4j;
import org.example.common.core.util.CommonUtil;
import org.junit.Test;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description: 二维前缀和二维差分
 * @time: 2023/12/14 13:02
 */
@Slf4j
public class Leet1214 {
    /**
     * <href>https://leetcode.cn/problems/stamping-the-grid/solutions/2562069/yong-you-piao-tie-man-wang-ge-tu-by-leet-kiaq/?envType=daily-question&envId=2023-12-14</href>
     * <p>
     * 覆盖所有 空 格子。
     * 不覆盖任何 被占据 的格子。
     * 我们可以放入任意数目的邮票。
     * 邮票可以相互有 重叠 部分。 ==> 说明有多个邮票。
     * 邮票不允许 旋转 。
     * 邮票必须完全在矩阵 内
     * </p>
     * <p>
     * 就是找出在给定格子中，最大的面积。
     * 还有要覆盖所有的格子。
     * <p>
     * 思路是什么。要怎么解决？
     * sum（i, j） = sum(i-1, j) + sum(i, j - 1) - sum(i-1, j-1) + grid(i,j)
     * <b>贴邮票时，如何快速判断右下角固定范围内不存在被占据的格子，而都是空格子呢？</b><br>
     * <b>最后做检查时，如何快速判断每个空格子都被邮票覆盖呢？</b>
     *
     * </p>
     *
     * @param grid
     * @param stampHeight
     * @param stampWith
     * @return
     */
    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWith) {
        int m = grid.length;
        int n = grid[0].length;
        if (m < stampWith || n > stampHeight) {
            return false;
        }
        // 处理数据。中间位置、右边、左边方向。0~m+1;
        int[][] sum = new int[m + 2][n + 2];
        int[][] diff = new int[m + 2][n + 2];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                /** 为什么要记上grid[i-1][j-1];
                 * 处理边界情况： 扩展后的数组使得边界上的前缀和计算更加方便。在计算 prefix_sum[i][j] 时，不需要额外判断 (i-1, j-1) 是否越界，因为所有的索引都在有效范围内。
                 *
                 * 简化代码逻辑： 扩展后的数组简化了代码实现，无需在计算前缀和时处理边界条件，避免了许多特殊情况的讨论。
                 *
                 * 提高计算效率： 在一些算法问题中，可能需要频繁地查询某个区域的前缀和。通过扩展数组，可以避免在每次查询时都进行越界判断，从而提高计算效率。
                 */
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + grid[i - 1][j - 1];
            }
        }
        CommonUtil.formatAndDisplayArray(sum);
        for (int i = 1; i + stampHeight - 1 <= m; i++) {
            for (int j = 1; j + stampWith - 1 <= n; j++) {
                int x = i + stampHeight - 1;
                int y = j + stampWith - 1;
                if (sum[x][y] - sum[x][j - 1] - sum[i - 1][y] + sum[i - 1][j - 1] == 0) {
                    diff[i][j]++;
                    diff[i][y + 1]--;
                    diff[x + 1][j]--;
                    diff[x + 1][y + 1]++;
                }
            }
        }
        log.info("======================");
        CommonUtil.formatAndDisplayArray(diff);
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 这里的用意是什么？
                /**
                 * 而在贴邮票的问题中，我们以 (i,j)(i,j)(i,j) 为左上角贴了一张邮票，
                 * 只到右下角 (i+stampHeight,j+stampWidth)(i+\textit{stampHeight},
                 * j+\textit{stampWidth})(i+stampHeight,j+stampWidth) 的范围内计数 +1。
                 * 因此我们定义一个二维数组 diff\textit{diff}diff，如果要在 (i,j)(i,j)(i,j) 处贴邮票，就令 diff(i,j)\textit{diff}(i,j)diff(i,j) 和 diff(x+1,y+1)\textit{diff}(x+1,y+1)diff(x+1,y+1) 加 111，令 diff(i,y+1)\textit{diff}(i,y+1)diff(i,y+1) 和 diff(x+1,j)\textit{diff}(x+1,j)diff(x+1,j) 减 111。
                 *
                 */
                diff[i][j] += diff[i - 1][j] + diff[i][j - 1] - diff[i - 1][j - 1];
                if (diff[i][j] == 0 && grid[i - 1][j - 1] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void main() {
        final int[][] ints = CommonUtil.convertToArray("[[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0]]", int[][].class);
        possibleToStamp(ints, 4, 3);
    }

    /**
     * row、col作为四个角，可以满足要求。
     *
     * @param row
     * @param col
     * @return
     */
    public boolean isArea(int row, int col, int[][] grid, int stampHeight, int stampWidth) {
        int m = grid.length;
        int n = grid[0].length;
        // 下左低角。
        int downLeftAreaUp = row - stampHeight;
        int downLeftAreaRight = col + stampWidth;
        // 下右低角。
        int downRightAreaUp = row - stampHeight;
        int downRightAreaRight = col - stampWidth;
        // 上左低角。
        int upLeftAreaUp = row + stampHeight;
        int upLeftAreaRight = col + stampWidth;
        // 上右低角。
        int upRightAreaUp = row + stampHeight;
        int upRightAreaRight = col - stampWidth;
        // 在此范围内有一个满足要求的就好。
        return true;
    }
}
