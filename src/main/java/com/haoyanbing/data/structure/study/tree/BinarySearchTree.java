package com.haoyanbing.data.structure.study.tree;

/**
 * @author haoyanbing
 * @since 2020/2/16 12:42
 */
public class BinarySearchTree<E extends Comparable<E>> {

    private class Node {
        public E value;
        public Node left, right;

        public Node(E value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        root = add(root, e);
    }

    private Node add(Node node, E e) {
        if (node == null) {
            ++size;
            return new Node(e);
        }
        if (node.value.compareTo(e) > 0) {
            node.left = add(node.left, e);
        } else if (node.value.compareTo(e) > 0) {
            node.right = add(node.right, e);
        } else {
            node.value = e;
        }
        return node;
    }
}
