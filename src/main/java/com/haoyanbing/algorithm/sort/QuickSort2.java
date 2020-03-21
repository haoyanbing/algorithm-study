package com.haoyanbing.algorithm.sort;

import java.util.Random;

/**
 * 排序算法-快速排序(双路快速排序)
 *
 * @author haoyanbing
 * @since 2020/3/21
 */
public class QuickSort2 implements Sort {

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
        swap(arr, l, new Random().nextInt(r - l + 1) + l);
        int v = arr[l];
        int i = l + 1;
        int j = r;
        while (true) {
            while (i <= r && arr[i] < v) {
                i++;
            }
            while (j > l && arr[j] > v) {
                j--;
            }

            if (i > j) {
                break;
            }
            swap(arr, i++, j--);
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
        new QuickSort2().sort(arr);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "ms");
        System.out.println("isSorted: " + SortTestHelper.isSorted(arr));
    }

}
