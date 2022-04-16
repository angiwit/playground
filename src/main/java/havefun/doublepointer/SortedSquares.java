package havefun.doublepointer;

public class SortedSquares {

    public static int[] sortedSquares(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];
        return sortedSquaresCore(nums);
    }

    public static int[] sortedSquaresCore(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int[] result = new int[nums.length];
        int k = nums.length - 1;
        while (left <= right) {
            if (nums[left] * nums[left] >= nums[right] * nums[right]) {
                result[k--] = nums[left] * nums[left];
                left++;
            } else {
                result[k--] = nums[right] * nums[right];
                right--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-4, -1, 0, 3, 10};
        int[] result = sortedSquares(nums);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
