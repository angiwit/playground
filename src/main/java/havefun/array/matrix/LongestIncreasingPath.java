package havefun.array.matrix;

public class LongestIncreasingPath {

    private static int[][] next = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static int longestIncreasingPath(int[][] matrix) {
        int res = 0;
        int[][] memo = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                res = Math.max(longestIncreasingPathCore(matrix, i, j, memo), res);
            }
        }
        return res;
    }

    /**
     * A cell reviewed at first, and then the adjacent cell is reviewed, and then the first cell is re-entered, now
     * 1. if first cell is not been initialized(value = 1), then the cell will be treated as a new cell and calculated into
     * the path again.
     * 2. if first cell is been initialized(value = 1), then the cell will be treated as a reviewed cell and return the
     * max path length it calculated (might be a partial value).
     * <p>
     * Under this scenario, there's a case is the path is jumped between two cells over and over again, e.g. matrix[0][0]
     * to matrix[0][1] and then back to matrix[0][0] and then matrix[0][1]...
     * <p>
     * To avoid this case, we need a check to make sure it won't walk back, here in this problem the condition is to check only
     * when the next cell value is bigger than current cell value.
     * For other problems, they should have their own condition to avoid this case.
     *
     * @param matrix
     * @param i
     * @param j
     * @param memo
     * @return
     */
    public static int longestIncreasingPathCore(int[][] matrix, int i, int j, int[][] memo) {
        if (memo[i][j] != 0) return memo[i][j];
        memo[i][j]++; //default value is 1, indicator to identify the cell is reviewed.
        for (int k = 0; k < 4; k++) {
            int row = i + next[k][0];
            int col = j + next[k][1];
            if (row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length && matrix[row][col] > matrix[i][j]) {
                memo[i][j] = Math.max(memo[i][j], longestIncreasingPathCore(matrix, row, col, memo) + 1);
            }
        }
        return memo[i][j];
    }

    public static void main(String[] args) {
        int[][] matrix = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        System.out.println(longestIncreasingPath(matrix));
    }
}
