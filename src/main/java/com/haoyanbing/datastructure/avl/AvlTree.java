package com.haoyanbing.datastructure.avl;

import java.util.*;

/**
 * AVL树
 * @author haoyanbing
 * @since 2020/2/18
 */
public class AvlTree<K extends Comparable<K>, V> {
    /**
     * 二分搜索树节点内部类
     */
    private class Node {
        K key;
        V value;
        Node left, right;
        int height;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
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
    public AvlTree() {
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
     * 判断是否时二分搜索树
     * @return 是否时二分搜索树
     */
    public boolean isBST() {
        List<K> list = new ArrayList<>();
        inOrder(root, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareTo(list.get(i - 1)) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 中序遍历按顺序放入keys集合
     * @param node 当前遍历节点
     * @param keys 输出集合
     */
    private void inOrder(Node node, List<K> keys) {
        if (node == null) {
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    /**
     * 判断二分树是否时平衡树
     * @return 是否平衡
     */
    public boolean isBalanced() {
        return isBalanced(root);
    }

    /**
     * 以node为根节点的二分树是否是平衡的
     * @param node 节点
     * @return 是否是平衡
     */
    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        if (Math.abs(getBalanceFactor(node)) > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    /**
     * 获取节点高度
     * @param node 节点
     * @return 节点高度
     */
    private int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }

    /**
     * 获取平衡因子
     * @param node 节点
     * @return 节点平衡因子
     */
    private int getBalanceFactor(Node node) {
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    /**
     * 对节点y进行向右旋转操作，返回旋转后新的根节点x
     *        y                              x
     *       / \                           /   \
     *      x   T4     向右旋转 (y)        z     y
     *     / \       - - - - - - - ->    / \   / \
     *    z   T3                       T1  T2 T3 T4
     *   / \
     * T1   T2
     * @param y 当前根节点
     * @return 右旋后的新根节点
     */
    private Node rightRotate(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;

        // 更新高度
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    /**
     * 对节点y进行向左旋转操作，返回旋转后新的根节点x
     *    y                             x
     *  /  \                          /   \
     * T1   x      向左旋转 (y)       y     z
     *     / \   - - - - - - - ->   / \   / \
     *   T2  z                     T1 T2 T3 T4
     *      / \
     *     T3 T4
     * @param y
     * @return
     */
    private Node leftRotate(Node y) {
        Node x = y.right;
        y.right = x.left;
        x.left = y;

        // 更新高度
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    /**
     * 添加元素
     * @param key 键
     * @param value 值
     */
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    /**
     * 递归添加元素
     * @param node 需要添加的父节点
     * @param key 键
     * @param value 值
     * @return 返回新的父节点
     */
    private Node add(Node node, K key, V value) {
        if (node == null) {
            ++size;
            return new Node(key, value);
        }
        if (node.key.compareTo(key) > 0) {
            node.left = add(node.left, key, value);
        } else if (node.key.compareTo(key) < 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        int balanceFactor = getBalanceFactor(node);

        // LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }

        // LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }

        // RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    /**
     * 判断是否包含元素
     * @param k 元素
     * @return 是否包含
     */
    public boolean contains(K k) {
        return contains(root, k);
    }

    /**
     * 递归查找是否包含元素
     * @param node 查找的父节点
     * @param k 元素
     * @return 返回是否包含
     */
    private boolean contains(Node node, K k) {
        if (node == null) {
            return false;
        }
        if (k.compareTo(node.key) == 0) {
            return true;
        } else if (k.compareTo(node.key) > 0) {
            return contains(node.right, k);
        } else {
            return contains(node.left, k);
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
            System.out.println(node.key);
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
            System.out.println(curr.key);
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
            System.out.println(node.key);
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
            System.out.println(node.key);
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
            System.out.println(curr.key);

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
    public K minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("is empty tree");
        }
        return minimum(root).key;
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
    public K maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("is empty tree");
        }
        return maximum(root).key;
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
    public K removeMin() {
        K min = minimum();
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
    public K removeMax() {
        K max = maximum();
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
     * @param k 待删除元素
     */
    public void remove(K k) {
        remove(root, k);
    }

    /**
     * 删除以node为根节点的二分树中的元素e
     * @param node 当前根节点
     * @param k 待删除元素
     * @return 删除元素e之后新的根节点
     */
    private Node remove(Node node, K k) {
        if (node == null) {
            return null;
        }
        if (k.compareTo(node.key) < 0) {
            node.left = remove(node.left, k);
            return node;
        } else if (k.compareTo(node.key) > 0) {
            node.right = remove(node.right, k);
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
