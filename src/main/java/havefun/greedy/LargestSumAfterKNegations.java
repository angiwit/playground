package havefun.greedy;

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
        if (k > 0 && (k & 0x1) == 1) {
            return sum - sorted[0];
        }
        return sum;
    }
}
