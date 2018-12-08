package RU.POLITEXNIK;

import jdk.nashorn.api.tree.Tree;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

class Solution {
    public static TreeNode invertTree(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        System.out.println(root.val + " " + root.left.val + " " + root.right.val + " " + root.left.left.val +
                " " + root.left.right.val + " " + root.right.left.val + " " + root.right.right.val + " ");
        invertTree(root);
        System.out.println(root.val + " " + root.left.val + " " + root.right.val + " " + root.left.left.val +
                " " + root.left.right.val + " " + root.right.left.val + " " + root.right.right.val + " ");
    }


}