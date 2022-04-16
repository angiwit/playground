package havefun.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Sort is very import!!!
 * Sort is very import!!!
 * Sort is very import!!!
 * Different sort make you think differently!
 * Find the best way to transfer state!!!
 * Think clearly what we need to remember in memory, if too many things need to remember, find another approach.
 * <p>
 * This one can make people think of sort by value very easily, since this way we can pick up meetings with
 * high values one by one and calculate the final result.
 * But, this approach is difficult to transfer state since it's difficult to find the previous state.
 * For example, if choosing current meeting, then you need to go through all the meetings before this one
 * to see if any meeting not suitable to choose since overlapped with current one. This is time-consuming.
 * The state transfer in this scenario is: pick current one and see previous meetings are available, no way
 * to transfer a single value since you need to remember all the meetings you choose in every cell.
 * <p>
 * If sorted by end time of the meetings, then if current meeting is reviewed, then it's easily to find the
 * last meeting we can choose via binary search, and the previous meeting is also calculated by state transfer
 * with all the meetings prev to it. The state transfer is much more simple.
 */
public class MaxMeetingValue {
    public static int maxValue(int[][] events, int k) {
        if (events.length == 0) return 0;
        return maxValueCore(events, k);
    }

    /**
     * @param events
     * @param k
     * @return V[i][k] =
     * v[2]: y
     * 0: n
     * V[i][k] = max(V[i-1][k-1], V[i][k-1]) when overlap
     * V[i][k] = max(V[i-1][k-1] + V[i]) when no overlap
     */
    public static int maxValueCore(int[][] events, int k) {
        List<Integer[]> eventList = new ArrayList<>();
        for (int i = 0; i < events.length; i++) {
            int[] event = events[i];
            Integer[] iEvent = new Integer[3];
            iEvent[0] = event[0];
            iEvent[1] = event[1];
            iEvent[2] = event[2];
            eventList.add(iEvent);
        }
        Collections.sort(eventList, (o1, o2) -> {
            if (o1[1] != o2[1]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });
        for (int i = 0; i < eventList.size(); i++) {
            System.out.println(String.format("meeting:[%s][%s]:[%s]", eventList.get(i)[0],
                    eventList.get(i)[1], eventList.get(i)[2]));
        }
        int[][][] dp = new int[eventList.size() + 1][k + 1][2];
        for (int n = 1; n < k + 1; n++) {
            for (int i = 1; i < eventList.size() + 1; i++) {
                dp[i][n][0] = Math.max(dp[i - 1][n][0], dp[i - 1][n][1]);
                int choose = 0;
                for (int j = i - 1; j >= 0; j--) {
                    if (eventList.get(j)[0] > eventList.get(i - 1)[1] || eventList.get(j)[1] < eventList.get(i - 1)[0]) {
                        choose = Math.max(dp[j + 1][n - 1][1] + eventList.get(i - 1)[2], choose);
                    } else {
                        choose = Math.max(eventList.get(i - 1)[2], choose);
                    }
                }
                dp[i][n][1] = choose;
                System.out.println(String.format("dp[%s][%s][0]:[%s]", i, n, dp[i][n][0]));
                System.out.println(String.format("dp[%s][%s][1]:[%s]", i, n, dp[i][n][1]));
            }

        }
        return Math.max(dp[eventList.size()][k][0], dp[eventList.size()][k][1]);
    }

    public static void main(String[] args) {
//        int[][] events = {{1, 2, 4}, {3, 4, 3}, {2, 3, 1}};
//        int[][] events = {{1, 2, 4}, {3, 4, 3}, {2, 3, 10}};
//        int[][] events = {{1, 1, 1}, {2, 2, 2}, {3, 3, 3}, {4, 4, 4}};
        int[][] events = {{31, 57, 53}, {5, 63, 29}, {54, 57, 32}, {55, 83, 28}, {25, 64, 5}, {5, 33, 33}, {32, 68, 27}, {30, 99, 54}};
        System.out.println(maxValue(events, 3));
    }

}
