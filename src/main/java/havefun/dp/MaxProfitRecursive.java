package havefun.dp;

public class MaxProfitRecursive {

    public static int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            max = Math.max(maxProfitCore(prices, 0, i), maxProfitCore(prices, 1, i));
            max = Math.max(max, maxProfitCore(prices, 2, i));
        }
        return max;
    }

    /**
     * For each day, sell/buy/do nothing, find the max value of three states.
     * For buy/do nothing, it's easy to calculate profit, but for sale, the profit depends on
     * buy in price.
     * <p>
     * Using op as state, we need three states: no op, buy, sell. This can be compressed to two states: hold/no hold
     *
     * @param prices
     * @return
     */
    public static int maxProfitCore(int[] prices, int op, int index) {
        if (index == 0) {
            if (op == 0) return 0; // no hold
            if (op == 1) return -prices[0]; // hold
        } else {
            if (op == 0)
                return Math.max(maxProfitCore(prices, 0, index - 1), maxProfitCore(prices, 1, index - 1) + prices[index]);
            if (op == 1)
                return Math.max(maxProfitCore(prices, 1, index - 1), maxProfitCore(prices, 0, index - 1) - prices[index]);
        }
        return 0;
    }

    public static int maxProfitCoreWithState(int[] prices, int op, int index, int[][] values) {
        if (index == 0) {
            if (op == 0) return 0; // no hold
            if (op == 1) return -prices[0]; // hold
        } else {
            if (values[index][op] != -1) return values[index][op];
            int profit = -1;
            if (op == 0) {
                profit = Math.max(maxProfitCoreWithState(prices, 0, index - 1, values),
                        maxProfitCoreWithState(prices, 1, index - 1, values) + prices[index]);
            }
            if (op == 1) {
                profit = Math.max(maxProfitCoreWithState(prices, 1, index - 1, values),
                        maxProfitCoreWithState(prices, 0, index - 1, values) - prices[index]);
            }
            values[index][op] = profit;
        }
        return 0;
    }

    public static void main(String[] args) {
        //        int[] prices = {7, 1, 5, 3, 6, 4};
//        System.out.println(maxProfit(prices));
        int[] prices = {1, 2, 3, 4, 5};
        System.out.println(maxProfit(prices));
    }
}
