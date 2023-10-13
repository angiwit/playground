package havefun.string;

public class ReverseString {

    public static void reverseString(char[] s) {
        if (s.length == 0) return;
        reverseStringCore(s, 0, s.length - 1);
    }

    public static void reverseStringCore(char[] s, int left, int right) {
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        reverseString(s);
        System.out.println(s);
    }
}
