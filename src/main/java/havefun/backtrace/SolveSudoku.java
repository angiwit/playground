package havefun.backtrace;

/**
 * https://leetcode-cn.com/problems/sudoku-solver/submissions/
 */
public class SolveSudoku {

    public static void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) return;
        solveSudokuCore(board);
    }

    public static boolean solveSudokuCore(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') continue;
                for (char k = '1'; k <= '9'; k++) {
                    if (isValid(i, j, k, board)) {
                        board[i][j] = k;
                        if (solveSudokuCore(board)) return true;
                        else board[i][j] = '.';
                    }
                }
                return false;
            }
        }
        return true;
    }

    private static boolean isValid(int i, int j, char t, char[][] board) {
        for (int row = 0; row < 9; row++) { //check col
            if (board[row][j] == t) return false;
        }
        for (int col = 0; col < 9; col++) { // check row
            if (board[i][col] == t) return false;
        }
        int row = (i / 3) * 3, col = (j / 3) * 3;
        for (int k = row; k < row + 3; k++) {
            for (int l = col; l < col + 3; l++) {
                if (board[k][l] == t) return false;
            }
        }
        return true;
    }
}
