package havefun.array;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/removing-minimum-number-of-magic-beans/
 */
public class MagicBeans {

    public static void main(String[] args) {
        int[] nums = {4, 1, 6, 5};
        System.out.println(minimumRemoval(nums));
    }

    public static long minimumRemoval(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        long result = sum;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            result = Math.min(result, sum -
                    Long.parseUnsignedLong(String.valueOf(nums[i])) * (nums.length - i));
        }
        return result;
    }
}
