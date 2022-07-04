package havefun.linkedlist;

public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        int carry = 0;
        ListNode dummyHead = new ListNode(-1);
        ListNode prev = dummyHead;
        while (l1 != null || l2 != null) {
            int l1v = l1 == null ? 0 : l1.val;
            int l2v = l2 == null ? 0 : l2.val;
            int product = l1v + l2v + carry;
            carry = product / 10;
            ListNode curr = new ListNode(product % 10);
            prev.next = curr;
            prev = curr;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry != 0) prev.next = new ListNode(carry);
        return dummyHead.next;
    }

    public static void main(String[] args) {

    }
}
