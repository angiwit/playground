package havefun.backtrace;

public class PathInMatrix {

    public static void main(String[] args) {
    }

    public static boolean pathInMatrix(char[][] chars, String path) {
        boolean[][] visited = new boolean[chars.length][chars[0].length];
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[0].length; j++) {
                if (path.charAt(0) == chars[i][j]) {
                    return pathInMatrixCore(chars, path, 1, i, j, visited);
                }
            }
        }
        return false;
    }

    public static boolean pathInMatrixCore(char[][] chars, String path, int index, int row, int col, boolean[][] visited) {
        if (index == path.length()) {
            return true;
        }
        boolean hasPath = false;
        if ((row >= 0 && row <= chars.length - 1) &&
                (col >= 0 && col <= chars[0].length - 1) &&
                visited[row][col] != true &&
                path.charAt(index) == chars[row][col]) {
            visited[row][col] = true;
            hasPath = pathInMatrixCore(chars, path, index + 1, row - 1, col, visited) ||
                    pathInMatrixCore(chars, path, index + 1, row + 1, col, visited) ||
                    pathInMatrixCore(chars, path, index + 1, row, col - 1, visited) ||
                    pathInMatrixCore(chars, path, index + 1, row, col + 1, visited);
            if (!hasPath) {
                visited[row][col] = false;
            }
        }
        return hasPath;
    }

}
