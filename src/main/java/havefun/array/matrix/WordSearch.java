package havefun.array.matrix;

/**
 * https://leetcode.cn/problems/word-search/
 */
public class WordSearch {
    int[] rows = {-1, 1, 0, 0};
    int[] cols = {0, 0, -1, 1};

    public boolean exist(char[][] board, String word) {
        if (board == null || word == null) return false;
        boolean[][] used = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (existCore(board, word, i, j, 0, used)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean existCore0(char[][] board, String word, int row, int col, int idx, boolean[][] used) {
        if (idx == word.length()) return true;
        if (check(board, word, row, col, idx, used)) {
            used[row][col] = true;
            /**
             * make sure the backtrace is done after the for loop.
             * If we backtrace IN the for loop, it means we only reviewed one (of the four) path and retract the value in
             * current starting cell.
             * Backtrace after the loop means after we reviewed four directions and we deem this cell is not a valid path
             * and then retract the value in current cell.
             */

            for (int i = 0; i < 4; i++) {
                int nextRow = row + rows[i];
                int nextCol = col + cols[i];
                if (existCore0(board, word, nextRow, nextCol, idx + 1, used)) {
                    return true;
                }
            }
            used[row][col] = false;
        }
        return false;
    }

    public boolean existCore(char[][] board, String word, int row, int col, int idx, boolean[][] used) {
        if (idx == word.length()) return true;
        if (check(board, word, row, col, idx, used)) {
            used[row][col] = true;
            /**
             * make sure the backtrace is done after the for loop.
             * If we backtrace IN the for loop, it means we only reviewed one (of the four) path and retract the value in
             * current starting cell.
             * Backtrace after the loop means after we reviewed four directions and we deem this cell is not a valid path
             * and then retract the value in current cell.
             */

            boolean result = existCore(board, word, row + 1, col, idx + 1, used) ||
                    existCore(board, word, row - 1, col, idx + 1, used) ||
                    existCore(board, word, row, col + 1, idx + 1, used) ||
                    existCore(board, word, row, col - 1, idx + 1, used);
            if (result) return true;
            used[row][col] = false;
        }
        return false;
    }

    private boolean check(char[][] board, String word, int row, int col, int idx, boolean[][] used) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length && idx < word.length()
                && board[row][col] == word.charAt(idx) && used[row][col] != true;
    }
}
