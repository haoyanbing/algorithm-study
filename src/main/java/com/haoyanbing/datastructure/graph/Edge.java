package com.haoyanbing.datastructure.graph;

/**
 * @author haoyanbing
 * @since 2020/3/29
 */
public class Edge<W extends Comparable<W>> implements Comparable<Edge<W>> {
    private int a, b;
    private W weight;

    public Edge(int a, int b, W weight) {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }

    public Edge() {
    }

    public int v() {
        return a;
    }

    public int w() {
        return b;
    }

    public W weight() {
        return weight;
    }

    public int other(int x) {
        if (x != a && x != b) {
            throw new IllegalArgumentException();
        }
        return x == a ? b : a;
    }

    @Override
    public int compareTo(Edge<W> o) {
        return o.weight().compareTo(this.weight());
    }
}
