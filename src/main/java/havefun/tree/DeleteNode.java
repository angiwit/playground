package havefun.tree;

import havefun.TreeNode;

/**
 * https://leetcode-cn.com/problems/delete-node-in-a-bst/
 */
public class DeleteNode {

    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        return deleteNodeCore(root, key);
    }

    /**
     * Note the difference between problem TrimBST, for trimBST, a node in the range is deterministic, but for this problem,
     * if we check a node is different with key, then we can get a bunch of nodes.
     * We don't need the if check after fetched the return value since the tree structure is not changed a lot, the subtree
     * still have relative order, the value returned from left tree must smaller than root and right tree must bigger than root.
     *
     * @param root
     * @param key
     * @return
     */
    private static TreeNode deleteNodeCore(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val == key) {
            if (root.left == null && root.right == null) return null;
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            if (root.left != null && root.right != null) {
                TreeNode curNode = root.right;
                while (curNode.left != null) {
                    curNode = curNode.left;
                }
                curNode.left = root.left;
                return root.right;
            }
        }
        if (root.val > key) root.left = deleteNodeCore(root.left, key); // if condition could be deleted here.
        if (root.val < key) root.right = deleteNodeCore(root.right, key);
        return root;
    }


}
