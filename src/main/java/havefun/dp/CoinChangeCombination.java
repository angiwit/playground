package havefun.dp;

public class CoinChangeCombination {

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        System.out.println(change(5, coins));
    }

    public static int change(int amount, int[] coins) {
        if (coins == null || coins.length == 0) return 0;
        return changeCore(amount, coins);
    }

    /**
     * dp: number of combinations.
     * dp[0] = 1: since dp[j] += dp[j - coins[i]], so if dp[0] = 0, then all values are 0.
     * Also, use no coins to build 0 amount has only one approach: do not pick any coin.
     *
     * @param amount
     * @param coins
     * @return
     */
    public static int changeCore(int amount, int[] coins) {
        int[] dp = new int[amount + 1]; // number of combinations.
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (coins[i] <= j) {
                    dp[j] = dp[j - coins[i]] + dp[j];
                }
            }
        }
        return dp[amount];
    }
}
