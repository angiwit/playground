package gc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Safe point or safe region is for GC. Default GC flag is set every 1000ms, so when main runs, after 1000ms global config is set.
 * When GC, a global 中断位 is set to true, all threads polls this flag, if true, threads will stop itself in the
 * nearest safe point or safe region. (When a thread wakes up, it checks this flag to determine if it should hang).
 * If any thread haven't reached to safe point, other threads needs to wait them......
 * Then threads can update OopMap, then JVM can move on to the next step of GC.
 * Reference: https://mp.weixin.qq.com/s/KDUccdLALWdjNBrFjVR74Q?utm_source=wechat_session&utm_medium=social&utm_oi=46376884895744
 */
public class SafePointAndSafeRegion {

    public static AtomicInteger num = new AtomicInteger(0);

    /**
     * safe point, *non counted loop return* and *method return* and *exception jump*.
     * safe region, a bigger safe point where thread status will not change.
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            /**
             * counted loop, no safe point set.
             */
            for (int i = 0; i < 200000000; i++) {
                num.getAndAdd(1);
            }
        };
        new Thread(runnable).start();
        new Thread(runnable).start();
        System.out.println("main thread begin to sleep");
        long start = System.currentTimeMillis();
        /**
         * thread sleep, safe region is SET, everytime exit the safe region, it checks if the
         * GC's enumeration is done, if not, it hangs in the safe region. Util the condition is true,
         * thread can exit the safe region.
         */
        Thread.sleep(1000L);
        System.out.println("main thread stopped sleep, slept:" + (System.currentTimeMillis() - start));
        System.out.println("num's result:" + num);
    }
}
/**
 * This can cause GC lag issue if some threads can't go to safe point very quick(GC enumeration not done),
 * then other threads needs to spin wait for them.
 * Two JVM arguments can be used to identify which threads are running slow to safe point:
 * -XX:+SafepointTimeout and -XX:SafepointTimeoutDelay=2000
 */
