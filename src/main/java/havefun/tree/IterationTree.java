package havefun.tree;

import havefun.TreeNode;

import java.util.Stack;

public class IterationTree {

    public static void main(String[] args) {

    }

    public void perOderIteration(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop().val);
            if (root.left != null) stack.push(root.left);
        }


    }
}
