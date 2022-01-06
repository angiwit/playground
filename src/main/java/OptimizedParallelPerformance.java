import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class OptimizedParallelPerformance {

    private static final ExecutorService executor = Executors.newFixedThreadPool(100);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Thread thread = Thread.currentThread();
//        System.out.println(thread.getName());
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//            }
//        }).start();
        System.out.println(Runtime.getRuntime().availableProcessors());
        long start = System.currentTimeMillis();
//        IntStream.range(1, 100)
//                .parallel()
//                .forEach(x -> {
//                    BlockingTasks.callInManagedBlock(() -> sleep());
//                });
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
