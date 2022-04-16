package havefun.array;

public class MaxProfit {

    // buy and sell once
    public int maxProfitI(int[] prices) {
        int least = Integer.MAX_VALUE;
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            least = Math.min(least, prices[i]);
            result = Math.max(prices[i] - least, result);
        }
        return result;
    }

    // buy and sell no limit
    public int maxProfitII(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int result = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] >= prices[i]) {
                result += prices[i + 1] - prices[i];
            }
        }
        return result;
    }
}
