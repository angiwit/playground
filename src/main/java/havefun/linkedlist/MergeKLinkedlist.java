package havefun.linkedlist;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKLinkedlist {

    public static void main(String[] args) {
        Queue<Integer> queue = new PriorityQueue<>();
        queue.offer(4);
        queue.offer(3);
        queue.offer(2);
        queue.offer(1);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode node : lists) {
            queue.offer(node);
        }
        ListNode dummyHead = new ListNode(-1);
        ListNode temp = dummyHead;
        while (!queue.isEmpty()) {
            ListNode min = queue.poll();
            if (min.next != null) {
                queue.offer(min.next);
            }
            temp.next = new ListNode(min.val);
            temp = temp.next;
        }
        return dummyHead.next;
    }
}
