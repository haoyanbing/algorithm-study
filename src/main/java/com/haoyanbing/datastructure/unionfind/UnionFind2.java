package com.haoyanbing.datastructure.unionfind;

/**
 * 并查集-Quick Union
 *
 * @author haoyanbing
 * @since 2020/3/25
 */
public class UnionFind2 {

    private int[] parent;

    public UnionFind2(int n) {
        this.parent = new int[n];
        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
        }
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;

        parent[pRoot] = qRoot;
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
