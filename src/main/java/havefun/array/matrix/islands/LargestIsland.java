package havefun.array.matrix.islands;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class LargestIsland {

    public static int largestIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        return largestIslandCore(grid);
    }

    /**
     * Use stack to mock the iteration and use set to record the seen grids.
     * Stack can support push/pop operation, so it's convenient to mock the recursive process.
     * Set can support contains operation, so it's easy to check if a grid is added or not.
     *
     * @param grid
     * @param r
     * @param c
     * @return
     */
    private static int bfs(int[][] grid, int r, int c) {
        int N = grid.length;
        Stack<Integer> stack = new Stack<>();
        Set<Integer> seen = new HashSet<>();
        stack.push(r * N + c); // Using row * N + c to transfer a coordinate to a number.
        seen.add(r * N + c);
        while (!stack.isEmpty()) {
            Integer n = stack.pop();
            int nRow = n / N;
            int nCol = n % N;
            int[] rows = {-1, 1, 0, 0};
            int[] cols = {0, 0, -1, 1};
            for (int i = 0; i < 4; i++) {
                int row = nRow + rows[i];
                int col = nCol + cols[i];
                if (seen.contains(row * N + col) || row < 0 || row >= grid.length || col < 0 || col >= grid[0].length
                        || grid[row][col] == 0) continue;
                if (grid[row][col] == 1) {
                    stack.push(row * N + col);
                    seen.add(row * N + col);
                }
            }
        }
        return seen.size();
    }

    public static int largestIslandCore(int[][] grid) {
        int max = 0;
        boolean hasZero = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    hasZero = true;
                    grid[i][j] = 1;
                    max = Math.max(max, bfs(grid, i, j));
                    grid[i][j] = 0;
                }
            }
        }
        return hasZero ? max : grid.length * grid[0].length;
    }

}
