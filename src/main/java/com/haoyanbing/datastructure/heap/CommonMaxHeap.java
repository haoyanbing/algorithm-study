package com.haoyanbing.datastructure.heap;

/**
 * 二叉堆-最大堆 通用版本
 *
 * @author haoyanbing
 * @since 2020/3/29
 */
public class CommonMaxHeap<T extends Comparable<T>> {

    /**
     * 第一个元素不用，从下标为1开始使用
     */
    private Object[] data;

    private int count;

    public CommonMaxHeap(int capacity) {
        data = new Object[capacity + 1];
        count = 0;
    }

    public CommonMaxHeap(T[] arr) {
        data = new Object[arr.length + 1];
        count = arr.length;
        for (int i = 0; i < arr.length; i++) {
            data[i + 1] = arr[i];
        }
        // heapify 堆化
        for (int i = count / 2; i >= 1; i--) {
            shiftDown(i);
        }
    }

    public void insert(T item) {
        data[++count] = item;
        shiftUp(count);
    }

    public T extractMax() {
        if (count <= 0) {
            throw new IllegalArgumentException("堆为空");
        }
        Object ret = data[1];
        data[1] = data[count];
        count--;
        shiftDown(1);
        return (T) ret;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    private void shiftUp(int k) {
        // 循环与父节点值比较，大于父节点值的话就交换位置
        while (k > 1 && ((T) data[k / 2]).compareTo((T) data[k]) < 0) {
            swap(data, k / 2, k);
            k /= 2;
        }
    }

    private void shiftDown(int k) {
        while (2 * k <= count) {
            int j = 2 * k; // 在此次循环中，data[k]和data[j]交换位置，默认j为k的左子节点
            // 如果有右子节点且右子节点大于左子节点，则j++为右子节点
            if (j + 1 <= count && ((T) data[j + 1]).compareTo((T) data[j]) > 0) {
                j += 1;
            }
            // 判断子节点中的较大的那个是否还是小于k节点的数值，是的话终止循环
            if (((T) data[k]).compareTo((T) data[j]) >= 0) {
                break;
            }
            // 否则交换位置
            swap(data, k, j);
            // k = j，继续循环
            k = j;
        }
    }

    private void swap(Object[] data, int i, int j) {
        Object temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public static void main(String[] args) {
        CommonMaxHeap<Integer> heap = new CommonMaxHeap<>(100);
        for (int i = 0; i < 100; i++) {
            heap.insert(i);
        }
        for (int i = 0; i < 100; i++) {
            System.out.println(heap.extractMax());
        }
    }
}
