package com.haoyanbing.datastructure.unionfind;

/**
 * 并查集-路径压缩
 *
 * @author haoyanbing
 * @since 2020/3/25
 */
public class UnionFind5 {

    private int[] parent;

    // size[i]表示以i为根的集合中元素的个数
    private int[] rank;

    public UnionFind5(int n) {
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

        // 路径压缩算法1
//        while (k != parent[k]) {
//            parent[k] = parent[parent[k]];
//            k = parent[k];
//        }

        // 路径压缩算法2
        if (k != parent[k]) {
            parent[k] = find(parent[k]);
        }
        return parent[k];
    }

}
