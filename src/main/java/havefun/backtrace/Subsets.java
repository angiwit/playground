package havefun.backtrace;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/subsets/
 */
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        subsetsCore(nums, result, new ArrayDeque<>(), 0);
        return result;
    }

    public void subsetsCore(int[] nums, List<List<Integer>> result, Deque<Integer> path, int start) {
        /**
         *  Everytime entered this method, add path into final result which is equivalent to
         *  traverse the whole tree and add every node into final result.
         */
        result.add(new ArrayList<>(path));
        if (start >= nums.length) {
            return;
        }
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            subsetsCore(nums, result, path, i + 1);
            path.removeLast();
        }
    }
}
