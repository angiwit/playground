package havefun.greedy;

import java.util.*;

public class MaxEvents {

    public static int maxEvents(int[][] events) {
        if (events.length == 0) return 0;
        return maxEventsCore(events);
    }

    public static int maxEventsCore(int[][] events) {
        List<Integer[][]> eventList = new ArrayList<>();
        for (int i = 0; i < events.length; i++) {
            Integer[][] event = new Integer[1][2];
            event[0][0] = events[i][0];
            event[0][1] = events[i][1];
            eventList.add(event);
        }
        Collections.sort(eventList, (o1, o2) -> {
            if (o1[0][0].intValue() != o2[0][0].intValue()) {
                return o1[0][0] - o2[0][0];
            } else {
                return o1[0][1] - o2[0][1];
            }
        });
        int currentDay = 1;
        Queue<Integer> days = new PriorityQueue<>();
        int max = 0;
        int i = 0;
        while (i < eventList.size() || days.size() > 0) {
            while (i < eventList.size() && eventList.get(i)[0][0] == currentDay) {
                days.add(eventList.get(i)[0][1]);
                i++;
            }

            while (days.size() > 0 && days.peek() < currentDay) {
                days.poll();
            }
            if (days.size() > 0) {
                days.poll();
                max++;
            }
            currentDay++;
        }
        return max;
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
