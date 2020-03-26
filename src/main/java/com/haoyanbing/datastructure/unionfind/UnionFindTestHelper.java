package com.haoyanbing.datastructure.unionfind;

import java.util.Random;

/**
 * @author haoyanbing
 * @since 2020/3/26
 */
public class UnionFindTestHelper {

    public static void main(String[] args) {
        int n = 10000;

        test1(n);
        test2(n);
        test3(n);
        test4(n);
        test5(n);
    }

    private static void test1(int n) {
        UnionFind1 unionFind1 = new UnionFind1(n);
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            Random random = new Random();
            unionFind1.union(random.nextInt(n), random.nextInt(n));
        }
        for (int i = 0; i < n; i++) {
            Random random = new Random();
            unionFind1.isConnected(random.nextInt(n), random.nextInt(n));
        }
        long end = System.currentTimeMillis();
        System.out.println("time: " + (end - start));
    }

    private static void test2(int n) {
        UnionFind2 unionFind2 = new UnionFind2(n);
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            Random random = new Random();
            unionFind2.union(random.nextInt(n), random.nextInt(n));
        }
        for (int i = 0; i < n; i++) {
            Random random = new Random();
            unionFind2.isConnected(random.nextInt(n), random.nextInt(n));
        }
        long end = System.currentTimeMillis();
        System.out.println("time: " + (end - start));
    }

    private static void test3(int n) {
        UnionFind3 unionFind3 = new UnionFind3(n);
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            Random random = new Random();
            unionFind3.union(random.nextInt(n), random.nextInt(n));
        }
        for (int i = 0; i < n; i++) {
            Random random = new Random();
            unionFind3.isConnected(random.nextInt(n), random.nextInt(n));
        }
        long end = System.currentTimeMillis();
        System.out.println("time: " + (end - start));
    }

    private static void test4(int n) {
        UnionFind4 unionFind4 = new UnionFind4(n);
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            Random random = new Random();
            unionFind4.union(random.nextInt(n), random.nextInt(n));
        }
        for (int i = 0; i < n; i++) {
            Random random = new Random();
            unionFind4.isConnected(random.nextInt(n), random.nextInt(n));
        }
        long end = System.currentTimeMillis();
        System.out.println("time: " + (end - start));
    }

    private static void test5(int n) {
        UnionFind5 unionFind5 = new UnionFind5(n);
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            Random random = new Random();
            unionFind5.union(random.nextInt(n), random.nextInt(n));
        }
        for (int i = 0; i < n; i++) {
            Random random = new Random();
            unionFind5.isConnected(random.nextInt(n), random.nextInt(n));
        }
        long end = System.currentTimeMillis();
        System.out.println("time: " + (end - start));
    }
}
