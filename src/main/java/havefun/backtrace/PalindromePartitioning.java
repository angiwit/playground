package havefun.backtrace;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {

    public static List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) return null;
        List<List<String>> result = new ArrayList<>();
        partitionCore(0, result, s, new ArrayList<>());
        return result;
    }

    public static void partitionCore(int start, List<List<String>> res, String s, List<String> once) {
        if (start >= s.length()) { // can Not replace with start == s.length() -1, since we need to add last char as well.
            res.add(once);
        }
        for (int i = start; i < s.length(); i++) { // i = start means cutting 0 char 'cause the whole string could be palindrome.
            String first = s.substring(start, i);
            if (isPalindrome(first)) {
                once.add(first);
            } else {
                continue;
            }
            partitionCore(i + 1, res, s, once);
            once.remove(once.size() - 1); // back to upper level, remove last one and check i++ one.
        }
    }

    private static boolean isPalindrome(String path) {
        return false;
    }

    public static void main(String[] args) {
        String test = "12345";
        System.out.println(test.substring(0, 0));
        System.out.println(test.substring(0, 1));
    }
}
