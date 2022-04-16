package havefun.array;

import java.util.HashMap;
import java.util.Map;

public class NumberOfSteps {

    public static int numberOfSteps(int num) {
        if (num == 0) return 0;
        Map<Integer, Integer> temps = new HashMap<>();
        return numberOfStepsCore2(num, 0, temps);
    }

    public static int numberOfStepsCore(int num, int result) {
        if (num == 0) return result;
        if (num % 2 == 0) {
            result = 1 + numberOfStepsCore(num / 2, result);
        } else {
            result = 1 + numberOfStepsCore(num - 1, result);
        }
        return result;
    }

    /**
     * if odd, minus 1 and then divide 2, it's 2 steps. If even, divide 2, it's 1 step, when number == 1, only 1 step.
     * A number minus 1 and divide 2: 10010001 - 1 = 10010000, divide 2: 10010000 >>= 1. which equals to 10010001 >>= 1.
     *
     * @param num
     * @return
     */
    public static int numberOfStepsCore1(int num) {
        int result = 0;
        while (num > 0) {
            result += (num > 1 ? 1 : 0) + (num & 0x01);
            num >>= 1;
        }
        return result;
    }

    /**
     * If the question changes to when it's odd, you can add 1 or minus 1, then how to solve this problem?
     * This approach can cause stackoverflowexception when calculating hashCode for a big number.
     * <p>
     * This is a typical recursive solution, calculate addup, then minusDown, then choose the small one.
     * Remember when calculating the addup, the state should NOT be changed, e.g. num value should keep stable.
     *
     * @param num
     * @return
     */
    public static int numberOfStepsCore2(int num, int result, Map<Integer, Integer> temps) {
        if (num == 2 || num == 1) return result + 1;
        if (num == 0) return result;
        if ((num & 0x01) == 1) {//odd
            int addUp, minusDown;
            if (temps.get(num + 1) != null) {
                addUp = temps.get(num + 1);
            } else {
                addUp = numberOfStepsCore2(num + 1, result, temps);
                temps.put(num + 1, addUp);
            }
            if (temps.get(num - 1) != null) {
                minusDown = temps.get(num - 1);
            } else {
                minusDown = numberOfStepsCore2(num - 1, result, temps);
                temps.put(num - 1, minusDown);
            }
            result = 1 + Math.min(addUp, minusDown);
        } else {
            int divide2;
            if (temps.get(num / 2) != null) {
                divide2 = temps.get(num / 2);
            } else {
                divide2 = numberOfStepsCore2(num / 2, result, temps);
                temps.put(num / 2, divide2);
            }
            result = 1 + divide2;
        }
        return result;
    }

    /**
     * Calculate via bit, when there's three leading 1, add 1, otherwise minus 1.
     *
     * @param num
     * @return
     */
    public static int numberOfStepsCore3(int num) {
        int result = 0;
        while (num > 0) {
            int oddEven = num & 0x1;
            if (oddEven == 1) {
                int leading0 = num & 0x7;
                if (leading0 == 1) {
                    num = num + 1;
                } else {
                    num = num - 1;
                }
            } else {
                num = num >>= 1;
            }
            result += 1;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(numberOfSteps(14));
        System.out.println(numberOfSteps(8));
        System.out.println(numberOfSteps(123));

    }
}
