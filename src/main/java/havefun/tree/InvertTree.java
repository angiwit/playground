package havefun.tree;

import havefun.TreeNode;

public class InvertTree {

    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        return invertTreeCore(root);
    }

    public static TreeNode invertTreeCore(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTreeCore(root.left);
        invertTreeCore(root.right);
        return root;
    }
}
