package havefun.dp;

public class NumDecodings {

    public static void main(String[] args) {
        System.out.println(numDecodings("27"));
    }

    public static int numDecodings(String s) {
        if (s.startsWith("0")) return 0;
        int[] dp = new int[s.length() + 1];
        dp[1] = 1;
        for (int i = 2; i < s.length() + 1; i++) {
            if (s.charAt(i - 1) == '0') {
                dp[i] = dp[i - 2];
            } else {
                int two = (s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1);
                if (two >= 0 && two <= 26) {
                    dp[i] = dp[i - 2] + dp[i - 1] + 1;
                } else {
                    dp[i] = dp[i - 1];
                }
            }
        }
        return dp[s.length()];
    }
}
