package havefun;

import java.util.ArrayList;
import java.util.List;

public class FindTargetSumWays {

    public int findTargetSumWays(int[] nums, int target) {
        if (nums == null) return 0;
        return findTargetSumWaysCore(nums, 0, nums.length, target, "", new ArrayList<>());
    }

    public int findTargetSumWaysCore(int[] nums, int start, int end, int target, String temp, List<String> result) {
        if (end == start) {
            if (target - nums[end] == 0) {
                temp += "-";
                if (!result.contains(temp)) result.add(temp);
            } else if (target + nums[end] == 0) {
                temp += "+";
                if (!result.contains(temp)) result.add(temp);
            } else {

            }
        }
        return 0;
    }
}
