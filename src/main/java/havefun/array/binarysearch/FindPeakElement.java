package havefun.array.binarysearch;

/**
 * https://leetcode.cn/problems/find-peak-element/
 */
public class FindPeakElement {

    public static int findPeakElement(int[] nums) {
        if (nums == null) return 0;
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            int mid = (i + j) / 2;
            if (nums[mid] > nums[mid + 1]) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        return i;
    }

    private static int findPeakElementCore(int[] nums, int start, int end) {
        if (start == end) return -1; // only one number.
        int middle = (start + end) / 2;
        int candidate = nums[middle];
        if (nums[middle - 1] < candidate && nums[middle + 1] < candidate) return middle;
        int left = findPeakElementCore(nums, start, middle - 1);
        int right = findPeakElementCore(nums, middle + 1, end);
        if (left != -1) return left;
        if (right != -1) return right;
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        System.out.println(findPeakElement(nums));
    }
}
