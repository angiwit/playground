package havefun.greedy;

import java.util.TreeMap;

/**
 * https://leetcode-cn.com/problems/lemonade-change/
 */
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
        TreeMap<Integer, Integer> count = new TreeMap<>();
        for (int i = 0; i < bills.length; i++) {
            Integer bill = count.get(bills[i]);
            if (bill == null) {
                count.put(bill, 1);
            } else {
                count.put(bills[i], bill + 1);
            }
            int change = bills[i] - 5;
            while (change > 0) {
                Integer changeKey = count.lowerKey(change);
                if (changeKey == null) {
                    return false;
                } else {
                    count.put(changeKey, count.get(changeKey) - 1);
                }
                change -= changeKey;
            }
        }
        return true;
    }
}
