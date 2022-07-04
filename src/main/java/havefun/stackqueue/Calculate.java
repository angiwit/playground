package havefun.stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.cn/problems/basic-calculator-ii/
 */
public class Calculate {
    public static int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int num = 0;
        char prevOp = '+';
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + (s.charAt(i) - '0');
            }
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == s.length() - 1) {
                switch (prevOp) {
                    case '+':
                        stack.offerLast(num);
                        break;
                    case '-':
                        stack.offerLast(-num);
                        break;
                    case '*':
                        stack.offerLast(stack.pollLast() * num);
                        break;
                    default:
                        stack.offerLast(stack.pollLast() / num);
                        break;
                }
                prevOp = s.charAt(i);
                num = 0;
            }
        }

        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pollFirst();
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(calculate("3+5 / 2"));
    }
}
