package havefun.dp;

// https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/
/**
 * There's three dimension array controls the result, so we need a three dimension table to memorize the temporary results.
 * We can use a 2 dimension array to memorize results but it could be complex.
 * When k bigger than n/2, the problem changes to you can buy and sell as many as times you want.
 */
public class MaxProfitInKTimes {

    public static void main(String[] args) {
//        int[] prices = {1, 2, 3, 4, 5};
//        int[] prices = {2, 4, 1};
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
//        int[] prices = {1, 2, 4};
        System.out.println(maxProfit(2, prices));
    }

    public static int maxProfit(int k, int[] prices) {
        if (prices.length == 0) return 0;
        if (k > prices.length / 2) {
            return maxProfitNolimit(prices);
        }
        return maxProfitCore(k, prices);
    }

    private static int maxProfitNolimit(int[] prices) {
        int[][] profit = new int[prices.length][2];
        profit[0][0] = 0;
        profit[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            profit[i][0] = Math.max(profit[i - 1][0], profit[i - 1][1] + prices[i]);
            profit[i][1] = Math.max(profit[i - 1][1], profit[i - 1][0] - prices[i]);
        }
        return Math.max(profit[prices.length - 1][0], profit[prices.length - 1][1]);
    }

    /**
     * Use a three dimension array to remember the states.
     * p[i][k][0] & p[i][k][1]: i -> day, k -> times, 0/1 -> not holding/holding stock when day's off.
     * p[i][k][0] = max(p[i-1][k][0], p[i-1][k][1] + prices[i])
     * p[i][k][1] = max(p[i-1][k][1], p[i-1][k-1][0] - prices[i])
     *
     * @param k
     * @param prices
     * @return
     */
    public static int maxProfitCore(int k, int[] prices) {
        int[][][] profit = new int[prices.length][k + 1][2];
        for (int i = 0; i < prices.length; i++) {
            for (int j = 0; j < 2; j++) {
                profit[i][0][j] = 0;
            }
        }
        // Make sure all the [0][i][1] is been initialized to -prices[0].
        for (int i = 1; i <= k; i++) {
            profit[0][i][0] = 0;
            profit[0][i][1] = -prices[0];
        }

        for (int j = 1; j < prices.length; j++) {
            for (int i = 1; i <= k; i++) {
                profit[j][i][0] = Math.max(profit[j - 1][i][0], profit[j - 1][i][1] + prices[j]);
                profit[j][i][1] = Math.max(profit[j - 1][i][1], profit[j - 1][i - 1][0] - prices[j]);
            }
        }
        return profit[prices.length - 1][k][0];
    }
}
