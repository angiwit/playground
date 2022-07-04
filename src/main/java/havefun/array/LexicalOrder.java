package havefun.array;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/lexicographical-numbers/
 */
public class LexicalOrder {

    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>();
        int number = 1;
        for (int i = 0; i < n; i++) {
            result.add(number);
            if (number * 10 < n) {
                number *= 10;
            } else {
                while (number % 10 == 9 || number / 10 + 1 > n) {
                    number /= 10;
                }
                number++;
            }
        }
        return result;
    }

}
