package bloomfilter;

import java.util.BitSet;

/**
 * BitSet: https://www.baeldung.com/java-bitset
 * Why using long[] in BitSet since long type has more space consuming than boolean?
 * The reason is a long can be flatted to 64 bits in memory, and every bit can be used to store a 1 or 0.
 * But for boolean type, it consumes 8 bits in memory, but 8 bits can only represent one value: true or false.
 * So using one bit to store a 1 to represent the existence instead of 8 bits to represent existence is more
 * spacing saving.
 * <p>
 * 1. The long[] is flatted into bits in memory.
 * 2. The long[] is extendable in memory if the space is not enough to store a value.
 */
public class BitMapTest {

    public static void main(String[] args) {
        findExistsOfPhoneNumber();
        bitMapSort();
    }

    public static void findExistsOfPhoneNumber() {
        int[] nums = {1, 2, 3, 10, 1000, 50000, 1000000, 161651013};
        BitSet bitSet = new BitSet(7);
        for (int i = 0; i < nums.length; i++) {
            bitSet.set(nums[i], true);
        }
        System.out.println(bitSet.size());
        System.out.println(bitSet.get(3));
        System.out.println(bitSet.get(5));
    }

    public static void bitMapSort() {
        int[] nums = {1, 2, 3, 10, 1000, 50000, 1000000, 161651013};
        BitSet bitSet = new BitSet(7);
        for (int i = 0; i < nums.length; i++) {
            bitSet.set(nums[i], true);
        }
        for (int i = 0; i < bitSet.size(); i++) {
            if (bitSet.get(i) == true) System.out.println(i);
        }
    }
}
