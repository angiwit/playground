package forkjoinpool;

import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

public class OptimizedParallelPerformance {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(Runtime.getRuntime().availableProcessors());
        IntStream.range(1, 100)
                .parallel()
                .forEach(x -> {
                    BlockingTasks.callInManagedBlock(() -> sleep());
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
