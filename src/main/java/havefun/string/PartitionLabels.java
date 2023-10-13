package havefun.string;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/partition-labels/
 */
public class PartitionLabels {

    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        partitionLabels(s).stream().forEach(System.out::println);
    }

    public static List<Integer> partitionLabels(String s) {
        if (s == null || s.length() == 0) return null;
        return partitionLabelsCore(s);
    }

    /**
     * From end find the first letter last index, then in the range of [first letter, last index] from left to right
     * to find the rightest index of chars in the range.
     * Once the left pointer equals to last index it means the current range is traversed completely and a range can
     * be added to the result.
     *
     * @param s
     * @return
     */
    public static List<Integer> partitionLabelsCore(String s) {
        int start = 0, longestEnd = 0;
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length() - 1; j >= 0; j--) {
                if (s.charAt(i) == s.charAt(j)) {
                    longestEnd = Math.max(longestEnd, j);
                }
            }
            if (i == longestEnd) {
                result.add(i - start + 1);
                start = i + 1;
            }
        }
        return result;
    }
}
