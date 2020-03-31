package com.haoyanbing.algorithm.shortestpath;

import com.haoyanbing.datastructure.graph.DenseWeightGraph;
import com.haoyanbing.datastructure.graph.Edge;
import com.haoyanbing.datastructure.heap.CommonIndexMaxHeap;

/**
 * 最短路径 - Dijkstra算法
 * @author haoyanbing
 * @since 2020/3/31
 */
public class Dijkstra {
    // 有权图
    private DenseWeightGraph<Integer> graph;
    private int s;
    private int[] distTo;
    private boolean[] marked;
    private Edge<Integer>[] from;


    public Dijkstra(DenseWeightGraph<Integer> graph, int s) {
        this.graph = graph;
        this.s = s;
        this.distTo = new int[graph.v()];
        this.marked = new boolean[graph.v()];
        this.from = new Edge[graph.v()];

        // 索引堆
        CommonIndexMaxHeap<Integer> heap = new CommonIndexMaxHeap<>(graph.v());

        distTo[s] = 0;
        from[s] = new Edge<>(s, s, 0);
        heap.insert(s, distTo[s]);

        while (!heap.isEmpty()) {
            int v = heap.extractMaxIndex();

            marked[v] = true;

            DenseWeightGraph<Integer>.Iterator iterator = graph.iterator(v);
            while (iterator.hasNext()) {
                Edge<Integer> next = iterator.next();
                int w = next.other(v);
                if (!marked[w]) {
                    if (from[w] == null || distTo[v] + next.weight() < distTo[w]) {
                        distTo[w] = distTo[v] + next.weight();
                        from[w] = next;
                    }
                    if (heap.contains(next.weight())) {
                        heap.set(w, next.weight());
                    } else {
                        heap.insert(w, next.weight());
                    }
                }
            }
        }
    }


    public void showDistTo() {
        for (int i = 0; i < distTo.length; i++) {
            System.out.println(i + ": " + distTo[i]);
        }
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
        Dijkstra dijkstra = new Dijkstra(graph, 0);
        dijkstra.showDistTo();
    }

}
