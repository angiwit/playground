package havefun.greedy;

import java.util.Arrays;

public class MinJump {

    public static int jumpDp(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int result[] = new int[nums.length];
        Arrays.fill(result, Integer.MAX_VALUE);
        result[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            int longestJump = Math.min(nums[i] + i, nums.length);
            for (int j = i + 1; j <= longestJump; j++) {
                result[j] = Math.min(result[i] + 1, result[j]);
            }
        }
        return result[nums.length];
    }

    public static int jump(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int result = 0, curEnd = 0;
        for (int i = 0; i < nums.length; i++) {
            int longestJump = Math.min(nums[i] + i, nums.length - 1);
            if (i == curEnd) {
                curEnd = longestJump;
                result++;
            }
        }
        return result;
    }
}
