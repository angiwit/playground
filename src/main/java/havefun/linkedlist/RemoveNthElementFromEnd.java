package havefun.linkedlist;

public class RemoveNthElementFromEnd {

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        return removeNthFromEndCore(head, n);
    }

    // 1 -> 2 -> 3 -> null
    public static ListNode removeNthFromEndCore(ListNode head, int n) {
        ListNode curr = head;
        int i = 0;
        while (curr != null && i < n + 1) {
            curr = curr.next;
            i++;
        }
        if (i < n + 1) return head;
        ListNode second = head;
        while (curr != null) {
            curr = curr.next;
            second = second.next;
        }
        second.next = second.next.next;
        return head;
    }

    public static ListNode removeNthFromEndCoreDummyHead(ListNode head, int n) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode fast = dummyHead;
        ListNode slow = dummyHead;
        int i = 0;
        while (i++ < n) {
            fast = fast.next;
        }
        ListNode prev = null;
        while (fast != null) {
            prev = slow;
            fast = fast.next;
            slow = slow.next;
        }
        prev.next = slow.next;
        return dummyHead.next;
    }
}
