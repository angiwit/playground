package havefun.tree;

import havefun.TreeNode;

import java.util.*;

public class ZigzagTreeTraverse {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return null;
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean order = true;
        while (!queue.isEmpty()) {
            Deque<Integer> deque = new ArrayDeque<>();
            for (int i = 0; i < queue.size(); i++) {
                TreeNode node = queue.poll();
                if (order) {
                    deque.offerLast(node.val);
                } else {
                    deque.offerFirst(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(new ArrayList<>(deque));
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
