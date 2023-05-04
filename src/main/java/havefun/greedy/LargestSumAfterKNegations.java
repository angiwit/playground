package havefun.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;
// https://leetcode.cn/problems/maximize-sum-of-array-after-k-negations/
public class LargestSumAfterKNegations {

    public static void main(String[] args) {
        // int[] nums = {3, -1, 0, 2};
        // int k = 3;
       int[] nums = {4, 2, 3};
       int k = 1;
        System.out.println(largestSumAfterKNegations(nums, k));
    }

    public static int largestSumAfterKNegations(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        return largestSumAfterKNegationsCore(nums, k);
    }

    /**
     * We shouldn't use sum to track the calculated value, we should calculate the sign for each number in the for loop,
     * and calculate the final sum in one motion. Because the using the sum to track the calculated value could be wrong.
     * E.g. [4, 3, 2] k = 1, after sort array is: [2, 3, 4] and sum is 9, k is 1, then we make the first number -2, the 
     * final value is 7, but in fact it should be [-2, 3, 4] and the sum is 5.
     * 
     * After the loop, if k is odd, is it correct to always minus the first number in the sorted array? 
     * Still has k means all the numbers in the array have been converted to positive number, and now we need to find the
     * smallest number in the coverted array which is the first number, so we should choose it to convert to negative.
     * @param nums
     * @param k
     * @return
     */
    public static int largestSumAfterKNegationsCoreIncorrect(int[] nums, int k) {
        int[] sorted = IntStream.of(nums)
                .boxed()
                .sorted(Comparator.comparingInt(Math::abs))
                .mapToInt(x -> x.intValue())
                .toArray();
        int sum = 0;
        for (int i = 0; i < sorted.length; i++) {
            if (sorted[i] <= 0 && k-- > 0) {
                sum -= sorted[i];
            } else {
                sum += sorted[i];
            }
        }
        if (k > 0 && (k << 1) == 1) {
            return sum - sorted[0];
        }
        return sum;
    }

    /**
     * Absolute value increasing means bigger negative number will in the left right smaller negative numbers in the right.
     * @param nums
     * @param k
     * @return
     * 
     */
    public static int largestSumAfterKNegationsCore(int[] nums, int k) {
        int[] sorted = IntStream.of(nums)
                .boxed()
                .sorted(Comparator.comparingInt(Math::abs))
                .mapToInt(x -> x.intValue())
                .toArray();
        for (int i = sorted.length - 1; i >= 0; i--) {
            if (sorted[i] < 0 && k-- > 0) sorted[i] = -sorted[i];
        }
        if (k > 0 && k % 2 == 1) sorted[0] = -sorted[0];
        return Arrays.stream(sorted).sum();
    }
}
