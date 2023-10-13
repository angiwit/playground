package havefun.array.matrix.islands;

/**
 * https://leetcode.cn/problems/island-perimeter/
 */
public class IslandPerimeter {

    public static int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        return islandPerimeterCore(grid);
    }

    /**
     * Note that here we're calculating all the grids with water and the edges of the matrix.
     * Grids with water
     *
     * @param grid
     * @return
     */
    public static int islandPerimeterCore(int[][] grid) {
        int perimeter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) continue;
                if (i - 1 < 0 || grid[i - 1][j] == 0) perimeter++;
                if (i + 1 >= grid.length || grid[i + 1][j] == 0) perimeter++;
                if (j - 1 < 0 || grid[i][j - 1] == 0) perimeter++;
                if (j + 1 > grid[0].length || grid[i][j + 1] == 0) perimeter++;
            }
        }
        return perimeter;
    }
}
