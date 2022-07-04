package havefun.tree;

import havefun.TreeNode;

import java.util.Stack;

public class IsValidBST {
    private static int prev = Integer.MIN_VALUE;

    public static boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST(root.left)) {
            return false;
        }
        if (prev >= root.val) {
            return false;
        }
        prev = root.val;
        return isValidBST(root.right);
    }

    public boolean isValidBSTIteration(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        int prev = Integer.MIN_VALUE;
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode pop = stack.pop();
                if (prev >= pop.val) {
                    return false;
                }
                root = pop.right;
                prev = pop.val;
            }
        }
        return true;
    }
}
