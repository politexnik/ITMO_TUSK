package RU.POLITEXNIK;

public class Solution {

    public static void main(String[] args) {
	// write your code here

    }
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        int left = 0;
        int right = 0;

        if (root.left != null)
            left = maxDepth(root.left);

        if (root.right != null)
            right = maxDepth(root.right);

        return 1 + ((left > right) ? left : right);
    }
}
