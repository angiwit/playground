package havefun.array;

public class PivotIndex {

    public static void main(String[] args) {
        int[] nums = {-1, -1, -1, -1, -1, 0};
        System.out.println(pivotIndex(nums));
    }

    public static int pivotIndex(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        return pivotIndexCore(nums);
    }

    public static int pivotIndexCore(int[] nums) {
        int[] leftMax = new int[nums.length];
        int[] rightMax = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            leftMax[i] = nums[i] + (i == 0 ? 0 : leftMax[i - 1]);
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            rightMax[i] = nums[i] + (i == nums.length - 1 ? 0 : rightMax[i + 1]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (leftMax[i] == rightMax[i]) return i;
        }
        return -1;
    }
}
