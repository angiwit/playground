package havefun.linkedlist;

public class RemoveLinkedListElements {

    static public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        return removeElementsCore(head, val);
    }

    public static ListNode removeElementsCore(ListNode head, int val) {
        ListNode prev = null;
        ListNode aNewHead = null;
        while (head != null) {
            if (head.val == val) {
                if (prev != null) {
                    prev.next = head.next;
                }
                head = head.next;
            } else {
                if (aNewHead == null) aNewHead = head;
                prev = head;
                head = head.next;
            }
        }
        return aNewHead;
    }

    public static ListNode removeElementsCoreDummyHead(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null) {
            if (temp.next.val == val) {
                temp.next = temp.next.next;
            }
            temp = temp.next;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        int[] head = {1, 2, 6, 3, 4, 5, 6};
        ListNode listNode = new ListNode(head[0]);
        ListNode listNode1 = listNode;
        for (int i = 1; i < head.length; i++) {
            listNode1.next = new ListNode(head[i]);
            listNode1 = listNode1.next;
        }

        ListNode result = removeElements(listNode, 6);
        System.out.println(result.val);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

}

/**
 * Definition for singly-linked list.
 **/

