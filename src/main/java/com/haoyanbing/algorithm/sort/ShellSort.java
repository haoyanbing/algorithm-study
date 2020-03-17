package com.haoyanbing.algorithm.sort;

import com.haoyanbing.algorithm.SortTestHelper;

/**
 * 排序算法-希尔排序
 * @author haoyanbing
 * @since 2020/3/17
 */
public class ShellSort implements Sort{

    @Override
    public void sort(int[] arr) {
        int gap;
        for (gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = 0; i < gap; i++) {
                for (int j = i + gap; j < arr.length; j += gap) {
                    int temp = arr[j];
                    int k;
                    for (k = j; k - gap >= 0 && temp < arr[k - gap]; k -= gap) {
                        arr[k] = arr[k - gap];
                    }
                    arr[k] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = SortTestHelper.generateRandomArray(100000, 10000);
        long start = System.currentTimeMillis();
        new ShellSort().sort(arr);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "ms");
        System.out.println("isSorted: " + SortTestHelper.isSorted(arr));
    }

}
