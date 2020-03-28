package com.haoyanbing.datastructure.graph;

import java.util.Random;

/**
 * 图-邻接矩阵
 *
 * @author haoyanbing
 * @since 2020/3/28
 */
public class DenseGraph {

    private int n, m;
    // 是否有向图
    private boolean directed;
    private boolean[][] g;

    public DenseGraph(int n, boolean isDirected) {
        this.n = n;
        this.m = 0;
        this.directed = isDirected;
        g = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g[i][j] = false;
            }
        }
    }

    public void addEdge(int v, int w) {
        if (v < 0 || v > n || w < 0 || w > n) {
            throw new IllegalArgumentException();
        }

        if (hasEdge(v, w)) return;

        g[v][w] = true;
        if (!directed) {
            g[w][v] = true;
        }
        m++;
    }

    private boolean hasEdge(int v, int w) {
        return g[v][w];
    }

    public int v() {
        return n;
    }

    public int e() {
        return m;
    }

    public Iterator iterator(int v) {
        return new Iterator(v);
    }

    class Iterator {
        private int index;
        private int v;

        public Iterator(int v) {
            this.index = 0;
            this.v = v;
        }

        public boolean hasNext() {
            for (; index < n; index++) {
                if (g[v][index]) {
                    return true;
                }
            }
            return false;
        }

        public int next() {
            return index++;
        }
    }

    public static void main(String[] args) {
        DenseGraph graph = new DenseGraph(10, false);
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            graph.addEdge(random.nextInt(10), random.nextInt(10));
        }

        for (int i = 0; i < 10; i++) {
            Iterator iterator = graph.iterator(i);
            System.out.print(i + " : ");
            while (iterator.hasNext()) {
                System.out.print(iterator.next() + ", ");
            }
            System.out.println();
        }

    }
}
