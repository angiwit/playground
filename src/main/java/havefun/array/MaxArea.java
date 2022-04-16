package havefun.array;

/**
 * https://leetcode-cn.com/problems/container-with-most-water/
 */
public class MaxArea {

    public static void main(String[] args) {
        int[] height = {2, 3, 4, 5, 18, 17, 6};
        System.out.println(maxArea(height));
    }

    public static int maxArea(int[] height) {
        if (height == null || height.length == 0) return 0;
        return maxAreaCore(height);
    }

    public static int maxAreaCore(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int result = 0;
        while (i < j) {
            int tempHeight = Math.min(height[i], height[j]);
            result = Math.max(tempHeight * (j - i), result);
            if (height[i] <= height[j]) i++;
            else j--;
        }
        return result;
    }
}
