package multithreading;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * When using ReentrantLock and need wait/notify operation, we need to use condition's await&signal/signalAll.
 */
public class ProducerConsumerUsingReentrantLock {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) {
        ProducerConsumerUsingReentrantLock sync = new ProducerConsumerUsingReentrantLock();
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
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("produced:" + i);
            queue.add(i);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public Integer consume() {
        try {
            lock.lock();
            while (queue.size() == 0) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Integer result = queue.remove();
            System.out.println("consumed:" + result);
            condition.signalAll();
            return result;
        } finally {
            lock.unlock();
        }
    }
}
