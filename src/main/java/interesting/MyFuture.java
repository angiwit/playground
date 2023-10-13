package interesting;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class MyFuture<T> {

    private Sync sync;
    private T result;

    public MyFuture() {
        this.sync = new Sync();
    }

    public void done(T result) {
        sync.release(1);
        this.result = result;
    }

    public boolean isDone() {
        return sync.isDone();
    }

    public T get() {
        //抢占锁来执行任务, 方法在抢占成功时返回
        sync.acquire(-1);
        if (result != null) return result;
        return null;
    }

    static class Sync extends AbstractQueuedSynchronizer {

        private static final long serialVersionUID = 1L;

        //future status
        private final int done = 1;
        private final int pending = 0;

        @Override
        protected boolean tryAcquire(int arg) {
            return getState() == done;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == pending) {
                if (compareAndSetState(pending, done)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        }

        public boolean isDone() {
            return getState() == done;
        }
    }
}
