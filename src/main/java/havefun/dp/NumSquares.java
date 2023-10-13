package havefun.dp;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/perfect-squares/
 */
public class NumSquares {

    public static void main(String[] args) {
        System.out.println(numSquares(12));
    }

    public static int numSquares(int n) {
        if (n == 0) return 0;
        return numSquaresCore(n);
    }

    public static int numSquaresCore(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= n; j++) {
                if (i >= j * j) {
                    dp[i] = Math.min(dp[i], dp[i - j * j] + 1); // choose number.
                }
                // Can not extend the value from previous result, since not every number is a square number.
//                else {
//                    dp[i] = dp[i - 1];
//                }
            }
        }
        return dp[n];
    }

    public static int numSquaresCoreRecursive(int n) {
        if (n == 0 || n == 1) return 1;
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < n / 2 + 1; i++) {
            if (res == Integer.MAX_VALUE) {
                res = numSquaresCoreRecursive(n - i * i) + 1;
            } else {
                res = Math.min(numSquaresCoreRecursive(n - i * i) + 1, res);
            }
        }
        return res;
    }
}