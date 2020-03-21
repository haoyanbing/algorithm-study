package com.haoyanbing.algorithm.sort;

import java.util.Random;

/**
 * 排序算法-快速排序(3路快速排序)
 *
 * @author haoyanbing
 * @since 2020/3/21
 */
public class QuickSort3 implements Sort {

    @Override
    public void sort(int[] arr) {
        innerSort(arr, 0, arr.length - 1);
    }

    private void innerSort(int[] arr, int l, int r) {
        if (l >= r) return;
        swap(arr, l, new Random().nextInt(r - l + 1) + l);
        int v = arr[l];
        int lt = l;
        int gt = r + 1;
        int i = l + 1;
        while (i < gt) {
            if (arr[i] == v) {
                i++;
            } else if (arr[i] < v) {
                swap(arr, i, lt + 1);
                lt++;
                i++;
            } else {
                swap(arr, i, gt - 1);
                gt--;
            }
        }
        swap(arr, l, lt);
        innerSort(arr, l, lt - 1);
        innerSort(arr, gt, r);
    }

    private void swap(int[] arr, int i, int j) {
        if (i == j) return;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = SortTestHelper.generateRandomArray(100000, 10000);
        long start = System.currentTimeMillis();
        new QuickSort3().sort(arr);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "ms");
        System.out.println("isSorted: " + SortTestHelper.isSorted(arr));
    }
}
