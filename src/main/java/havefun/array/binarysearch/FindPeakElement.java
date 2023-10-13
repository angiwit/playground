package havefun.array.binarysearch;

/**
 * https://leetcode.cn/problems/find-peak-element/
 */
public class FindPeakElement {

    /**
     * In binary search, it's like two people finally one day, they'll meet, and the thing making them move to each other is the their middle.
     * This problem, we need to make sure the right pointer always points to an element that is bigger than its right element, left pointer 
     * always points to an element that is bigger than its left element. With this prerequisite, one day when they meet, their left and right
     * are all smaller elements.
     *
     * Why do we use i < j instead of i <= j? The reason is when i == j, we can claim that we already found the peak element, so there's no 
     * necessary to continue the loop, and one step more loop can make mistaken result.
     * 
     * We also need to consider the boundry issue, if there's only one element, the left == right, the for loop will not execute and the result
     * is the only element which is also the left index. If there's two elements, left < right, and the middle is left, if nums[mid] < nums[mid + 1],
     * then left points to right, and right is the peak element. Basically with this approach, the i and j could be not moved at all, even they
     * move, the moving happens in the array, and the boundry is automatically been taken care.
     * 
     * @param nums
     * @return
     */
    public static int findPeakElementCorrect(int[] nums) {
        if (nums == null) return 0;
        int i = 0; int j = nums.length - 1;
        while (i < j) {
            int mid = (i + j) / 2;
            if (nums[mid] > nums[mid + 1]) {
                // when mid element is bigger than its right element, it means the mid element is a posible peak element.
                // we let j point to the bigger element, which means we'll always make j points to a bigger value.
                // And more important, we're making sure the right element is smaller than nums[j].
                // In the final step, i could move to j's position and then we can claim we found a peak element.
                j = mid;
            } else {
                // when mid element is smaller than its right element, it menas the mid element is not a posible peak element.
                // but its right element could be. Making i = its right element because we're making i pointing to final result.
                // And more important, we're making sure the left element is smaller than num[i].
                // In the step, j could move to i's position and then we can claim we found a peak element.
                i = mid + 1;
            }
        }
        return i;
    }

    /**
     * The reason line 33 is not correct is the array's -1 and n position in fact has value which is minus infinity.
     * Adding the checking will ignore these two position value and leading to incorrect result.
     * With this thinking, this problem is hardly to be solved, we need to consider the two pointers and the moving
     * and the move condition, e.g. always make sure the right pointer's right element smaller and left pointer's left
     * element smaller as well.
     * @param nums
     * @return
     */
    public static int findPeakElementInCorrect(int[] nums) {
        if (nums == null) return 0;
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            int mid = (i + j) / 2;
            if (mid + 1 < nums.length && mid - 1 >= 0 && nums[mid] > nums[mid + 1] && nums[mid] < nums[mid - 1]) {
                return mid;
            }
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
        int[] nums1 = {1,2};
        System.out.println(findPeakElementCorrect(nums1));
    }
}
