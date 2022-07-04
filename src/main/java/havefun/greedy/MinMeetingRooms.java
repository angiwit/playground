package havefun.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//meeting rooms II
public class MinMeetingRooms {

    public int minMeetingRooms(int[][] meetings) {
        Arrays.sort(meetings, Comparator.comparingInt(o -> o[0]));
        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        rooms.add(meetings[0][1]);
        for (int i = 1; i < meetings.length; i++) {
            if (meetings[i][0] >= rooms.peek()) {
                rooms.poll();
            }
            rooms.add(meetings[i][1]);
        }
        return rooms.size();
    }
}
