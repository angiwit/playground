package havefun.dp;

import java.util.Arrays;

public class Minjump {
    public static int jump(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return jumpCore(nums);
    }

    /**
     * A very clear approach to record both max value and max value index is:
     * 1. keep iterate index and use Math.max to record the max value.
     * 2. keep iterate index and check if the current index reaches to end(end will be updated to the max position).
     *
     * @param nums
     * @return
     */
    public static int jumpCore(int[] nums) {
        int end = 0;
        int maxPosition = 0;
        int result = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxPosition = Math.max(maxPosition, nums[i] + i); // calculate the max value in the current can jump to range.
            if (i == end) { // check the sub range, to see if sub range jumped completely.
                end = maxPosition; // After the last index of sub range is checked, update the end to max position.
                result++; // time of jump add 1 because all index in the sub range can be reached in one jump.
            }
        }
        return result;
    }

    /**
     * State could be transfer from different pre-states, so there must be a double loop needed.
     * If state could be transfer from direct pre-state, e.g. previous rob/current can't rob,
     * yesterday buy/today sell, then no double loop needed.
     * <p>
     * !!! State could be updated in batch approach, in this case, result[j] updated in a for loop.!!!
     *
     * @param nums
     * @return
     */
    public static int jumpCoreDP(int[] nums) {
        int[] result = new int[nums.length];
        Arrays.fill(result, Integer.MAX_VALUE);
        result[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            int longestJump = Math.min(nums[i] + i, nums.length - 1);
            for (int j = i + 1; j <= longestJump; j++) {
                result[j] = Math.min(result[i] + 1, result[j]);
            }
        }
        return result[nums.length - 1];
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(jump(nums));
    }
}
