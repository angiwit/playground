package havefun.linkedlist;

import org.checkerframework.checker.units.qual.K;

public class ReverseLinkedlist {

    public static void main(String[] args) {

    }

    public static ListNode reverseList(ListNode head) {
        if (head == null) return null;
        return reverseListCore(head);
    }

    public static ListNode reverseListCore(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            next.next = cur;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    // havn't verified this approach.
    public ListNode reverseLinkedList(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        while (dummyHead.next != null && dummyHead.next.next != null) {
            ListNode first  = dummyHead.next;
            ListNode second  = dummyHead.next.next;
            
            if (second.next != null) {
                first.next = second.next;
            } else {
                first.next = null;
            }
            second.next = first;
            dummyHead.next = second;
            
            dummyHead = dummyHead.next;
        }
        return dummyHead;
    }
}
