package havefun.tree;

import havefun.TreeNode;

/**
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 */
public class LowestCommonAncestor {

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        return lowestCommonAncestorCore(root, p, q);
    }

    /**
     * The first several return are in the next level.
     * TreeNode left = xxxx(root.left);
     * TreeNode right = xxxx(root.right);
     * Above two lines are standard way to fetch result returned by the method.
     * with the left and right, do post processing and return the final result.
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestorCore(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root.val == p.val || root.val == q.val) { // review current node's value, if equals to a target, return.
            return root; // traverse and return in next level.
        }
        TreeNode left = lowestCommonAncestorCore(root.left, p, q); // The approach to get the returned value.
        TreeNode right = lowestCommonAncestorCore(root.right, p, q); // upper level result.
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root; // upper level root is the lowest common ancestor.
    }

    //No need to use this method to traverse the tree again.
    public static boolean nodeInTree(TreeNode root, TreeNode target) {
        if (root == null) return false;
        return root.val == target.val || nodeInTree(root.left, target);
    }
}
