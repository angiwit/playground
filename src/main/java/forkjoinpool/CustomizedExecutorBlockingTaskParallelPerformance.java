package forkjoinpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

/**
 * If using customized executor, all threads will be used when running blocking tasks.
 */
public class CustomizedExecutorBlockingTaskParallelPerformance {

    private static final ExecutorService executor = Executors.newFixedThreadPool(100);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        List<Future<Integer>> futures = new ArrayList<>();
        IntStream.range(1, 100)
                .forEach(x -> {
                    futures.add(executor.submit(() -> sleep()));
                });
        for (Future<Integer> future : futures) {
            System.out.println(future.get());
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        executor.shutdown();
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
