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
            int middle = (end + start) / 2; // start + end/2 - start/2 = start/2 + end/2
            mergeSortCore(nums, start, middle);
            mergeSortCore(nums, middle + 1, end);

            /**
             * For array's length is 2, middle is 0, start is 0, end is 1, but we need left to be 1 as the new array's length.
             * For array's length is 4, middle is 1, start is 0, end is 3, the left length is 1-0+1=2, the right's length is 3-1=2.
             * For an array, choose an index as pivotal, then it can be partitioned to two parts:
             * 1. left part length is index - 0 + 1
             * 2. right part length is (array's last index) - index
             * 3. the element of index included in left part.
             */
            int left = middle - start + 1; //left array size, the middle elements in this array.
            int right = end - middle; // right array size
            int[] leftA = new int[left];
            int[] rightA = new int[right];
            for (int i = 0; i < left; i++) {
                leftA[i] = nums[i + start];
            }
            for (int i = 0; i < right; i++) {
                rightA[i] = nums[i + middle + 1];
            }
            int i, j, k;
            i = 0;
            j = 0;
            k = start;
            while (i < left && j < right) {
                if (leftA[i] <= rightA[j]) {
                    nums[k++] = leftA[i++];
                } else {
                    nums[k++] = rightA[j++];
                }
            }
            while (i < left) {
                nums[k++] = leftA[i++];
            }
            while (j < right) {
                nums[k++] = rightA[j++];
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 3, 4, 2};
        mergeSort(nums);
    }
}
