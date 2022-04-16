package havefun;

public class DropEggs {

    /**
     * Consider we have all the eggs in each floor, then dropping them in each floor, to see which floor we can
     * get the min attempts.
     * result = Math.min(f(n-1, k-1), f(n-i, k)) + 1
     */
    public static void main(String[] args) {
        // k: eggs, n: floors.
        System.out.println(superEggDrop(2, 6));
    }

    public static int superEggDrop(int k, int n) {
        int[][] count = new int[k + 1][n + 1];
        return superEggDropCore(k, n, count);
    }

    /**
     * Recursive with memorizing.
     * For each i, after the egg dropping, it split to two scenarios: broken or not broken, which equals to a node with left leaf and right leaf.
     * Find the bigger value of left sub-tree and right sub-tree in each iteration.
     * Finally, find the smallest value in all iterations.
     * Use a 2d array to store the value calculated.
     */
    public static int superEggDropCore(int k, int n, int[][] count) {
        if (k == 1) return n;
        if (n == 0) return 0;
        if (count[k][n] != 0) return count[k][n];
        int curResult = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            //in floor i -> broken: calculate f(k-1, n-1); not-broken: calculate f(k, n-i).
            curResult = Math.min(
                    Math.max(superEggDropCore(k - 1, i - 1, count), superEggDropCore(k, n - i, count)),
                    curResult) + 1;
            count[k][n] = curResult;
        }
        return count[k][n];
    }
}
