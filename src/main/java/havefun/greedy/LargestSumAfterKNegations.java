package havefun.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class LargestSumAfterKNegations {

    public static void main(String[] args) {
        int[] nums = {3, -1, 0, 2};
        int k = 3;
//        int[] nums = {4, 2, 3};
//        int k = 1;
        System.out.println(largestSumAfterKNegations(nums, k));
    }

    public static int largestSumAfterKNegations(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        return largestSumAfterKNegationsCore(nums, k);
    }

    public static int largestSumAfterKNegationsCore(int[] nums, int k) {
        nums = IntStream.of(nums)
                .boxed()
                .sorted(Comparator.comparingInt(Math::abs))
                .mapToInt(Integer::intValue)
                .toArray();
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] <= 0) {
                k--;
                nums[i] = -nums[i];
            }
        }
        if ((k & 0x1) == 1) {
            nums[0] = -nums[0];
        }
        return Arrays.stream(nums).sum();
    }
}
