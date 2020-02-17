package com.haoyanbing.data.structure.study.tree.bst;

/**
 * @author haoyanbing
 * @since 2020/2/16 12:42
 */
public class BinarySearchTree<E extends Comparable<E>> {

    /**
     * 二分搜索树节点内部类
     */
    private class Node {
        E value;
        Node left, right;
        Node(E value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * 根节点
     */
    private Node root;
    /**
     * 数量
     */
    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    /**
     * 获取数量
     * @return 数量
     */
    public int size() {
        return size;
    }

    /**
     * 判断是否是空
     * @return 是否是空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 添加元素
     * @param e 元素
     */
    public void add(E e) {
        root = add(root, e);
    }

    /**
     * 递归添加元素
     * @param node 需要添加的父节点
     * @param e 元素
     * @return 返回新的父节点
     */
    private Node add(Node node, E e) {
        if (node == null) {
            ++size;
            return new Node(e);
        }
        if (node.value.compareTo(e) > 0) {
            node.left = add(node.left, e);
        } else if (node.value.compareTo(e) < 0) {
            node.right = add(node.right, e);
        } else {
            node.value = e;
        }
        return node;
    }

    /**
     * 判断是否包含元素
     * @param e 元素
     * @return 是否包含
     */
    public boolean contains(E e) {
        return contains(root, e);
    }

    /**
     * 递归查找是否包含元素
     * @param node 查找的父节点
     * @param e 元素
     * @return 返回是否包含
     */
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (e.compareTo(node.value) == 0) {
            return true;
        } else if (e.compareTo(node.value) > 0) {
            return contains(node.right, e);
        } else {
            return contains(node.left, e);
        }
    }

    /**
     * 前序遍历
     */
    public void perOrder() {
        perOrder(root);
    }

    /**
     * 递归前序遍历
     * @param node 查询的当前节点
     */
    private void perOrder(Node node) {
        if (node != null) {
            System.out.println(node.value);
            perOrder(node.left);
            perOrder(node.right);
        }
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * 递归中序遍历
     * @param node 查询当前节点
     */
    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.value);
            inOrder(node.right);
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    /**
     * 递归后序遍历
     * @param node 当前查询节点
     */
    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.value);
        }
    }
}
