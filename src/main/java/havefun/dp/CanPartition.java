package havefun.dp;

/**
 * SRTBOT:
 * subproblem: F(i, j): there are numbers in the list can be formed to j.
 * relation: F(n, t) = f(i, j)
 * Base case: F(0, 0) = true
 */
public class CanPartition {

    public static boolean canPartition(int[] nums) {
        return canPartitionCore(nums);
    }

    /**
     * @param nums
     * @return
     */
    public static boolean canPartitionCore(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 == 1) return false;
        int target = sum / 2;
        return canPartitionCore1(nums, target);
    }

    /**
     * @param nums
     * @param target
     * @return
     */
    public static boolean canPartitionCore1(int[] nums, int target) {
        boolean[][] dp = new boolean[nums.length + 1][target + 1];
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = false;
        }
        for (int i = 0; i <= target; i++) {
            dp[0][i] = false;
        }
        dp[0][0] = true;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j <= target; j++) {
                if (nums[i - 1] == j) {
                    dp[i][j] = true;
                } else if (j > nums[i - 1]) {
                    // two options: choose or not choose current item.
                    dp[i][j] = dp[i - 1][j - nums[i]] || dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[nums.length][target];
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
//        int[] nums = {1, 2, 3, 5};
//        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(canPartition(nums));
    }
}
