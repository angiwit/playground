package havefun.array;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaxSlidingWindow {


    public static void main(String[] args) {
        Deque<Integer> test = new LinkedList<>();
        test.offerFirst(1);
        test.offerFirst(2);
        test.offerFirst(3);
        System.out.println(test.peekFirst());
        System.out.println(test.peekLast());
    }

    public static void main1(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] result = maxSlidingWindow(nums, k);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + ",");
        }
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length) return new int[]{0};
        Queue<Integer> sort = new PriorityQueue<>((x, y) -> y - x);
        for (int i = 0; i < k; i++) {
            sort.offer(nums[i]);
        }
        int[] result = new int[nums.length - k + 1];
        for (int i = k; i < nums.length + 1; i++) {
            result[i - k] = sort.peek();
            sort.remove(nums[i - k]);
            if (i < nums.length) sort.offer(nums[i]);
        }
        return result;
    }

    public static int[] maxSlidingWindow1(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length) return new int[]{0};
        Deque<Integer> monotone = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!monotone.isEmpty() && nums[i] >= nums[monotone.peekLast()]) {
                monotone.removeLast();
            }
            monotone.offerLast(i);
        }
        int[] result = new int[nums.length - k + 1];
        result[0] = monotone.peekFirst();
        for (int i = k; i < nums.length; i++) {
            while (!monotone.isEmpty() && nums[monotone.peekLast()] <= nums[i]) {
                monotone.removeLast();
            }
            monotone.offerLast(i);
            while (!monotone.isEmpty() && monotone.peekFirst() <= i - k) {
                monotone.removeFirst();
            }
            result[i - k + 1] = monotone.peekFirst();
        }
        return result;
    }
}
