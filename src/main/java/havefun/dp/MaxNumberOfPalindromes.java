package havefun.dp;

import java.util.Arrays;

public class MaxNumberOfPalindromes {

    public static void main(String[] args) {
        boolean[][] dp = new boolean[10][10];
        System.out.println(dp[0][0]);
    }

    public static int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        return countSubstringsCore(s);
    }

    // nested if can be merged together, refer to countSubstringsCore1.
    public static int countSubstringsCore(String s) {
        boolean[][] dp = new boolean[s.length() + 1][s.length() + 1];
        Arrays.fill(dp, false);
        int result = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 1) {
                        result++;
                        dp[i][j] = true;
                    } else if (dp[i + 1][j - 1]) {
                        result++;
                        dp[i][j] = true;
                    }
                }
            }
        }
        return result;
    }

    public static int countSubstringsCore1(String s) {
        boolean[][] dp = new boolean[s.length() + 1][s.length() + 1];
        Arrays.fill(dp, false);
        int result = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i + 1][j - 1])) {
                    result++;
                    dp[i][j] = true;
                }
            }
        }
        return result;
    }
}
