package com.haoyanbing.datastructure.graph;

import java.util.Random;

/**
 * 图-邻接矩阵(有权图)
 *
 * @author haoyanbing
 * @since 2020/3/28
 */
public class DenseWightGraph<W extends Comparable<W>> {

    private int n, m;
    // 是否有向图
    private boolean directed;
    private Edge<W>[][] g;

    public DenseWightGraph(int n, boolean isDirected) {
        this.n = n;
        this.m = 0;
        this.directed = isDirected;
        g = new Edge[n][n];
    }

    public void addEdge(int v, int w, W weight) {
        if (v < 0 || v > n || w < 0 || w > n) {
            throw new IllegalArgumentException();
        }

        if (hasEdge(v, w)) {
            m--;
        }

        g[v][w] = new Edge<>(v, w, weight);
        if (!directed) {
            g[w][v] = new Edge<>(w, v, weight);
        }
        m++;
    }

    private boolean hasEdge(int v, int w) {
        return g[v][w] != null;
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

    public class Iterator {
        private int index;
        private int v;

        public Iterator(int v) {
            this.index = 0;
            this.v = v;
        }

        public boolean hasNext() {
            for (; index < n; index++) {
                if (g[v][index] != null) {
                    return true;
                }
            }
            return false;
        }

        public Edge<W> next() {
            return g[v][index++];
        }
    }

    public static void main(String[] args) {
        DenseWightGraph graph = new DenseWightGraph<Integer>(10, false);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            graph.addEdge(random.nextInt(10), random.nextInt(10), random.nextInt(10));
        }

        for (int i = 0; i < 10; i++) {
            DenseWightGraph.Iterator iterator = graph.iterator(i);
            System.out.print(i + " : ");
            while (iterator.hasNext()) {
                Edge<Integer> edge = iterator.next();
                System.out.print("{" + edge.v() + ", " + edge.w() + ", " + edge.weight() + "}");
            }
            System.out.println();
        }

    }
}
