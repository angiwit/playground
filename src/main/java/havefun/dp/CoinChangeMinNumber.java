package havefun.dp;

import java.util.Arrays;

// find the min count of coins for change.
public class CoinChangeMinNumber {

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println(minCoinChangeDP(coins, amount));
    }

    public static int minCoinChange(int[] coins, int target) {
        if (coins == null || coins.length == 0) return 0;
        int[] result = new int[target + 1];
        Arrays.fill(result, -1);
        return minCoinChangeCoreOptimized(coins, target, result);
    }

    /**
     * Multiway tree! ways number equals to coins.length.
     * For each i, a min value is calculated, so need a variable to store the min value in the for loop.
     * Everytime chosen coins[i], the problem becomes in all coins, find target - coins[i] coins.
     * <p>
     * !!!For multiple chosen problem, for loop is in the inner of recursive method!!!
     * !!!For one time chosen problem, for loop is in the outer of recursive method!!!
     *
     * @param coins
     * @param target
     * @return
     */
    public static int minCoinChangeCore(int[] coins, int target) {
        int res = Integer.MAX_VALUE;
        if (target == 0) return 0;
        if (Arrays.stream(coins).anyMatch(x -> x == target)) {
            return 1;
        }
        for (int i = 0; i < coins.length; i++) {
            if (target >= coins[i]) {
                res = Math.min(res, minCoinChangeCore(coins, target - coins[i]) + 1);
            }
        }
        return res;
    }

    public static int minCoinChangeCoreOptimized(int[] coins, int target, int[] result) {
        if (result[target] == -1) {
            // This can be replaced with Integer.MAX_VALUE, but in fact, the coin number should NOT bigger than target, so use target instead.
            int res = target;
            if (target == 0) return 0;
            if (Arrays.stream(coins).anyMatch(x -> x == target)) {
                return 1;
            }
            for (int i = 0; i < coins.length; i++) {
                if (target >= coins[i]) {
                    res = Math.min(res, minCoinChangeCore(coins, target - coins[i]) + 1);
                    result[target] = res;
                }
            }
            return res;
        } else {
            return result[target];
        }
    }

    public static int minCoinChangeDP(int[] coins, int target) {
        int[] result = new int[target + 1]; // min coins when amount.
        Arrays.fill(result, Integer.MAX_VALUE);
        result[0] = 0;
        for (int i = 0; i < coins.length; i++) { // iterate item first.
            // Iterate from small to big, since the right value need to use the value calculated in the left.
            // If we need to avoid the using of the left value, we need to iterate from right to left.
            /**
             * j can start with 0, but need another check which is (j - coins[i]) > 0.
             * which means the amount smaller than coins[i] do Not need to re-calculate.
             * Starting with coins[i] can reduce the additional check.
             */
            for (int j = coins[i]; j <= target; j++) {
                if (result[j - coins[i]] != Integer.MAX_VALUE) {
                    /**
                     * Only when result[j - coins[i]] is calculated, then result[j] can be calculated.
                     * Also, if we do Not add this check, there could be overflow exception in Integer.
                     */

                    /**
                     * result[j]: do Not choose current coin.
                     * result[j - coins[i]]: choose current coin, so needs adding 1.
                     */
                    result[j] = Math.min(result[j - coins[i]] + 1, result[j]);
                }
            }
        }
        return result[target] == Integer.MAX_VALUE ? -1 : result[target];
    }

}
