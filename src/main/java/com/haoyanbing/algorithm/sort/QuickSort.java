package com.haoyanbing.algorithm.sort;

import java.util.Random;

/**
 * 排序算法-快速排序(基本实现)
 *
 * @author haoyanbing
 * @since 2020/3/21
 */
public class QuickSort implements Sort {

    @Override
    public void sort(int[] arr) {
        innerSort(arr, 0, arr.length - 1);
    }

    private void innerSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int p = partition(arr, l, r);
        innerSort(arr, l, p - 1);
        innerSort(arr, p + 1, r);
    }

    private int partition(int[] arr, int l, int r) {
        // 优化点：对于基本有序的数组时间复杂度会降低到 O(n*n)，这里随机选择v的值
        swap(arr, l, new Random().nextInt(r - l + 1) + l);

        int v = arr[l];
        int j = l;
        int i = l + 1;
        while (i <= r) {
            if (arr[i] < v) {
                swap(arr, i, ++j);
            }
            i++;
        }
        swap(arr, l, j);
        return j;
    }

    private void swap(int[] arr, int i, int j) {
        if (i == j) return;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = SortTestHelper.generateRandomArray(100000, 100);
        long start = System.currentTimeMillis();
        new QuickSort().sort(arr);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "ms");
        System.out.println("isSorted: " + SortTestHelper.isSorted(arr));

        long start2 = System.currentTimeMillis();
        new QuickSort().sort(arr);
        long end2 = System.currentTimeMillis();
        System.out.println("耗时：" + (end2 - start2) + "ms");
        System.out.println("isSorted: " + SortTestHelper.isSorted(arr));
    }
}
