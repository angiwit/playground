package havefun.dp;

public class UniquePathsWithObstacles {

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) return 0;
        if (obstacleGrid[0][0] == 1) return 0;
        return uniquePathsWithObstaclesCore(obstacleGrid);
    }

    public static int uniquePathsWithObstaclesCore(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] steps = new int[row][col];
        for (int i = 0; i < col; i++) {
            if (obstacleGrid[0][i] == 1 || (i > 0 && steps[0][i - 1] == -1)) {
                steps[0][i] = -1;
            } else {
                steps[0][i] = 1;
            }
        }
        for (int i = 0; i < row; i++) {
            if (obstacleGrid[i][0] == 1 || (i > 0 && steps[i - 1][0] == -1)) {
                steps[i][0] = -1;
            } else {
                steps[i][0] = 1;
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (obstacleGrid[i][j] == 1) {
                    steps[i][j] = -1;
                } else if (steps[i - 1][j] == -1 && steps[i][j - 1] == -1) {
                    steps[i][j] = -1;
                } else {
                    if (steps[i - 1][j] == -1) {
                        steps[i][j] = steps[i][j - 1];
                    } else if (steps[i][j - 1] == -1) {
                        steps[i][j] = steps[i - 1][j];
                    } else steps[i][j] = steps[i - 1][j] + steps[i][j - 1];
                }
            }
        }
        return steps[row - 1][col - 1] == -1 ? 0 : steps[row - 1][col - 1];
    }

    public static void main(String[] args) {
        int[][] obstacleGrid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
//        int[][] obstacleGrid = {{0, 1}, {0, 0}};
        System.out.println(uniquePathsWithObstacles(obstacleGrid));
    }
}
