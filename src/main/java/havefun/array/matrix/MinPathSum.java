package havefun.array.matrix;

import java.util.ArrayList;
import java.util.List;

public class MinPathSum {

    public static void main(String[] args) {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(minPathSum(grid));
    }

    private static int[] nextRow = {1, 0};
    private static int[] nextCol = {0, 1};
    private static int min = Integer.MAX_VALUE;

    public static int minPathSum(int[][] grid) {
        List<Integer> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(grid, 0, 0, result, path);
        return result.stream().reduce((x, y) -> x + y).get();
    }

    private static void dfs(int[][] grid, int row, int col, List<Integer> result, List<Integer> path) {
        if (row >= grid.length - 1 && col >= grid[0].length - 1) {
            int sum = path.stream().reduce((x, y) -> x + y).get();
            if (sum < min) {
                result.clear();
                result.addAll(new ArrayList<>(path));
            }
            return;
        }

        for (int i = 0; i < 2; i++) {
            path.add(grid[row][col]);
            int nRow = row + nextRow[i];
            int nCol = col + nextCol[i];
            if (nRow >= grid.length - 1) nRow = grid.length - 1;
            if (nCol >= grid[0].length - 1) nCol = grid[0].length - 1;
            if (nRow <= grid.length - 1 && nCol <= grid[0].length - 1) {
                dfs(grid, nRow, nCol, result, path);
                path.remove(path.get(path.size() - 1));
            }
        }
    }

    public static int minPathSumDp(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) continue;
                else if (i == 0) dp[i][j] = dp[i][j - 1] + grid[i][j];
                else if (j == 0) dp[i][j] = dp[i - 1][j] + grid[i][j];
                else dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }
}

