package com.haoyanbing.algorithm.sort;

/**
 * 排序算法-选择排序
 * @author haoyanbing
 * @since 2020/3/15
 */
public class SelectionSort {

    private static void sort(Integer[] arr) {
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
        Integer[] arr = {3, 5, 2, 7, 6, 1, 9, 0};
        sort(arr);
        for (Integer value : arr) {
            System.out.print(" " + value);
        }
    }
}
