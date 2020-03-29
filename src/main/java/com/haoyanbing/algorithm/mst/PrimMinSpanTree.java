package com.haoyanbing.algorithm.mst;

import com.haoyanbing.datastructure.graph.DenseWightGraph;
import com.haoyanbing.datastructure.graph.Edge;
import com.haoyanbing.datastructure.heap.CommonIndexMaxHeap;

import java.util.ArrayList;
import java.util.List;

/**
 * 最小生成树-Prim算法
 *
 * @author haoyanbing
 * @since 2020/3/29
 */
public class PrimMinSpanTree<W extends Comparable<W>> {
    private DenseWightGraph<W> graph;
    private CommonIndexMaxHeap<Edge<W>> heap;
    private boolean[] marked;
    private List<Edge<W>> mst;

    public PrimMinSpanTree(DenseWightGraph<W> graph) {
        this.graph = graph;
        this.heap = new CommonIndexMaxHeap<>(graph.v() + 1);
        marked = new boolean[graph.v()];
        mst = new ArrayList<>();

        // prim
        visit(0);
        while (!heap.isEmpty()) {
            Edge<W> edge = heap.extractMax();
            if (marked[edge.v()] == marked[edge.w()]) {
                continue;
            }
            mst.add(edge);
            if (marked[edge.v()]) {
                visit(edge.w());
            } else {
                visit(edge.v());
            }
        }
    }

    private void visit(int v) {
        if (marked[v]) return;
        marked[v] = true;
        DenseWightGraph<W>.Iterator iterator = graph.iterator(v);
        while (iterator.hasNext()) {
            Edge<W> edge = iterator.next();
            if (!marked[edge.other(v)]) {
                Edge<W> item = heap.getItem(edge.other(v));
                if (item == null) {
                    heap.insert(edge.other(v), edge);
                } else if (item.weight().compareTo(edge.weight()) > 0) {
                    heap.set(edge.other(v), edge);
                }
            }
        }
    }

    public List<Edge<W>> getMst() {
        return mst;
    }

    public static void main(String[] args) {
        DenseWightGraph<Integer> graph = new DenseWightGraph<>(7, false);
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 6, 20);
        graph.addEdge(0, 2, 100);
        graph.addEdge(6, 5, 30);
        graph.addEdge(5, 2, 40);
        graph.addEdge(5, 4, 50);
        graph.addEdge(4, 3, 60);
        graph.addEdge(3, 1, 70);
        graph.addEdge(3, 2, 80);
        graph.addEdge(2, 1, 90);

        PrimMinSpanTree<Integer> lazyPrim = new PrimMinSpanTree<>(graph);
        List<Edge<Integer>> mst = lazyPrim.getMst();
        for (Edge<Integer> edge : mst) {
            System.out.println("{" + edge.v() + ", " + edge.w() + ", " + edge.weight() + "}");
        }
    }
}
