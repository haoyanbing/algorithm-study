package com.haoyanbing.algorithm.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 排序算法测试辅助类
 *
 * @author haoyanbing
 * @since 2020/3/15
 */
public class SortTestHelper {

    /**
     * 生成数量为n的随机数组
     *
     * @param n   数量
     * @param max 最大值
     * @return 数据
     */
    public static int[] generateRandomArray(int n, int max) {
        int[] arr = new int[n];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(max);
        }
        return arr;
    }

    /**
     * 判断数组是否有序(升序)
     *
     * @param arr 数组
     * @return 是否有序
     */
    public static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Map<String, Sort> map = new HashMap<>(10);
        map.put("SelectionSort", new SelectionSort());
        map.put("InsertionSort", new InsertionSort());
        map.put("BubbleSort", new BubbleSort());
        map.put("ShellSort", new ShellSort());
        map.put("MergeSort", new MergeSort());
        map.put("QuickSort3", new QuickSort3());

        int[] arr = generateRandomArray(100000, 1000);

        for (Map.Entry<String, Sort> entry : map.entrySet()) {
            int[] arrCopy = Arrays.copyOf(arr, arr.length);
            long start = System.currentTimeMillis();
            entry.getValue().sort(arrCopy);
            long end = System.currentTimeMillis();
            System.out.println(entry.getKey() + ": " + (end - start) + "ms");
            System.out.println("isSorted: " + SortTestHelper.isSorted(arrCopy));
            System.out.println("------------------------");
        }

    }
}
