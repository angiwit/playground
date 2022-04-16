package havefun.backtrace;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    /**
     * IMPORTANT!! When comes to end, the final number is reviewed in **current round**
     * After the for loop, pop a number and the index will automatically increase one to next number.
     **/
    public static void combineCore(int k, int start, int end, Stack<Integer> found, List<List<Integer>> result) {
        if (found != null && found.size() == k) {
            List<Integer> temp = new ArrayList<>();
            temp.addAll(found);
            result.add(temp);
            /**
             * Pop the last element we just added, and since the method returns here, after return,
             * we need to pop another element to make the last element change to another one.
             */
            return;
        }
        // when comes to end, the final number is reviewed in current round
        for (int i = start; i < end; i++) {
            found.push(i);
            combineCore(k, i + 1, end, found, result);
            found.pop();
        }
        // out of the for loop, and begin next round loop, clear state.
//        if (found.size() > 0) found.pop();
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
        if (found != null && found.size() == k) {
            List<Integer> temp = new ArrayList<>();
            temp.addAll(found);
            result.add(temp);
            found.pop();
            return;
        }

        for (int i = start; i <= found.size() + end + 1 - k; i++) {
//            if (found.size() + (end - i - 1) < k) break; // i < found.size() + end + 1 - k
            found.push(i);
            combineCore(k, i + 1, end, found, result);
        }
        // out of the for loop, and begin next round loop, clear state.
        if (found.size() > 0) found.pop();
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
