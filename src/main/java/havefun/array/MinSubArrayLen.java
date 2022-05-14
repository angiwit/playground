package havefun.array;

// https://leetcode-cn.com/problems/minimum-size-subarray-sum/
public class MinSubArrayLen {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 3};
        System.out.println(minSubArrayLen(7, nums));
    }

    public static int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return minSubArrayLenCore(target, nums);
    }

    /**
     * Note that when removing from start, the sum still might bigger than target, so update result
     * in the while loop.
     *
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLenCore(int target, int[] nums) {
        int start = 0, sum = 0, result = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum >= target) {
                while (sum >= target) {
                    result = Math.min(result, i - start + 1);
                    sum -= nums[start++];
                }
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
