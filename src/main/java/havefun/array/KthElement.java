package havefun.array;

/**
 * In an acs ordered array, the biggest number is array.length - 1.
 * The second biggest number is array.length - 2.
 * The thrid biggest number is array.length - 3.
 * So the kth biggest number is array.length - k.
 * <p>
 * After an array is partitioned, let's say the pivotal element's index is i, then the array is partitioned
 * to left part and right part, the right part's length is array.length - i, and ith element is included into
 * the right part, the left part length is i, and ith element is not included.
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
        int pivotal = partition(nums, start, end);
        if (pivotal == end + 1 - k) {
            return nums[pivotal];
        } else if (pivotal < end + 1 - k) {
            // k in big partition, still find kth element. start = pivotal + 1
            return findKthLargestCore(nums, k, pivotal + 1, end);
        } else {
            // k in small partition, find k - (size - pivotal)
            return findKthLargestCore(nums, k - (end + 1 - pivotal), start, pivotal - 1);
        }
    }

    private static int partition(int[] nums, int start, int end) {
        int small = start - 1;
        int pivotal = nums[end];
        for (int i = start; i < end; i++) {
            if (nums[i] < pivotal) {
                if (++small != i) {
                    swap(nums, i, small);
                }
            }
        }
        swap(nums, ++small, end);
        return small;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
