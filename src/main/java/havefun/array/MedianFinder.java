package havefun.array;

import java.util.PriorityQueue;
import java.util.Queue;

public class MedianFinder {

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
    }

    private static final Queue<Integer> min = new PriorityQueue<>();
    private static final Queue<Integer> max = new PriorityQueue<>((o1, o2) -> o2 - o1);

    public MedianFinder() {
        min.clear();
        max.clear();
    }

    public void addNum(int num) {
        if (max.size() == 0) {
            max.offer(num);
            return;
        }
        if (max.peek() > num) {
            max.offer(num);
            if (max.size() - 2 == min.size()) {
                min.offer(max.poll());
            }
        } else {
            min.offer(num);
            if (min.size() > max.size()) {
                max.offer(min.poll());
            }
        }
    }

    public double findMedian() {
        if (max.size() == 0) return 0;
        if (max.size() > min.size()) {
            return max.peek();
        }
        return (max.peek() + min.peek()) / 2.0d;
    }
}
