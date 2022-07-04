package havefun.tree;

import havefun.TreeNode;

/**
 * https://mp.weixin.qq.com/s/yewlHvHSilMsrUMFIO8WAA
 */
public class NextNodeInOrder {

    public static TreeNode nextNodeInOrder(TreeNode root, TreeNode target) {
        if (target == null) return null;
        if (target.right != null) {
            TreeNode temp = target.right;
            while (temp.left != null) {
                temp = temp.left;
            }
            return temp;
        }

        TreeNode temp = target;
        while (temp.parent != null) {
            if (temp.parent.left == temp) return temp.parent;
            else temp = temp.parent;
        }
        return null;
    }
}
