package havefun.doublepointer;

public class RemoveElement {

    public static void main(String[] args) {
//        int[] nums = {3, 2, 2, 3};
//        System.out.println(removeElement(nums, 3));

//        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
//        System.out.println(removeElement(nums, 3));
        int[] nums = {3, 2, 2, 3};
        int result = removeElement(nums, 3);
        for (int i = 0; i < result; i++) {
            System.out.println(nums[i]);
        }
    }

    public static int removeElement(int[] nums, int val) {
        if (nums.length == 0) return 0;
        return removeElementCore2(nums, val);
    }

    /**
     * Using two pointers to move all the non-val to the array's beginning.
     *
     * @param nums
     * @param val
     * @return
     */
    public static int removeElementCore2(int[] nums, int val) {
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow++] = nums[fast++];
            } else {
                fast++;
            }
        }
        return slow;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
