package havefun.stackqueue;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/evaluate-reverse-polish-notation/
 */
public class EvalRPN {

    public static void main(String[] args) {
        String[] input = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(evalRPN(input));
    }

    public static int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) return 0;
        return evalRPNCore(tokens);
    }

    public static int evalRPNCore(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if (tokens.length == 1 &&
                    (tokens[i].equals("+") ||
                            tokens[i].equals("-") ||
                            tokens[i].equals("*") ||
                            tokens[i].equals("/"))) {
                int first = stack.pop();
                int second = stack.pop();
                int result = calculate(first, second, tokens[i]);
                stack.push(result);
            } else {
                stack.push(Integer.parseInt(tokens[i]));
            }
        }
        return stack.pop();
    }

    private static int calculate(int first, int second, String tokens) {
        if (tokens.equals("+")) return second + first;
        if (tokens.equals("-")) return second - first;
        if (tokens.equals("*")) return second * first;
        if (tokens.equals("/")) return second / first;
        throw new RuntimeException("unknown operation!");
    }
}
