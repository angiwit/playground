package havefun.dp;

import java.util.Arrays;

public class FindLengthOfLCIS {

    public static void main(String[] args) {
//        int[] nums = {1, 3, 5, 4, 7};
//        int[] nums = {2, 2, 2, 2, 2};
        int[] nums = {1, 3, 5, 7};
        System.out.println(findLengthOfLCIS(nums));
    }

    public static int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return findLengthOfLCISCore(nums);
    }

    public static int findLengthOfLCISCore(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int max = 1;
        int curMax = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
                curMax = Math.max(curMax, dp[i]);
            } else {
                curMax = 1;
                max = Math.max(dp[i - 1], max);
            }
        }
        return Math.max(curMax, max);
    }
}
