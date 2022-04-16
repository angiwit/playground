package havefun.array;

import java.util.ArrayDeque;
import java.util.Deque;

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

    public static int minSubArrayLenCore(int target, int[] nums) {
        int sum = 0;
        Deque<Integer> result = new ArrayDeque<>();
        int minLen = Integer.MAX_VALUE;
        int i = 0;
        while (i < nums.length) {
            result.addLast(i);
            sum += nums[i];
            if (sum >= target) {
                while (sum >= target && result.size() > 0) {
                    minLen = Math.min(minLen, result.getLast() - result.getFirst());
                    sum -= nums[result.removeFirst()];
                }
            }
            i++;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen + 1;
    }
}
