package havefun.array;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.cn/problems/remove-k-digits/
 */
public class RemoveKdigits {

    public String removeKdigits(String num, int k) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < num.length(); i++) {
            while (!stack.isEmpty() && k > 0 && stack.peekLast() > num.charAt(i)) {
                stack.pollLast();
                k--;
            }
            stack.offerLast(num.charAt(i));
        }

        for (int i = 0; i < k; i++) {
            stack.pollLast();
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            char t = stack.pollFirst();
            if (t == '0' && sb.isEmpty()) {
                continue;
            }
            sb.append(t);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
