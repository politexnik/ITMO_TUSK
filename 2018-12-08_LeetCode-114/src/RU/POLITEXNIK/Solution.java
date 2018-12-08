package RU.POLITEXNIK;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);
        System.out.println(root.val + " " + root.left.val + " " + root.right.val + " " + root.left.left.val +
                " " + root.left.right.val + " " + root.right.right.val + " ");
        flatten(root);
        System.out.println(root.val + " " + root.right.val + " " + root.right.right.val + " " + root.right.right.right.val +
                " " + root.right.right.right.right.val + " " + root.right.right.right.right.right.val + " ");
    }

    public static void flatten(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new ArrayDeque<>();
        goDeep(root,queue);
        TreeNode current;
        while (!queue.isEmpty()) {
            current = queue.poll();
            current.left = null;
            current.right = queue.peek();
        }
    }

    public static void goDeep(TreeNode node, Queue<TreeNode> queue) {
        if (node == null) return;
        queue.add(node);
        goDeep(node.left, queue);
        goDeep(node.right, queue);
    }



}
