package havefun.tree;

import havefun.TreeNode;

public class IsSymmetric {

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return false;
        return isSymmetricCore(root.left, root.right);
    }

    public static boolean isSymmetricCore(TreeNode left, TreeNode right) {
        /**
         * If left child is null in tree1 and right child is null in tree2, then this still is a symmetric tree.
         * So we can return true if left == null && right == null.
         */
        if (left == null && right == null) return true;
        if (left == null && right != null) return false;
        if (left != null && right == null) return false;
        return left.val == right.val
                && isSymmetricCore(left.left, right.right)
                && isSymmetricCore(left.right, right.left);
    }
}
