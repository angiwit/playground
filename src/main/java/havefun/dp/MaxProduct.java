package havefun.dp;


public class MaxProduct {

    public static void main(String[] args) {
        int[] nums = {2, 3, -2, 4};
        System.out.println(maxProduct(nums));
    }

    /**
     * If I can know ith number max product, then I can know (i+1)th number's max product in polynomial time.
     * So the (i+1)th number result rely on ith result.
     */
    public static int maxProduct(int[] nums) {
        int result = Integer.MIN_VALUE, min = 1, max = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                int temp = max;
                max = min;
                min = temp;
            }
            min = Math.min(nums[i], min * nums[i]);
            max = Math.max(nums[i], max * nums[i]);
            result = Math.max(result, max);
        }
        return result;
    }
}
