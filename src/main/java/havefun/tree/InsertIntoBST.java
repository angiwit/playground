package havefun.tree;

import havefun.TreeNode;

public class InsertIntoBST {

    public static TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return null;
        return insertIntoBSTCore(root, val);
    }

    public static TreeNode insertIntoBSTCore(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val > val) {
            if (root.left == null) {
                root.left = new TreeNode(val);
            } else {
                insertIntoBSTCore(root.left, val);
            }
        }
        if (root.val < val) {
            if (root.right == null) {
                root.right = new TreeNode(val);
            } else {
                insertIntoBSTCore(root.right, val);
            }
        }
        return root;
    }

    public static TreeNode insertIntoBSTCore1(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val > val) {
            TreeNode node = insertIntoBSTCore1(root.left, val);
            if (node == null) {
                root.left = new TreeNode(val);
            }
        }
        if (root.val < val) {
            TreeNode node = insertIntoBSTCore1(root.right, val);
            if (node == null) {
                root.right = new TreeNode(val);
            }
        }
        return root;
    }
}
