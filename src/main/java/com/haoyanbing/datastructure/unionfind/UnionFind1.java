package com.haoyanbing.datastructure.unionfind;

/**
 * 并查集-Quick Find
 *
 * @author haoyanbing
 * @since 2020/3/25
 */
public class UnionFind1 {

    private int[] data;

    private int count;

    public UnionFind1(int n) {
        this.count = 0;
        this.data = new int[n];
        for (int i = 0; i < n; i++) {
            this.data[i] = i;
        }
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;

        for (int i = 0; i < count; i++) {
            if (data[i] == pRoot) {
                data[i] = qRoot;
            }
        }
    }

    public boolean isConnected(int p, int q) {
        return find(q) == find(p);
    }

    private int find(int k) {
        return data[k];
    }

}
