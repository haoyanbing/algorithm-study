package com.haoyanbing.datastructure.unionfind;

/**
 * 并查集-基于size的优化
 *
 * @author haoyanbing
 * @since 2020/3/25
 */
public class UnionFind3 {

    private int[] parent;

    // size[i]表示以i为根的集合中元素的个数
    private int[] size;

    public UnionFind3(int n) {
        this.parent = new int[n];
        this.size = new int[n];
        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
            size[i] = 1;
        }
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;

        if (size[pRoot] > size[qRoot]) {
            parent[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
        } else {
            parent[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
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
