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
        int step = 0;
        int longestJump = 0;
        for (int i = 0; i < nums.length; i++) {
            longestJump = Math.max(nums[i] + i, longestJump);
            if (i == end) {
                end = longestJump;
                step++;
            }
        }
        return step;
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
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            int longestJump = Math.min(nums[i] + i, nums.length - 1);
            for (int j = i + 1; j <= longestJump; j++) {
                dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }
        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(jump(nums));
    }
}
