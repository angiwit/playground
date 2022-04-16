package havefun.doublepointer;

public class TrapRainwater {

    public static int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        return trapCore(height);
    }

    public static int trapCore(int[] height) {
        int leftMax = 0;
        int rightMax = 0;
        int total = 0;
        int i = 0;
        int j = height.length - 1;
        while (i < j) {
            leftMax = Math.max(leftMax, height[i]);
            rightMax = Math.max(rightMax, height[j]);
            if (height[i] <= height[j]) {
                total += leftMax - height[i];
                i++;
            } else {
                total += rightMax - height[j];
                j--;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(height));
    }
}
