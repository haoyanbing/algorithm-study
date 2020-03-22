package com.haoyanbing.algorithm.sort;

import com.haoyanbing.datastructure.heap.MaxHeap;

/**
 * 排序算法-堆排序
 * @author haoyanbing
 * @since 2020/3/22
 */
public class HeapSort implements Sort {

    @Override
    public void sort(int[] arr) {
        MaxHeap heap = new MaxHeap(arr);
        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = heap.extractMax();
        }
    }

    public static void main(String[] args) {
        int[] arr = SortTestHelper.generateRandomArray(100000, 10000);
        long start = System.currentTimeMillis();
        new HeapSort().sort(arr);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "ms");
        System.out.println("isSorted: " + SortTestHelper.isSorted(arr));
    }
}
