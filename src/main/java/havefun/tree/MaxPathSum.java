package havefun.tree;

import havefun.TreeNode;

public class MaxPathSum {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-10);
        TreeNode left = new TreeNode(9);
        TreeNode right = new TreeNode(20);
        TreeNode right_left = new TreeNode(15);
        TreeNode right_right = new TreeNode(7);

        right.left = right_left;
        right.right = right_right;
        root.left = left;
        root.right = right;
        System.out.println(maxPathSum(root));
    }

    private static int maxPath = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        maxPathSumCore(root);
        return maxPath;
    }

    public static int maxPathSumCore(TreeNode root) {
        if (root == null) return 0;
        int left = Math.max(maxPathSumCore(root.left), 0);
        int right = Math.max(maxPathSumCore(root.right), 0);
        maxPath = Math.max(left + right + root.val, maxPath);
        return Math.max(left, right) + root.val;
    }
}
