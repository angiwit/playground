package havefun.dp;

public class CompleteKnapsack {

    public static int completeKnapsack(int[][] items, int k) {
        return completeKnapsackCore(items, k);
    }

    /**
     * Another way: iterate capacity first then item.
     *
     * @param items
     * @param k
     * @return
     */
    public static int completeKnapsackCore(int[][] items, int k) {
        int[][] value = new int[k + 1][items.length + 1];
        for (int i = 0; i < k + 1; i++) {
            value[i][0] = 0;
        }
        for (int i = 0; i < items.length + 1; i++) {
            value[0][i] = 0;
        }
        for (int i = 1; i < k + 1; i++) {
            for (int j = 1; j < items.length + 1; j++) {
                if (i >= items[j][0]) {
                    value[i][j] = Math.max(value[i][j], value[i - items[j - 1][0]][j] + items[j - 1][1]);
                } else {
                    value[i][j] = value[i][j - 1];
                }
            }
        }
        return value[k][items.length];
    }
}
