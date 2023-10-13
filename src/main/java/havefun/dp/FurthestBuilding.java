package havefun.dp;

import java.util.PriorityQueue;
// https://leetcode.cn/problems/furthest-building-you-can-reach/
public class FurthestBuilding {

    private static int ans = 0;

    public static int furthestBuilding(int[] heights, int bricks, int ladders) {
        furthestBuildingCore(heights, bricks, ladders, 0);
        return ans;
    }

    private static void furthestBuildingCore(int[] heights, int bricks, int ladders, int index) {
        if (index == heights.length - 1 || (heights[index + 1] - heights[index] > bricks && ladders == 0)) {
            ans = Math.max(index, ans);
            return;
        }
        int consumeBricks = heights[index + 1] - heights[index];
        if (consumeBricks <= 0) {
            furthestBuildingCore(heights, bricks, ladders, index + 1);
        } else {
            // It doesn't seem this solution is working. Because here we always use bricks first which might not be an optimal solution.
            if (bricks >= consumeBricks) {
                furthestBuildingCore(heights, bricks - consumeBricks, ladders, index + 1);
            }
            if (ladders > 0) {
                furthestBuildingCore(heights, bricks, ladders - 1, index + 1);
            }
        }
    }

    public static int furthestBuilding1(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> y - x);
        for (int i = 1; i < heights.length; i++) {
            int consum = heights[i] - heights[i - 1];
            if (consum < 0) continue;
            if (consum < bricks) {
                queue.add(consum);
                bricks -= consum;
            } else {
                if (ladders > 0) {
                    ladders--;
                    bricks += queue.peek() == null ? 0 : queue.poll(); // get max bricks count to ensure the following buildings usage.
                } else {
                    return i - 1;
                }
            }
        }
        return heights.length - 1;
    }

    public static void main(String[] args) {
        int[] heights = {4, 2, 7, 6, 9, 14, 12};
        int bricks = 5, ladders = 1;
        System.out.println(furthestBuilding1(heights, bricks, ladders));
    }

}
