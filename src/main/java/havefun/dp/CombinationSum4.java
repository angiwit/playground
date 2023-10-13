package havefun.dp;

public class CombinationSum4 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int target = 4;
        System.out.println(combinationSum4(nums, target));
    }

    public static int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        return combinationSum4Core(nums, target);
    }

    public static int combinationSum4Core(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int j = 1; j <= target; j++) {
            for (int i = 0; i < nums.length; i++) {
                if (j >= nums[i]) {
                    dp[j] = dp[j - nums[i]] + dp[j];
                }
            }
        }
        return dp[target];
    }
}
