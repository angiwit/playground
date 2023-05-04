package havefun.greedy;

/**
 * https://leetcode-cn.com/problems/monotone-increasing-digits/
 */
public class MonotoneIncreasingDigits {

    public static void main(String[] args) {
        System.out.println(monotoneIncreasingDigitsInCorrect(1234));
    }

    public static int monotoneIncreasingDigits(int n) {
        if (n < 10) return n;
        return monotoneIncreasingDigitsCore(n);
    }

    /**
     * We need to process the numbers from end to start, once we found a increasing pair(since from end), we change left number in 
     * the pair to a value smaller than itself by 1, and change the right number in the pair to 9. Then we continue to check if
     * the number matches decreasing condition, if true, then we get a final answer, if not, we can continue this process.
     * Take 10 as an example, 1 is the left, 0 is the right, we decrease 1 by 1 to 0 and change 0 to 9, which is correct.
     * @param n
     * @return
     */
    public static int monotoneIncreasingDigitsCore(int n) {
        StringBuilder sb = new StringBuilder(String.valueOf(n));
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

    /**
     * We can't consider this problem to iterate the chars from start to end, because this is not correct. E.g. for 332, we find
     * the first char starting to drop which is the second 3. And from this position to its right, all numbers should be replaced 
     * with 9, and the number before it should be replaced with a number smaller than itself by 1, and all numbers before this 
     * number should keep same, which we can get a correct answer. An example is 120 the first drop number is 2, after chaning 
     * the result will be 99 which is not correct but in fact it should be 119.
     * 
     * @param n
     * @return
     */
    public static int monotoneIncreasingDigitsInCorrect(int n) {
        StringBuilder sb = new StringBuilder(String.valueOf(n));
        char left = sb.charAt(0);
        int startIndex = 0;
        int k = 0;
        while (k < sb.length()) {
            if (k + 1 < sb.length()) {
                if (left > sb.charAt(k + 1)) {
                    startIndex = k;
                    break;
                }
            }
            k++;
        }
        if (k == sb.length()) return n;
        // Next is the smaller value, we need to replace next and all its right numbers to 9 and reduce the first 1 and
        // then concat all the numbers before first to return the final result.
        if (startIndex - 1 >= 0) {
            sb.setCharAt(startIndex - 1, Character.forDigit(left - '0' - 1, 10));
            for (int i = startIndex; i < sb.length(); i++) {
                sb.setCharAt(i, '9');
            }
        } else {
            // first number is the startIndex, minus first number by 1, change all left numbers to 9.
            sb.setCharAt(0, Character.forDigit(sb.charAt(0) - '0' - 1, 10));
            for (int i = 1; i < sb.length(); i++) {
                sb.setCharAt(i, '9');
            }
        }
        return Integer.parseInt(sb.toString());
    }
}
