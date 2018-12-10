package RU.Politexnik;

import java.util.ArrayList;

public class TreeMapBinary<T1, T2> {

    private class Node<T1, T2> {
        T1 key;
        T2 value;
        Node left;
        Node right;

        Node(T1 key, T2 Value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "(" + value.toString() + ")";
        }
    }

    private Node root;

//    TreeMapBinary(ArrayList<Integer> vals) {
//        for (int i : vals)
//            this.insert(i);
//    }

    public void put(T1 key, T2 value) {
        Node node = new Node(key, value);
        if (root == null) {
            root = node;
        } else {
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                if (key.hashCode() == current.key.hashCode()) {
                    if (key.equals(current.key)) {
                        current.value = node.value;
                        return;
                    }
                }
                if (key.hashCode() <= current.key.hashCode()) {
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

    boolean isBalanced(boolean precision) {
        return Math.abs(countDepth(root.left) - countDepth(root.right)) <= ((precision) ? 0 : 1);
    }

    private int countDepth(Node node) {
        if (node == null) return 0;

        int left = 0;
        int right = 0;

        if (node.left != null)
            left = countDepth(node.left);

        if (node.right != null)
            right = countDepth(node.right);

        return 1 + ((left > right) ? left : right);
    }

    public T2 getValue(T1 key) {
        if (root == null) {
            return null;
        } else {
            Node current = root;
            //Node parent;
            while (true) {
                //parent = current;
                if (key.hashCode() == current.key.hashCode()) {
                    if (key.equals(current.key)) {
                        return (T2) current.value;
                    } else {
                        current = current.left;
                        if (current == null) {
                            return null;
                        }
                    }
                } else if (key.hashCode() < current.key.hashCode()) {
                    current = current.left;
                    if (current == null) {
                        return null;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        return null;
                    }
                }
            }
        }
    }

    public boolean remove(T1 key) {
        if (root == null) {
            return false;
        } else {
            Node current = root;
            Node parent = root;
            while (true) {
                if (key.hashCode() == current.key.hashCode()) {
                    if (key.equals(current.key)) {
                        if (parent != current){
                            if (parent.left == current && current.right == null) {

                            }

                        }


                    }
                }
                if (key.hashCode() <= current.key.hashCode()) {
                    parent = current;
                    current = current.left;
                    if (current == null) {
                        return false;
                    }
                } else {
                    parent = current;
                    current = current.right;
                    if (current == null) {
                        return false;
                    }
                }
            }
        }
        return false;
    }


}