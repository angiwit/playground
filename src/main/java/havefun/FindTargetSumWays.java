package havefun;

import java.util.List;

// https://leetcode.cn/problems/target-sum/
public class FindTargetSumWays {


    public int findTargetSumWays(int[] nums, int target) {
        if (nums == null) return 0;
        return findTargetSumWaysCore1(nums, target, 0, 0, 0);
    }

    /** 
     * There's DP approach needs more reading and understanding.
    */
    public int findTargetSumWaysCore1(int[] nums, int target, int result, int index, int totalCount) {
        if (index == nums.length) {
            if (target == result) return ++totalCount;
        } else {
            totalCount = findTargetSumWaysCore1(nums, target, result + nums[index], index + 1, totalCount);
            totalCount = findTargetSumWaysCore1(nums, target, result - nums[index], index + 1, totalCount);
        } 
        return totalCount;
    }
    //First version, timed out!
    public int findTargetSumWaysCore(int[] nums, int target, List<Integer> operators, int totalCount) {
        if (operators.size() == nums.length) {
            // calculate the target value to see if equals.
            if (calculateTarget(operators, nums) == target) {
                System.out.println("Matching operators is: " + operators);
                return ++totalCount;
            }
        } else {
            // assign operators to left values in nums.
            for (int j = 0; j < 2; j++) {
                operators.add(j);
                totalCount = findTargetSumWaysCore(nums, target, operators, totalCount);
                // need backtrack here.
                operators.remove(operators.size() - 1);
            }
        }
        return totalCount;
    }

    private int calculateTarget(List<Integer> operators, int[] nums) {
        int firstOp = operators.get(0);
        int result = 0;
        if (firstOp == 1) {
            result = 0 - nums[0];
        } else {
            result = nums[0];
        }
        
        for (int i = 1; i < nums.length; i++) {
            if (operators.get(i) == 0) {
                result += nums[i];
            } else {
                result -= nums[i];
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        FindTargetSumWays findTargetSumWays = new FindTargetSumWays();
        int[] nums = {27,33,4,43,31,44,47,6,6,11,39,37,15,16,8,19,48,17,18,39};
        int target = 24;
        System.out.println(findTargetSumWays.findTargetSumWays(nums, target));
    }
}
