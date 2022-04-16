package havefun.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class AvoidFlood {

    public static void main(String[] args) {
        int[] rains = {1, 2, 0, 0, 2, 1};
        int[] result = avoidFlood(rains);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    public static int[] avoidFlood(int[] rains) {
        if (rains == null || rains.length == 0) return new int[0];
        return avoidFloodCore(rains);
    }

    public static int[] avoidFloodCore(int[] rains) {
        Map<Integer, Integer> recentFullDate = new HashMap<>();
        TreeSet<Integer> canPumpDate = new TreeSet<>();
        int[] result = new int[rains.length];
        Arrays.fill(result, -1);
        for (int i = 0; i < rains.length; i++) {
            if (rains[i] == 0) {
                canPumpDate.add(i);
                result[i] = 1;
                continue;
            }
            if (!recentFullDate.containsKey(rains[i])) {
                recentFullDate.put(rains[i], i);
                continue;
            }
            Integer pumpDay = canPumpDate.higher(recentFullDate.get(rains[i]));
            if (pumpDay == null) return new int[0];
            result[pumpDay] = rains[i];
            recentFullDate.put(rains[i], i);
            canPumpDate.remove(pumpDay);
        }
        return result;
    }
}
