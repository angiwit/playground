package havefun.backtrace;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CombinationSum {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        combinationSumCore1AnotherStyle(candidates, target, 0, new Stack<>(), result);
        return result;
    }

    // incorrect
    public static void combinationSumCore(int[] candidates,
                                          int target,
                                          int start,
                                          Stack<Integer> state,
                                          List<List<Integer>> result) {
        for (int i = start; i < candidates.length; i++) {
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
        if (state.size() > 0) state.pop();
    }

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
        if (state.size() > 0) state.pop();
    }

    //correct
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
            if (calculateSum(state) > target) {
                state.pop();
                continue; // forward
            }
            combinationSumCore1(candidates, target, i, state, result);
            // begin next round review
            if (state.size() > 0) state.pop();
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
        if (calculateSum(state) > target) {
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
            if (state.size() > 0) state.pop();
        }
    }

    private static int calculateSum(Stack<Integer> state) {
        int i = state.size() - 1;
        int result = 0;
        while (state.size() > 0 && i >= 0) {
            result += state.get(i);
            i--;
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
