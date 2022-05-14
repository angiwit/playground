package havefun.doublepointer;

public class TrapRainwater {

    public static int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        return trapCore(height);
    }

    public static int trapCore(int[] height) {
        int i = 0, j = height.length - 1;
        int leftMax = height[0], rightMax = height[j];
        int result = 0;
        while (i < j) {
            leftMax = Math.max(leftMax, height[i]);
            rightMax = Math.max(rightMax, height[j]);
            if (leftMax <= rightMax) {
                result += leftMax - height[i++];
            } else {
                result += rightMax - height[j--];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(height));
    }
}
