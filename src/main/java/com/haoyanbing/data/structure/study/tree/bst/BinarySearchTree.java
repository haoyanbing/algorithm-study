package com.haoyanbing.data.structure.study.tree.bst;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * 二分搜索树
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

    /**
     * 构造函数
     */
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
     * 前序遍历-非递归版
     */
    public void perOrderNR() {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            System.out.println(curr.value);
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
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

    /**
     * 层序遍历
     */
    public void levelOrder() {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node curr = queue.remove();
            System.out.println(curr.value);

            if (curr.left != null) {
                queue.add(curr.left);
            }
            if (curr.right != null) {
                queue.add(curr.right);
            }
        }
    }

    /**
     * 查询最小元素
     * @return 最小元素
     */
    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("is empty tree");
        }
        return minimum(root).value;
    }

    /**
     * 递归查询最小元素
     * @param node 当前查询节点
     * @return 最小节点
     */
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    /**
     * 查询最大元素
     * @return 最大元素
     */
    public E maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("is empty tree");
        }
        return maximum(root).value;
    }

    /**
     * 递归查询最大元素
     * @param node 当前查询节点
     * @return 最大节点
     */
    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    /**
     * 删除最小元素
     * @return 最小元素
     */
    public E removeMin() {
        E min = minimum();
        removeMin(root);
        return min;
    }

    /**
     * 删除已node为跟节点的最下元素
     * @param node 当前更元素
     * @return 删除最小节点后的新根节点
     */
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            --size;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 删除最大元素
     * @return 最大元素
     */
    public E removeMax() {
        E max = maximum();
        removeMax(root);
        return max;
    }

    /**
     * 删除以node为根节点的二分树中最大的元素
     * @param node 当前查询根节点
     * @return 删除以node为根节点删除最大元素后新的根节点
     */
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            --size;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 删除指定元素
     * @param e 待删除元素
     */
    public void remove(E e) {
        remove(root, e);
    }

    /**
     * 删除以node为根节点的二分树中的元素e
     * @param node 当前根节点
     * @param e 待删除元素
     * @return 删除元素e之后新的根节点
     */
    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.value) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.value) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {
            // 1.待删除节点左节点为空
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                --size;
                return rightNode;
            }

            // 2.待删除节点右节点为空
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                --size;
                return leftNode;
            }

            // 3.待删除节点左右节点都不为空
            Node successor = minimum(node.right);
            successor.left = node.left;
            successor.right = removeMin(node.right);
            node.left = node.right = null;
            return successor;
        }
    }
}
