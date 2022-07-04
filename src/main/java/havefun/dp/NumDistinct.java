package havefun.dp;

/**
 * https://leetcode.cn/problems/distinct-subsequences/
 */
public class NumDistinct {

    /**
     * consider this as knapsack, the items are s, the target is t. So iterate s in outer loop, t in inner loop.
     * The number of combinations can be split to two parts: including current s and not including current s.
     * if s[i-1] == t[j-1]: including current is: dp[i-1][j-1], not including is: dp[i-1][j],
     * so combination sum is dp[i-1][j-1] + dp[i-1][j].
     * 假设s = 'ra', t = 'r':
     * 1. review s.charAt(0) and t.charAt(0): r=r, 可以选择使用s的r来匹配t的r，也可以选择不用s的r来匹配t的r。
     * 1.1: 使用s的r代表着在这之前的匹配情况是'' -> ''，这个时候组合数目是dp[i-1][j-1]
     * 1.2: 不使用s的r代表着在s的r之前可能有其他的r可以跟t的r匹配上，这个时候组合的数目是dp[i-1][j]。（可能为0，可能不为0）。
     * 1.3: 将两种可能性相加，即为s[0]和t[0]的情况下的组合总数。
     * <p>
     * 2. review s.charAt(1) and t.charAt(0): a != r, 这个时候代码a不存在由t构成的子字符串。
     * 所以可能的组合总数一定是a之前的字符串和t能够匹配上的组合总数： dp[i-1][j].
     * <p>
     * 初始化: 当t为空字符串的时候，不管s是什么字符串，都有一种组合，就是删除s的所有字符的组合。所以dp[i][0] = 1.
     *
     * @param s
     * @param t
     * @return
     */
    public static int numDistinct(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i <= s.length(); i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[s.length()][t.length()];
    }
}
