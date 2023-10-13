package havefun.array.binarysearch;

// https://leetcode.cn/problems/search-insert-position/
public class SearchInsert {

    public static void main(String[] args) {
//        int[] nums = {1, 3, 5, 6};
//        System.out.println(searchInsert(nums, 5));
//        int[] nums = {1, 3, 5, 6};
//        System.out.println(searchInsert(nums, 2));
        int[] nums = {1, 3, 5, 6};
        System.out.println(searchInsert(nums, 7));
    }

    public static int searchInsert(int[] nums, int target) {
        return searchInsertCore(nums, target, 0, nums.length - 1);
    }

    public static int searchInsertCore(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int pivotal = (left + right) / 2;
            if (nums[pivotal] == target) {
                return pivotal;
            } else if (nums[pivotal] < target) {
                left = pivotal + 1;
            } else {
                right = pivotal - 1;
            }
        }
        if (left > nums.length - 1) {
            return nums.length;
        }
        // all numbers are bigger than target.
        if (nums[left] > target) {
            return left;
        }
        return right;
    }
}
