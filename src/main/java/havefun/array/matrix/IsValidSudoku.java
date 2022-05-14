package havefun.array.matrix;

public class IsValidSudoku {

    public static boolean isValidSudoku(char[][] board) {
        if (board == null) return false;
        int[][] row = new int[9][9];
        int[][] col = new int[9][9];
        int[][][] boxes = new int[3][3][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int index = board[i][j] - '0' - 1;
                    row[i][index]++;
                    col[j][index]++;
                    boxes[i / 3][j / 3][index]++;
                    if (row[i][index] > 1 || col[j][index] > 1 || boxes[i / 3][j / 3][index] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
