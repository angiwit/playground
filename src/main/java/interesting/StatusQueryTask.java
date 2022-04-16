package interesting;

import java.util.concurrent.Callable;

public class StatusQueryTask implements Callable<String> {

    private String requestId;

    public StatusQueryTask(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public String call() throws Exception {
        System.out.println("long polling with retry!");
        Thread.sleep(1000L);
        return requestId;
    }
}
