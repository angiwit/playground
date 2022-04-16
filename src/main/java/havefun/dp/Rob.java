package havefun.dp;

public class Rob {

    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        return robCore(nums);
    }

    public static int robCore(int[] nums) {
        int[][] result = new int[nums.length][2];
        result[0][1] = nums[0];
        result[0][0] = 0;
        result[1][1] = nums[1];
        result[1][0] = nums[0];
        for (int i = 2; i < nums.length; i++) {
            result[i][0] = Math.max(result[i - 1][1], result[i - 1][0]);
            result[i][1] = result[i - 1][0] + nums[i];
        }
        return Math.max(result[nums.length - 1][0], result[nums.length - 1][1]);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        System.out.println(rob(nums));
    }

}
