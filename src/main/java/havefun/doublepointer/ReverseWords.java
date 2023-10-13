package havefun.doublepointer;

public class ReverseWords {

    public static String reverseWords(String s) {
        StringBuilder sb = new StringBuilder(s);
        trimString(sb);
        reverseString(sb, 0, s.length() - 1);
        reverseEachWord(sb);
        return sb.toString();
    }

    private static void reverseEachWord(StringBuilder sb) {
        int i = 0, j = sb.length() - 1;
        while (i < j) {
            while (sb.charAt(i) != ' ') {
                i++;
            }
            reverseString(sb, i, j - 1);
            i = j + 1;
            j = i + 1;
        }
    }

    private static void reverseString(StringBuilder sb, int i, int j) {
        while (i < j) {
            char temp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(j));
            sb.setCharAt(j, temp);
        }
    }

    private static void trimString(StringBuilder s) {
        int i = 0, j = s.length() - 1;
        StringBuilder sb = new StringBuilder();
        while (s.charAt(i) != ' ') i++;
        while (s.charAt(j) != ' ') j--;
        while (i < j) {
            if (s.charAt(i) != ' ' || sb.charAt(sb.length() - 1) != ' ') {
                sb.append(s.charAt(i++));
            }
        }
    }
}
