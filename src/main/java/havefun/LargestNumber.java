package havefun;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/largest-number/
 */
public class LargestNumber {

    /**
     * if compareTo returns negative, ordering from small to large.
     * if compareTo returns positive, ordering from large to small.
     * If want to sort by nature order, pass the larger as the parameter, otherwise pass the smaller as the parameter.
     * Regard the numbers as string, so that the + operation becomes to string concanation, different concat order
     * will have different result and we can use this as the compare function in the comparator.
     *
     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {
        String[] ss = new String[nums.length];
        for (int i = 0; i < nums.length; i++) ss[i] = "" + nums[i];
        Arrays.sort(ss, (o1, o2) -> (o2 + o1).compareTo(o1 + o2)); // o2 + o1 larger than o1 + o2, then ()
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ss.length; i++) sb.append(ss[i]);
        if (sb.charAt(0) == '0') return "0";
        return sb.toString();
    }

    public static void main(String[] args) {
        Integer[] nums = {1, 3, 2};
        Arrays.sort(nums, (x, y) -> y.compareTo(x));
    }
}
