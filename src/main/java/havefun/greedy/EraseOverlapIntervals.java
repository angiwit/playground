package havefun.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class EraseOverlapIntervals {

    public static void main(String[] args) {
        int[][] intervals = {{1, 2}, {1, 2}, {1, 2}};
        System.out.println(eraseOverlapIntervals(intervals));
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        return eraseOverlapIntervalsCore(intervals);
    }

    /**
     * We need to sort the array by the range end, if sorting by the start, then a big range might be chosen
     * and more small ranges could be skipped, e.g. [1, 100], [2, 4] [3, 5], [6, 8],the picked one is [1, 100].
     * So if we want to pick up as many as possible ranges, we need to sort by the end, otherwise by the start.
     * <p>
     * Since we're calculating removal count, so the init value of nonOverlaps should be 1 since the first one is
     * picked up by default.
     *
     * @param intervals
     * @return
     */
    public static int eraseOverlapIntervalsCore(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
        int nonOverlaps = 1, end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= end) {
                nonOverlaps++;
                end = intervals[i][1];
            }
        }
        return intervals.length - nonOverlaps;
    }
}
