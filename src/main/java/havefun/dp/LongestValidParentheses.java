package havefun.dp;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/longest-valid-parentheses/
 */
public class LongestValidParentheses {

    public static void main(String[] args) {
        System.out.println(longestValidParentheses(")()())"));
    }

    //()()((
    public static int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // placeholder also the key point.
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (!stack.isEmpty()) {
                    max = Math.max(max, i - stack.peek());
                } else {
                    stack.push(i); // placeholder
                }
            }
        }
        return max;
    }


    public static int longestValidParenthesesDP(String s) {
        int[] dp = new int[s.length() + 1];
        int max = 0;
        for (int i = 2; i < s.length(); i = i + 2) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = dp[i - 2] + 2;
                }
                if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
