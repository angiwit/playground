package forkjoinpool;

import java.util.stream.IntStream;

public class DefaultParallelPerformance {

    /**
     * ForkJoinPool creates core - 1 threads to run tasks by default,
     * If using BlockManager, then at most 2 * core threads will be created.
     */
    public static void main(String[] args) {
        IntStream.range(1, 100)
                .parallel()
                .forEach(x -> {
                    System.out.println(sleep());
                });
    }

    public static Integer sleep() {
        try {
            Thread.sleep(1000L);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
