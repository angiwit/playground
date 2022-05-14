package havefun.doublepointer;

import havefun.linkedlist.ListNode;

/**
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists-lcci/
 */
public class IntersectionNode {

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        return getIntersectionNodeCore(headA, headB);
    }

    public static ListNode getIntersectionNodeCore(ListNode headA, ListNode headB) {
        int headALength = 0;
        ListNode tempA = headA;
        while (tempA != null) {
            headALength++;
            tempA = tempA.next;
        }
        int headBLength = 0;
        ListNode tempB = headB;
        while (tempB != null) {
            headBLength++;
            tempB = tempB.next;
        }

        if (headBLength > headALength) {
            swap(headA, headB);
        }

        int distinct = Math.abs(headALength - headBLength);
        while (distinct > 0) {
            distinct--;
            headA = headA.next;
        }
//        if (headALength >= headBLength) {
//            int distinct = headALength - headBLength;
//            while (distinct > 0) {
//                distinct--;
//                headA = headA.next;
//            }
//        } else {
//            int distinct = headBLength - headALength;
//            while (distinct > 0) {
//                distinct--;
//                headB = headB.next;
//            }
//        }
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    private static void swap(ListNode a, ListNode b) {
        ListNode temp = a;
        a = b;
        b = temp;
    }
}
