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
        System.out.println("------");
        System.out.println(tree.minimum());
        System.out.println(tree.maximum());
        System.out.println(tree.removeMax());
        System.out.println("------");
        tree.inOrder();
        System.out.println("------");
        tree.add(100);
        System.out.println(tree.contains(100));
        tree.remove(100);
        System.out.println(tree.contains(100));

        System.out.println(tree.toString());
    }
}
