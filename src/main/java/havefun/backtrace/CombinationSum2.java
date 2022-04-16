package havefun.backtrace;

import java.util.*;

public class CombinationSum2 {

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSum2Core2(candidates, target, 0, new ArrayDeque<>(), result);
        return result;
    }

    /**
     * @param candidates
     * @param target
     * @param start
     * @param stack
     * @param result
     */
    // correct
    public static void combinationSum2Core2(int[] candidates,
                                            int target,
                                            int start,
                                            Deque<Integer> stack,
                                            List<List<Integer>> result) {
        /**
         * end condition here, do not need another method to calculate sum, need an arrayDeque to add and remove.
         * end condition in for loop, need to calculate sum, need a stack to push and pop.
         */
        if (target == 0) {
            result.add(new ArrayList<>(stack));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) break;
            /**
             * Notice the i > start not i > 0, if i > 0, duplicate elements have no opportunity to be chosen.
             * i > start means: in same round, only the first i will be chosen, others not!!!
             * 同一树层上不重复，同一树枝上可以重复！
             * But for different level, different i have opportunity to be chosen!!!
             * Only skip the same round i!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! genius, guys!
             */
            if (i > start && candidates[i] == candidates[i - 1]) continue;
            stack.addLast(candidates[i]);
            combinationSum2Core2(candidates, target - candidates[i], i + 1, stack, result);
            stack.removeLast();
        }
    }


    // incorrect
    public static void combinationSum2Core(int[] candidates,
                                           int target,
                                           int start,
                                           Stack<Integer> stack,
                                           List<List<Integer>> result) {
        int sum = calculateSum(stack);
        if (sum == target) {
            List<Integer> temp = new ArrayList<>(stack);
            result.add(temp);
            return;
        } else if (sum > target) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            stack.push(candidates[i]);
            combinationSum2Core(candidates, target, i + 1, stack, result);
            /**
             * pop element here instead of after the for loop.
             */
            if (stack.size() > 0) stack.pop();
        }
    }

    // incorrect
    public static void combinationSum2Core1(int[] candidates,
                                            int target,
                                            int start,
                                            Stack<Integer> stack,
                                            List<List<Integer>> result) {
        for (int i = start; i < candidates.length; i++) {
            stack.push(candidates[i]);
            int sum = calculateSum(stack);
            if (sum == target) {
                List<Integer> temp = new ArrayList<>(stack);
                result.add(temp);
                stack.pop();
            } else if (sum > target) {
                stack.pop();
            } else {
                combinationSum2Core1(candidates, target, i + 1, stack, result);
                /**
                 * pop element here instead of after the for loop.
                 */
                if (stack.size() > 0) stack.pop();
            }
        }
    }

    private static int calculateSum(Stack<Integer> stack) {
        int result = 0;
        for (int i = 0; i < stack.size(); i++) {
            result += stack.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
//        int[] candidates = {10, 1, 2, 7, 6, 1, 5, 1};
//        int target = 8;
        int[] candidates = {2, 5, 2, 1, 2};
        int target = 5;
        List<List<Integer>> result = combinationSum2(candidates, target);
        for (List<Integer> list : result) {
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i) + " ");
            }
            System.out.println("");
        }
    }
}
