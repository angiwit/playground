package havefun.greedy;

//https://leetcode.cn/problems/gas-station/
public class CanCompleteCircuit {

    public static void main(String[] args) {
        int[] gas = {2, 3, 4};
        int[] cost = {3, 4, 3};
        System.out.println(canCompleteCircuit(gas, cost));
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || gas.length == 0 || cost == null || cost.length == 0) {
            return -1;
        }
        return canCompleteCircuitCore(gas, cost);
    }

    public static int canCompleteCircuitCore(int[] gas, int[] cost) {
        int count = 0;
        int start = -1;
        int left = 0;
        for (int i = 0; i < 2 * gas.length + 1; i++) {
            int realIndex = i % gas.length;
            left = left + gas[realIndex] - cost[realIndex];
            count++;
            if (left < 0) {
                count = 0;
                left = 0;
                start = -1;
                continue;
            } else {
                if (start == -1) {
                    start = realIndex;
                }
            }
            if (count == gas.length + 1) {
                return start;
            }
        }
        return -1;
    }
}
