package com.haoyanbing.datastructure.unionfind;

/**
 * 并查集-基于rank的优化
 *
 * @author haoyanbing
 * @since 2020/3/25
 */
public class UnionFind4 {

    private int[] parent;

    // size[i]表示以i为根的集合中元素的深度
    private int[] rank;

    public UnionFind4(int n) {
        this.parent = new int[n];
        this.rank = new int[n];
        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
            rank[i] = 1;
        }
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;

        if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else if (rank[qRoot] > rank[pRoot]) {
            parent[pRoot] = qRoot;
        } else {
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
    }

    public boolean isConnected(int p, int q) {
        return find(q) == find(p);
    }

    private int find(int k) {
        while (k != parent[k]) {
            k = parent[k];
        }
        return k;
    }

}
