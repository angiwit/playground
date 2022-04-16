package havefun.dp;

import java.util.Arrays;

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
        /**
         * dp[0] should be initialized to 0 since dp[1] should equal to 1.
         */
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            // j * j could equals to i, so use <= here.
            for (int j = 1; j * j <= i; j++) {
                /**
                 * Formula should reflect the calculation in the problem.
                 * j * j equals 1 which means a square number found.
                 * i - j * j means the problem is divided into a sub problem: found the smallest number of
                 * i - j * j.
                 * every number can be formed by multiple 1 * 1, so we don't need to check if i - j * j is
                 * also can be formed by square number, since it definitely is.
                 */
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
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