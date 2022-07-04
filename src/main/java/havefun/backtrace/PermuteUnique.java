package havefun.backtrace;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PermuteUnique {

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> once = new ArrayDeque<>();
        boolean[] used = new boolean[nums.length];
        permuteUniqueCore(nums, result, once, used);
        return result;
    }

    public static void permuteUniqueCore(int[] nums,
                                         List<List<Integer>> result,
                                         Deque<Integer> once,
                                         boolean[] used) {
        if (once.size() == nums.length) {
            result.add(new ArrayList<>(once));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            if (used[i]) continue;
            once.addLast(nums[i]);
            used[i] = true;
            permuteUniqueCore(nums, result, once, used);
            used[i] = false;
            once.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        List<List<Integer>> result = permuteUnique(nums);
        System.out.println(result);
    }


}
