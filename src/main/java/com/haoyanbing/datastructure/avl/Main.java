package com.haoyanbing.datastructure.avl;

/**
 * @author haoyanbing
 * @since 2020/2/18
 */
public class Main {
    public static void main(String[] args) {
        AvlTree<Integer, String> tree = new AvlTree<>();
        for (int i = 0; i < 100; i++) {
            tree.add(i, i + "");
        }
        tree.inOrder();
        System.out.println(tree.isBalanced());
        System.out.println(tree.isBST());
    }
}
