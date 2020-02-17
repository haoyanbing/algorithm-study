package com.haoyanbing.data.structure.study.tree.bst;

import java.util.Random;

/**
 * @author haoyanbing
 * @since 2020/2/17
 */
public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i = 0; i < 100; i++) {
            tree.add(new Random().nextInt(1000));
        }
        tree.inOrder();
    }
}
