package com.haoyanbing.algorithm.mst;

import com.haoyanbing.datastructure.graph.DenseWeightGraph;
import com.haoyanbing.datastructure.graph.Edge;
import com.haoyanbing.datastructure.heap.CommonMaxHeap;

import java.util.ArrayList;
import java.util.List;

/**
 * 最小生成树-Lazy prim算法
 *
 * @author haoyanbing
 * @since 2020/3/29
 */
public class LazyPrimMinSpanTree<W extends Comparable<W>> {

    private DenseWeightGraph<W> graph;

    // 最大堆
    private CommonMaxHeap<Edge<W>> heap;

    // 访问过的节点
    private boolean[] marked;

    // 最小生成树的所有边
    private List<Edge<W>> mst;

    public LazyPrimMinSpanTree(DenseWeightGraph<W> graph) {
        this.graph = graph;
        this.heap = new CommonMaxHeap<>(graph.v() + 1);
        marked = new boolean[graph.v()];
        mst = new ArrayList<>();

        // lazy prim
        visit(0);
        while (!heap.isEmpty() && mst.size() < graph.v() - 1) {
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
        DenseWeightGraph<W>.Iterator iterator = graph.iterator(v);
        while (iterator.hasNext()) {
            Edge<W> edge = iterator.next();
            if (!marked[edge.other(v)]) {
                heap.insert(edge);
            }
        }
    }

    public List<Edge<W>> getMst() {
        return mst;
    }

    public static void main(String[] args) {
        DenseWeightGraph<Integer> graph = new DenseWeightGraph<>(7, false);
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

        LazyPrimMinSpanTree<Integer> lazyPrim = new LazyPrimMinSpanTree<>(graph);
        List<Edge<Integer>> mst = lazyPrim.getMst();
        for (Edge<Integer> edge : mst) {
            System.out.println("{" + edge.v() + ", " + edge.w() + ", " + edge.w() + "}");
        }
    }
}
