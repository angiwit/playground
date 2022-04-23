package havefun.tree;

import havefun.TreeNode;

import java.util.*;

public class TreeTraversalIteration {

    public static void preOrderTraversalIteration(TreeNode treeNode) {
        if (treeNode == null) return;
        List<Integer> result = preOrderTraversalIterationCore(treeNode);
    }

    public static List<List<Integer>> levelOrder(TreeNode treeNode) {
        if (treeNode == null) return new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> level = new ArrayList<>();
        TreeNode endNode = new TreeNode(-1, null, null);
        queue.offer(treeNode);
        queue.offer(endNode);
        while (queue.size() > 0) {
            TreeNode temp = queue.poll();
            if (temp == endNode) {
                result.add(new ArrayList<>(level));
                level.clear();
                if (queue.size() > 0) {
                    queue.offer(endNode);
                }
            } else {
                level.add(temp.val);
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
            }
        }
        return result;
    }

    public static List<Integer> preOrderTraversalIterationCore(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(treeNode);
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return result;
    }

    public static List<Integer> middleOrderTraversalIterationCore(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        TreeNode node = treeNode;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                TreeNode pop = stack.pop();
                result.add(pop.val);
                node = pop.right;
            }
        }
        return result;
    }

    /**
     * left->right->root = reverse(root->right->left) = transformed(preOrderTraversal)
     *
     * @param treeNode
     * @return
     */
    public static List<Integer> postOrderTraversalIterationCore(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(treeNode);
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }
        return reverse(result);
    }

    private static List<Integer> reverse(List<Integer> result) {
        int i = 0;
        int j = result.size() - 1;
        while (i < j) {
            int left = result.get(i);
            int right = result.get(j);
            int temp = left;
            result.add(i, right);
            result.add(j, temp);
        }
        return result;
    }
}
