package havefun.array.matrix.islands;

/**
 * https://leetcode.cn/problems/number-of-islands/
 */
public class NumIslands {

    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        return numIslandsCore(grid);
    }

    public static int numIslandsCore(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // Note that we can't pass the result into the dfs because the value here will not be changed even there's modification
                // in dfs method. Because for basic types, it's value passing, so the passed value to dfs is a copy of current value.
                if (grid[i][j] == '1') count++;  
                dfs(grid, i, j);
            }
        }
        return count;
    }

    /**
     * Note to use the border check condition in the top of the method to avoid checking the border checking when recursive.
     * @param grid
     * @param row
     * @param col
     */
    private static void dfs(char[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) return;
        if (grid[row][col] == '2' || grid[row][col] == '0') return;
        grid[row][col] = '2';
        dfs(grid, row - 1, col);
        dfs(grid, row + 1, col);
        dfs(grid, row, col - 1);
        dfs(grid, row, col + 1);
    }
}
