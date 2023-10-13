package havefun.doublepointer;

import havefun.linkedlist.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 */
public class DetectCycle {

    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            if (fast == slow) {
                ListNode ptr = head;
                while (slow != ptr) {
                    slow = slow.next;
                    ptr = ptr.next;
                }
                return slow;
            }
        }
        return null;
    }

    public ListNode detectCycleHash(ListNode head) {
        Set<ListNode> vector = new HashSet<>();
        while (head != null) {
            if (vector.contains(head)) {
                return head;
            } else {
                vector.add(head);
            }
            head = head.next;
        }
        return null;
    }
}
