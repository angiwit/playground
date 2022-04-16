package havefun.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
//        int[][] intervals = {{1, 4}, {0, 4}};
        Arrays.stream(merge(intervals)).forEach(x -> System.out.println(x[0] + " " + x[1]));
    }

    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return null;
        if (intervals.length == 1) return intervals;
        return mergeCore(intervals);
    }

    public static int[][] mergeCore(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int curStart = intervals[0][0];
        int curEnd = intervals[0][1];
        List<int[]> res = new ArrayList<>();
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > curEnd) {
                res.add(new int[]{curStart, curEnd});
                curStart = intervals[i][0];
                curEnd = intervals[i][1];
            } else {
                if (intervals[i][1] > curEnd) {
                    curEnd = intervals[i][1];
                }
            }
            if (i == intervals.length - 1) {
                res.add(new int[]{curStart, curEnd});
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
