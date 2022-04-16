package havefun.string;

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels {

    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        partitionLabels(s).stream().forEach(System.out::println);
    }

    public static List<Integer> partitionLabels(String s) {
        if (s == null || s.length() == 0) return null;
        return partitionLabelsCore(s);
    }

    public static List<Integer> partitionLabelsCore(String s) {
        List<Integer> res = new ArrayList<>();
        int longestIndex = 0;
        int startIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length() - 1; j >= i; j--) {
                if (s.charAt(i) == s.charAt(j)) {
                    longestIndex = Math.max(j, longestIndex);
                }
            }
            if (longestIndex == i) {
                res.add(i - startIndex + 1);
                startIndex = i + 1;
            }
        }
        return res;
    }
}
