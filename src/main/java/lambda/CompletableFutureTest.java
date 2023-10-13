package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureTest {

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "done";
        });
        CompletableFuture<String> result = future.thenApply(x -> {
            List<String> temp = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                temp.add("done");
            }
            return String.join(" ", temp);
        });
        System.out.println(result.get());
    }
}
