package havefun.array.matrix;

public class SearchMatrix {

    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int up = 0;
        int right = n - 1;
        while (up < m && right >= 0) {
            if (matrix[up][right] > target) {
                right--;
            } else if (matrix[up][right] < target) {
                up++;
            } else {
                return true;
            }
        }
        return false;
    }
}
