package havefun.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class FindMinArrowShots {

    public static void main(String[] args) {
//        int[][] points = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
//        int[][] points = {{1, 2}, {3, 4}, {5, 6}, {7, 8}};
        int[][] points = {{3, 9}, {7, 12}, {3, 8}, {6, 8}, {9, 10}, {2, 9}, {0, 9}, {3, 9}, {0, 6}, {2, 8}};
        System.out.println(findMinArrowShots(points));
    }

    public static int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) return 0;
        return findMinArrowShotsCore(points);
    }

    public static int findMinArrowShotsCore(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(o -> o[0]));
        int res = 1;
        int curEnd = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > curEnd) {
                res++;
                curEnd = points[i][1];
            } else {
                curEnd = Math.min(curEnd, points[i][1]);
            }
        }
        return res;
    }
}
