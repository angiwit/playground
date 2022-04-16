package havefun.array;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElements {

    public static int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) return new int[nums.length];
        return nextGreaterElementCore1(nums);
    }

    public static int[] nextGreaterElementCore1(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[n];
        Arrays.fill(result, -1);
        for (int i = 0; i < 2 * n + 1; i++) {
            /**
             * Notice that you need to be very careful when writing if in while loop!
             * Make sure the if condition needs to in the loop not as the condition,
             * most cases you need to make the if condition parallel with while condition,
             * since once the if not match and nothing changed(state), there's a dead loop.
             *
             * while (stack.size() > 0) {
             *      if (nums[i % n] > nums[stack.peek()]) {
             *          result[stack.pop()] = nums[i % n];
             *      }
             * }
             */
            while (stack.size() > 0 && nums[i % n] > nums[stack.peek()]) {
                result[stack.pop()] = nums[i % n];
            }
            /**
             * Make sure what the mode number should be!!!
             */
            stack.push(i % n);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1};
//        int[] nums = {1, 2, 3, 4, 3};
        int[] result = nextGreaterElements(nums);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
