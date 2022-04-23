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

    public static int changeCore(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1; // use numbers to trial.
        for (int i = 1; i <= coins.length; i++) {
            for (int j = coins[i - 1]; j <= amount; j++) { // calculate small amount first, and big amount need to use the small amount value.
                dp[j] += dp[j - coins[i - 1]];
            }
        }
        return dp[amount];
    }
}
