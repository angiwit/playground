package havefun.datastructure;

import java.util.Stack;

public class RemoveDuplicates {

    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (!stack.isEmpty() && stack.peek() == s.charAt(i)) {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < stack.size(); i++) {
            stringBuilder.append(stack.get(i));
        }
        return stringBuilder.toString();
    }

    public String removeDuplicatesStringBuilder(String s) {
        StringBuilder stack = new StringBuilder(s);
        int top = -1;
        for (int i = 0; i < s.length(); i++) {
            if (top != -1 && stack.charAt(top) == s.charAt(i)) {
                stack.deleteCharAt(top);
                top--;
            } else {
                stack.append(s.charAt(i));
                top++;
            }
        }
        return stack.toString();
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        for (int i = 0; i < stack.size(); i++) {
            System.out.println(stack.get(i));
        }
        System.out.println("This is a splitter");
        for (int i = stack.size() - 1; i >= 0; i--) {
            System.out.println(stack.get(i));
        }
    }
}
