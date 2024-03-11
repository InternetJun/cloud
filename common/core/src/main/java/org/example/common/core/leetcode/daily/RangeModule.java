package org.example.common.core.leetcode.daily;

import org.example.common.core.leetcode.link.ListNode;
import org.example.common.core.util.CommonUtil;
import org.junit.Test;

import java.util.*;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 增加，删除、获取区间中的值合并代码。
 * @time: 2023/11/12 17:14
 */
public class RangeModule {
    ArrayList<int[]> list;
    TreeMap<Integer, Integer> intervals;
    public RangeModule() {
        this.list = new ArrayList<>();
        this.intervals = new TreeMap<Integer, Integer>();
    }

    public void addRange(int left, int right) {
        // list会是一个有序的列表。
        /** add:[10, 20) --> 10~19
         *
         * 1, 05
         * 2,
         * remove:[10,14)
         * query(13,15)
         * query(16,17)
         */
        if (list.size() == 0) {
            list.add(new int[]{left, right - 1});
            return;
        }
        list.sort((Comparator.comparingInt(o -> o[0])));
        int index = 0;
        int[][] temp = new int[list.size()+1][2];
        // 比他小的直接忽略；
        while (list.get(index)[1] < left) {
            temp[index] = list.get(index);
            index++;
        }
        // 需要有合并的
        int[] insert = new int[]{left, right-1};
        int leftMin = Integer.MAX_VALUE, rightMax = Integer.MIN_VALUE;
        while (list.get(index++)[0] <= right - 1) {
            leftMin = Math.min(leftMin, insert[0]);
            rightMax = Math.max(rightMax, insert[1]);
        }
        temp[++index] = new int[]{leftMin, rightMax};
        // 其他的就是向后处理了。这里的index获取失败的。
        while (index < list.size()) {
            temp[index++] = list.get(index);
        }
        // 更新temp 为list的内容。

        Map.Entry<Integer, Integer> entry = intervals.higherEntry(left);
    }

    /**
     * 获取到[left, right)是否在其中
     *
     * @param left
     * @param right
     * @return
     */
    public boolean queryRange(int left, int right) {
        return false;
    }

    public void removeRange(int left, int right) {

    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int[][] res = new int[intervals.length + 1][2];
        int idx = 0;
        // 遍历区间列表：
        // 首先将新区间左边且相离的区间加入结果集
        int i = 0;
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            res[idx++] = intervals[i++];
        }
        // 接着判断当前区间是否与新区间重叠，重叠的话就进行合并，直到遍历到当前区间在新区间的右边且相离，
        // 将最终合并后的新区间加入结果集
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        res[idx++] = newInterval;
        // 最后将新区间右边且相离的区间加入结果集
        while (i < intervals.length) {
            res[idx++] = intervals[i++];
        }

        return Arrays.copyOf(res, idx);
    }

    @Test
    public void testI() {
        int[][] ints= {{1,3}, {6,9}};
        int[] newI = {2, 5};
        insert(ints, newI);
    }

    // [[10,30],[20,60],[80,100],[150,180]]
    // [[10,60],[80,100],[150,180]]

    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        ArrayList<Interval> res = new ArrayList<>();
        Collections.sort(intervals,(a,b)->a.start-b.start);
        int len = intervals.size();
        int i = 0;
        while (i < len) {
            int left = intervals.get(i).start;
            int right = intervals.get(i).end;
            while (i < len-1 && intervals.get(i+1).start <= right) {
                right = Math.max(right,intervals.get(i+1).end);
                i++;
            }
            res.add(new Interval(left,right));
            i++;
        }
        return res;
    }

    /**
     * 进一步的优化了，不是一步步的走了，而是一个跳跃式的有记忆化的获取先前的值。
     *
     * @param intervals
     * @return
     */
    public ArrayList<Interval> mergeTwoPoint(ArrayList<Interval> intervals) {
        ArrayList<Interval> res = new ArrayList<>();
        Collections.sort(intervals, Comparator.comparingInt(a -> a.start));
        int len = intervals.size();
        int start = 0;
        while (start < len) {
            int end = start + 1;
            while (end < len && intervals.get(end).start <= intervals.get(start).end) {
                intervals.get(start).end = Math.max(intervals.get(start).end, intervals.get(end).end);
                end++;
            }
            res.add(intervals.get(start));
            start = end;
        }
        return res;
    }

    /**
     * 对相同值的不同字符，记录最大的。<br/>
     *
     * <b>AAAAHHHBBCDHHHH</b>
     * @param s
     * @return
     */
    public int[] countSameWithLargest(String s) {
        int[] res = new int[26];
        final char[] chars = s.toCharArray();
        Map<Character, LinkedList<Integer>> map = new HashMap<>();
        char pre = ' ';
        int start = 0;
        while (start < s.length()) {
            int count = 1;
            int end = start + 1;
            // 程序这里有问题，要是为len - 2 了；
            pre = chars[start];
            while (end <= s.length() - 1 && pre == chars[end]) {
                end++;
                count++;
            }
            // 从这里开始。
            start = end;
            final LinkedList<Integer> orDefault = map.getOrDefault(pre, new LinkedList<Integer>());
            orDefault.add(count);
            map.put(pre, orDefault);
        }

        return res;
    }

    @Test
    public void main() {
        String s = "AAAAHHHBBCDHHHH";
        final int[] ints = countSameWithLargest(s);
    }

    /**
     * 全部删除的情况。
     *
     * @param head
     * @return
     */
    public ListNode deleteDup(ListNode head) {
        final ListNode dummy = new ListNode(-1);
        dummy.next = head;
        /**这里是从虚拟的节点开始。
         * */
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int val = cur.next.val;
                while (cur.next != null && val == cur.next.val) {
                    // 没有删除掉自己
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    /**
     * 保留一个重复的元素。
     *
     * @param head
     * @return
     */
    public ListNode deleteRemainOne(ListNode head) {
        final ListNode dummy = new ListNode(-1);
        dummy.next = head;
        /**
         * 这里的是从真实节点开始的。
         * */
        ListNode cur = head;
        while (cur != null) {
            int val = cur.val;
            while (cur.next != null && val == cur.next.val) {
                cur.next = cur.next.next;
                // 没有删除掉自己
            }
            cur = cur.next;
        }


        return dummy.next;
    }

    /**
     * 这时要求有要是有多个（大于2个的时候，要求保留2个重复元素呢？）
     */

    @Test
    public void testLink() {
        final ListNode listNode = CommonUtil.buildLinkedList(new int[]{1, 1, 1, 2, 2, 3, 4, 5, 5});
        System.out.println(deleteDup(listNode));
    }

    public class Interval {
      int start;
      int end;
      public Interval(int start, int end) {
        this.start = start;
        this.end = end;
      }
    }
}
