package org.example.common.core.leetcode.daily;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/11 10:45
 */
@Slf4j
public class Leet1111 {
    /**
     * 获取到
     * 24 36 57 ==> 3次的替换数据
     * 24 35  ==》 1次；
     * 24 37 56==》 2次；
     *
     * @param row
     * @return
     */
    public int minSWapCouple(int[] row) {
        final ArrayList<Integer[]> list = new ArrayList<>();
        for (int i = 0; i < row.length; i=i+2) {
            if (Math.abs(row[i]-row[i+1]) != 1) {
                Integer[] tmp = new Integer[]{row[i], row[i+1]};
                list.add(tmp);
            }
        }

        // 排序
        list.sort((Comparator.comparingInt(o -> o[0])));
        int ans = 0;
        return ans;
    }

    int[] p = new int[70];
    void union(int a, int b) {
        log.info("要把b的元素值付给a.赋值的值a:{},b:{}", find(a), find(b));
        p[find(a)] = p[find(b)];
    }
    int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
    // 为什么想要用并查级呢？因为都有一样的祖先的
    public int minSwapsCouples(int[] row) {
        int n = row.length, m = n / 2;
        for (int i = 0; i < m; i++) {
            p[i] = i;
        }
        log.info("p元素{}，长度为：{}", p,p.length);
        for (int i = 0; i < n; i += 2) {
            log.info("==要计算的值是：a:{};b:{}", row[i] / 2, row[i + 1] / 2);
            union(row[i] / 2, row[i + 1] / 2);
        }
        log.info("{}", p);
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            log.info("find的值为：{}; i的值为多少：{}",find(i), i);
            if (i == find(i)) {
                cnt++;
            }
        }
        return m - cnt;
    }

    @Test
    public  void main(){
        int[] nums = {0,1,2,4,3,6,5,7};
        System.out.println(minSwapsCouples(nums));
    }

    public int minSwapsCouplesSol(int[] row) {
        int len = row.length;
        int N = len / 2;
        UnionFind unionFind = new UnionFind(N);
        for (int i = 0; i < len; i += 2) {
            log.info("{}；；；；{}",row[i] / 2, row[i + 1] / 2);
            unionFind.union(row[i] / 2, row[i + 1] / 2);
        }
        log.info("{}", unionFind.parent);
        return N - unionFind.getCount();
    }

    private class UnionFind {

        private int[] parent;

        private int count;

        public int getCount() {
            return count;
        }

        public UnionFind(int n) {
            this.count = n;
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            log.info("{}：{}；{}:{}", x,y,rootX, rootY);
            if (rootX == rootY) {
                return;
            }

            parent[rootX] = rootY;
            count--;
        }
    }
}
