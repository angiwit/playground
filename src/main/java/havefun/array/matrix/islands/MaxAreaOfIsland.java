package havefun.array.matrix.islands;

/**
 * https://leetcode.cn/problems/max-area-of-island/
 */
public class MaxAreaOfIsland {
    public static int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        return maxAreaOfIslandCore(grid);
    }

    private static int dfs(int[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) return 0;
        if (grid[r][c] == 0 || grid[r][c] == 2) return 0;
        grid[r][c] = 0; // set to 0 to indicate the cell is calculated.
        return 1 +
                dfs(grid, r + 1, c) +
                dfs(grid, r - 1, c) +
                dfs(grid, r, c + 1) +
                dfs(grid, r, c - 1);
    }

    public static int maxAreaOfIslandCore(int[][] grid) {
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                maxArea = Math.max(maxArea, dfs(grid, i, j));
            }
        }
        return maxArea;
    }

}
