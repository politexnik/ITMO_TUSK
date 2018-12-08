package RU.POLITEXNIK;

import java.util.ArrayList;

public class Solution {

    public static void main(String[] args) {
	// Проверка алгоритма. Печать дерева в ширину
        ListNode listNode = new ListNode(0);
        listNode.next = new ListNode(1);
        listNode.next.next = new ListNode(2);
        listNode.next.next.next = new ListNode(3);
        listNode.next.next.next.next = new ListNode(4);
        listNode.next.next.next.next.next = new ListNode(5);
        listNode.next.next.next.next.next.next = new ListNode(6);

        TreeNode treeNode = sortedListToBST(listNode);
        System.out.println(treeNode.val + " " + treeNode.left.val+ " " + treeNode.right.val+ " " + treeNode.left.left.val
                + " " + treeNode.left.right.val+ " " + treeNode.right.left.val+ " " + treeNode.right.right.val);
    }

    public static TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        ArrayList<Integer> list = listNodeToArrayList(head);    //переработка в массив
        TreeNode root = null;
        root = getBSTreeFromSortedArrayList(list, root, 0, list.size() - 1);
        return root;
    }

    public static ArrayList<Integer> listNodeToArrayList(ListNode head) {

        ArrayList<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        return list;
    }

    public static TreeNode getBSTreeFromSortedArrayList(ArrayList<Integer> list, TreeNode root, int startPos, int endPos) {
        //условие выхода если 1 элемент
        if (root == null) {
            root = new TreeNode(list.get((endPos - startPos) / 2));
        } else {
            if (startPos == endPos) {
                put(root, list.get(startPos));
                return root;
            } else if (endPos - startPos == 1) {    // если 2 элемента
                put(root, list.get(startPos));
                put(root, list.get(endPos));
                return root;
            }
            put(root, list.get((endPos + startPos) / 2));
        }
        getBSTreeFromSortedArrayList(list, root, startPos, (endPos + startPos) / 2 - 1);
        getBSTreeFromSortedArrayList(list, root, (endPos + startPos) / 2 + 1, endPos);

        //put(root, list.remove((startPos + endPos) / 2));
        return root;
    }

    public static void put(TreeNode root, Integer value ) {
        TreeNode node = new TreeNode(value);
        if (root == null) {
            root = node;
        } else {
            TreeNode current = root;
            TreeNode parent;
            while (true) {
                parent = current;
                if (value <= current.val) {
                    current = current.left;
                    if (current == null) {
                        parent.left = node;
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = node;
                        return;
                    }
                }
            }
        }
    }

}
