package havefun.dp;

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
        for (int i = 0; i < nums.length + 1; i++) {
            dp[i][0] = false;
        }
        for (int i = 0; i < target + 1; i++) {
            dp[0][i] = false;
        }
        /**
         * dp[0][0] = true is important! It equals to target is 0 and there's no element, it can be
         * considered as there's two empty array each of them's sum is 0.
         */
        dp[0][0] = true;

        for (int i = 1; i < nums.length + 1; i++) {
            for (int j = 1; j < target + 1; j++) {
                if (nums[i - 1] == j) {
                    // when a number equals to target, result is true.
                    dp[i][j] = true;
                } else if (j > nums[i - 1]) {
                    // when target greater than a number, then we can choose or not choose this number,
                    // either case is true, then the result is true.
                    /**
                     * dp[i - 1][j]: DO NOT CHOOSE current number, check previous number if is true.
                     * dp[i - 1][j - nums[i - 1]]: CHOOSE current number, check [i-1][j-nums[i-1]] if is true.
                     */
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                } else {
                    /**
                     * target less than current number, CAN NOT CHOOSE current number, result equals to previous result.
                     */
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
