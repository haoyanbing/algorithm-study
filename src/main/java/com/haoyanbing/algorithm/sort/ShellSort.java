package com.haoyanbing.algorithm.sort;

/**
 * 排序算法-希尔排序
 * @author haoyanbing
 * @since 2020/3/17
 */
public class ShellSort implements Sort {

    @Override
    public void sort(int[] arr) {
        // 循环修改gap值
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 对 gap 个数组进行插入排序
            for (int i = 0; i < gap; i++) {
                // 插入排序
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

    public void sort2(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int temp = arr[i];
                int j = i - gap;
                while (j >= 0 && temp < arr[j]) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = temp;
            }
        }
    }

    public void sort3(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int temp = arr[i];
                int j = i;
                while (j - gap >= 0 && temp < arr[j - gap]) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = SortTestHelper.generateRandomArray(100000, 10000);
        long start = System.currentTimeMillis();
        new ShellSort().sort3(arr);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "ms");
        System.out.println("isSorted: " + SortTestHelper.isSorted(arr));
    }

}
