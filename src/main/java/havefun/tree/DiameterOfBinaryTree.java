package havefun.tree;

import havefun.TreeNode;

/**
 * https://leetcode.cn/problems/diameter-of-binary-tree/
 */
public class DiameterOfBinaryTree {

    private int ans = 1;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        diameterOfBinaryTreeCore(root);
        return ans - 1;
    }

    public int diameterOfBinaryTreeCore(TreeNode root) {
        if (root == null) return 0;
        int left = diameterOfBinaryTreeCore(root.left);
        int right = diameterOfBinaryTreeCore(root.right);
        ans = Math.max(1 + left + right, ans);
        return Math.max(left, right) + 1;
    }

    public void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        diameterOfBinaryTree(node);
    }
}
