package havefun.tree;

import havefun.TreeNode;

public class IsSymmetric {

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return false;
        return isSymmetricCore(root.left, root.right);
    }

    public static boolean isSymmetricCore(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null && right != null) return false;
        if (right == null && left != null) return false;
        return left.val == right.val
                && isSymmetricCore(left.left, right.right)
                && isSymmetricCore(left.right, right.left);
    }
}
