package havefun.recursivewithmemo;

import java.util.Arrays;
import java.util.Comparator;
// https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended-ii/
public class MaxMeetingValueRecursive {

    public static int maxValue(int[][] events, int k) {
        if (events.length == 0) return 0;
        int max = 0;
        int[][] values = new int[events.length][k + 1];
        Arrays.sort(events, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });
        for (int i = 0; i < events.length; i++) {
            System.out.println(String.format("meeting[%s][%s]:[%s]", events[i][0], events[i][1], events[i][2]));
            max = Math.max(maxValueCoreWithState(events, k, i, values), max);
        }
        return max;
    }

    /**
     * For each meeting, choose current or not choose current to see which option can get the biggest value.
     * Recursive stands for there's a value's change to make all the calculation start again.
     * In this scenario, k's meeting's biggest value = (k-1)'s meeting's biggest value + current meeting value.
     * Or ignore current meeting, (k-1)'s meeting's biggest value util k == 0.
     *
     * @param events
     * @param k
     * @param index
     * @return
     */
    public static int maxValueCore(int[][] events, int k, int index) {
        if (k == 0 || index < 0) return 0;
        //find prev
        int prev = -1;
        for (int i = index - 1; i >= 0; i--) {
            if (events[i][1] < events[index][0]) {
                prev = i;
                break;
            }
        }
        // choose current
        int max = 0;
        if (prev >= 0 && prev != index) {
            max = events[index][2] + maxValueCore(events, k - 1, prev);
        } else {
            max = events[index][2];
        }
        // compare choosing current and not choosing current.
        return Math.max(maxValueCore(events, k, index - 1), max);
    }


    public static int maxValueCoreWithState(int[][] events, int k, int index, int[][] values) {
        if (index < 0 || k == 0) return 0;
        if (values[index][k] != 0) return values[index][k];
        //find prev
        int prev = binarySearchPrevious(events, index);
        // choose current
        int max = 0;
        if (prev >= 0 && prev != index) {
            max = events[index][2] + maxValueCoreWithState(events, k - 1, prev, values);
        } else {
            max = events[index][2];
        }
        // compare choosing current and not choosing current.
        int curMax = Math.max(maxValueCoreWithState(events, k, index - 1, values), max);
        values[index][k] = curMax;
        return curMax;
    }


    /**
     * Imagination, in binary search:
     * If there's only one element, left == middle == right
     * If there's two elements, left == middle < right
     * If there's three elements, left < middle < right
     *
     * @param events
     * @param index
     * @return
     */
    private static int binarySearchPrevious(int[][] events, int index) {
        if (index <= 0) return -1;
        int target = events[index][0];
        int start = 0;
        int end = index;
        while (start < end) {
            int midIndex = start + ((end - start) / 2); // two numbers pick up the left one.
            if (events[midIndex][1] < target) {
                start = midIndex + 1;
            } else {
                // when element bigger than target, change end to element index.
                // In this way, we're always moving the left index until we find the first element smaller than target.
                // end moves to left but never cross the border to the value smaller than end.
                end = midIndex;
            }
        }
        /**
         * if target value may not exist in the array, then another check is necessary:
         * if (array[i] == target) {
         *      return i;
         * }
         * if we need to find the first value that smaller than target, then return end - 1 is also not correct.
         * even if target may not exist, but end value will always greater/equals than target, and keep moving to the leftest.
         * if the end is smaller than target, then we need to return end.
         */
        return end - 1;
    }

    public static void main(String[] args) {
//        int[][] events = {{1, 2, 4}, {3, 4, 3}, {2, 3, 1}};
//        int[][] events = {{1, 2, 4}, {3, 4, 3}, {2, 3, 10}};
        int[][] events = {{1, 1, 1}, {2, 2, 2}, {3, 3, 3}, {4, 4, 4}};
//        int[][] events = {{31, 57, 53}, {5, 63, 29}, {54, 57, 32}, {55, 83, 28}, {25, 64, 5}, {5, 33, 33}, {32, 68, 27}, {30, 99, 54}};
//        int[][] events = {{19, 42, 7}, {41, 73, 15}, {52, 73, 84}, {84, 92, 96}, {6, 64, 50}, {12, 56, 27}, {22, 74, 44}, {38, 85, 61}};
//        int[][] events = {{87, 95, 42}, {3, 42, 37}, {20, 42, 100}, {53, 84, 80}, {10, 88, 38}, {25, 80, 57}, {18, 38, 33}};
        System.out.println(maxValue(events, 3));
    }
}
