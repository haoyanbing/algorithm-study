package com.haoyanbing.datastructure.heap;

import java.util.Random;

/**
 * 二叉堆-最大索引堆
 *
 * @author haoyanbing
 * @since 2020/3/22
 */
public class IndexMaxHeap {
    /**
     * 第一个元素不用，从下标为1开始使用
     */
    private int[] data;

    private int[] indexes;

    private int count;

    public IndexMaxHeap(int capacity) {
        data = new int[capacity + 1];
        indexes = new int[capacity + 1];
        count = 0;
    }

    public void insert(int item) {
        data[++count] = item;
        indexes[count] = count;
        shiftUp(count);
    }

    public int extractMax() {
        if (count <= 0) {
            throw new IllegalArgumentException("堆为空");
        }
        int ret = data[indexes[1]];
        indexes[1] = indexes[count];
        count--;
        shiftDown(1);
        return ret;
    }

    private void shiftUp(int k) {
        while (k > 1 && data[indexes[k / 2]] < data[indexes[k]]) {
            swap(indexes, k / 2, k);
            k /= 2;
        }
    }

    private void shiftDown(int k) {
        while (2 * k <= count) {
            int j = 2 * k; // 在此次循环中，data[k]和data[j]交换位置，默认j为k的左子节点
            // 如果有右子节点且右子节点大于左子节点，则j++为右子节点
            if (j + 1 <= count && data[indexes[j + 1]] > data[indexes[j]]) {
                j += 1;
            }
            // 判断子节点中的较大的那个是否还是小于k节点的数值，是的话终止循环
            if (data[indexes[k]] >= data[indexes[j]]) {
                break;
            }
            // 否则交换位置
            swap(indexes, k, j);
            // k = j，继续循环
            k = j;
        }
    }

    private void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public static void main(String[] args) {
        IndexMaxHeap heap = new IndexMaxHeap(100);
        for (int i = 0; i < 100; i++) {
            heap.insert(new Random().nextInt(100));
        }
        for (int i = 0; i < 100; i++) {
            System.out.println(heap.extractMax());
        }
    }
}
