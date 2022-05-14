package havefun.tree;

import havefun.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ZigzagTreeTraverse {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return null;
        List<List<Integer>> result = new ArrayList<>();
        ArrayDeque<TreeNode> deque1 = new ArrayDeque<>();
        ArrayDeque<TreeNode> deque2 = new ArrayDeque<>();
        deque1.addLast(root);
        boolean left2Right = true;
        while (!deque1.isEmpty() || !deque2.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            if (left2Right) {
                int length = deque1.size();
                while (length > 0) {
                    TreeNode node = deque1.removeFirst();
                    temp.add(node.val);
                    if (node.left != null) deque2.addLast(root.left);
                    if (node.right != null) deque2.addLast(root.right);
                }
            } else {
                int length = deque2.size();
                while (length > 0) {
                    TreeNode node = deque2.removeLast();
                    if (node.left != null) deque1.addLast(root.left);
                    if (node.right != null) deque1.addLast(root.right);
                }
            }
            result.add(temp);
        }
        return result;
    }

    public List<List<Integer>> zigzagLevelOrderAnotherWay(TreeNode node) {
        if (node == null) return null;
        boolean flag = true;
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.add(node);
        deque.add(null);
        List<List<Integer>> result = new ArrayList<>();
        while (!deque.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            if (flag) {
                TreeNode temp = deque.removeFirst();
                if (temp != null) {
                    list.add(temp.val);
                    if (temp.left != null) {
                        deque.offerLast(temp.left);
                    }
                    if (temp.right != null) {
                        deque.offerLast(temp.right);
                    }
                } else {
                    result.add(list);
                    flag = !flag;
                }
            } else {
                TreeNode temp = deque.removeLast();
                if (temp != null) {
                    list.add(temp.val);
                    if (temp.right != null) {
                        deque.offerFirst(temp.right);
                    }
                    if (temp.left != null) {
                        deque.offerFirst(temp.left);
                    }
                } else {
                    result.add(list);
                    flag = !flag;
                }
            }
        }
        return result;
    }
}
