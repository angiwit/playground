package virtualmachine;

public class SimpleTest {

    public static void main(String[] args) {
        test1();
        test(100);
        test2();
    }

    public static void test1() {
        int i = 10;
        test(i);
        /**
         * A BIPUSH and ISTORE is executed so the value in local variable table changed.
         */
        i = 30;
        System.out.println(i);
    }

    /**
     * L0
     * LINENUMBER 17 L0
     * BIPUSH 20
     * ISTORE 0
     * L1
     * LINENUMBER 18 L1
     * GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
     * ILOAD 0
     * INVOKEVIRTUAL java/io/PrintStream.println (I)V
     * <p>
     * A new local variable is created for i, so it won't affect the passing number.
     *
     * @param i
     */
    public static void test(int i) {
        i = 20;
        System.out.println(i);
    }

    public static void test2() {
        int i = 0;
        i++;
        System.out.println(i);
    }
}
