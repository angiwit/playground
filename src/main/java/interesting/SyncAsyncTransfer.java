package interesting;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class SyncAsyncTransfer<T> {

    /**
     * long polling
     *
     * @param requestId
     * @return
     */
    public static String request(String requestId) {
        MessagePublisher task = new MessagePublisher(requestId);
        task.run();
        StatusQueryTask statusQueryTask = new StatusQueryTask(requestId);
        try {
            String result = new FutureTask<>(statusQueryTask).get();
            return result;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * custom future.
     */
    public static Map<String, MyFuture<Object>> pendingFutures = new ConcurrentHashMap<>();

    public static <T> void callback(String requestId, T result) {
        MyFuture<Object> future = pendingFutures.get(requestId);
        future.done(result);
    }

    public static String requestAQS(String requestId) {
        MessagePublisher task = new MessagePublisher(requestId);
        task.run();
        pendingFutures.put(requestId, new MyFuture<>());
        return (String) pendingFutures.get(requestId).get();
    }


}
