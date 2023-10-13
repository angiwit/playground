package havefun.array;

/**
 * https://leetcode.cn/problems/shifting-letters/
 */
public class ShiftingLetters {

    /**
     * https://blog.csdn.net/qq_37499840/article/details/89047554
     *
     * @param s
     * @param shifts
     * @return
     */
    public static String shiftingLetters(String s, int[] shifts) {
        StringBuilder sb = new StringBuilder();
        int x = 0;
        for (int i = 0; i < shifts.length; i++) {
            x = (x + shifts[i]) % 26;
        }
        for (int i = 0; i < s.length(); i++) {
            int t = s.charAt(i) - 'a';
            sb.append((t + x) % 26 + 97);
            x = Math.floorMod(x - shifts[i], 26);
        }
        return sb.toString();
    }

    public static String shiftingLetters0(String s, int[] shifts) {
        StringBuilder sb = new StringBuilder();
        int n = shifts.length;
        for (int i = n - 2; i >= 0; i--) {
            shifts[i] = (shifts[i + 1] + shifts[i]) % 26;
        }
        char[] temp = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            int num = temp[i] - 'a';
            sb.append((char) ((num + shifts[i]) % 26 + 97));

        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "aaa";
        int[] shifts = {1, 2, 3};
        System.out.println(shiftingLetters0(s, shifts));
    }
}
