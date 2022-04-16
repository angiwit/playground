package havefun.dp;

public class Knapsack {

    public static int knapsack(int[][] items, int k) {
        return knapsackCore(items, k);
    }

    public static int knapsackCore(int[][] items, int k) {
        int[][] value = new int[items.length + 1][k + 1];
        for (int i = 0; i < items.length + 1; i++) {
            value[i][0] = 0;
        }
        for (int i = 0; i < k + 1; i++) {
            value[0][i] = 0;
        }

        for (int i = 1; i < items.length + 1; i++) {
            for (int j = 1; j < k + 1; j++) {
                int weight = items[i - 1][0];
                int v = items[i - 1][1];
                if (weight <= j) {
                    value[i][j] = Math.max(value[i - 1][j], value[i - 1][j - weight] + v);
                } else {
                    value[i][j] = value[i - 1][j];
                }
            }
        }
        /**
         * item ranges: 1 - item.length
         * capacity ranges: 1 - capacity
         * so, value[item.length][capacity] is the result of last item reviewed and capacity is largest capacity's value.
         */
        return value[items.length][k];
    }


}
