package com.haoyanbing.datastructure.graph;

import java.util.ArrayList;
import java.util.List;

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
        if (v <= 0 || v > n || w <= 0 || w > n) {
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
}
