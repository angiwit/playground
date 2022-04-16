package havefun.array;

public class GenerateMatrix {

    public static void main(String[] args) {
        int[][] res = generateMatrix(4);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(String.format("[%s]", res[i][j]));
            }
            System.out.println();
        }
    }

    public static int[][] generateMatrix(int n) {
        if (n == 0) return null;
        return generateMatrixCore(n);
    }

    //incorrect
    public static int[][] generateMatrixCore(int n) {
        int[][] res = new int[n][n];
        boolean fillingRow = true;
        boolean left2Right = true;
        boolean up2Down = true;
        int row = 0;
        int col = 0;
        int i = 1;
        while (i <= n * n) {
            if (fillingRow) {
                if (left2Right) {
                    if (res[row][col] != 0 || col > n - 1) {
                        row++;
                        col--;
                        up2Down = true;
                        fillingRow = false;
                    } else {
                        res[row][col] = i;
                        if (col < n - 1) {
                            col++;
                        }
                        i++;
                    }
                } else {
                    if (res[row][col] != 0 || col < 0) {
                        row--;
                        col++;
                        up2Down = false;
                        fillingRow = false;
                    } else {
                        res[row][col] = i;
                        if (col > 0) {
                            col--;
                        }
                        i++;
                    }
                }
            } else {
                if (up2Down) {
                    if (res[row][col] != 0 || row > n - 1) {
                        col--;
                        row--;
                        left2Right = false;
                        fillingRow = true;
                    } else {
                        res[row][col] = i;
                        if (row < n - 1) {
                            row++;
                        }
                        i++;
                    }
                } else {
                    if (res[row][col] != 0 || row < 0) {
                        col++;
                        row++;
                        left2Right = true;
                        fillingRow = true;
                    } else {
                        res[row][col] = i;
                        if (row > 0) {
                            row--;
                        }
                        i++;
                    }
                }
            }
        }
        return res;
    }

    public void iterateMatrix(int[][] n) {
        int left = 0;
        int right = n[0].length - 1;

        int up = 0;
        int down = n.length - 1;
        while (left <= right && up <= down) {
            for (int i = left; i <= right; i++) {
                System.out.print(n[up][i]);
            }
            up++;
            for (int i = up; i <= down; i++) {
                System.out.print(n[i][right]);
            }
            right--;
            for (int i = right; i >= left; i--) {
                System.out.println(n[down][i]);
            }
            down--;
            for (int i = down; i >= up; i--) {
                System.out.println(n[i][right]);
            }
            left++;
        }
    }
}
