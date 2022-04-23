package havefun.dp;

/**
 * https://leetcode-cn.com/problems/jian-sheng-zi-lcof/
 */
public class CuttingRope {

    public static void main(String[] args) {
        System.out.println(cuttingRope(10));
    }

    public static int cuttingRope(int n) {
        if (n <= 1) return 0;
        return cuttingRopeCore(n);
    }

    public static int cuttingRopeCore(int n) {
        int[] dp = new int[n + 1]; // n length rope's max value.
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(Math.max((i - j) * j, dp[i - j] * j), dp[i]);
            }
        }
        return dp[n];
    }
}
