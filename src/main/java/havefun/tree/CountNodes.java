package havefun.tree;

import havefun.TreeNode;

public class CountNodes {

    public static int countNodes(TreeNode root) {
        if (root == null) return 0;
        return countNodesCore(root);
    }

    public static int countNodesCore(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodesCore(root.left) + countNodesCore(root.right);
    }
}
