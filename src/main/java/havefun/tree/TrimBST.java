package havefun.tree;

import havefun.TreeNode;

/**
 * https://leetcode.cn/problems/trim-a-binary-search-tree/
 */
public class TrimBST {

    public static TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) return null;
        return trimBSTCore(root, low, high);
    }

    public static TreeNode trimBSTCore(TreeNode root, int low, int high) {
        if (root == null) return null;
        if (root.val < low) return trimBSTCore(root.right, low, high);
        if (root.val > high) return trimBSTCore(root.left, low, high);

        root.left = trimBSTCore(root.left, low, high);
        root.right = trimBSTCore(root.right, low, high);
        return root;
    }
}
