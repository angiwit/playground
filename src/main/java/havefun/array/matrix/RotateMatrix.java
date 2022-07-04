package havefun.array.matrix;

/**
 * https://leetcode.cn/problems/rotate-image/
 */
public class RotateMatrix {
    public static void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        int N = matrix.length - 1;
        int top = 0, bottom = N - 1, left = 0, right = N - 1;
        for (int i = left; i <= N; i++) {
            int curValue = matrix[top][i];
            
        }
    }

}
