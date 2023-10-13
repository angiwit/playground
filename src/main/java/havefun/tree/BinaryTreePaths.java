package havefun.tree;

import havefun.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreePaths {

    public static List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<String> result = new ArrayList<>();
        binaryTreePathsCore(root, new LinkedList<>(), result);
        return result;
    }

    public static void binaryTreePathsCore(TreeNode root, List<String> queue, List<String> result) {
        if (root == null) return;
        queue.add(String.valueOf(root.val));
        if (root.left == null && root.right == null) {
            String temp = String.join("->", queue);
            result.add(temp);
            /**
             * current path is found! remove the last element added, this could be a left node or a right node.
             */
            queue.remove(queue.size() - 1);
            return;
        }
        binaryTreePathsCore(root.left, queue, result);
        binaryTreePathsCore(root.right, queue, result);
        /**
         * After sub-tree is traversed, and a path is found, all path include current element is found
         * pop the current element, back to its parent node.
         */
        queue.remove(queue.size() - 1);
    }

    //another approach
    public static void binaryTreePathsCore1(TreeNode root, List<String> queue, List<String> result) {
        if (root == null) return;
        queue.add(String.valueOf(root.val));
        if (root.left == null && root.right == null) {
            String temp = String.join("->", queue);
            result.add(temp);
//            queue.remove(queue.size() - 1);
            return;
        }
        if (root.left != null) {
            binaryTreePathsCore1(root.left, queue, result);
            /**
             * can't put this in the last line of this method, since when found left leaf, we need to pop.
             */
            queue.remove(queue.size() - 1);
        }

        if (root.right != null) {
            binaryTreePathsCore1(root.right, queue, result);
            /**
             * can't put this in the last line of this method, since when found right leaf, we need to pop.
             */
            queue.remove(queue.size() - 1);
        }
    }

}
