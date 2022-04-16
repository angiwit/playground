package havefun.greedy;

public class CanJump {
    public static boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        if (nums.length == 1 && nums[0] == 0) return true;
        return canJumpCore1(nums);
    }

    public static boolean canJumpCore1(int[] nums) {
        int longestJump = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > longestJump) return false;
            longestJump = Math.max(nums[i] + i, longestJump);
            if (longestJump >= nums.length - 1) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        int[] nums1 = {3, 2, 1, 0, 4};
        int[] nums2 = {0, 1};
        int[] nums3 = {2, 5, 0, 0};
        int[] nums4 = {4, 2, 0, 0, 1, 1, 4, 4, 4, 0, 4, 0};
        System.out.println(canJump(nums4));
    }
}
