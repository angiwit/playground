package havefun.backtrace;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
// https://leetcode.cn/problems/combination-sum/
public class CombinationSum {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        combinationSumCore(candidates, target, 0, new Stack<>(), result);
        return result;
    }

    public static void combinationSumCore(int[] candidates,
                                          int target,
                                          int start,
                                          Stack<Integer> state,
                                          List<List<Integer>> result) {
        int sum = calculateSum(state);
        if (sum == target) {
            result.add(new ArrayList<>(state));
            return;
        } else if (sum > target) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            state.push(candidates[i]);
            // when sum is smaller than target, always review current element, once method returns, it will pop and i will increase 1 to next element.
            combinationSumCore(candidates, target, i, state, result);
            state.pop();
        }
    }

    // incorrect
    public static void combinationSumCoreWithCutting(int[] candidates,
                                                     int target,
                                                     int start,
                                                     Stack<Integer> state,
                                                     List<List<Integer>> result) {
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target)
                continue; // cutting, if current bigger than target, no need to check current element.
            state.push(candidates[i]);
            int sum = calculateSum(state);
            if (sum == target) {
                result.add(new ArrayList<>(state));
                state.pop();
            } else if (target > sum) {
                combinationSumCore(candidates, target, i, state, result);
            } else {
                state.pop();
            }
        }
        // begin next round review
        state.pop();
    }

    //correct
    /**
     * Only this problem can use this style because the i's forward always happens in for loop instead of recursive call with i + 1.
     * @param candidates
     * @param target
     * @param start
     * @param state
     * @param result
     */
    public static void combinationSumCore1(int[] candidates,
                                           int target,
                                           int start,
                                           Stack<Integer> state,
                                           List<List<Integer>> result) {
        for (int i = start; i < candidates.length; i++) {
            /**
             * How to make it move forward?
             * How about always make it spin, once sum greater/equals than/to target, then it should forward.
             */
            state.push(candidates[i]);
            int sum = calculateSum(state);
            //need forward.
            if (sum == target) {
                result.add(new ArrayList<>(state));
                state.pop();
                continue;// forward
            }
            if (sum > target) {
                state.pop();
                continue; // forward
            }
            combinationSumCore1(candidates, target, i, state, result);
            // begin next round review
            state.pop();
        }
    }

    //correct
    public static void combinationSumCore1AnotherStyle(int[] candidates,
                                                       int target,
                                                       int start,
                                                       Stack<Integer> state,
                                                       List<List<Integer>> result) {
        int sum = calculateSum(state);
        //need forward.
        if (sum == target) {
            result.add(new ArrayList<>(state));
            return;
        }
        if (sum > target) {
            return;
        }
        // only when calculateSum(state) < target, proceeding to below part.
        for (int i = start; i < candidates.length; i++) {
            /**
             * How to make it move forward?
             * How about always make it spin, once sum ge than target, then it should forward.
             */
            state.push(candidates[i]);
            combinationSumCore1AnotherStyle(candidates, target, i, state, result);
            // begin next round review
            state.pop();
        }
    }

    private static int calculateSum(Stack<Integer> state) {
        int result = 0;
        for (int i = 0; i < state.size(); i++) {
            result += state.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        List<List<Integer>> result = combinationSum(candidates, 7);
        for (List<Integer> integers : result) {
            for (Integer integer : integers) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }
}
