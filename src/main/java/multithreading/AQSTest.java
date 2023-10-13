package multithreading;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 1. signal vs signalAll
 * 2. signalAll vs condition lock
 * 3. how a thread get a lock?
 * 4. how a thread waits for result via AQS?
 * 5. what's the use of tryAcquire? Is it blocking? How it implements blocking?
 * 6. what's the use of tryRelease in lock and semaphore?
 * 7. how a thread signal another thread(s)?
 */
public class AQSTest {

    public static void main(String[] args) {

    }

    class Sync extends AbstractQueuedSynchronizer {

        private int pending = 0;
        private int done = 1;

        @Override
        protected boolean tryAcquire(int arg) {
            return getState() == done;
        }

        @Override
        protected boolean tryRelease(int arg) {
            int state = getState();
            if (state == pending) {
                compareAndSetState(pending, done);
                return true;
            }
            return true;
        }
    }
}
