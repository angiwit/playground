package multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Note the difference between synchronized(this) and synchronized(object),
 * one is this.wait/notify, another is object.wait/notify.
 */
public class ProducerConsumerUsingSynchronized {

    private static int MAX_CAPACITY = 3;
    private static List<Integer> queue = new ArrayList<>(MAX_CAPACITY);

    public static void main(String[] args) {
        ProducerConsumerUsingSynchronized sync = new ProducerConsumerUsingSynchronized();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                Integer next = new Random(System.currentTimeMillis()).nextInt(10);
                sync.producer(next);
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                sync.consumer();
            }
        }).start();
    }

    public void producer(Integer integer) {
        synchronized (queue) {
            while (queue.size() == 3) {
                try {
                    // release lock
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("produced:" + integer);
            queue.add(integer);
            queue.notifyAll();
        }
    }

    public Integer consumer() {
        synchronized (queue) {
            while (queue.size() == 0) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Integer result = queue.remove(0);
            System.out.println("consumed:" + result);
            queue.notifyAll();
            return result;
        }
    }
}
