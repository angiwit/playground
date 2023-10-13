package havefun.tree;

import havefun.TreeNode;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/
 */
public class KthSmallestInBinaryTree {

    public static int kthSmallest(TreeNode root, int k) {
        if (root == null || k == 0) return 0;
        return kthSmallestCore(root, k);
    }

    public static int kthSmallestCore(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = root;
        while (!stack.isEmpty() || temp != null) {
            if (temp != null) {
                stack.push(temp);
                temp = temp.left;
            } else {
                temp = stack.pop();
                if (--k == 0) {
                    break;
                } else {
                    temp = temp.right;
                }
            }
        }
        return temp.val;
    }
}
