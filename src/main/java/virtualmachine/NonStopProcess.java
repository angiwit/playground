package virtualmachine;

public class NonStopProcess {

    public static void main(String[] args) {
        while (true) {
            try {
                sleep();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void sleep() throws InterruptedException {
        Thread.sleep(1000L);
        System.out.println("1");
    }
}
