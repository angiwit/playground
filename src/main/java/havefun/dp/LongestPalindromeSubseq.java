package havefun.dp;

public class LongestPalindromeSubseq {

    public static void main(String[] args) {
        String s = "bbbab";
        System.out.println(longestPalindromeSubseq(s));
    }

    public static int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) return 0;
        return longestPalindromeSubseqCore(s);
    }

    public static int longestPalindromeSubseqCore(String s) {
        int[][] dp = new int[s.length() + 1][s.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }
        int max = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], Math.max(dp[i][j - 1], dp[i + 1][j - 1]));
                }
                max = Math.max(dp[i][j], max);
            }
        }
        return dp[0][s.length() - 1];
    }
}
