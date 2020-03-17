package com.haoyanbing.algorithm.sort;

import com.haoyanbing.algorithm.SortTestHelper;

/**
 * 排序算法-选择排序
 *
 * @author haoyanbing
 * @since 2020/3/15
 */
public class SelectionSort {

    private static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = SortTestHelper.generateRandomArray(200000, 1000);
        long start = System.currentTimeMillis();
        sort(arr);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "ms");
//        for (Integer value : arr) {
//            System.out.print(" " + value);
//        }
    }
}