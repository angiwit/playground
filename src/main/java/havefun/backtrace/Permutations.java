package havefun.backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/permutations/
 */
public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.fill(used, false);
        permuteCore(nums, result, new LinkedList<>(), used);
        return result;
    }

    public void permuteCore(int[] nums, List<List<Integer>> result, LinkedList<Integer> path, boolean[] used) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            path.offer(nums[i]);
            used[i] = true;
            permuteCore(nums, result, path, used);
            path.removeLast();
            used[i] = false;
        }
    }
}
