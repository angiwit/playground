package havefun.greedy;

/**
 * https://leetcode-cn.com/problems/monotone-increasing-digits/
 */
public class MonotoneIncreasingDigits {

    public static void main(String[] args) {
        System.out.println(monotoneIncreasingDigits(332));
    }

    public static int monotoneIncreasingDigits(int n) {
        if (n < 10) return n;
        return monotoneIncreasingDigitsCore(n);
    }

    public static int monotoneIncreasingDigitsCore(int n) {
        StringBuilder sb = new StringBuilder(n);
        int lastReplace = sb.length();
        for (int i = sb.length() - 1; i >= 1; i--) {
            char prev = sb.charAt(i - 1);
            char curr = sb.charAt(i);
            if (prev > curr) {
                lastReplace = i;
                /**
                 * need to change this number otherwise the answer is a bigger number than input. e.g.
                 * 9998, if not changing this char, the answer is 9999 instead of 8999.
                 */
                sb.setCharAt(i - 1, Character.forDigit(prev - '0' - 1, 10));
            }
        }
        for (int i = lastReplace; i < sb.length(); i++) {
            sb.setCharAt(i, '9');
        }
        return Integer.parseInt(sb.toString());
    }
}
