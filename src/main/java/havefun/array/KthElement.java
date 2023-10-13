package havefun.array;

/**
 * In an acs ordered array, the biggest number is array.length - 1.
 * The second biggest number is array.length - 2.
 * The thrid biggest number is array.length - 3.
 * So the kth biggest number is array.length - k.
 * <p>
 * length-index: number of cells from index to end.
 * k - (num.length - parition): new kth element
 * <p>
 * i++: increase i after the current statement ran.
 * ++i: increase i before the current statement ran.
 */
public class KthElement {

    public static void main(String[] args) {

        int[] nums = {0, 0, 0, 0, 0, 0};
        System.out.println(findKthLargest(nums, 4));
    }

    public static int findKthLargest(int[] nums, int k) {
        return findKthLargestCore(nums, k, 0, nums.length - 1);
    }

    public static int findKthLargestCore(int[] nums, int k, int start, int end) {
        if (start > end) return -1;
        int partition = partition(nums, start, end);
        if (partition == nums.length - k) return nums[partition];
        if (partition < nums.length - k) {
            return findKthLargestCore(nums, k, partition + 1, end);
        } else {
            return findKthLargestCore(nums, k - (nums.length - partition), start, partition - 1);
        }
    }

    private static int partition(int[] nums, int start, int end) {
        int middle = nums[end];
        int boundary = start - 1; // note here should be starting at `start - 1`.
        for (int i = start; i < end; i++) {
            if (nums[start] < middle) {
                swap(nums, ++boundary, i);
            }
        }
        swap(nums, ++boundary, end);
        return boundary;
    }

    private static void swap(int[] nums, int boundary, int i) {
        int temp = nums[i];
        nums[i] = nums[boundary];
        nums[boundary] = temp;
    }


}
