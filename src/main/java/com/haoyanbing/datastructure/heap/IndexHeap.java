package com.haoyanbing.datastructure.heap;

import java.util.Random;

/**
 * 二叉堆-最大索引堆 通用版本
 *
 * @author haoyanbing
 * @since 2020/3/22
 */
public class IndexHeap<T extends Comparable<T>> {
    /**
     * 第一个元素不用，从下标为1开始使用
     */
    private Object[] data;

    /**
     * 索引数组，存储data中元素下标，真正交换位置的是这个数组，data中元素不会动
     */
    private int[] indexes;

    private int count;

    private int capacity;

    public IndexHeap(int capacity) {
        this.capacity = capacity;
        data = new Object[capacity + 1];
        indexes = new int[capacity + 1];
        count = 0;
    }

    public void insert(T item) {
        data[++count] = item;
        indexes[count] = count;
        shiftUp(count);
    }


    // 向最大索引堆中插入一个新的元素, 新元素的索引为i, 元素为item
    // 传入的i对用户而言,是从0索引的
    public void insert(int i, T item) {
        i += 1;
        data[i] = item;
        indexes[count + 1] = i;
        count++;

        shiftUp(count);
    }

    // 将最大索引堆中索引为i的元素修改为newItem
    public void set(int i, T item) {
        i += 1;
        data[i] = item;
        // 找到indexes[j] = i, j表示data[i]在堆中的位置
        // 之后shiftUp(j), 再shiftDown(j)
        for (int j = 1; j <= count; j++)
            if (indexes[j] == i) {
                shiftUp(j);
                shiftDown(j);
                return;
            }
    }

    // 获取最大索引堆中索引为i的元素
    public T getItem(int i) {
        return (T) data[i + 1];
    }

    public T extractMax() {
        if (count <= 0) {
            throw new IllegalArgumentException("堆为空");
        }
        Object ret = data[indexes[1]];
        indexes[1] = indexes[count];
        count--;
        shiftDown(1);
        return (T) ret;
    }

    private void shiftUp(int k) {
        while (k > 1 && ((T) data[indexes[k / 2]]).compareTo((T) data[indexes[k]]) < 0) {
            swap(indexes, k / 2, k);
            k /= 2;
        }
    }

    private void shiftDown(int k) {
        while (2 * k <= count) {
            int j = 2 * k; // 在此次循环中，data[k]和data[j]交换位置，默认j为k的左子节点
            // 如果有右子节点且右子节点大于左子节点，则j++为右子节点
            if (j + 1 <= count && ((T) data[indexes[j + 1]]).compareTo((T) data[indexes[j]]) > 0) {
                j += 1;
            }
            // 判断子节点中的较大的那个是否还是小于k节点的数值，是的话终止循环
            if (((T) data[indexes[k]]).compareTo((T) data[indexes[j]]) >= 0) {
                break;
            }
            // 否则交换位置
            swap(indexes, k, j);
            // k = j，继续循环
            k = j;
        }
    }

    public boolean isEmpty() {
        return count == 0;
    }

    private void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public static void main(String[] args) {
        IndexHeap heap = new IndexHeap(100);
        for (int i = 0; i < 100; i++) {
            heap.insert(new Random().nextInt(100));
        }
        for (int i = 0; i < 100; i++) {
            System.out.println(heap.extractMax());
        }
    }
}
