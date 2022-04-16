package havefun.dp;

public class MaxSubArray {

    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return maxSubArrayCore(nums);
    }

    public static int maxSubArrayCore(int[] nums) {
        int[] maxSum = new int[nums.length];
        maxSum[0] = nums[0];
        int max = maxSum[0];
        for (int i = 1; i < nums.length; i++) {
            maxSum[i] = Math.max(maxSum[i - 1] + nums[i], nums[i]);
            max = Math.max(maxSum[i], max);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
    }
}
