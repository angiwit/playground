package havefun.dp;

import java.util.Arrays;

public class LastStoneWeight {

    public static void main(String[] args) {
        int[] stones = {2, 7, 4, 1, 8, 1};
        int sum = Arrays.stream(stones).sum();
        int half = sum / 2;
        System.out.println(sum - 2 * biggestValueCanGetForRecursive(stones, stones.length - 1, half));
    }

    public static int lastStoneWeightII(int[] stones) {
        if (stones == null || stones.length == 0) return 0;
        return lastStoneWeightIICoreDP(stones);
    }

    /**
     * dp[i][j]: the biggest value can get when target value is j and after reviewing ith item.
     * The smallest minus value: sum - dp[i][j] - dp[i][j].
     * Can be optimized to use one-dimension array.
     *
     * @param stones
     * @return
     */
    private static int lastStoneWeightIICoreDP(int[] stones) {
        int sum = Arrays.stream(stones).sum();
        int half = sum / 2;
        int[][] dp = new int[stones.length + 1][half + 1];
        for (int i = 0; i < stones.length; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= half; i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i <= stones.length; i++) {
            for (int j = 1; j <= half; j++) {
                if (j > stones[i - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j - stones[i - 1]] + stones[i - 1], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return sum - 2 * dp[stones.length][half];
    }

    private static int lastStoneWeightIICoreDPOptimized(int[] stones) {
        int sum = Arrays.stream(stones).sum();
        int half = sum / 2;
        int[] dp = new int[half + 1];
        for (int i = 1; i <= stones.length; i++) {
            for (int j = half; j >= 1; j--) {
                if (j > stones[i - 1]) {
                    dp[j] = Math.max(dp[j], dp[j - stones[i - 1]] + stones[i - 1]);
                }
            }
        }
        return sum - 2 * dp[half];
    }

    private static int lastStoneWeightIICoreDPOptimizedV2(int[] stones) {
        int sum = Arrays.stream(stones).sum();
        int half = sum / 2;
        int[] result = new int[half + 1];
        for (int i = 0; i < half + 1; i++) {
            result[i] = 0;
        }
        for (int i = 1; i <= stones.length; i++) {
            for (int j = half; j >= stones[i - 1]; j--) {
                //choose
                result[j] = Math.max(result[j - stones[i - 1]] + stones[i - 1], result[j]);
            }
        }
        return sum - 2 * result[half];
    }

    /**
     * Iterate stones util the last one, last stone is compared with empty, so choosing the
     * last stone(if it's smaller than target) can get a bigger value.
     * <p>
     * Tree-structure: left node is choose, right node is no choose,
     * and everytime choose the bigger sum of left subtree or right subtree.
     * Post order traversal of the tree, the result is the path sum.
     * <p>
     * Here using the biggest index and keep reducing it until 0 instead of a for loop.
     * The reason is each time the stones that can be chosen changes, e.g. at first, all stones can be chosen,
     * but after reviewing the first stone, the first stone can NOT be chosen, in a for loop, it's a backtrace approach.
     * <p/>
     *
     * @param stones
     * @param idx
     * @param target
     * @return correct
     */
    private static int biggestValueCanGetRecursive(int[] stones, int idx, int target) {
        if (target == 0 || idx < 0) return 0;
        if (stones[idx] <= target) {
            // Either choose or not choose, choose the bigger value one.
            return Math.max(biggestValueCanGetRecursive(stones, idx - 1, target - stones[idx]) + stones[idx],
                    biggestValueCanGetRecursive(stones, idx - 1, target));
        } else {
            // can NOT choose
            return biggestValueCanGetRecursive(stones, idx - 1, target);
        }
    }

    // incorrect but can be changed to below, in below approach, the recursive won't go into for loop again:
    /**
     * int res = 0;
     * for(int i = 0; i < stones.length; i++) {
     *     res = Math.max(biggestValueRecursive(stones, i, target), res);
     * }
     * return res;
     */
    /**
     * Can be
     *
     * @param stones
     * @param start
     * @param target
     * @return
     */
    private static int biggestValueCanGetForRecursive(int[] stones, int start, int target) {
        if (target == 0) return 0;
        int res = Integer.MIN_VALUE;
        for (int i = start; i < stones.length; i++) {
            if (stones[i] <= target) {
                res = Math.max(biggestValueCanGetForRecursive(stones, start + 1, target - stones[i]) + stones[i],
                        biggestValueCanGetForRecursive(stones, start + 1, target));
            } else {
                res = biggestValueCanGetForRecursive(stones, start + 1, target);
            }
        }
        return res;
    }

    private static int biggestValueCanGetOptimized(int[] stones, int idx, int target, int[][] res) {
        if (res[idx][target] == 0) {
            if (target == 0 || idx < 0) {
                res[idx][target] = 0;
                return 0;
            }
            if (stones[idx] <= target) {
                // Either choose or not choose, choose the bigger value one.
                int value = Math.max(biggestValueCanGetRecursive(stones, idx - 1, target - stones[idx]) + stones[idx],
                        biggestValueCanGetRecursive(stones, idx - 1, target));
                res[idx][target] = value;
                return value;
            } else {
                // can NOT choose
                int value = biggestValueCanGetRecursive(stones, idx - 1, target);
                res[idx][target] = value;
                return value;
            }
        } else {
            return res[idx][target];
        }
    }
}
