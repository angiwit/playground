package forkjoinpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

/**
 * Non blocking task still uses all thread defined in the customized threadpool.
 */
public class CustomizedExecutorNonBlockingTaskPerformance {
    private static final ExecutorService executor = Executors.newFixedThreadPool(100);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Future<Integer>> futures = new ArrayList<>();
        IntStream.range(1, 100)
                .parallel()
                .forEach(x -> futures.add(executor.submit(() -> calculate(x))));
        for (Future<Integer> future : futures) {
            System.out.println(future.get());
        }
        executor.shutdown();
    }

    public static Integer calculate(Integer x) {
        System.out.println(Thread.currentThread().getName());
        return x * 2;
    }
}
