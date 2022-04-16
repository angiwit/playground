package havefun.greedy;

import java.util.Arrays;

public class EraseOverlapIntervals {

    public static void main(String[] args) {
        int[][] intervals = {{1, 2}, {1, 2}, {1, 2}};
        System.out.println(eraseOverlapIntervals(intervals));
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        return eraseOverlapIntervalsCore(intervals);
    }

    public static int eraseOverlapIntervalsCore(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
        int res = 1;
        int curEnd = intervals[0][1];
        for (int i = 0; i < intervals.length - 1; i++) {
            /**
             * Here can NOT be replaced with if(intervals[i+1][0] < curEnd) { res++};
             * Since if a wider range is chosen, then there's more ranges need to be skipped.
             * E.g. [1, 100] is chosen, then [2,5], [3,6], [4,7] all need to be skipped.
             * So calculate continuous ranges first, this way small ranges will be picked up.
             */
            if (intervals[i + 1][0] >= curEnd) {
                res++;
                curEnd = intervals[i + 1][1];
            }
        }
        return intervals.length - res;
    }
}
