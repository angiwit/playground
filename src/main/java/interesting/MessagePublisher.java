package interesting;

public class MessagePublisher implements Runnable {
    private String requestId;

    MessagePublisher(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public void run() {
        System.out.println("send to kafka!");
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
