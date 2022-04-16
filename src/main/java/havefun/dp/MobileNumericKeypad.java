package havefun.dp;

public class MobileNumericKeypad {

    static int row[] = {0, 0, -1, 0, 1};
    static int col[] = {0, -1, 0, 1, 0};
    static char[][] keyPad =
            {{'1', '2', '3'},
                    {'4', '5', '6'},
                    {'7', '8', '9'},
                    {'*', '0', '#'}};

    public static void main(String[] args) {
        System.out.println('1' - '0');
    }

    /**
     * For each key, there are N possible combinations, e.g. N = 2: key1 -> 11, 12, 14. key2 -> 22, 21, 23, 25.
     *
     * @param N
     * @return
     */
    public static long getCount(int N) {
        if (N == 0) return 0;
        if (N == 1) return 10;
        int result = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (i < 4 && i >= 0 && j < 3 && j >= 0 && keyPad[i][j] != '*' && keyPad[i][j] != '#') {
                    result += getCountCoreUtil(i, j, N);
                }
            }
        }
        return result;
    }

    // possible combination for each single position.
    public static long getCountCoreUtil(int i, int j, int N) {
        if (N == 1) return 10;
        int result = 0;
        int[] row = {0, 0, 0, -1, 1};
        int[] col = {0, -1, 1, 0, 0};
        for (int k = 0; k < 5; k++) {
            int ro = i + row[k];
            int co = j + col[k];
            if (ro < 3 && ro >= 0 && co < 4 && co >= 0 && keyPad[ro][co] != '*' && keyPad[ro][co] != '#') {
                result += getCountCoreUtil(ro, co, N - 1);
            }
        }
        return result;
    }

    public static long getCountCoreDP(int N) {
        int[][] dp = new int[10][N + 1];
        for (int i = 0; i < 10; i++) {
            dp[i][0] = 0;
            dp[i][1] = 1;
        }
        int[] r = {0, 1, -1, 0, 0};
        int[] c = {0, 0, 0, -1, 1};
        for (int l = 0; l <= N; l++) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    if (keyPad[i][j] != '*' && keyPad[i][j] != '#') {
                        int num = keyPad[i][j] - '0';
                        dp[num][l] = 0;
                        for (int k = 0; k < 5; k++) {
                            int row = i + r[k];
                            int col = j + c[k];
                            if (row < 4 && row >= 0 && col < 3 && col >= 0 && keyPad[row][col] != '*' && keyPad[row][col] != '#') {
                                int next = keyPad[i][j] - '0';
                                dp[num][l] = dp[num][l] + dp[next][l - 1];
                            }
                        }
                    }
                }
            }
        }
        int result = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                result += dp[i][j];
            }
        }
        return result;
    }
}
