package havefun.greedy;

import java.util.Arrays;

public class Candy {

    public static void main(String[] args) {
        int[] ratings = {1, 0, 2};
        System.out.println(candy(ratings));
    }

    public static int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;
        return candyCore(ratings);
    }

    /**
     * Only if you're finding an ascending range, otherwise there's no need to use while loop to
     * record the start and end index.
     * This as an example, we need to calculate an ascending range sum, then use an array to record
     * each value like result[i + 1] = result[i] + 1 is much more simple.
     */
    public static int candyCore(int[] ratings) {
        int[] result = new int[ratings.length];
        Arrays.fill(result, 1);
        for (int i = 0; i < ratings.length - 1; i++) {
            if (ratings[i + 1] > ratings[i]) {
                result[i + 1] = result[i] + 1;
            }
        }
        for (int i = ratings.length - 1; i > 0; i--) {
            if (ratings[i - 1] > ratings[i]) {
                result[i - 1] = Math.max(result[i] + 1, result[i - 1]);
            }
        }
        return Arrays.stream(result).sum();
    }
}
