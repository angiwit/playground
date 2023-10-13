package havefun.array;

public class MergeSort {

    /**
     * If we need to pass a range as parameter to make this array more even, and we have a middle index.
     * Then we can pass startIndex, middleIndex as the first range, and middleIndex + 1, endIndex as the
     * second range.
     *
     * @param nums
     */
    public static void mergeSort(int[] nums) {
        mergeSortCore(nums, 0, nums.length - 1);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    public static void mergeSortCore(int[] nums, int start, int end) {
        if (start < end) {
            int partition = (end + start) / 2;
            mergeSortCore(nums, start, partition);
            mergeSortCore(nums, partition + 1, end);
            int[] left = new int[partition - start + 1];
            int[] right = new int[end - partition];
            for (int i = 0; i < left.length; i++) {
                left[i] = nums[i + start];
            }
            for (int i = 0; i < right.length; i++) {
                right[i] = nums[i + partition + 1];
            }
            int i = 0, j = 0, k = start;
            while (i < left.length && j < right.length) {
                if (left[i] > right[j]) {
                    nums[k++] = right[j++];
                } else {
                    nums[k++] = left[i++];
                }
            }
            while (i < left.length) {
                nums[k++] = left[i++];
            }
            while (j < right.length) {
                nums[k++] = right[j++];
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 3, 4, 2};
        mergeSort(nums);
    }
}
