package havefun.array.binarysearch;

// https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/
public class SearchRange {
    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int[] result = searchRange(nums, 8);
        System.out.println(result[0] + " " + result[1]);
    }

    public static int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int[]{-1, -1};
        return searchRangeCore(nums, target, 0, nums.length - 1);
    }

    public static int[] searchRangeCore(int[] nums, int target, int left, int right) {
        int found = -1;
        while (left <= right) {
            int pivotal = (right + left) / 2;
            if (nums[pivotal] > target) {
                right = pivotal - 1;
            } else if (nums[pivotal] < target) {
                left = pivotal + 1;
            } else {
                found = pivotal;
                break;
            }
        }
        if (found == -1) {
            return new int[]{-1, -1};
        }
        int _left = found;
        while (_left >= 0 && nums[_left] == target) {
            _left--;
        }
        int _right = found;
        while (_right <= nums.length - 1 && nums[_right] == target) {
            _right++;
        }
        return new int[]{++_left, --_right};
    }
}
