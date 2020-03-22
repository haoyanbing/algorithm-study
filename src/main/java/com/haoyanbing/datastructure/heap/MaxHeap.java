package com.haoyanbing.datastructure.heap;

/**
 * 二叉堆-最大堆
 *
 * @author haoyanbing
 * @since 2020/3/22
 */
public class MaxHeap {

    /**
     * 第一个元素不用，从下标为1开始使用
     */
    private int[] data;

    private int count;

    public MaxHeap(int capacity) {
        data = new int[capacity + 1];
        count = 0;
    }

    public void insert(int item) {
        data[++count] = item;
        shiftUp(count - 1);
    }

    public int extractMax() {
        if (count <= 0) {
            throw new IllegalArgumentException("堆为空");
        }
        int ret = data[1];
        data[1] = data[count];
        count--;
        shiftDown(1);
        return ret;
    }

    private void shiftUp(int k) {
        while (k > 1 && data[k / 2] < data[k]) {
            swap(data, k / 2, k);
            k /= 2;
        }
    }

    private void shiftDown(int k) {
        while (2 * k <= count) {
            int j = 2 * k; // 在此次循环中，data[k]和data[j]交换位置，默认j为k的左子节点
            // 如果有右子节点且右子节点大于左子节点，则j++为右子节点
            if (j + 1 <= count && data[j + 1] > data[j]) {
                j++;
            }
            // 判断子节点中的较大的那个是否还是小于k节点的数值，是的话终止循环
            if (data[k] >= data[j]) {
                break;
            }
            // 否则交换位置
            swap(data, k, j);
            // k = j，继续循环
            k = j;
        }
    }

    private void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[i] = temp;
    }

    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap(100);
        for (int i = 0; i < 100; i++) {
            heap.insert(i);
        }

        for (int i = 0; i < 100; i++) {
            System.out.println(heap.extractMax());
        }
    }
}
