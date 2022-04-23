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

    public static int[][] generateMatrixCore(int n) {
        int[][] res = new int[n][n];
        int i = 0, num = 1;
        int up = 0, left = 0, right = n - 1, down = n - 1;
        while (i <= n * n) {
            for (int j = left; j <= right; j++) {
                res[up][j] = num++;
            }
            up++;
            for (int j = up; j <= down; j++) {
                res[j][right] = num++;
            }
            right--;
            for (int j = right; j >= left; j--) {
                res[down][j] = num++;
            }
            down--;
            for (int j = down; j >= up; j--) {
                res[left][j] = num++;
            }
            left++;
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
