package havefun.backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/24-game/
 */
public class JudgePoint24 {

    private static final String[] ops1 = {"ADD", "SUB1", "SUB2", "MUL", "DIV1", "DIV2"};
    private static final String[] ops2 = {"ADD", "SUB", "MUL", "DIV"};

    // operations can be replaced to number, it's more easy to write the code.
    private static final int ADD = 0;
    private static final int MUL = 1;
    private static final int SUB = 2;
    private static final int DIV = 3;

    private static final Double SMALL_VALUE = 0.000006D;

    /**
     * 当操作数为减的时候，组合一共有两种，一种是num[i] - num[i + 1], 一种是num[i + 1] - num[i]，如何才能保证两种组合都被遍历到？
     * 有两种方法，一种是对减法定义两种操作符，SUB1代表前一个数减去后一个数，SUB2代表后一个数减去前一个数。
     * 第二种方法是使用双重for循环，第二层for循环从0开始，这样第一层for循环到了下一个数的时候，会作为被减数来减去前一个数即num[i + 1] - num[i]
     *
     * @param cards
     * @return
     */
    public static boolean judgePoint24(int[] cards) {
        List<Double> deque = new ArrayList<>();
        for (int i = 0; i < cards.length; i++) {
            deque.add((double) cards[i]);
        }

        return judgePoint24Core(deque);
    }

    /**
     * For ADD and MUL, the order doesn't matter, so in the for loop, there's duplicate calculations.
     *
     * @param list
     * @return
     */
    private static boolean judgePoint24Core(List<Double> list) {
        if (list.size() <= SMALL_VALUE)
            return false; // means last time the list one value is not 24. If it was 24, the method is returned already
        if (list.size() == 1 && list.get(0) - 24 <= SMALL_VALUE) return true;

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {

                if (i != j) { // skip the calculation when i == j.
                    List<Double> list2 = new ArrayList<>();
                    for (int k = 0; k < list.size(); k++) {
                        if (k != i && k != j) {
                            list2.add(list.get(k));
                        }
                    }

                    for (int k = 0; k < 4; k++) {
                        if ((ops2[k].equals("ADD") || ops2[k].equals("MUL")) && i > j)
                            continue; // means it's backward calculation, num[i + 1] + num[i].
                        if (ops2[k].equals("ADD")) {
                            list2.add(list.get(i) + list.get(j));
                        } else if (ops2[k].equals("SUB")) {
                            list2.add(list.get(i) - list.get(j));
                        } else if (ops2[k].equals("MUL")) {
                            list2.add(list.get(i) * list.get(j));
                        } else if (ops2[k].equals("DIV")) {
                            if (list.get(j) <= SMALL_VALUE) {
                                continue;
                            } else {
                                list2.add(list.get(i) / list.get(j));
                            }
                        }
                        if (judgePoint24Core(list2)) return true;
                        list2.remove(list2.size() - 1);
                    }
                }

            }
        }
        return false;
    }
}
