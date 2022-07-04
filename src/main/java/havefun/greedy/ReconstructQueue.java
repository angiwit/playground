package havefun.greedy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/queue-reconstruction-by-height/
 */
public class ReconstructQueue {

    public static int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0) return null;
        return reconstructQueueCore(people);
    }

    /**
     * [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
     * sort by height: [7,0],[7,1],[6,1],[5,0],[5,2],[4,4]
     * when height is same, sort by index ascending, since the small index will be reviewed first
     * and has no impact on the following elements, otherwise, if reviewing the bigger index first,
     * the smaller element insertion will change the count of before or after that index!!!
     * [7,0]
     * [7,0] -> [7,1]
     * [7,0] -> [6, 1] -> [7,1]
     * [5,0] -> [7,0] -> [6, 1] -> [7,1]
     * [5,0] -> [7,0] -> [5,2] -> [6, 1] -> [7,1]
     * [5,0] -> [7,0] -> [5,2] -> [6, 1] -> [4,4] -> [7,1]
     *
     * @param people
     * @return
     */
    public static int[][] reconstructQueueCore(int[][] people) {
        // sort height by descend order, so we can insert the smaller index correctly.
        Arrays.sort(people, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            } else {
                return o2[0] - o1[0];
            }
        });
        List<int[]> result = new LinkedList<>();
        for (int i = 0; i < people.length; i++) {
            result.add(people[i][1], people[i]);
        }
        return result.toArray(new int[result.size()][2]);
    }
}
