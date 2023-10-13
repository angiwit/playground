package havefun.linkedlist;

import java.util.List;

//https://leetcode.cn/problems/reverse-nodes-in-k-group/
public class ReverseKGroup {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        System.out.println(reverseKGroup(head, 2));
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode prev = null;
        ListNode curStart = head;
        ListNode curEnd = head;
        while (curEnd != null) {
            // find first end node.
            int i = 0;
            while (curEnd.next != null && i < k - 1) {
                curEnd = curEnd.next;
                i++;
            }
            if (curEnd != null) {
                ListNode next = curEnd.next;
                // swap with prev.
                curEnd.next = prev;
                prev = curStart;
                curStart = next;
                curEnd = next;
            }
        }
        return prev;
    }

    public static ListNode reverseKGroupCorrect(ListNode head, int k) {
        ListNode hair = new ListNode(-1);
        hair.next = head;
        ListNode prev = hair;
        while (head != null) {
            ListNode tail = head;
            for (int i = 0; i < k - 1; i++) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            ListNode next = tail.next;
            ListNode[] values = reverseGroup(head, tail);
            ListNode headNew = values[0];
            ListNode tailNew = values[1];

            prev.next = headNew;
            tailNew.next = next;
            prev = tailNew;
            head = next;
        }
        return hair.next;
    }


    public static ListNode[] reverseGroup(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode p = head;
        while (prev != tail) {
            ListNode nex = p.next;
            p.next = prev;
            prev = p;
            p = nex;
        }
        return new ListNode[]{tail, head};
    }
    

    public ListNode reverseKGroup1(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode prev = dummy;
        ListNode end = dummy;
        
        while (end.next != null) {
            for (int i = 0; i < k - 1 && end != null; i++) end = end.next;
            if (end == null) break;
            ListNode start = prev.next; 
            ListNode next = end.next;

            end.next = null;
            prev.next = reverseGroupInternal(start); 
            start.next = next;
            prev = start; // change prev to next head before node, so that in line90 the prev.next can get the correct start node.

            end = next;                        
        }
        return dummy.next;
        
    }

    private ListNode reverseGroupInternal(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            /**
             * we don't need the below line, the reason is the cur.next = prev will always connect the next node to it's previous node.
             * since the previous node is assigned to last round current node(previous node) already.
             */
            // next.next = cur; 
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}
