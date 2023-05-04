package havefun.dp;

//https://leetcode.cn/problems/maximum-subarray/
public class MaxSubArray {

    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return maxSubArrayCore(nums);
    }

    public static int maxSubArrayCore(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }
        return dp[nums.length - 1];
    }
    
    private int maxSubArrayCore1(int[] nums) {
        int max = nums[0];
        int curSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (curSum < 0) curSum = 0;
            curSum += nums[i];
            max = Math.max(max, curSum);
        }
        return max;
    } 

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
    }
}
