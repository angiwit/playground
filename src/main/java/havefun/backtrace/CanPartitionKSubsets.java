package havefun.backtrace;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/partition-to-k-equal-sum-subsets/
 */
public class CanPartitionKSubsets {

    /**
     * We can use a memo to record the result for each num state(choose/not choose)
     * For each number, we can record the result, e.g. we have two numbers [2,2] and we separate them into 2 parts.
     * After the first iteration, the first number is been calculated and memo[0] = memo[1]
     *
     * @param nums
     * @param k
     * @return
     */

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int target = Arrays.stream(nums).sum() / k;
        boolean r = Arrays.stream(nums).anyMatch(x -> x > target);
        if (r) return false;
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        return canPartitionKSubsetsCore(nums, target, k, used, 0);
    }

    // every num in nums is smaller than target.
    private static boolean canPartitionKSubsetsCore(int[] nums, int target, int k, boolean[] used, int sum) {
        if (k == 0) {
            for (int i = 0; i < used.length; i++) {
                if (!used[i]) return false;
            }
            return true;
        }

        // reverse order to make sure the big num be chosen first, since the sort is ascending order.
        for (int i = nums.length - 1; i >= 0; i--) {
            if (used[i]) continue;
            if (sum + nums[i] > target) continue;
            if (i + 1 < nums.length && nums[i + 1] == nums[i]) continue;
            used[i] = true;
            if (sum + nums[i] == target && canPartitionKSubsetsCore(nums, target, k - 1, used, 0)) return true;
            if (sum + nums[i] < target && canPartitionKSubsetsCore(nums, target, k, used, sum + nums[i])) return true;
            used[i] = false;
        }
        return false;
    }


    public static boolean canPartitionKSubsets1(int[] nums, int k) {
        int target = Arrays.stream(nums).sum() / k;
        boolean r = Arrays.stream(nums).anyMatch(x -> x > target);
        if (r) return false;
        Map<Integer, Boolean> memo = new HashMap<>();
        Arrays.sort(nums);
        int[] bucket = new int[k + 1];
        int used = 0;
        return canPartitionKSubsetsCore1(nums, target, 0, k, bucket, memo, used);
    }

    // use memo
    private static boolean canPartitionKSubsetsCore1(int[] nums, int target, int start, int k, int[] bucket, Map<Integer, Boolean> memo, int used) {
        if (k == 0) return true;
        if (bucket[k] == target) {
            boolean res = canPartitionKSubsetsCore1(nums, target, 0, k - 1, bucket, memo, used);
            memo.put(used, res);
            return res;
        }

        if (memo.containsKey(used)) return memo.get(used);

        for (int i = start; i < nums.length; i++) {
            if (((used >> i) & 1) == 1) continue;
            if (bucket[k] + nums[i] > target) continue;
            bucket[k] += nums[i];
            used |= i << i;
            if (canPartitionKSubsetsCore1(nums, target, i + 1, k, bucket, memo, used)) return true;
            bucket[k] -= nums[i];
            used ^= i << i;
        }
        return false;
    }

    public static void main(String[] args) {
//        int[] nums = {4, 3, 2, 3, 5, 2, 1};
//        int k = 4;
        int[] nums = {1, 2, 3, 4};
        int k = 3;
        System.out.println(canPartitionKSubsets(nums, k));
    }
}
