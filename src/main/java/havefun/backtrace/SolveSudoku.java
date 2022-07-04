package havefun.backtrace;

/**
 * https://leetcode-cn.com/problems/sudoku-solver/
 */
public class SolveSudoku {

    public static void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) return;
        solveSudokuCore0(board);
    }

    public static boolean solveSudoKuCore1(char[][] board) {
        boolean[][] rowUsed = new boolean[9][10];
        boolean[][] colUsed = new boolean[9][10];
        boolean[][][] boxUsed = new boolean[3][3][10];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] >= '1' && board[i][j] <= '9') {
                    int num = board[i][j] - '0';
                    rowUsed[i][num] = true;
                    colUsed[j][num] = true;
                    boxUsed[i][j][num] = true;
                }
            }
        }

        return solveSudoKuCore1Recursive(board, rowUsed, colUsed, boxUsed);
    }

    private static boolean solveSudoKuCore1Recursive(char[][] board, boolean[][] rowUsed, boolean[][] colUsed, boolean[][][] boxUsed) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    for (char k = '1'; k < '9'; k++) {
                        int num = k - '0';
                        if (!rowUsed[i][num] && !colUsed[j][num] && !boxUsed[i][j][num]) {
                            board[i][j] = Character.forDigit(num, 10);
                            if (solveSudoKuCore1Recursive(board, rowUsed, colUsed, boxUsed)) {
                                return true;
                            }
                            board[i][j] = '.';
                            rowUsed[i][j] = false;
                            colUsed[i][j] = false;
                            boxUsed[i][j][num] = false;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean solveSudokuCore0(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    for (char k = '1'; k <= '9'; k++) {
                        if (isValid(i, j, k, board)) {
                            board[i][j] = k;
                            if (solveSudokuCore0(board)) return true;
                            board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValid(int i, int j, char t, char[][] board) {
        for (int k = 0; k < 9; k++) {
            if (board[i][k] == t) return false;
        }
        for (int k = 0; k < 9; k++) {
            if (board[k][j] == t) return false;
        }
        // index range: [0 - 8], so below formula is correct.
        /**
         * [8,8] is the last cell, and after normalization, the [row, col] is [6, 6], add 3 is [9, 9].
         */
        int row = i / 3 * 3;
        int col = j / 3 * 3;
        for (int k = row; k < row + 3; k++) {
            for (int l = col; l < col + 3; l++) {
                if (board[k][l] == t) return false;
            }
        }
        return true;
    }
}
