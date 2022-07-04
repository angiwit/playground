package havefun.array;

/**
 * https://leetcode.cn/problems/rotate-array/
 */
public class Rotate {

    /**
     * There are multiple solution:
     * 1. Use another array to store the elements and use (i + k) % n to locate the new array index.
     * 2. Mock the process, everytime replace one element in i + k index, and make sure this happens to all elements.
     * 3. Use double-reverse approach, reverse the array and then reverse two sub arrays.
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
            i++;
            j--;
        }
    }
}
