package havefun.doublepointer;

public class TrapRainwater {

    public static int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        return trapCore(height);
    }

    public static int trapCore(int[] height) {
        int i = 0, j = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int result = 0;
        while (i < j) {
            leftMax = Math.max(height[i], leftMax);
            rightMax = Math.max(height[j], rightMax);
            if (leftMax < rightMax) { // if leftMax at index i, then leftMax - height[i] = 0.
                result += leftMax - height[i];
                i++;
            } else {
                result += rightMax - height[j];
                j--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(height));
    }
}
