package havefun.greedy;

public class LemonadeChange {

    public static void main(String[] args) {
        int[] bills = {5, 5, 5, 10, 20};
//        int[] bills = {5, 5, 10, 10, 20};
//        int[] bills = {5, 5, 10, 20, 5, 5, 5, 5, 5, 5, 5, 5, 5, 10, 5, 5, 20, 5, 20, 5};
        System.out.println(lemonadeChange(bills));
    }

    public static boolean lemonadeChange(int[] bills) {
        if (bills == null || bills.length == 0) return false;
        return lemonadeChangeCore(bills);
    }

    public static boolean lemonadeChangeCore(int[] bills) {
        int fiveNo = 0;
        int tenNo = 0;
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] > 5 && fiveNo == 0 && tenNo == 0) {
                return false;
            }
            int expectedChange = bills[i] - 5;
            // have ten.
            int tenCount = Math.min(expectedChange / 10, tenNo);
            tenNo -= tenCount;
            expectedChange -= tenCount * 10;
            int fiveCount = Math.min(expectedChange / 5, fiveNo);
            fiveNo -= fiveCount;
            expectedChange -= fiveCount * 5;
            if (expectedChange > 0) {
                return false;
            }
            if (bills[i] == 5) fiveNo++;
            if (bills[i] == 10) tenNo++;
        }
        return true;
    }
}
