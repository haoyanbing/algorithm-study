package com.haoyanbing.algorithm;

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
}
