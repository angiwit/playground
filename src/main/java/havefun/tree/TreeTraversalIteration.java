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
        List<Integer> result = new ArrayList<>();
        stack.push(treeNode);
        while (stack.size() > 0) {
            TreeNode node = stack.pop();
            result.add(node.val);
            // Add right to stack first, then left, when popping, the order is: root -> left -> right.
            // When popping out an element, check its left and right, stack makes this naturally recursive.
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return result;
    }

    public static List<Integer> middleOrderTraversalIterationCore(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        TreeNode temp = treeNode;
        while (temp != null || stack.size() > 0) {
            if (temp != null) {
                stack.push(temp);
                temp = temp.left;
            } else {
                TreeNode pop = stack.pop();
                result.add(pop.val);
                temp = temp.right;
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
        List<Integer> result = new ArrayList<>();
        stack.push(treeNode);
        while (stack.size() > 0) {
            // recursively add and pop elements.
            TreeNode temp = stack.pop();
            result.add(temp.val);
            if (temp.left != null) {
                stack.push(temp.left);
            }
            if (temp.right != null) {
                stack.push(temp.right);
            }
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
