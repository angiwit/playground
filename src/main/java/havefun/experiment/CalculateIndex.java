package havefun.experiment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CalculateIndex {

    /**
     * Since array's index starts from 0, so given an index,
     * 1. the number of the right partition is size - index, index included, which means index is in the right partition.
     * 2. the number of the left partition equals index, index not included.
     *
     * @param args
     */
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            nums.add(i);
        }
        Random random = new Random();
        int rand = 0;
        while (rand < 56) {
            rand = random.nextInt(100);
        }
        System.out.println("nums size is:" + nums.size());
        System.out.println("rand is: " + rand);
        // result = k - (size - rand)
        System.out.println("new target is:" + (56 - (100 - rand)));
    }
}
