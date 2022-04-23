package havefun.backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/palindrome-partitioning/
 * We need to partition the string TOTALLY to find the partitions.
 */
public class PalindromePartitioning {

    public static List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) return null;
        List<List<String>> result = new ArrayList<>();
        partitionCore(0, result, s, new ArrayList<>());
        return result;
    }

    public static void partitionCore(int start, List<List<String>> res, String s, List<String> once) {
        if (start >= s.length()) {
            res.add(new ArrayList<>(once));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            String sub = s.substring(start, i + 1);
            if (sub.length() == 1) {
                once.add(sub);
            } else if (isPalindrome(sub)) {
                once.add(sub);
            } else { // if this is not palindrome, then the string can not be partitioned here, so continue.
                continue;
            }
            partitionCore(i + 1, res, s, once);
            once.remove(once.size() - 1);
        }
    }

    private static boolean isPalindrome(String path) {
        int i = 0, j = path.length() - 1;
        while (i < j) {
            if (path.charAt(i) == path.charAt(j)) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String test = "aab";
        List<List<String>> result = partition(test);
        for (int i = 0; i < result.size(); i++) {
            System.out.print("[");
            for (int j = 0; j < result.get(i).size(); j++) {
                System.out.print("[");
                System.out.print(j == result.get(i).size() - 1 ? result.get(i).get(j) : result.get(i).get(j) + ",");
                System.out.print("]");
            }
            System.out.print("]");
        }

    }
}
