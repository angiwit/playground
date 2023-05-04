package havefun.doublepointer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

//https://leetcode.cn/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/submissions/
public class LongestSubarray {

    public static int longestSubarray(int[] nums, int limit) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0, right = 0, result = 0;
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        while (right < nums.length) {
            treeMap.put(nums[right], treeMap.getOrDefault(nums[right], 0) + 1);
            while (treeMap.lastKey() - treeMap.firstKey() > limit) {
                treeMap.put(nums[left], treeMap.getOrDefault(nums[left], 0) - 1);
                if (treeMap.get(nums[left]) == 0) {
                    treeMap.remove(nums[left]);
                }
                left++;
            }
            result = Math.max(result, right - left + 1);
            right++;
        }
        return result;
    }

    /**
     * Using two deque to track the values in asc order and desc order corresponding to minQueue and maxQueue.
     * First element in maxQueue is the biggest number encountered and the first element in minQueue is the smallest.
     * Calculate the max minus value of values in encountered numbers is to use the biggest number to minus the smallest one.
     * We also need to pop out the element once we found the max minus value is bigger than limit, so we need to use another
     * pointer to point to the beginning of the range.
     * When biggest - smallest > limit, it means the first value in maxQueue or minQueue is not a suitable value, we need to
     * pop it out and make the left pointer forward 1 step.
     * Otherwise means the right - left is a valid result, and we need to check if it's bigger than current max, if true update
     * current max (return value). 
     * 
     * Stack or Deque are suitable data structure for tracking max value or min value in an array range, we need this keen to 
     * ensure we can come up this when we encounter a similar problem.
     * @param nums
     * @param limit
     * @return
     */
    public static int longestSubarray1(int[] nums, int limit) {
        Deque<Integer> maxQueue = new ArrayDeque<>();
        Deque<Integer> minQueue = new ArrayDeque<>();
        int l = 0, r = 0, ret = 0;
        while (r < nums.length) {
            while (!maxQueue.isEmpty() && maxQueue.peekLast() < nums[r])
                maxQueue.removeLast();
            while (!minQueue.isEmpty() && minQueue.peekLast() > nums[r])
                minQueue.removeLast();

            maxQueue.offerLast(nums[r]);
            minQueue.offerLast(nums[r]);
            r++;
            while (maxQueue.peekFirst() - minQueue.peekFirst() > limit) {
                if (maxQueue.peekFirst() == nums[l]) maxQueue.remove();
                if (minQueue.peekFirst() == nums[l]) minQueue.remove();
                l += 1;
            }

            ret = Math.max(ret, r - l);

        }
        return ret;
    }

    public static void main(String[] args) {
        int[] nums = {4, 2, 2, 2, 4, 4, 2, 2};
        int limit = 0;
        System.out.println(longestSubarray1(nums, limit));
    }

}
