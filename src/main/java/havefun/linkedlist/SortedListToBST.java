package havefun.linkedlist;

import havefun.TreeNode;

public class SortedListToBST {

    public static TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        return sortedListToBSTCore(head, null);
    }

    public static TreeNode sortedListToBSTCore(ListNode left, ListNode right) {
        ListNode fast = left, slow = left;
        while (fast != right && fast.next != right) {
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = sortedListToBSTCore(left, slow);
        root.right = sortedListToBSTCore(slow.next, right);
        return root;
    }

}
