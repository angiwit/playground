package havefun.tree;

import havefun.TreeNode;

public class InvertTree {

    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        return invertTreeCore(root);
    }

    public static TreeNode invertTreeCore(TreeNode root) {
        if (root.left != null || root.right != null) {
            TreeNode left = root.left;
            root.left = root.right;
            root.right = left;
        }
        if (root.left != null) invertTreeCore(root.left);
        if (root.right != null) invertTreeCore(root.right);
        return root;
    }
}
