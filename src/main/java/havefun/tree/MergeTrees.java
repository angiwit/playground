package havefun.tree;

import havefun.TreeNode;

/**
 * https://leetcode-cn.com/problems/merge-two-binary-trees/
 */
public class MergeTrees {

    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return null;
        return mergeTreesCore(root1, root2);
    }

    public static TreeNode mergeTreesCore(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return null;
        else if (root1 == null) return root2;
        else if (root2 == null) return root1;
        else if (root1 != null && root2 != null) {
            root1.val = root1.val + root2.val;
        }
        root1.left = mergeTreesCore(root1.left, root2.left);
        root1.right = mergeTreesCore(root1.right, root2.right);
        return root1;
    }

    // incorrect and reason
    public static TreeNode mergeTreesCore1(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        if (root1 == null && root2 == null) return null;
        if (root1 != null && root2 != null) {
            root1.val = root1.val + root2.val;
        }
        /**
         * The if condition should NOT be applied here, since after adding the condition,
         * the recursive will NOT run all the time, only the condition matches, then the
         * recursive will run.
         */
        if (root1.left == null) {
            root1.left = mergeTreesCore(null, root2.left);
        }
        if (root1.right == null) {
            root1.right = mergeTreesCore(null, root2.right);
        }

        return root1;
    }
}
