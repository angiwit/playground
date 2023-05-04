package havefun.dp;
// https://leetcode.cn/problems/house-robber-ii
public class RobII {
    
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        return Math.max(robCore(nums, true), robCore(nums, false));
    }

    private int robCore(int[] nums, boolean robFirst) {
        int[][] result = new int[nums.length][2];
        if (robFirst) {
            result[0][1] = nums[0];
            result[0][0] = 0;
        } else {
            result[0][1] = 0;
            result[0][0] = 0;
        }
        for (int i = 1; i < nums.length; i++) {
            if (i == nums.length - 1 && robFirst) {
                result[i][0] = Math.max(result[i - 1][1], result[i - 1][0]);
                result[i][1] = result[i][0];
            } else {
                result[i][0] = Math.max(result[i - 1][1], result[i - 1][0]);
                result[i][1] = result[i - 1][0] + nums[i];
            }
        }
        return Math.max(result[nums.length - 1][0], result[nums.length - 1][1]);
    }
}
