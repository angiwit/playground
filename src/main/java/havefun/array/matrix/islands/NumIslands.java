package havefun.array.matrix.islands;

/**
 * https://leetcode.cn/problems/number-of-islands/
 */
public class NumIslands {

    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        return numIslandsCore(grid);
    }

    private static void dfs(char[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) return;
        if (grid[row][col] == '2' || grid[row][col] == '0') return;
        grid[row][col] = '2';
        dfs(grid, row - 1, col);
        dfs(grid, row + 1, col);
        dfs(grid, row, col - 1);
        dfs(grid, row, col + 1);
    }

    public static int numIslandsCore(char[][] grid) {
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') result++; // here if there's one 1, there'll be at least 1 island.
                dfs(grid, i, j);
            }
        }
        return result;
    }
}
