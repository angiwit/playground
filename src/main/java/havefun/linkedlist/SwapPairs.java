package havefun.linkedlist;

public class SwapPairs {

    public static ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        return swapPairsCore(head);
    }

    public static ListNode swapPairsCore(ListNode head) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        ListNode result = head.next;
        while (temp != null && temp.next != null && temp.next.next != null) {
            ListNode first = temp.next;
            ListNode second = first.next;
            first.next = second.next;
            second.next = first;
            dummyHead.next = second;
            dummyHead = first;
            temp = second.next;
        }
        return result;
    }
}
