package havefun.dp;

public class FindMaxForm {

    public static void main(String[] args) {
        String[] strs = {"10", "0001", "111001", "1", "0"};
        int m = 5;
        int n = 3;
        System.out.println(findMaxForm(strs, m, n));
    }

    public static int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0) return 0;
        return findMaxFormCore(strs, m, n);
    }

    // m 0, n 1.
    public static int findMaxFormCore(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < strs.length; i++) {
            int zeroCount = 0;
            int oneCount = 0;
            for (int j = 0; j < strs[i].length(); j++) {
                if (strs[i].charAt(j) == '0') zeroCount++;
                else oneCount++;
            }
            /**
             * Although the array is 2d, but since there's 2 variables in it,
             * so it can be considered as a 1d array, and we need to update the array's value
             * in each round when new item comes in.
             * If we iterate the array from left to right, then left value can be re-calculated
             * when calculating right values.
             * So iterate from right to left can avoid the re-calculate, and the value dp[j][k] is
             * still the value it calculated in previous round.
             */
            for (int j = m; j >= zeroCount; j--) {
                for (int k = n; k >= oneCount; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - zeroCount][k - oneCount] + 1);
                }
            }
        }
        return dp[m][n];
    }
}
