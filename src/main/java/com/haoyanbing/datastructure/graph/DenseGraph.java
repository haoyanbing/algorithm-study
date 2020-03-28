package com.haoyanbing.datastructure.graph;

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
        if (v <= 0 || v > n || w <= 0 || w > n) {
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
}
