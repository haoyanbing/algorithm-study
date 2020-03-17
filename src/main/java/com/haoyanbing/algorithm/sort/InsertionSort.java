package com.haoyanbing.algorithm.sort;

import com.haoyanbing.algorithm.SortTestHelper;

import java.util.Arrays;

/**
 * 排序算法-插入排序
 *
 * @author haoyanbing
 * @since 2020/3/17
 */
public class InsertionSort implements Sort {

    /**
     * 基础版本
     *
     * @param arr 数组
     */
    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                // 交换位置 arr[j] 和 arr[j-1] 的位置
                int temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
            }
        }
    }

    /**
     * 优化版本
     * 不必每次交换
     *
     * @param arr 数组
     */
    private void sort2(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            int j;
            for (j = i; j > 0 && temp < arr[j - 1]; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = SortTestHelper.generateRandomArray(100000, 1000);
        int[] arr2 = Arrays.copyOf(arr, arr.length);

        long start = System.currentTimeMillis();
        new InsertionSort().sort(arr);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "ms");
        System.out.println("isSorted: " + SortTestHelper.isSorted(arr));

        System.out.println("----------------------");

        long start2 = System.currentTimeMillis();
        new InsertionSort().sort2(arr2);
        long end2 = System.currentTimeMillis();
        System.out.println("耗时：" + (end2 - start2) + "ms");
        System.out.println("isSorted: " + SortTestHelper.isSorted(arr2));
    }

}
