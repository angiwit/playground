package havefun.backtrace;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

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
