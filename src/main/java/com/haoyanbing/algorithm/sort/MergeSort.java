package com.haoyanbing.algorithm.sort;

import com.haoyanbing.algorithm.SortTestHelper;

/**
 * 排序算法-归并排序
 *
 * @author haoyanbing
 * @since 2020/3/17
 */
public class MergeSort implements Sort {

    @Override
    public void sort(int[] arr) {
        merge(arr, 0, arr.length - 1);
    }

    private void merge(int[] arr, int l, int r) {
        // 递归到底的情况
        if (l >= r)
            return;

        // 继续拆分为两个数组递归
        int m = (l + r) / 2;
        merge(arr, l, m);
        merge(arr, m + 1, r);

        // 优化点：如果已经有序则不需要往下进行
        if (arr[m] < arr[m + 1])
            return;

        // i为左边数据起始下标，j为右边数组起始下标
        int i = l;
        int j = m + 1;

        // 创建一个大小相同的数组存放排完序的值
        int[] aux = new int[r - l + 1];

        // 依次向aux数组中填充值
        for (int k = 0; k < aux.length; k++) {
            if (i > m) {
                // 情况1：左边数组已经比较完了，直接使用右边数组
                aux[k] = arr[j];
                j++;
            } else if (j > r) {
                // 情况2：右边数组已经比较完了，直接使用左边数组
                aux[k] = arr[i];
                i++;
            } else if (arr[i] < arr[j]) {
                // 情况3：左边数组当前值比右边小，使用左边的值
                aux[k] = arr[i];
                i++;
            } else {
                // 情况3：右边数组当前值比坐标小，使用右边的值
                aux[k] = arr[j];
                j++;
            }
        }

        // 将排序好的数组 aux 中的值填充回 arr 数组中对应的位置
        for (int n = l; n <= r; n++) {
            arr[n] = aux[n - l];
        }
    }

    public static void main(String[] args) {
        int[] arr = SortTestHelper.generateRandomArray(1000000, 1000);
        long start = System.currentTimeMillis();
        new MergeSort().sort(arr);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "ms");
        System.out.println("isSorted: " + SortTestHelper.isSorted(arr));
    }
}
