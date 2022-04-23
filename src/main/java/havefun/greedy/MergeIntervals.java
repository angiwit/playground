package havefun.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/merge-intervals/
 */
public class MergeIntervals {

    public static void main(String[] args) {
//        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
//        int[][] intervals = {{1, 4}, {0, 4}};
        int[][] intervals = {{1, 4}, {4, 5}};
        Arrays.stream(merge(intervals)).forEach(x -> System.out.println(x[0] + " " + x[1]));
    }

    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return null;
        if (intervals.length == 1) return intervals;
        return mergeCore(intervals);
    }

    /**
     * Sort by start, for end sorting there are two options:
     * 1. sort end in the Arrays.sort.
     * 2. pick up end value by using Math.max/ Math.min.
     *
     * @param intervals
     * @return
     */
    public static int[][] mergeCore(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int start = intervals[0][0], end = intervals[0][1];
        List<int[]> result = new ArrayList<>();
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= end) {
                end = Math.max(end, intervals[i][1]);
            } else {
                result.add(new int[]{start, end});
                start = intervals[i][0];
                end = intervals[i][1];
            }
            if (i == intervals.length - 1) {
                result.add(new int[]{start, end});
            }
        }
        return result.toArray(new int[result.size()][2]);
    }
}
