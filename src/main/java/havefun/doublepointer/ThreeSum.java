package havefun.doublepointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) return new ArrayList<>();
        Arrays.sort(nums);
        return threeSumCore(nums);
    }

    public static List<List<Integer>> threeSumCore(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) continue;
            /**
             * We need at least run once and after that we need to check if condition is matched, so writing in this way:
             * if (i == 0 || condition) or if (i > 0 || !condition)
             */
            if (i == 0 || nums[i] != nums[i - 1]) {
                int end = nums.length - 1;
                /**
                 * Here can be changed to while(left < right) approach, which is more simple.
                 * Below approach is increasing left in the for loop and decreasing the end in the while loop.
                 */
                for (int j = i + 1; j < end; j++) {
                    if (j == i + 1 || nums[j] != nums[j - 1]) {
                        /**
                         * end > j + 1, when while loop executed completely, the end's value is at least j + 1.
                         * It'll never reach j, Think clearly what value should be after the while loop!
                         */
                        while (end > j + 1 && nums[i] + nums[j] + nums[end] > 0) {
                            end--;
                        }
                        if (nums[i] + nums[j] + nums[end] == 0) {
                            List<Integer> temp = new ArrayList<>();
                            temp.add(nums[i]);
                            temp.add(nums[j]);
                            temp.add(nums[end]);
                            result.add(temp);
                            end--;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        int[] nums = {-1, 0, 1, 2, -1, -4};
        int[] nums = {-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4};
//        int[] nums = {0, 0, 0, 0, 0, 0};
        List<List<Integer>> result = threeSum(nums);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i).get(0) + "," + result.get(i).get(1) + "," + result.get(i).get(2));
        }
    }
}
