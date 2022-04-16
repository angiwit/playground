package havefun.dp;

public class MaxProfitWithFee {

    public static int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) return 0;
        return maxProfitCore(prices, fee);
    }

    public static int maxProfitCore(int[] prices, int fee) {
        int[][] result = new int[prices.length][2];
        result[0][0] = 0;
        result[0][1] = -prices[0] - fee;
        for (int i = 1; i < prices.length; i++) {
            result[i][0] = Math.max(result[i - 1][0], result[i - 1][1] + prices[i]);
            result[i][1] = Math.max(result[i - 1][1], result[i - 1][0] - prices[i] - fee);
        }
        return Math.max(result[prices.length - 1][0], result[prices.length - 1][1]);
    }

    public static void main(String[] args) {
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;
        System.out.println(maxProfit(prices, fee));
    }
}
