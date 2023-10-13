package havefun.array;

import java.util.Arrays;

public class FindContentChildren {

    public static int findContentChildren(int[] g, int[] s) {
        if (g == null || g.length == 0 || s == null || s.length == 0) return 0;
        return findContentChildrenCore(g, s);
    }

    public static int findContentChildrenCore(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int max = 0;
        int i = 0;
        int j = 0;
        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) {
                max++;
                i++;
                j++;
            } else {
                j++;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] g = {1, 2};
        int[] s = {1, 2, 3};
        System.out.println(findContentChildren(g, s));
    }
}
