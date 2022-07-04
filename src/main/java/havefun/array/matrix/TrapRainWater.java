package havefun.array.matrix;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TrapRainWater {

    public static int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0) return 0;
        int r = heightMap.length;
        int c = heightMap[0].length;

        boolean[][] visited = new boolean[r][c];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x[1]));

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (i == 0 || j == 0 || i == r - 1 || j == c - 1) {
                    pq.offer(new int[]{i * c + j, heightMap[i][j]});
                    visited[i][j] = true;
                }
            }
        }

        int result = 0;
        int[][] next = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] / c + next[i][0];
                int nc = cur[0] % c + next[i][1];
                if (nr >= 0 && nc >= 0 && nr < r && nc < c && !visited[nr][nc]) {
                    if (heightMap[nr][nc] < cur[1]) {
                        result += cur[1] - heightMap[nr][nc];
                    }
                    pq.offer(new int[]{nr * c + nc, Math.max(heightMap[nr][nc], cur[1])});
                    visited[nr][nc] = true;
                }
            }
        }
        return result;
    }
}
