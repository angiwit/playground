package havefun.dp;
// https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
public class MaxProfit {

    public static void main(String[] args) {
//        int[] prices = {7, 1, 5, 3, 6, 4};
//        System.out.println(maxProfit(prices));
        int[] prices = {1, 2, 3, 4, 5};
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        return maxProfitCore(prices);
    }

    /**
     * p[i][0/1]:
     * hold day's off: max(p[i-1][1] -> no selling yet, p[i-1][0] - prices[i] -> buy)
     * non-hold day's off: max(p[i-1][0] -> no buying yet, p[i-1][1] + prices[n] -> sell)
     *
     * @param prices
     * @return
     */
    public static int maxProfitCore(int[] prices) {
        int[][] profit = new int[prices.length][2];
        profit[0][0] = 0;
        profit[0][1] = -prices[0];
        for (int i = 1; i <= prices.length - 1; i++) {// when i==5, the for loop body need execution, but not 6.
            profit[i][0] = Math.max(profit[i - 1][0], profit[i - 1][1] + prices[i]);
            profit[i][1] = Math.max(profit[i - 1][1], profit[i - 1][0] - prices[i]);
        }
        return Math.max(profit[prices.length - 1][0], profit[prices.length - 1][1]);
    }
}
