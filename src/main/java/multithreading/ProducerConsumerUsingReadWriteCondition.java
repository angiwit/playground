package multithreading;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Two condition approach, thus everytime the signal will always signal to the right thread.
 */
public class ProducerConsumerUsingReadWriteCondition {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition producer = lock.newCondition();
    private static Condition consumer = lock.newCondition();
    private static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) {
        ProducerConsumerUsingReadWriteCondition sync = new ProducerConsumerUsingReadWriteCondition();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                Integer next = new Random(System.currentTimeMillis()).nextInt(10);
                sync.produce(next);
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                sync.consume();
            }
        }).start();
    }

    public void produce(Integer i) {
        try {
            lock.lock();
            while (queue.size() == 3) {
                try {
                    // release the lock
                    producer.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("produced:" + i);
            queue.add(i);
            consumer.signal();
        } finally {
            lock.unlock();
        }
    }

    public Integer consume() {
        try {
            lock.lock();
            while (queue.size() == 0) {
                try {
                    consumer.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Integer result = queue.remove();
            System.out.println("consumed:" + result);
            producer.signal();
            return result;
        } finally {
            lock.unlock();
        }
    }
}
