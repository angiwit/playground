package havefun.dp;

public class MaxProfitWithFreeze {

    public static int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        return maxProfitCore2(prices, 2);
    }

    /**
     * @param prices
     * @return
     */
    public static int maxProfitCore(int[] prices) {
        int[][] dp = new int[prices.length][3];
        dp[0][0] = -prices[0]; // hold
        dp[0][1] = 0; // non hold non freeze
        dp[0][2] = 0; // non hold freeze
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2]);
            //freeze after ith day's end with stock sell.
            dp[i][2] = dp[i - 1][0] + prices[i];
        }
        return Math.max(dp[prices.length - 1][1], dp[prices.length - 1][2]);
    }

    /**
     * @param prices
     * @param freeze freeze day after sell.
     * @return
     */
    public static int maxProfitCore2(int[] prices, int freeze) {
        int[][] dp = new int[prices.length][3];
        dp[0][0] = -prices[0]; //hold
        dp[0][1] = 0; // non hold non freeze
        dp[0][2] = 0; // non hold freeze
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            if (i > freeze) {
                dp[i][1] = Math.max(dp[i - freeze][1], dp[i - freeze][2]);
            } else {
                /**
                 * If freeze bigger than i, it means no stock been sell yet.
                 * So day i no hold no freeze means day i-1 no hold no freeze.
                 */
                dp[i][1] = dp[i - 1][1];
            }
            dp[i][2] = dp[i - 1][0] + prices[i];
        }
        return Math.max(dp[prices.length - 1][1], dp[prices.length - 1][0]);
    }

    public static int maxProfitCore1(int[] prices) {
        int[][][] dp = new int[prices.length][2][2];
        dp[0][0][0] = 0;
        dp[0][0][1] = 0;
        dp[0][1][0] = -prices[0];
        dp[0][0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0][0] = Math.max(dp[i - 1][0][0], dp[i - 1][0][1]);
            dp[i][0][1] = dp[i - 1][1][0] + prices[i];
            dp[i][1][0] = Math.max(dp[i - 1][0][0] - prices[i], dp[i - 1][1][0]);
            dp[i][1][1] = dp[i][1][0];
        }
        return Math.max(dp[prices.length - 1][0][0], dp[prices.length - 1][0][1]);
    }


    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 0, 2};
        System.out.println(maxProfit(prices));
    }
}
