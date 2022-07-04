package havefun.backtrace;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/n-queens/submissions/
 */
public class SolveNQueens {

    public static void main(String[] args) {
        solveNQueens(4);
    }

    public static List<List<String>> solveNQueens(int n) {
        if (n == 0) return null;
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(board[i], '.');
        }
        solveNQueensCore(n, result, new ArrayList<>(), 0, board);
        return result;
    }

    public static void solveNQueensCore(int n, List<List<String>> result, List<String> path, int row, char[][] board) {
        if (row == n) {
            result.add(addBoardToResult(board));
            return;
        }
        for (int col = 0; col < n; col++) {
            if (checkBoard(row, col, board)) {
                board[row][col] = 'Q';
                solveNQueensCore(n, result, path, row + 1, board);
                board[row][col] = '.';
            }
        }
    }

    private static List<String> addBoardToResult(char[][] board) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < board.length; j++) {
                sb.append(board[i][j]);
            }
            result.add(sb.toString());
        }
        return result;
    }

    private static boolean checkBoard(int i, int j, char[][] board) {
        for (int l = 0; l < i; l++) {
            if (board[l][j] == 'Q') return false;
        }
        for (int m = 0; m < j; m++) {
            if (board[i][m] == 'Q') return false;
        }
        for (int l = i - 1, m = j - 1; l >= 0 && m >= 0; l--, m--) {
            if (board[l][m] == 'Q') return false;
        }
        for (int l = i - 1, m = j + 1; l >= 0 && m < board.length; l--, m++) {
            if (board[l][m] == 'Q') return false;
        }
        return true;
    }
}
