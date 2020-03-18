package com.haoyanbing.algorithm.sort;

/**
 * 排序算法-冒泡排序
 * @author haoyanbing
 * @since 2020/3/18
 */
public class BubbleSort implements Sort {

    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = SortTestHelper.generateRandomArray(100000, 10000);
        long start = System.currentTimeMillis();
        new BubbleSort().sort(arr);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "ms");
        System.out.println("isSorted: " + SortTestHelper.isSorted(arr));
    }
}
