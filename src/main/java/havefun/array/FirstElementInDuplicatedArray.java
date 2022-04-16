package havefun.array;

import java.util.Arrays;

public class FirstElementInDuplicatedArray {

    public static int firstElement(int[] nums, int k) {
        if (nums.length == 0) return -1;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
        int left = firstSmallerElementLeftestIndex(nums, k, 0, nums.length - 1);
        int right = firstRightestSmallerElementCore(nums, k, 0, nums.length - 1);
        System.out.println(left + " " + right);
        return 1;
    }

    public static int firstLeftestSmallerElementCore(int[] nums, int k, int left, int right) {
        /**
         * when left to the last step, its value may smaller/equals than target.
         * when right to the last step, its value definitely bigger/equals to target.
         * This can NOT cover single element equals to target's scenario.
         */
        while (left <= right) { // left < right
            int mid = (left + right) / 2;
            if (nums[mid] >= k) {
                right = mid - 1; // right = mid
            } else {
                left = mid + 1;
            }
        }
        if (nums[left] == k) return left;
        return -1;
    }

    public static int firstSmallerElementLeftestIndex(int[] nums, int k, int start, int end) {
        int firstSmallerElement = firstRightestSmallerElementCore(nums, k, start, end);
        if (nums[firstSmallerElement] == k)
            return firstLeftestSmallerElementCore(nums, nums[firstSmallerElement - 1], start, end);
        return firstLeftestSmallerElementCore(nums, nums[firstSmallerElement], start, end);
    }

    public static int firstRightestSmallerElementCore(int[] nums, int k, int left, int right) {
        /**
         * when left to the last step, its value may smaller/equals to target.
         * when right to the last step, its value may bigger/equals to target.
         * the last round: left == right, checks if the final element equals to target.
         */
        while (left <= right) {
            int mid = left + ((right - left) / 2);
            if (nums[mid] <= k) { // this is the key point, when reviewing number is smaller than target, we make left move to right.
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (nums[right] < k) return right;
        else return right - 1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 6, 0, 2, 3, 3, 4, 4, 5, 6, 2, 6};
        System.out.println(firstElement(nums, 7));
    }
}
