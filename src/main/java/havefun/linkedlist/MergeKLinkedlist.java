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

    public ListNode mergeKLists0(ListNode[] lists) {
        if (lists.length == 0) return null;
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int start, int end) {
        if (start == end) return lists[start];
        int mid = (start + end) / 2;
        ListNode one = merge(lists, start, mid);
        ListNode two = merge(lists, mid + 1, end);
        return mergeTwo(one, two);
    }

    private ListNode mergeTwo(ListNode one, ListNode two) {
        if (one == null) return two;
        if (two == null) return one;
        if (one.val <= two.val) {
            one.next = mergeTwo(one.next, two);
            return one;
        } else {
            two.next = mergeTwo(one, two.next);
            return two;
        }
    }
}
