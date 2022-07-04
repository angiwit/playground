package havefun.array;

public class NextPermutation {

    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (i >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
            // i和j交换完成后，i后面的所有数字都是降序的，所以从i开始将后续数字变为升序。
        }
        // reverse放在外层，因为如果数字本身是完全降序的，这个时候没有发生交换。
        // 但是我们仍然需要把整个数字做翻转来拿到下一个开始最小值。
        reverse(nums, i + 1, nums.length - 1);
    }

    // 升序组成最小的数字
    // Since the numbers after between i and j are desc order, so making them asc.
    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
