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
        combineCore(k, 1, n + 1, new Stack<>(), result);
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
     * When cutting branch, calculate the state's size and the numbers left, if not sufficient, break current loop.
     *
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
        for (int i = start; i > k - 1 - end - found.size(); i++) {
            // left still need choose: k - found.size(), index should be less than [end - (k - found.size()) + 1].
//            if (i > end - (k - found.size()) + 1) break;
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
