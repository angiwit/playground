package havefun.dp;

public class MinCostClimbingStairs {

    public static int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0) return 0;
        if (cost.length == 1 || cost.length == 2) return 0;
        return minCostClimbingStairsCore2(cost);
    }

    /**
     * Think differently, the top floor is outside of the array's length, so a cost.length+1 dp array is created,
     * then first step and second step do NOT need any cost, so they're 0, the dp records every step's cost exactly.
     * If your last step is i-2, then the cost is dp[i-2] + cost[i-2], otherwise it's dp[i-1] + cost[i-1], which
     * means the money already spent + money needed in that step.
     *
     * @param cost
     * @return
     */
    public static int minCostClimbingStairsCore(int[] cost) {
        int[] dp = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= cost.length; i++) {
            dp[i] = Math.min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
        }
        return dp[cost.length];
    }

    /**
     * The top floor's index is cost.length + 1, and when check the top's result, we need only review
     * cost.length-1 and cost.length-2's value, so cost.length dp is enough.
     * In each step, we need to check the min value we can get in previous steps, and the current cost
     * is min value + cost[current].
     *
     * @param cost
     * @return
     */
    public static int minCostClimbingStairsCore2(int[] cost) {
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }
        return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
    }

    public static void main(String[] args) {
//        int[] cost = {10, 15, 20};
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(minCostClimbingStairs(cost));
    }
}
