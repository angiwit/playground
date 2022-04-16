package havefun.greedy;

public class WiggleMaxLength {

    public static int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return wiggleMaxLengthCoreDPOptimized(nums);
    }

    public static int wiggleMaxLengthCore(int[] nums) {
        int i = 0;
        int flag = -1;
        int result = 0;
        int prev = 0;
        while (i < nums.length - 1) {
            while (i < nums.length - 1 && nums[i + 1] == nums[prev]) {
                i++;
            }
            if (i >= nums.length - 1) return result;
            if (flag == -1) {
                if (nums[i + 1] - nums[prev] > 0) {
                    flag = 1;
                } else {
                    flag = 0;
                }
                result++;
                prev = i + 1;
            } else if (flag == 0) {
                if (nums[i + 1] - nums[prev] > 0) {
                    flag = 1;
                    result++;
                    prev = i + 1;
                }
            } else if (flag == 1) {
                if (nums[i + 1] - nums[prev] < 0) {
                    flag = 0;
                    result++;
                    prev = i + 1;
                }
            }
            i++;
        }
        return result + 1;
    }

    public static int wiggleMaxLengthCoreDP(int[] nums) {
        int[][] values = new int[nums.length][2];
        values[0][0] = 1;
        values[0][1] = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] - nums[j] > 0) {
                    values[i][0] = Math.max(values[i][0], values[j][1] + 1);
                } else if (nums[i] - nums[j] < 0) {
                    values[i][1] = Math.max(values[i][1], values[j][0] + 1);
                } else {
                    values[i][0] = values[i - 1][0];
                    values[i][1] = values[i - 1][1];
                }
            }
        }
        return Math.max(values[nums.length - 1][0], values[nums.length - 1][1]);
    }

    public static int wiggleMaxLengthCoreDPOptimized(int[] nums) {
        int up = 1;
        int down = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            }
            if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }

    public static void main(String[] args) {
        int[] nums = {1, 17, 5, 10, 13, 15, 10, 5, 16, 8};
//        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
//        int[] nums = {0, 0};
        System.out.println(wiggleMaxLength(nums));
    }
}
