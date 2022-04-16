package havefun.greedy;

public class MonotoneIncreasingDigits {

    public static void main(String[] args) {
        System.out.println(monotoneIncreasingDigits(332));
    }

    public static int monotoneIncreasingDigits(int n) {
        if (n < 10) return n;
        return monotoneIncreasingDigitsCore(n);
    }

    public static int monotoneIncreasingDigitsCore(int n) {
        String ns = String.valueOf(n);
        StringBuilder ad = new StringBuilder(ns);
        int firstPlace = ns.length();
        for (int i = ns.length() - 1; i > 0; i--) {
            char cur = ad.charAt(i);
            char prev = ad.charAt(i - 1);
            if (prev > cur) {
                firstPlace = i;
                ad.setCharAt(i - 1, Character.forDigit(Character.getNumericValue(ns.charAt(i - 1)) - 1, 10));
            }
        }
        for (int i = firstPlace; i < ad.length(); i++) {
            ad.setCharAt(i, '9');
        }
        return Integer.parseInt(ad.toString());
    }
}
