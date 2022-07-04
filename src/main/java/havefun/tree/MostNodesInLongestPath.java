package havefun.tree;

import java.util.HashSet;
import java.util.Set;

public class MostNodesInLongestPath {

    private int max = 0;

    public int solution(Tree T) {
        // write your code in Java SE 8
        if (T == null) return 0;
        Set<Integer> set = new HashSet<>();
        solutionCore(T, set);
        return max;
    }

    private void solutionCore(Tree t, Set<Integer> set) {
        if (t == null || set.contains(t.x)) {
            // a path is found
            max = Math.max(max, set.size());
            return;
        }
        set.add(t.x);
        solutionCore(t.l, set);
        solutionCore(t.r, set);
        set.remove(t.x);
    }

    static class Tree {
        public int x;
        public Tree l;
        public Tree r;
    }
}
