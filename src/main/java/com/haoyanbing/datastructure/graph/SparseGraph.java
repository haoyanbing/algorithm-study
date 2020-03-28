package com.haoyanbing.datastructure.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 图-邻接表
 *
 * @author haoyanbing
 * @since 2020/3/28
 */
public class SparseGraph {

    private int n, m;
    // 是否有向图
    private boolean directed;
    private List<Integer>[] g;

    public SparseGraph(int n, boolean isDirected) {
        this.n = n;
        this.m = 0;
        this.directed = isDirected;
        g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList();
        }
    }

    public void addEdge(int v, int w) {
        if (v < 0 || v > n || w < 0 || w > n) {
            throw new IllegalArgumentException();
        }

        g[v].add(w);
        if (v != w && !directed) {
            g[w].add(v);
        }
        m++;
    }

    private boolean hasEdge(int v, int w) {
        for (Integer o : g[v])
            if (o == w)
                return true;

        return false;
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
            return g[v].size() > index;
        }

        public int next() {
            return g[v].get(index++);
        }
    }

    public static void main(String[] args) {
        SparseGraph graph = new SparseGraph(10, false);
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
