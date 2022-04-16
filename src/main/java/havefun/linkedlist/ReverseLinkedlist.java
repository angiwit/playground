package havefun.linkedlist;

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
//          next.next = cur;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}
