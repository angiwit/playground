package havefun.dp;

public class UniquePaths {

    public static int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) return 0;
        return uniquePathsCore(m, n);
    }

    public static int uniquePathsCore(int m, int n) {
        int[][] steps = new int[m][n];
        for (int i = 0; i < n; i++) {
            steps[0][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            steps[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                steps[i][j] = steps[i - 1][j] + steps[i][j - 1];
            }
        }
        return steps[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int m = 3;
        int n = 3;
        System.out.println(uniquePaths(m, n));
    }
}
