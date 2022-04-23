package havefun.string;

public class ReverseSentence {

    /**
     * I use java instead of c
     * c fo daetsni avaj esu I
     * c of instead java use I
     *
     * @param input
     * @return
     */
    public static String reverseSentence(String input) {
        int slow = 0, fast = 0;
        // remove extra
        StringBuilder stringBuilder = new StringBuilder(input);
        while (fast < input.length()) {
            if (input.charAt(fast) != ' ') {
                stringBuilder.setCharAt(slow++, stringBuilder.charAt(fast++));
            } else {
                stringBuilder.append(" "); // encounter blank then fast-forward all remaining blanks.
                while (fast < input.length() && input.charAt(fast) == ' ') {
                    fast++;
                }
            }
        }
        stringBuilder.replace(slow + 1, input.length() - 1, "").trimToSize();

        int n = stringBuilder.length();
        int k = 0, end = 0;
        StringBuilder result = new StringBuilder();
        while (k < n) {
            while (stringBuilder.charAt(k) != ' ') {
                end++;
            }
            result.append(reverseString(stringBuilder.substring(k, end)));
            k = end + 1;
            end = k + 1;
        }
        return result.toString().trim();
    }

    public static String reverseString(String input) {
        if (input == null || input.length() == 0) return null;
        StringBuilder stringBuilder = new StringBuilder(input);
        int i = 0, j = input.length() - 1;
        while (i < j) {
            char temp = stringBuilder.charAt(i);
            stringBuilder.setCharAt(i, stringBuilder.charAt(j));
            stringBuilder.setCharAt(j, temp);
        }
        return stringBuilder + " ";
    }
}
