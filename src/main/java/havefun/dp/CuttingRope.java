package havefun.dp;

import java.util.Arrays;

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

    /**
     * From 4 start, cut will always gets higher value. max with dp[i] only benifit when i smaller than 4.
     * For length in 1, 2, 3, we can choose not to cut the rope to get the highest value.
     * Note that the inner for loop condition is j <= i/2, because this approach has symmetry. E.g.: dp[5] * dp[6] = dp[6] * dp[5]
     * @param n
     * @return
     */
    public static int cuttingRopeCore(int n) {
        int[] dp = new int[n + 1]; // n length rope's max value.
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                // We have to use max function with dp[i] as a parameter since this is an inplace update.
                dp[i] = Math.max(dp[i - j] * dp[j], dp[i]); 
            }
        }
        return dp[n];
    }

    /**
     * Both above method and this method are correct, but note that this approach the inner for loop condition
     * is j <= i - 1, which means this is not symmetry. E.g. dp[5] * 6 != dp[6] * 5.
     * Since this can cover the biggest value case: 3 * dp[n - 3], so this is also the correct solution.
     * @param n
     * @return
     */
    public static int cuttingRopeCore1(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i - 1; j++) {
                dp[i] = Math.max(dp[i - j] * j, dp[i]);
            }
        }
        return dp[n];
    }


    public int cuttingRopeRecursive(int n) {
        if (n <= 3) return n - 1;
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        for (int i = 0; i < 4; i++) {
            memo[i] = i;
        }
        cuttingRopeRecursiveCore(n, memo);
        return memo[n];
    }

    /**
     * Find the meaning of the recursive function: get the corresponding max value with a input n.
     * So the recursive relation is: n needs to be cut into smaller ropes, and calculate the smaller ropes max value first.
     * After that the whole rope's biggest value can be calculated with those smaller ropes max value.
     * Calculate smaller ropes needs smaller n, which is n - i, also we need a memo table to record the values which is memo.
     * Finally we need the exit condition, which is the base cases, e.g. n <=3. 
     * @param n
     * @param memo
     * @return
     */
    private int cuttingRopeRecursiveCore(int n, int[] memo) {
        if (memo[n] != -1) return memo[n];
        // from n reduce until 3.
        for (int i = 1; i <= n - 1; i++) {
            memo[n] = Math.max(cuttingRopeRecursiveCore(n - i, memo) * cuttingRopeRecursiveCore(i, memo), memo[n]);
        }
        return memo[n];
    }
}
