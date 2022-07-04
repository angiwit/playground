package havefun.array;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/delete-columns-to-make-sorted-ii/
 */
public class MinDeletionSize {

    public static void main(String[] args) {
        String[] strs = {"zyx", "wvu", "tsr"};
        System.out.println(minDeletionSize(strs));
    }

    public static int minDeletionSize(String[] strs) {
        if (strs == null || strs.length == 0) return 0;
        int perLen = strs[0].length();
        int result = 0;
        String[] cur = new String[perLen];
        for (int i = 0; i < perLen; i++) {
            String[] cur2 = Arrays.copyOf(cur, perLen);
            for (int j = 0; j < strs.length; j++) {
                cur2[j] += strs[j].charAt(i);
            }
            if (isSorted(cur2)) {
                cur = cur2;
            } else {
                result++;
            }
        }
        return result;
    }

    private static boolean isSorted(String[] strs) {
        for (int i = 0; i < strs.length - 1; i++) {
            if (strs[i].compareTo(strs[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }


}
