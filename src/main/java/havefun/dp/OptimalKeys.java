package havefun.dp;

public class OptimalKeys {

    public static void main(String[] args) {
        System.out.println(optimalKeys(20));
    }

    /**
     * look back in the previous results to find a biggest value and update current index.
     * To use i - j - 1 to get the number of copy time.
     *
     * @param input
     * @return
     */
    public static int optimalKeys(int input) {
        int[] dp = new int[input + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= input; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int j = 0; j <= i - 3; j++) {
                dp[i] = Math.max(dp[j] * (i - j - 1), dp[i]);
                System.out.println(String.format("dp[%s]=%s", i, dp[i]));
            }
        }
        return dp[input];
    }
}
