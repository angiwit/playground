package havefun.dp;

public class OptimalKeys {

    public static void main(String[] args) {
        System.out.println(optimalKeys(7));
    }


    public static int optimalKeys(int input) {
        int[] dp = new int[input + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i <= input; i++) { // i = 6, cv 3 chars
            dp[i] = dp[i - 1] + 1;
            for (int j = i - 3; j >= 0; j--) { // j = 3: now screen has 3 chars
                dp[i] = Math.max(dp[j] * (i - j - 1), dp[i]); // index4: ca 3 chars, index5: cp 3 chars, index6: cv 3 chars.
                // so (i - j - 2) cv + existing = (i - j - 1) * existing chars.
            }
        }
        return dp[input];
    }
}
