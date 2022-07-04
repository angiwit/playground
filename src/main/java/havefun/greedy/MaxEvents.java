package havefun.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/maximum-number-of-events-that-can-be-attended/
 */
public class MaxEvents {

    public static int maxEvents(int[][] events) {
        if (events.length == 0) return 0;
        return maxEventsCore(events);
    }

    /**
     * at same start day, we should choose the smallest end day meeting. Since a bigger end day meeting can be chosen
     * if following days, but a small end day may be not.
     * <p>
     * How to find all the meetings starts at someday or ends at someday?
     * 1. Add start day and end day into two lists at index events[i][0], events[i][1].
     * 2. Use a set to store the meetings can be chosen is particular day:
     *
     * @param events
     * @return
     */
    public static int maxEventsCore(int[][] events) {
        Arrays.sort(events, Comparator.comparingInt(o -> o[0]));
        int currentDay = 1;
        int maxEvents = 1;
        int result = 0;
        PriorityQueue<Integer> ends = new PriorityQueue<>();
        int i = 0;
        while (i < events.length || !ends.isEmpty()) {
            while (i < events.length && events[i][0] == currentDay) {
                ends.offer(events[i++][1]);
            }
            while (!ends.isEmpty() && ends.peek() < currentDay) {
                ends.poll();
            }
            // choose one day to participant the event.
            if (!ends.isEmpty()) {
                ends.remove(); // remove the head of the queue. choose the smallest end event to participant.
                result += 1;
            }
            currentDay++;
        }
        return result;
    }

    public static void main(String[] args) {
//        int[][] events = {{1, 2}, {2, 3}, {3, 4}, {1, 2}};
//        int[][] events = {{1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7}};
//        int[][] events = {{1, 4}, {4, 4}, {2, 2}, {3, 4}, {1, 1}};
//        int[][] events = {{1, 5}, {1, 5}, {1, 5}, {2, 3}, {2, 3}};
//        int[][] events = {{1, 2}, {2, 3}, {3, 4}};
        int[][] events = {{27, 27}, {8, 10}, {9, 11}, {20, 21}, {25, 29}, {17, 20}, {12, 12}, {12, 12}, {10, 14}, {7, 7}, {6, 10}, {7, 7}, {4, 8}, {30, 31}, {23, 25}, {4, 6}, {17, 17}, {13, 14}, {6, 9}, {13, 14}};
//        int[][] events = {{1, 2}, {2, 2}, {3, 3}, {3, 4}, {3, 4}};
        System.out.println(maxEvents(events));
    }

}
