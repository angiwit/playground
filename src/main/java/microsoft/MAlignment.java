package microsoft;

import java.util.Arrays;

public class MAlignment {

    public static void main(String[] args) {
        int[] A = {-1, 3, 5, 9, 11, 29};
        System.out.println(mAlignment(A, 4));
    }

    public static int mAlignment(int[] A, int M) {
        if (A == null || A.length == 0) return 1;
        boolean[] dp = new boolean[A.length];
        Arrays.fill(dp, false);
        dp[0] = true;
        for (int i = 1; i < A.length; i++) {
            for (int j = i - 1; j > 0; j--) {
                if (dp[j] == false || A[j] == A[i]) continue;
                if ((A[i] - A[j]) % M == 0 || (A[j] - A[i]) % M == 0) {
                    dp[i] = true;
                } else {
                    dp[i] = false;
                    break;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i]) res++;
        }
        return res;
    }
}
