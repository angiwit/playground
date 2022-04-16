package havefun.greedy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ReconstructQueue {

    public static int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0) return null;
        return reconstructQueueCore(people);
    }

    public static int[][] reconstructQueueCore(int[][] people) {
        List<int[]> res = new LinkedList<>();
        Arrays.sort(people, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o2[0] - o1[0];
        });
        for (int i = 0; i < people.length; i++) {
            res.add(people[i][1], people[i]);
        }
        return res.toArray(new int[people.length][]);
    }
}
