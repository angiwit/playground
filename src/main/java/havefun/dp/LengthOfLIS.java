package havefun.dp;

import java.util.Arrays;

public class LengthOfLIS {

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(nums));
    }

    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return lengthOfLISCore(nums);
    }

    public static int lengthOfLISCore(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return Arrays.stream(dp).max().orElse(0);
    }
}
