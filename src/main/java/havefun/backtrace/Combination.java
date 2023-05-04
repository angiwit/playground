package havefun.backtrace;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/combinations/
 */
public class Combination {

    /**
     * Think clearly what data structure can be used to store state, and all iteration depends on this data structure.
     *
     * @param n
     * @param k
     * @return
     */
    public static List<List<Integer>> combine(int n, int k) {
        if (n <= 0 || k <= 0) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        combineCore(k, 1, n, new Stack<>(), result);
        return result;
    }


    public static void combineCore(int k, int start, int end, Stack<Integer> found, List<List<Integer>> result) {
        if (found.size() == k) {
            result.add(new ArrayList<>(found));
            return;
        }
        for (int i = start; i < end; i++) {
            found.push(i);
            combineCore(k, i + 1, end, found, result);
            found.pop();
        }
    }

    /**
     * Known start and end and found.size(), we need to calculate the i range that the for loop should execute.
     * We still need to find (k - found.size()) elements. from i to end there's (end - i) elements.
     * So the loop condition is: k - found.size() <= end - i, which equals to i <= end - (k - found.size()).
     * In this problem, the index is not from 0 but from 1, so we need to add 1 to right equasion to i <= end - (k - found.size()) + 1,
     * but this could also change based on the passed end from invocation, e.g. if passed n + 1 as end, there's no need to add 1 in above equasion.
     * @param k
     * @param start
     * @param end
     * @param found
     * @param result
     */
    public static void combineCoreWithCutting(int k, int start, int end, Stack<Integer> found, List<List<Integer>> result) {
        if (found.size() == k) {
            result.add(new ArrayList<>(found));
            return;
        }
        for (int i = start; i <= end - (k - found.size()) + 1; i++) {
            found.push(i);
            combineCore(k, i + 1, end, found, result);
            found.pop();
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> result = combine(4, 3);
        for (List<Integer> integers : result) {
            for (Integer integer : integers) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }
}
