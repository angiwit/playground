package havefun.tree;

import havefun.TreeNode;

public class LowestCommonAncestor {

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        return lowestCommonAncestorCore(root, p, q);
    }

    public static TreeNode lowestCommonAncestorCore(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (nodeInTree(root.left, p) && nodeInTree(root.left, q)) {
            return lowestCommonAncestorCore(root.left, p, q);
        }
        if (nodeInTree(root.right, p) && nodeInTree(root.right, q)) {
            return lowestCommonAncestorCore(root.right, p, q);
        }
        if (nodeInTree(root.left, p) && nodeInTree(root.right, q)) {
            return root;
        }
        if (nodeInTree(root.right, p) && nodeInTree(root.left, q)) {
            return root;
        }
        return null;
    }

    public static boolean nodeInTree(TreeNode root, TreeNode target) {
        if (root == null) return false;
        return root.val == target.val || nodeInTree(root.left, target) || nodeInTree(root, target);
    }
}
