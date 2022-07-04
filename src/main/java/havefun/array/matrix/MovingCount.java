package havefun.array.matrix;

/**
 * Using i, j from 0/1 as start to iterate the matrix means that: the cell can only be accessed from left cell
 * or upper cell, but not the right cell or the under cell.
 */
public class MovingCount {

    public int movingCount(int[][] matrix, int k) {
        boolean[] dp = new boolean[matrix.length * matrix[0].length];
        return movingCountCore(matrix, 0, 0, k, dp);
    }

    private int movingCountCore(int[][] matrix, int row, int col, int k, boolean[] dp) {
        int count = 0;
        if (check(row, col, matrix.length, matrix[0].length, k, dp)) {
            dp[row * col + col] = true;
            return 1 + movingCountCore(matrix, row + 1, col, k, dp)
                    + movingCountCore(matrix, row - 1, col, k, dp)
                    + movingCountCore(matrix, row, col + 1, k, dp)
                    + movingCountCore(matrix, row, col - 1, k, dp);
        }
        return count;
    }

    private boolean check(int i, int j, int rows, int cols, int k, boolean[] dp) {
        if (i >= 0 && i < rows && j >= 0 && j < cols && dp[i * j + j] == false && sum(i) + sum(j) < k) return true;
        return false;
    }

    private int sum(int i) {
        int sum = 0;
        while (i > 0) {
            sum += i % 10;
            i = i / 10;
        }
        return sum;
    }

}
