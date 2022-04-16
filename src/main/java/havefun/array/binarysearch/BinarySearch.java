package havefun.array.binarysearch;

public class BinarySearch {

    public static int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        return searchCoreIteration(nums, target, 0, nums.length - 1);
    }

    /**
     * If using left <= right, then right = middle - 1; if left < right, then right = middle.
     * Consider this:
     * 1. if at last, left == right, and left <= right is the condition,
     * if we don't make right move to left, then the middle will be 0 and into infinite loop.
     * 2. if at last, there are two elements, and left < right is the condition,
     * middle always equals to left, if we make right = right - 1, then when left + 1 reached,
     * left + 1 == right, loop end! The right element will be skipped.
     *
     * @param nums
     * @param target
     * @param left
     * @param right
     * @return
     */
    public static int searchCoreIteration(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int middle = left + ((right - left) / 2);
            if (nums[middle] < target) {
                left = middle + 1;
            } else if (nums[middle] > target) {
                right = middle - 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    public static int searchCoreRecursive(int[] nums, int target, int start, int end) {
        if (end == start + 1) {
            if (nums[start] == target) return start;
            if (nums[end] == target) return end;
            return -1;
        }
        if (end == start) {
            if (nums[start] == target) return start;
            return -1;
        }
        int pivotal = (end - start) / 2 + start;
        if (nums[pivotal] == target) {
            return pivotal;
        } else if (nums[pivotal] > target) {
            return searchCoreRecursive(nums, target, start, pivotal);
        } else if (nums[pivotal] < target) {
            return searchCoreRecursive(nums, target, pivotal, end);
        }
        return -1;
    }

    public static void main(String[] args) {
//        int[] nums = {-1, 0, 3, 5, 9, 12};
        int[] nums = {-1, 0, 3, 5, 9, 12};
        System.out.println(search(nums, 2));
    }
}
