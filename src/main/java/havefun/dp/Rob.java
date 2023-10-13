package havefun.dp;

//https://leetcode.cn/problems/house-robber
public class Rob {

    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        return robCore(nums);
    }

    /**
     * For each house, we have two choice: rob/not rob and we can use a 2d array with 2 columns to represent these two cases.
     * dp[i][0] means not rob, dp[i][1] means rob, dp means for each house, two cases, the corresponding max values.
     * @param nums
     * @return
     */
    public static int robCore(int[] nums) {
        int[][] result = new int[nums.length][2];
        result[0][1] = nums[0];
        result[0][0] = 0;
        for (int i = 2; i < nums.length; i++) {
            result[i][0] = Math.max(result[i - 1][1], result[i - 1][0]);
            result[i][1] = result[i - 1][0] + nums[i];
        }
        return Math.max(result[nums.length - 1][0], result[nums.length - 1][1]);
    }
    
    /**
     * Each element in the dp array represents a sub problem answer!!!
     * @param nums
     * @return
     */
    public static int robCoreOptimized1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0]; //if only one house, rob it to get highest benifit.
        dp[1] = Math.max(nums[0], nums[1]); // if only two houses, rob the bigger value one.
         //only one house, max value is to rob this house, value is nums[0]
        for (int i = 2; i < nums.length; i++) {
            // when reviewing current item, there's two choices, rob / not rob. If rob, the max value should be transited from last not robbed max
            // value, if not rob, the max value is the previous house max value(after reviewing last house). And we should choose the max among them.
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }

    
    /**
     * Since the new value calculation only needs two previous values, so we can reduce the space consumption with two variables.
     * @param nums
     * @return
     */
    public static int robCoreOptimized2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int prev = nums[0]; int next = Math.max(nums[0], nums[1]); 
        for (int i = 2; i < nums.length; i++) {
            int temp = Math.max(next, prev + nums[i]);
            prev = next;
            next = temp;
        }
        return next;
    }

    public static void main(String[] args) {
        int[] nums = {100};
        System.out.println(robCoreOptimized1(nums));
    }

}
