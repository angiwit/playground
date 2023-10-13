package havefun.array;

public class ReverseInt {

    /**
     * Note that here we have to use (MIN_VALUE or MAX_VALUE) / 10 to check, instead of result * 10, since
     * after result * 10, the number may already overflow.
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            if (result < Integer.MIN_VALUE / 10 || result > Integer.MAX_VALUE / 10) {
                return 0;
            }
            int digit = x % 10;
            result = result * 10 + digit;
            x = x / 10;
        }
        return result;
    }
}
